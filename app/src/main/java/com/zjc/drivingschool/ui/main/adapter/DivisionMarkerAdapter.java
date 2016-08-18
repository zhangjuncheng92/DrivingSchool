package com.zjc.drivingschool.ui.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.School;
import com.zjc.drivingschool.eventbus.MarkerOnClickEvent;

import java.io.Serializable;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename HospitalMarkerAdapter.java
 * @Date 2015-01-10
 * @description 地区marker适配器
 */
public class DivisionMarkerAdapter implements Serializable {
    private BaiduMap baiduMap;
    private LayoutInflater inflater;

    private View divisionView;
    private TextView tvName;
    public Marker targetMarker;
    //中心点的marker
    BitmapDescriptor markerPoint = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_map_point);
    //医院marker
    private BitmapDescriptor markerHospital;

    public DivisionMarkerAdapter(Context context, BaiduMap baiduMap) {
        super();
        this.baiduMap = baiduMap;
        inflater = LayoutInflater.from(context);
        initBaiDuMapListener();
        markerHospital = BitmapDescriptorFactory.fromView(initDivisionMarkerView());
    }

    /**
     * 初始化医疗资源的markerView
     *
     * @return
     */
    private View initDivisionMarkerView() {
        divisionView = inflater.inflate(R.layout.healing_map_marker_division, null);
        tvName = (TextView) divisionView.findViewById(R.id.healing_marker_division_name);
        return divisionView;
    }

    private void initBaiDuMapListener() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getExtraInfo() != null) {
                    School o = (School) marker.getExtraInfo().getSerializable("school");
                    if (o != null) {
                        //发送点击事件
                        if (EventBus.getDefault().hasSubscriberForEvent(MarkerOnClickEvent.class)) {
                            EventBus.getDefault().post(new MarkerOnClickEvent(o));
                            marker.setExtraInfo(null);
                        }
                    }
                }
                return false;
            }
        });
    }

    public void addAll(List<School> schools) {
        // 当传递过来的数据为空时，直接返回
        if (schools == null) {
            return;
        }
        baiduMap.clear();
        if (targetMarker != null) {
            addTargetMarker(targetMarker.getPosition());
        }
        for (int i = 0; i < schools.size(); i++) {
            addDivisionMarker(schools.get(i));
        }
    }

    public void addDivisionMarker(School school) {
        // 定义Maker坐标点
        LatLng lng = new LatLng(school.getLatitude(), school.getLongitude());
        //设置marker数据
        tvName.setText(school.getTitle());
        markerHospital = BitmapDescriptorFactory.fromView(divisionView);

        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(lng).icon(markerHospital);
        // 在地图上添加Marker，并显示
        addMarkerToBaiDuMap((Marker) (baiduMap.addOverlay(option)), school);
        //回收BitmapDescriptor
        markerHospital.recycle();
    }

    private void addMarkerToBaiDuMap(Marker marker, School school) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("school", school);
        marker.setExtraInfo(bundle);
    }

    public void addTargetMarker(LatLng lng) {
        if (targetMarker != null) {
            targetMarker.remove();
        }

        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(lng).icon(markerPoint);
        // 在地图上添加Marker，并显示
        targetMarker = (Marker) baiduMap.addOverlay(option);
        targetMarker.setToTop();
    }
}
