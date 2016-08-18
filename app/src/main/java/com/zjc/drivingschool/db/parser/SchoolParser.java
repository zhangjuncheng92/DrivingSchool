package com.zjc.drivingschool.db.parser;

import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.zjc.drivingschool.db.model.School;

import java.util.ArrayList;
import java.util.List;

public class SchoolParser {


    /**
     *解析百度地图的CloudPoiInfo类为School
     * @param poiInfoList
     * @return
     */
    public List<School> parserArray(List<CloudPoiInfo> poiInfoList) {
        List<School> schools = new ArrayList<>();
        for (int i = 0; i < poiInfoList.size(); i++) {
            School school = new School();
            CloudPoiInfo cloudPoiInfo = poiInfoList.get(i);
            school.parserIdByExtras(cloudPoiInfo.extras);
            school.setTitle(cloudPoiInfo.title);
            school.setLatitude(cloudPoiInfo.latitude);
            school.setLongitude(cloudPoiInfo.longitude);
            schools.add(school);
        }
        return schools;
    }

}
