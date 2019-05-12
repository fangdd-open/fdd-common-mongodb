package com.fangdd.traffic.common.mongo.dao;

import com.fangdd.traffic.common.mongo.core.BaseEntityDao;

/**
 * Created by ycoe on 17/1/23.
 */
public abstract class BaseTestEntityDao<T> extends BaseEntityDao<T> {
    @Override
    protected String getMongoClientName() {
        return "yMongoClient";
    }
}
