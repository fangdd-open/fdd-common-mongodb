package com.fangdd.traffic.common.mongo.reflection.dto;

/**
 * Created by ycoe on 17/5/4.
 */
public class ReadStatusDto {
    /**
     * 当前类名
     */
    private FieldMate fieldMate;

    /**
     * 主类
     */
    private ClassMate classMate;

    /**
     * 当前读取的文档ID
     */
    private Object id;

    public void setFieldMate(FieldMate fieldMate) {
        this.fieldMate = fieldMate;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public void setClassMate(ClassMate classMate) {
        this.classMate = classMate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("当前类:");
        sb.append(classMate.getClazz().getName());
        sb.append(",");

        sb.append("当前文档_id=");
        sb.append(id);

        sb.append(",字段:");
        sb.append(fieldMate.getClassMate().getClazz().getName());
        sb.append(".");
        sb.append(fieldMate.getName());
        sb.append("[");
        sb.append(fieldMate.getField().getType().getName());
        sb.append("]");

        return sb.toString();
    }
}
