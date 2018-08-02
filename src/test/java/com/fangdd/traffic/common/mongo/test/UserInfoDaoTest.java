package com.fangdd.traffic.common.mongo.test;

import com.fangdd.traffic.common.mongo.dao.UserInfoDao;
import com.fangdd.traffic.common.mongo.pojo.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ycoe on 17/7/21.
 */
public class UserInfoDaoTest extends BaseJunitTest {
    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void get(){
        UserInfo userInfo = userInfoDao.getEntityById("5971c8cba8264c59f196f94b");
        System.out.println(userInfo.getId() + "=>" + userInfo.getNickName());
    }
}
