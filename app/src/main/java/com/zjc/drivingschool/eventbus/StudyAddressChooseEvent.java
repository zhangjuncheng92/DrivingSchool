package com.zjc.drivingschool.eventbus;


import com.baidu.mapapi.search.core.PoiInfo;
import com.zjc.drivingschool.db.model.School;

/**
 * Created by Administrator on 2015/7/14.
 */
public class StudyAddressChooseEvent {
    private PoiInfo poiInfo;

    public PoiInfo getPoiInfo() {
        return poiInfo;
    }

    public void setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    public StudyAddressChooseEvent(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }
}
