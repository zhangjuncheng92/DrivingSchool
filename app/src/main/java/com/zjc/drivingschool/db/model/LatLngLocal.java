package com.zjc.drivingschool.db.model;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

/**
 * Created by asus1 on 2015/10/25.
 */
public class LatLngLocal implements Serializable {
    private Double latitude;
    private Double longitude;

    public LatLngLocal(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LatLng getBaiduLatLngByLocal() {
        return new LatLng(latitude, longitude);
    }
}
