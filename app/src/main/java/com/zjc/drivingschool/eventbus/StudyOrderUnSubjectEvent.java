package com.zjc.drivingschool.eventbus;


/**
 * Created by Administrator on 2015/7/14.
 */
public class StudyOrderUnSubjectEvent {
    private String orid;

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public StudyOrderUnSubjectEvent(String orid) {
        this.orid = orid;
    }
}
