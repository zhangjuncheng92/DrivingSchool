package com.zjc.drivingschool.eventbus;


import com.zjc.drivingschool.db.model.Division;

/**
 * Created by Administrator on 2015/7/14.
 */
public class MarkerOnClickEvent {
    private Division division;

    public MarkerOnClickEvent(Division division) {
        this.division = division;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
