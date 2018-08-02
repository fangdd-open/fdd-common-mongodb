package com.fangdd.traffic.common.mongo.dao;

import com.fangdd.traffic.common.mongo.core.BaseEntityDao;
import com.fangdd.traffic.common.mongo.core.YMongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by ycoe on 17/1/23.
 */
public abstract class BaseTestEntityDao<T> extends BaseEntityDao<T> {
    @Autowired
    private YMongoClient yMongoClient;

    @Value("${mongodb.database.waterfall}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected YMongoClient getYMongoClient(){
        return yMongoClient;
    }
}
