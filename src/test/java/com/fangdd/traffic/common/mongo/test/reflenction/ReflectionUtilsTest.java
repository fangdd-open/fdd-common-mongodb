package com.fangdd.traffic.common.mongo.test.reflenction;

import com.fangdd.traffic.common.mongo.pojo.house.House;
import com.fangdd.traffic.common.mongo.reflection.ReflectionUtils;
import com.fangdd.traffic.common.mongo.reflection.dto.ClassMate;
import org.junit.Test;

/**
 * Created by ycoe on 17/1/6.
 */
public class ReflectionUtilsTest {
    @Test
    public void analyseTest(){
        Class clazz = House.class;

        ReflectionUtils.getClassMate(clazz);

        ClassMate classMate = ReflectionUtils.getClassMate(clazz);
        classMate.getFieldMateMap().forEach((key, value)->{
            System.out.println(key);
        });
        System.out.println(classMate);
    }
}
