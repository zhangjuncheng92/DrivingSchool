package com.zjc.drivingschool.ui.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.eventbus.StudyAddressChooseEvent;
import com.zjc.drivingschool.ui.study.adapter.StudyAddressAdapter;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename AddressAddFrg.java
 * @Date 2015-06-09
 * @description 个人中心 添加我的地址界面
 */
public class StudyAddressFragment extends ZBaseToolBarFragment implements View.OnClickListener, BaiduMap.OnMapClickListener, OnGetPoiSearchResultListener, ZBaseRecyclerViewAdapter.OnItemClickListener {
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private EasyRecyclerView mRecyclerView;
    private StudyAddressAdapter mAdapter;

    /**
     * 定位相关
     */
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    /**
     * 是否首次定位
     */
    boolean isFirstLoc = true;
    private LatLng currentLatlng;
    private PoiSearch mPoiSearch = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "我的订单");
    }

    @Override
    protected int inflateContentView() {
        return R.layout.learn_address_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initRecyclerView();
        initAdapter();
        initBaiduMap();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
    }

    private void initAdapter() {
        mAdapter = new StudyAddressAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
    }


    private void initBaiduMap() {
        // 获取地图控件引用
        mMapView = (MapView) rootView.findViewById(R.id.learn_address_frg_map);
        // 获得地图的实例
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setTrafficEnabled(false);
        //武汉坐标
        if (currentLatlng == null) {
            currentLatlng = new LatLng(30.543622, 114.433890);
        }
        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatus status = new MapStatus.Builder().target(currentLatlng).zoom(12).build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(status);
        mBaiduMap.animateMapStatus(msu);

        mBaiduMap.setOnMapClickListener(this);
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);

        initLocation();
    }

    /**
     * 初始化定位功能
     */
    private void initLocation() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000 * 60);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Util.showCustomMsg(getActivity(), "未找到结果");
            mAdapter.clear();
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            /**每次搜索结果都必须重新new adapter设置才有效果*/
            mAdapter.addAll(result.getAllPoi());
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onItemClick(View view, int position) {
        PoiInfo poiInfo = (PoiInfo) mAdapter.getItem(position);
        EventBus.getDefault().post(new StudyAddressChooseEvent(poiInfo));
        getFragmentManager().popBackStack();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
//            显示定位图标、信息
//            设置开发者获取到的方向信息，顺时针0 - 360
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            mLocClient.stop();
            if (isFirstLoc) {
                isFirstLoc = false;
                //移动地图
                seachPoiByLatLng(new LatLng(location.getLatitude(),
                        location.getLongitude()));
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(currentLatlng);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }

    }

    @Override
    public void onMapClick(LatLng latLng) {
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(u);
        seachPoiByLatLng(latLng);
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        mapPoi.getName();
        seachPoiByLatLng(mapPoi.getPosition());
        return false;
    }

    /**
     * 根据经纬度搜索附近的小区（范围1km）
     *
     * @param latLng
     */
    public void seachPoiByLatLng(LatLng latLng) {
        currentLatlng = latLng;
        PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
        searchOption.location(latLng);
        searchOption.keyword("小区");
        searchOption.pageCapacity(50);
        searchOption.pageNum(0);
        searchOption.radius(2000);
        mPoiSearch.searchNearby(searchOption);
    }


    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mMapView != null) {
            EventBus.getDefault().unregister(this);
            mLocClient.stop();
            mBaiduMap.setMyLocationEnabled(false);
            mPoiSearch.destroy();
            mMapView.onDestroy();
            mMapView = null;
        }
        super.onDestroy();
    }

}
