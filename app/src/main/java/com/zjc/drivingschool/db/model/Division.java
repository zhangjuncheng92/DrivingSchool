package com.zjc.drivingschool.db.model;


import java.io.Serializable;

public class Division implements Serializable {

    //区
    private int id;

    //名称
    private String name;

    private int num;

    //纬度
    private Double lat;

    //经度
    private Double lon;

    private LatLngLocal latLngLocal;

    public Division() {
    }

    public Division(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Division(int id, LatLngLocal latLngLocal, String name) {
        this.id = id;
        this.latLngLocal = latLngLocal;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public LatLngLocal getLatLngLocal() {
        return new LatLngLocal(lat, lon);
    }

    public void setLatLngLocal(LatLngLocal latLngLocal) {
        this.lat = latLngLocal.getLatitude();
        this.lon = latLngLocal.getLongitude();
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lon;
    }

    public void setLng(Double lng) {
        this.lon = lng;
    }
}
