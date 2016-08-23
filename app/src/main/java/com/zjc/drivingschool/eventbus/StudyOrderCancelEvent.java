package com.zjc.drivingschool.eventbus;


import com.baidu.mapapi.search.core.PoiInfo;

/**
 * Created by Administrator on 2015/7/14.
 */
public class StudyOrderCancelEvent {
    private String orid;

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public StudyOrderCancelEvent(String orid) {
        this.orid = orid;
    }
}
