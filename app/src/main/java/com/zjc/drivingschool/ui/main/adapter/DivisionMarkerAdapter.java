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
import com.zjc.drivingschool.db.model.Division;
import com.zjc.drivingschool.eventbus.MarkerOnClickEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename HospitalMarkerAdapter.java
 * @Date 2015-01-10
 * @description 地区marker适配器
 */
public class DivisionMarkerAdapter implements Serializable {
    private List<Division> list = new ArrayList<>();
    private Context mContext;
    private BaiduMap baiduMap;
    private Marker mCurrentMarker;
    private LayoutInflater inflater;

    private View divisionView;
    private TextView tvName;
    private TextView tvNum;
    public Marker targetMarker;
    //中心点的marker
    BitmapDescriptor markerPoint = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_map_point);
    //医院marker
    private BitmapDescriptor markerHospital;

    public DivisionMarkerAdapter(Context context, BaiduMap baiduMap) {
        super();
        this.mContext = context;
        this.baiduMap = baiduMap;
        inflater = LayoutInflater.from(context);
        initBaiduMapLinstener();
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
        tvNum = (TextView) divisionView.findViewById(R.id.healing_marker_division_num);
        return divisionView;
    }

    private void initBaiduMapLinstener() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getExtraInfo() != null) {
                    Division o = (Division) marker.getExtraInfo().getSerializable("division");
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

    public void addAll(List<Division> list) {
        // 当传递过来的数据为空时，直接返回
        if (list == null) {
            return;
        }
        baiduMap.clear();
        this.list.clear();
        this.list.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            addDivisionMarker(list.get(i));
        }
    }

    public List<Division> getAll() {
        return this.list;
    }

    public void addDivisionMarker(Division division) {
        if (division.getLatLngLocal().getLatitude() == null || division.getLatLngLocal().getLongitude() == null) {
            return;
        }

        // 定义Maker坐标点
        LatLng lng = division.getLatLngLocal().getBaiduLatLngByLocal();
        //设置marker数据
        tvName.setText(division.getName());
        tvNum.setText(division.getNum() + "");
        markerHospital = BitmapDescriptorFactory.fromView(divisionView);

        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(lng).icon(markerHospital);
        // 在地图上添加Marker，并显示
        addMarkerToBadiduMap((Marker) (baiduMap.addOverlay(option)), division);
        //回收BitmapDescriptor
        markerHospital.recycle();
    }

    private void addMarkerToBadiduMap(Marker marker, Division division) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("division", division);
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

    public Marker getDivisionMarker() {
        return mCurrentMarker;
    }

    public void onDestroy() {
        markerHospital = null;
        mCurrentMarker = null;
        divisionView = null;
    }
}
