package com.fangdd.traffic.common.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author xuwenzhen
 */
@SpringBootApplication(scanBasePackages = "com.fangdd", exclude = {MongoAutoConfiguration.class})
//@EnableGraphQL
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
