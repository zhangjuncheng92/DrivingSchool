package com.zjc.drivingschool.db.model;


import java.io.Serializable;
import java.util.Map;

public class School implements Serializable {
    private String title;
    private double latitude;
    private double longitude;
    private String pushid;// 推送ID
    private String schoolid;// 驾校ID

    private LatLngLocal latLngLocal;

    public LatLngLocal getLatLngLocal() {
        return new LatLngLocal(latitude, longitude);
    }

    public void setLatLngLocal(LatLngLocal latLngLocal) {
        this.latitude = latLngLocal.getLatitude();
        this.longitude = latLngLocal.getLongitude();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void parserIdByExtras(Map<String, Object> extras) {
        this.pushid = (String) extras.get("pushid");
        this.schoolid = (String) extras.get("schoolid");
    }

    public String getPushid() {
        return pushid;
    }

    public String getSchoolid() {
        return schoolid;
    }
}
