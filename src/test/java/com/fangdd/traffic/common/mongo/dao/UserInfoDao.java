package com.fangdd.traffic.common.mongo.dao;

import com.fangdd.traffic.common.mongo.pojo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Created by ycoe on 17/7/21.
 */
@Service
public class UserInfoDao extends BaseTestEntityDao<UserInfo> {
    @Override
    protected String getCollectionName() {
        return "user_info";
    }

    @Override
    protected Class<UserInfo> getDocumentClass() {
        return UserInfo.class;
    }
}
