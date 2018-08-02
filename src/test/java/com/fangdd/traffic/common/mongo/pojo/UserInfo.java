package com.fangdd.traffic.common.mongo.pojo;

import com.fangdd.traffic.common.mongo.annotation.Field;

/**
 * Created by ycoe on 17/7/21.
 */
public class UserInfo {
    private String id;

    @Field("nick_name")
    private String nickName;

    @Field("cover_img")
    private String coverImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
