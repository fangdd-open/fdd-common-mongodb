package com.fangdd.traffic.common.mongo.pojo.house;

import com.google.common.base.Strings;

import java.io.Serializable;

/**
 * Created by ycoe on 16/7/5.
 */
public class SurroundingFacility implements Serializable {
    private static final long serialVersionUID = -8968798938528088668L;

    /**
     * 公交
     */
    private String buses;

    /**
     * 生活配套
     */
    private String lifeSupport;

    /**
     * 学区配套
     */
    private String schoolSupport;

    /**
     * 交通出行
     */
    private String trafficSupport;

    /**
     * 医疗配套
     */
    private String medicalSupport;

    public String getBuses() {
        return buses;
    }

    public void setBuses(String buses) {
        this.buses = buses;
    }

    public String getLifeSupport() {
        return lifeSupport;
    }

    public void setLifeSupport(String lifeSupport) {
        this.lifeSupport = lifeSupport;
    }

    public String getSchoolSupport() {
        return schoolSupport;
    }

    public void setSchoolSupport(String schoolSupport) {
        this.schoolSupport = schoolSupport;
    }

    public String getTrafficSupport() {
        return trafficSupport;
    }

    public void setTrafficSupport(String trafficSupport) {
        this.trafficSupport = trafficSupport;
    }

    public String getMedicalSupport() {
        return medicalSupport;
    }

    public void setMedicalSupport(String medicalSupport) {
        this.medicalSupport = medicalSupport;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(buses) && // NOSONAR
                Strings.isNullOrEmpty(lifeSupport) &&
                Strings.isNullOrEmpty(medicalSupport) &&
                Strings.isNullOrEmpty(schoolSupport) &&
                Strings.isNullOrEmpty(trafficSupport);
    }
}
