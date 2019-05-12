package com.fangdd.traffic.common.mongo.configure;

import com.fangdd.traffic.common.mongo.core.YMongoClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuwenzhen on 2019/5/11.
 */
@Configuration
@ConditionalOnClass(YMongoClient.class)
public class MongoDbConfigure {
    @Bean("yMongoClient")
    @ConfigurationProperties("mongodb.db")
    public YMongoClient getMongoCollect() {
        return new YMongoClient();
    }
}
