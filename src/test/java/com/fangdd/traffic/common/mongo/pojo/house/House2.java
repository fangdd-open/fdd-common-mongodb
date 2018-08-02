package com.fangdd.traffic.common.mongo.pojo.house;

import java.util.Date;

/**
 * Created by ycoe on 17/1/9.
 */
public class House2 extends House {
    private House2 parent;

    private Date date;

    public House2 getParent() {
        return parent;
    }

    public void setParent(House2 parent) {
        this.parent = parent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
