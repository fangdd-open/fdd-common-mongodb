package com.fangdd.traffic.common.mongo.core;

import com.fangdd.traffic.common.mongo.MongoConverter;
import com.fangdd.traffic.common.mongo.Pagination;
import com.fangdd.traffic.common.mongo.constant.MongoUpdateOperatorsConsts;
import com.fangdd.traffic.common.mongo.reflection.ReflectionUtils;
import com.fangdd.traffic.common.mongo.reflection.dto.AutoIncrementInfo;
import com.fangdd.traffic.common.mongo.reflection.dto.ClassMate;
import com.fangdd.traffic.common.mongo.reflection.dto.FieldMate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by ycoe on 16/5/2.
 */
public abstract class BaseEntityDao<T> extends YCollection<T> {
    public static UpdateOptions UPSERT_UPDATE_OPTIONS = new UpdateOptions();

    private YMongoClient mongoClient;

    private Class<T> classType;

    static {
        UPSERT_UPDATE_OPTIONS.upsert(true);
    }

    public static final String ID = "_id";

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 在spring中注册的YMongoClient类名称
     *
     * @return
     */
    protected abstract String getMongoClientName();

    @Override
    protected YMongoClient getYMongoClient() {
        if (mongoClient != null) {
            return mongoClient;
        }
        if (applicationContext != null) {
            mongoClient = applicationContext.getBean(getMongoClientName(), YMongoClient.class);
        }
        return mongoClient;
    }

    /**
     * 通过ID进行修改
     *
     * @param id
     * @param update
     * @return
     */
    public <IDType> UpdateResult updateById(IDType id, Bson update) {
        return updateOne(Filters.eq("_id", id), update, new UpdateOptions());
    }

    /**
     * 通过查询条件更新一条记录，更新仅对设置有值的字段进行修改，不$unset为空的字段
     * 如果需要$unset为空字段，请使用update(T entity)方法
     *
     * @param query
     * @param entity
     */
    public UpdateResult updateOne(Bson query, T entity) {
        return updateOne(query, entity, new UpdateOptions());
    }

    /**
     * 通过查询条件更新一条记录，更新仅对设置有值的字段进行修改，不$unset为空的字段
     * 如果需要$unset为空字段，请使用update(T entity)方法
     *
     * @param query
     * @param entity
     * @param options 更新操作配置
     */
    private UpdateResult updateOne(Bson query, T entity, UpdateOptions options) {
        Document doc = getDocument(entity, MongoConverter.OPTION_UPDATE);
        return super.updateOne(query, new Document("$set", doc), options);
    }

    /**
     * 通过实体ID去更新
     * 为空的字符不做$unset操作
     *
     * @param entity
     * @return
     */
    public UpdateResult updateEntitySafe(T entity) {
        ClassMate classMate = ReflectionUtils.getClassMate(entity.getClass());
        FieldMate idFieldMate = classMate.getFieldMate("id");
        Object id = ReflectionUtils.getField(idFieldMate, entity);
        if (id == null) {
            throw new MongoException("id不能为空！");
        }
        return updateOne(Filters.eq("_id", id), entity);
    }

    /**
     * 本方法仅会更新实体内定义且未被标识为@Ignore(update=true)的字段
     * 如果属性值为空，则会被删除
     * 嵌套document/list会被直接覆盖
     * 如果不需要清空，updateEntitySafe()方法！
     *
     * @param entity
     */
    public UpdateResult updateEntity(T entity) {
        Class clazz = entity.getClass();
        ClassMate classMate = ReflectionUtils.getClassMate(clazz);
        FieldMate fieldMate = classMate.getFieldMate("id");
        Object id = ReflectionUtils.getField(fieldMate, entity);
        if (id == null) {
            throw new MongoException("id不能为空！");
        }
        Document updateData = MongoConverter.getUpdateDocument(entity);
        return updateOne(Filters.eq(ID, id), updateData);
    }

    /**
     * 本方法仅会更新实体内定义且未被标识为@Ignore(update=true)的字段
     * 如果属性值为空，则会被删除
     * 嵌套document/list会被直接覆盖
     * 如果不需要清空，upsertEntitySafe()方法！
     * 如果id对应的记录不存在，则会进行insert
     *
     * @param entity
     */
    public UpdateResult upsertEntity(T entity) {
        Class clazz = entity.getClass();
        ClassMate classMate = ReflectionUtils.getClassMate(clazz);
        FieldMate fieldMate = classMate.getFieldMate("id");
        Object id = ReflectionUtils.getField(fieldMate, entity);
        if (id == null) {
            throw new MongoException("id不能为空！");
        }
        Document updateData = MongoConverter.getUpdateDocument(entity);
        return updateOne(Filters.eq(ID, id), updateData, UPSERT_UPDATE_OPTIONS);
    }

    /**
     * 通过实体ID去更新
     * 为空的字符不做$unset操作
     * 如果id对应的记录不存在，则进行insert操作
     *
     * @param entity
     * @return
     */
    public UpdateResult upsertEntitySafe(T entity) {
        ClassMate classMate = ReflectionUtils.getClassMate(entity.getClass());
        FieldMate idFieldMate = classMate.getFieldMate("id");
        Object id = ReflectionUtils.getField(idFieldMate, entity);
        if (id == null) {
            throw new MongoException("id不能为空！");
        }
        return updateOne(Filters.eq("_id", id), entity, UPSERT_UPDATE_OPTIONS);
    }

    public Document getDocument(T entity, int option) {
        Document doc = MongoConverter.toDocument(entity, option);

        if ((option & MongoConverter.OPTION_INSERT) == MongoConverter.OPTION_INSERT) {
            //如果是insert
            Class clazz = entity.getClass();
            ClassMate classMate = ReflectionUtils.getClassMate(clazz);
            AutoIncrementInfo autoIncrementInfo = classMate.getAutoIncrementInfo();
            if (autoIncrementInfo != null && autoIncrementInfo.isAutoIncrement()) {
                Object entityId = doc.get(ID);
                if (entityId == null || "0".equals(entityId.toString())) {
                    //参见文档:https://docs.mongodb.org/manual/tutorial/create-an-auto-incrementing-field/
                    Object id = getNextSequence(autoIncrementInfo);
                    doc.put(ID, id);
                    FieldMate fieldMate = classMate.getFieldMate("id");
                    ReflectionUtils.setField(fieldMate, entity, id); //写入entity
                }
            }
        } else if ((option & MongoConverter.OPTION_UPDATE) == MongoConverter.OPTION_UPDATE) {
            //如果是update
            doc.remove(ID);
        }
        return doc;
    }

    public UpdateOneModel<T> getUpdateOneMode(Bson filter, T entity) {
        return getUpdateOneMode(filter, entity, new UpdateOptions());
    }


    public UpdateOneModel<T> getUpdateOneMode(Bson filter, T entity, UpdateOptions option) {
        Document updateData = MongoConverter.getUpdateDocument(entity);
        return new UpdateOneModel<T>(filter, updateData, option);
    }

    public UpdateOneModel<T> getUpdateOneSafeMode(Bson filter, T entity) {
        return getUpdateOneSafeMode(filter, entity, new UpdateOptions());
    }

    public UpdateOneModel<T> getUpdateOneSafeMode(Bson filter, T entity, UpdateOptions option) {
        Document doc = getDocument(entity, MongoConverter.OPTION_UPDATE);
        Document set = new Document(MongoUpdateOperatorsConsts.SET, doc);
        return new UpdateOneModel<T>(filter, set, option);
    }

    public <IDType> T getEntityById(IDType id) {
        return getEntityById(id, null);
    }

    public <IDType> T getEntityById(IDType id, Bson projection) {
        return getEntity(Filters.eq(ID, id), projection);
    }

    /**
     * 通过IDs获取记录，返回的记录按ids的顺序返回
     *
     * @param ids
     * @param <IDType>
     * @return
     */
    public <IDType> List<T> findEntityByIds(List<IDType> ids) {
        return findEntityByIds(ids, null);
    }

    /**
     * 通过IDs获取记录，返回的记录按ids的顺序返回
     *
     * @param ids        ids
     * @param projection 需要筛选的字段
     * @param <IDType>   id类型
     * @return
     */
    public <IDType> List<T> findEntityByIds(List<IDType> ids, Bson projection) {
        List<T> entityList = findEntities(Filters.in(ID, ids), null, null, null, projection);
        if (entityList != null && !entityList.isEmpty()) {
            ClassMate classMate = ReflectionUtils.getClassMate(getDocumentClass());
            Field filed = classMate.getFieldMate("id").getField();
            Map entityIdMap = Maps.newHashMap();
            entityList
                    .forEach(entity -> {
                        try {
                            entityIdMap.put(filed.get(entity), entity);
                        } catch (IllegalAccessException e) {
                        }
                    });

            List<T> list = Lists.newArrayList();
            ids.forEach(id -> {
                T entity = (T) entityIdMap.get(id);
                if (entity != null) {
                    list.add(entity);
                }
            });

            return list;
        }
        return entityList;
    }

    public List<T> findEntities(Bson query, Bson sort, Integer skip, Integer limit) {
        return findEntities(query, sort, skip, limit, null);
    }

    public List<T> findEntities(Bson query, Bson sort, Integer skip, Integer limit, Bson projection) {
        if (query == null) {
            query = new Document();
        }
        FindIterable<T> iterable = find(query);
        if (sort != null) {
            iterable = iterable.sort(sort);
        }
        if (skip != null) {
            iterable = iterable.skip(skip);
        }
        if (limit != null) {
            iterable = iterable.limit(limit).batchSize(limit);
        } else {
            iterable = iterable.batchSize(100);
        }
        if (projection != null) {
            iterable = iterable.projection(projection);
        }
        iterable = iterable.batchSize(100);
        return Lists.newArrayList(iterable);
    }

    public T getEntity(Bson query, Bson projection) {
        return find(query)
                .projection(projection)
                .first();
    }

    public T getEntity(Bson query) {
        return getEntity(query, null);
    }

    public Pagination<T> findEntitiesWithTotal(Bson query, Bson sort, Integer skip, Integer limit) {
        return findEntitiesWithTotal(query, sort, skip, limit, null);
    }

    public Pagination<T> findEntitiesWithTotal(Bson query, Bson sort, Integer skip, Integer limit, Bson projections) {
        Pagination<T> pagination = new Pagination();
        List<T> entities = findEntities(query, sort, skip, limit, projections);
        pagination.setList(entities);
        int size = entities.size();
        long total = 0;
        if (size == 0) {
            if (skip > 0) {
                //不好说，还是查询一下总数
                total = count(query);
            }
        } else {
            if (size < limit) {
                total = size + skip;
            } else {
                total = count(query);
            }
        }

        pagination.setTotal(total);
        return pagination;
    }

    public long getNextSequence() {
        Class clazz = getDocumentClass();
        ClassMate classMate = ReflectionUtils.getClassMate(clazz);
        AutoIncrementInfo autoIncrementInfo = classMate.getAutoIncrementInfo();
        return (long) getNextSequence(autoIncrementInfo);
    }

    @Override
    protected Class<T> getDocumentClass() {
        if (classType != null) {
            return classType;
        }
        Type t = getClass().getGenericSuperclass();

        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            classType = (Class<T>) p[0];
            return classType;
        }

        return null;
    }

    @Override
    protected String getDatabaseName() {
        return getYMongoClient().getDatabaseName();
    }
}
