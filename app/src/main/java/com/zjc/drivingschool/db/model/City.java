package com.zjc.drivingschool.db.model;

import java.io.Serializable;

public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String city;
    private LatLngLocal latLngLocal;
    private int isLocation;

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public LatLngLocal getLatLngLocal() {
        return latLngLocal;
    }

    public void setLatLngLocal(LatLngLocal latLngLocal) {
        this.latLngLocal = latLngLocal;
    }

    public int getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(int isLocation) {
        this.isLocation = isLocation;
    }

    public City() {
    }

    public City(String id, String city) {
        this.id = id;
        this.city = city;
    }

    public City(String id, String city, int isLocation) {
        this.id = id;
        this.city = city;
        this.isLocation = isLocation;
    }

    public City(String id, String city, LatLngLocal latLngLocal) {
        this.id = id;
        this.city = city;
        this.latLngLocal = latLngLocal;
    }
}
