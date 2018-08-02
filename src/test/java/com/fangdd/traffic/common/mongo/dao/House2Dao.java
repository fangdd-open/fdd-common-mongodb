package com.fangdd.traffic.common.mongo.dao;

import com.fangdd.traffic.common.mongo.pojo.house.House2;
import org.springframework.stereotype.Service;

/**
 * Created by ycoe on 16/7/5.
 */
@Service
public class House2Dao extends BaseTestEntityDao<House2> {
    @Override
    protected String getCollectionName() {
        return "house";
    }
}
