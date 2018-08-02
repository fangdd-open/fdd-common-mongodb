package com.fangdd.traffic.common.mongo.utils;

import org.bson.types.ObjectId;

/**
 * UUIDUtils
 *
 * @date 2016/5/3
 */
public class UUIDUtils {
    private UUIDUtils() {
    }

    public static String generateUUID() {
        return ObjectId.get().toString();
    }
}
