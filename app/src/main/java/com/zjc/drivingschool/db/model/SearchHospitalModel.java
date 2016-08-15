package com.zjc.drivingschool.db.model;

import java.io.Serializable;

public class SearchHospitalModel implements Serializable {

    //主键
    private Integer id;

    //科室id
    private LatLngLocal latLngLocal;

    //缩放等级
    private float zoom;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LatLngLocal getLatLngLocal() {
        return latLngLocal;
    }

    public void setLatLngLocal(LatLngLocal latLngLocal) {
        this.latLngLocal = latLngLocal;
    }


    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
