package com.zjc.drivingschool.eventbus;


/**
 * Created by Administrator on 2015/7/14.
 */
public class StudyOrderAssessEvent {
    private String orid;

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public StudyOrderAssessEvent(String orid) {
        this.orid = orid;
    }
}
