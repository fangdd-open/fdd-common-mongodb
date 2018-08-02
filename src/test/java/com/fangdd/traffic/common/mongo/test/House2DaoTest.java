package com.fangdd.traffic.common.mongo.test;

import com.alibaba.fastjson.JSONObject;
import com.fangdd.traffic.common.mongo.dao.House2Dao;
import com.fangdd.traffic.common.mongo.dao.HouseDao;
import com.fangdd.traffic.common.mongo.pojo.house.House;
import com.fangdd.traffic.common.mongo.pojo.house.House2;
import com.fangdd.traffic.common.mongo.reflection.ReflectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ycoe on 17/2/6.
 */
public class House2DaoTest extends BaseJunitTest {
    @Autowired
    private House2Dao house2Dao;

    @Autowired
    private HouseDao houseDao;

    @Test
    public void get(){
        House h1 = houseDao.getEntityById(10097L);
        System.out.println(JSONObject.toJSONString(h1));

        House2 h2 = house2Dao.getEntityById(10097L);
        System.out.println(JSONObject.toJSONString(h2));
    }

    @Test
    public void getNextSequence() {
//        ReflectionUtils.getClassMate(House.class);
        long houseId = houseDao.getNextSequence();
        System.out.println(houseId);

        houseId = house2Dao.getNextSequence();
        System.out.println(houseId);
    }
}
