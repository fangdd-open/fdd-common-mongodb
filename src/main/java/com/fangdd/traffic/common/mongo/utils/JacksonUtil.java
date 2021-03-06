package com.fangdd.traffic.common.mongo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuwenzhen
 * @date 2019/4/2
 */
public class JacksonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper objectMapper;

    private JacksonUtil() {
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            logger.error("readValue error", e);
        }

        return null;
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            logger.error("readValue error", e);
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        if (object instanceof Bson) {
            BsonDocument asBsonDocument = ((Bson) object).toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry());
            return asBsonDocument.toJson();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("toJSon error", e);
        }

        return null;
    }
}
