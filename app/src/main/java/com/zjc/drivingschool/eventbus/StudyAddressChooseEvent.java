package com.zjc.drivingschool.eventbus;


import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.zjc.drivingschool.db.model.School;

/**
 * Created by Administrator on 2015/7/14.
 */
public class StudyAddressChooseEvent {
    private CloudPoiInfo poiInfo;

    public CloudPoiInfo getPoiInfo() {
        return poiInfo;
    }

    public void setPoiInfo(CloudPoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    public StudyAddressChooseEvent(CloudPoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }
}
