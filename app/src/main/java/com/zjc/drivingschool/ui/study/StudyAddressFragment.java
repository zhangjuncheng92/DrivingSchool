package com.zjc.drivingschool.ui.study;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.eventbus.StudyAddressChooseEvent;
import com.zjc.drivingschool.ui.main.MainMapFragment;
import com.zjc.drivingschool.ui.study.adapter.StudyAddressAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename AddressAddFrg.java
 * @Date 2015-06-09
 * @description 个人中心 添加我的地址界面
 */
public class StudyAddressFragment extends ZBaseToolBarFragment implements View.OnClickListener, OnGetPoiSearchResultListener, ZBaseRecyclerViewAdapter.OnItemClickListener, BaiduMap.OnMapTouchListener, OnGetGeoCoderResultListener, CloudListener {
    private MapView mMapView;
    private BaiduMap mBaiDuMap;

    private EasyRecyclerView mRecyclerView;
    private StudyAddressAdapter mAdapter;

    /**
     * 定位相关
     */
    LocationClient mLocClient;
    public MyLocationListener myListener = new MyLocationListener();

    GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
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
        mBaiDuMap = mMapView.getMap();
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);

        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);

        //地图点击监听
        mBaiDuMap.setOnMapTouchListener(this);

        CloudManager.getInstance().init(this);
        initLocation();
    }

    /**
     * 初始化定位功能
     */
    private void initLocation() {
        // 开启定位图层
        mBaiDuMap.setMyLocationEnabled(true);
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
//        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            Util.showCustomMsg(getActivity(), "未找到结果");
//            mAdapter.clear();
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            /**每次搜索结果都必须重新new adapter设置才有效果*/
//            mAdapter.addAll(result.getAllPoi());
//        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onItemClick(View view, int position) {
        CloudPoiInfo poiInfo = (CloudPoiInfo) mAdapter.getItem(position);
        EventBus.getDefault().post(new StudyAddressChooseEvent(poiInfo));
        getFragmentManager().popBackStack();
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Util.showCustomMsg("抱歉，未能找到结果");
            return;
        }
        mAdapter.clear();
        //添加地址
        CloudPoiInfo poiInfo = new CloudPoiInfo();
        poiInfo.title = reverseGeoCodeResult.getAddress();
        poiInfo.latitude = reverseGeoCodeResult.getLocation().latitude;
        poiInfo.longitude = reverseGeoCodeResult.getLocation().longitude;
        mAdapter.add(poiInfo);
        getMedicalResourceOfCity(reverseGeoCodeResult.getLocation());
    }

    @Override
    public void onGetSearchResult(CloudSearchResult cloudSearchResult, int i) {
        //在此处理相应的检索结果
        List<CloudPoiInfo> cloudPoiInfos = cloudSearchResult.poiList;
        if (cloudPoiInfos.size() > 0) {
            mAdapter.addAll(cloudPoiInfos);
        }
    }

    @Override
    public void onGetDetailSearchResult(DetailSearchResult detailSearchResult, int i) {

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mLocClient.stop();
            setLocationIcon(location);
            upDateMapByLatOfTarget(new LatLng(location.getLatitude(), location.getLongitude()));
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(location.getLatitude(), location.getLongitude())));
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            LatLng latLng = new LatLng(mBaiDuMap.getMapStatus().target.latitude, mBaiDuMap.getMapStatus().target.longitude);
            upDateMapByLatOfTarget(latLng);
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        }
    }

    /**
     * 显示定位图标、信息 设置开发者获取到的方向信息，顺时针0 - 360
     */

    private void setLocationIcon(BDLocation location) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                .direction(100)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiDuMap.setMyLocationData(locData);
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
        mBaiDuMap.clear();
        //中心点的marker
        BitmapDescriptor markerPoint = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_point);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(lng).icon(markerPoint);
        // 在地图上添加Marker，并显示
        mBaiDuMap.addOverlay(option);
    }

//    /**
//     * 根据经纬度搜索附近的小区（范围1km）
//     *
//     * @param latLng
//     */
//    public void searchPoiByLatLng(LatLng latLng) {
//        currentLatlng = latLng;
//        PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
//        searchOption.location(latLng);
//        searchOption.keyword("小区");
//        searchOption.pageCapacity(50);
//        searchOption.pageNum(0);
//        searchOption.radius(2000);
//        mPoiSearch.searchNearby(searchOption);
//    }

    /**
     * 查询整个市的医疗资源
     */

    public void getMedicalResourceOfCity(LatLng latLng) {
        NearbySearchInfo info = new NearbySearchInfo();
        info.location = latLng.longitude + "," + latLng.latitude;
        info.ak = MainMapFragment.AK;
        //此处info.ak为服务端ak，非Adnroid sdk端ak， 且此服务端ak和Adnroid sdk端ak 是在同一个账户。
        info.geoTableId = MainMapFragment.TABLE_ID;
        // info.geoTableId 是存储在于info.ak相同开发账户中。
        info.q = MainMapFragment.KEY;
        info.radius = MainMapFragment.RADIUS;
        CloudManager.getInstance().nearbySearch(info);
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
            mBaiDuMap.setMyLocationEnabled(false);
            mPoiSearch.destroy();
            mMapView.onDestroy();
            mMapView = null;
            mSearch.destroy();
        }
        super.onDestroy();
    }

}
