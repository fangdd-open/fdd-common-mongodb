package com.fangdd.traffic.common.mongo.dao;

import com.fangdd.traffic.common.mongo.pojo.house.House;
import org.springframework.stereotype.Service;

/**
 * Created by ycoe on 16/7/5.
 */
@Service
public class HouseDao extends BaseTestEntityDao<House> {
    @Override
    protected String getCollectionName() {
        return "house";
    }
}
