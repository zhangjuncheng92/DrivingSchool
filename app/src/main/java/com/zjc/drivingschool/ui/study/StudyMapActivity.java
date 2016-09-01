package com.zjc.drivingschool.ui.study;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.response.TeacherLocal;
import com.zjc.drivingschool.utils.Constants;


/**
 * @author Z
 * @Filename LoginActivity.java
 * @Date 2015.09.14
 * @description 登录activity
 */
public class StudyMapActivity extends ZBaseActivity {
    private TeacherLocal teacherLocal;
    private MapView mMapView;
    private BaiduMap mBaiDuMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_map_act);
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.ARGUMENT)) {
            teacherLocal = (TeacherLocal) getIntent().getExtras().getSerializable(Constants.ARGUMENT);
            initMap();
        }
    }


    private void initMap() {
        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.id_mapView);
        // 获得地图的实例
        mBaiDuMap = mMapView.getMap();

        upDateMapByLatOfTarget(new LatLng(teacherLocal.getLatitude(), teacherLocal.getLongitude()));
    }

    /**
     * 根据坐标将地图移动到指定位置
     * 设置搜索条件里面的坐标
     * 设置地图上面的target图标
     *
     * @param location
     */
    private void upDateMapByLatOfTarget(LatLng location) {
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(location);
        if (mBaiDuMap != null) {
            mBaiDuMap.animateMapStatus(u);
            addTargetMarker(location);
        }
    }

    public void addTargetMarker(LatLng lng) {
        //中心点的marker
        BitmapDescriptor markerPoint = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_point);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(lng).icon(markerPoint);
        // 在地图上添加Marker，并显示
        mBaiDuMap.addOverlay(option);
    }
}
