package com.fangdd.traffic.common.mongo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ycoe on 17/7/21.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    /**
     * 数据库字段
     *
     * @return
     */
    String value() default "";
}
