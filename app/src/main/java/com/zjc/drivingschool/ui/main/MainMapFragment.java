package com.zjc.drivingschool.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.mobo.mobolibrary.ui.base.ZBaseFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.LatLngLocal;
import com.zjc.drivingschool.db.model.School;
import com.zjc.drivingschool.db.model.SearchHospitalModel;
import com.zjc.drivingschool.db.parser.SchoolParser;
import com.zjc.drivingschool.eventbus.MarkerOnClickEvent;
import com.zjc.drivingschool.ui.main.adapter.DivisionMarkerAdapter;
import com.zjc.drivingschool.ui.school.SchoolActivity;
import com.zjc.drivingschool.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * @author Z
 * @Filename MainMapFragment.java
 * @Date 2015.10.20
 * @description 看病抓药-医疗资源地图显示界面
 */
public class MainMapFragment extends ZBaseFragment implements View.OnClickListener, OnGetGeoCoderResultListener, BaiduMap.OnMapTouchListener, BaiduMap.OnMapStatusChangeListener, CloudListener {
    private MapView mMapView;
    private BaiduMap mBaiDuMap;

    //定位相关
    LocationClient mLocClient;
    public MyLocationListener myListener = new MyLocationListener();
    public SearchHospitalModel searchHospitalModel = new SearchHospitalModel();
    /**
     * 区域医疗资源适配器
     */
    private DivisionMarkerAdapter schoolAdapter;


    private ImageView imgLocation;

    // 搜索模块，也可去掉地图模块独立使用
    GeoCoder mSearch = null;
    /**
     * 地图初始缩放等级
     */
    public static float MAP_ZOOM = 16;
    /**
     * 静态常量
     */
    public static String AK = "a49QpA1I2HVWCXL3ULMLvPxlU9Chpt98";
    private static String KEY = "武汉";
    public static int TABLE_ID = 148148;
    private static int RADIUS = 10000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("searchHospitalModel")) {
                searchHospitalModel = (SearchHospitalModel) savedInstanceState.getSerializable("searchHospitalModel");
            }
        }
    }

    @Override
    protected int inflateContentView() {
        return R.layout.healing_map_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 获取地图控件引用
        mMapView = (MapView) rootView.findViewById(R.id.id_mapView);
        imgLocation = (ImageView) rootView.findViewById(R.id.healing_map_frg_img_location);
        // 获得地图的实例
        mBaiDuMap = mMapView.getMap();

        initSearchModel();
        initLocation();
        initMapListener();
        hideBaiDuLogo();

        CloudManager.getInstance().init(this);
        schoolAdapter = new DivisionMarkerAdapter(getActivity(), mBaiDuMap);
    }

    private void initSearchModel() {
        searchHospitalModel.setZoom(MAP_ZOOM);
    }

    private void initMapListener() {
        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);

        //地图点击监听
        mBaiDuMap.setOnMapTouchListener(this);

        //比例尺监听
        mBaiDuMap.setOnMapStatusChangeListener(this);

        /**定位监听*/
        imgLocation.setOnClickListener(this);
    }

    private void hideBaiDuLogo() {
        mMapView.showZoomControls(false);
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.GONE);
        }
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
        option.setIsNeedAddress(true);
        option.setOpenGps(false);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000 * 30);
        mLocClient.setLocOption(option);
        mLocClient.start();
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
            getMedicalResourceOfCity();
        }

        public void onReceivePoi(BDLocation poiLocation) {
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
        searchHospitalModel.setLatLngLocal(new LatLngLocal(location.latitude, location.longitude));
        schoolAdapter.addTargetMarker(location);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(location);
        if (mBaiDuMap != null) {
            mBaiDuMap.animateMapStatus(u);
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.healing_map_frg_img_location) {
            mLocClient.start();
        }
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            LatLngLocal latLngLocal = new LatLngLocal(mBaiDuMap.getMapStatus().target.latitude, mBaiDuMap.getMapStatus().target.longitude);
            LatLngLocal beforeLatLng = searchHospitalModel.getLatLngLocal();

            searchHospitalModel.setLatLngLocal(latLngLocal);
            schoolAdapter.addTargetMarker(searchHospitalModel.getLatLngLocal().getBaiduLatLngByLocal());
            //判断移动距离是否足够
            if (DistanceUtil.getDistance(beforeLatLng.getBaiduLatLngByLocal(), latLngLocal.getBaiduLatLngByLocal()) > (RADIUS / 2)) {
                getMedicalResourceOfCity();
            }
        }
    }

    /**
     * 地图状态改变接口
     *
     * @param mapStatus
     */
    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        /**    zoom 地图缩放级别 3~19
         当缩放等级小于MAP_ZOOM时，获取医院数据，当缩放等级大于MAP_ZOOM时，获取医疗资源分布*/
//        float zoom = mapStatus.zoom;
//        float beforeZoom = searchHospitalModel.getZoom();
//        searchHospitalModel.setZoom(zoom);
//        if (zoom > MAP_ZOOM && beforeZoom <= MAP_ZOOM) {
//        } else if (zoom <= MAP_ZOOM && beforeZoom > MAP_ZOOM) {
//            getMedicalResourceOfCity();
//        }
    }

    /**
     * 移动到某个地区
     *
     * @param name
     */
    public void moveToArea(String name) {
        if (TextUtils.equals(name, "全部")) {
            mLocClient.start();
            return;
        }
        mSearch.geocode(new GeoCodeOption().city(SharePreferencesUtil.getInstance().readCity().getCity()).address(name));
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult == null || geoCodeResult.getLocation() == null) {
            Util.showCustomMsg(getActivity(), "无效的地址");
            return;
        }
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }


    /**
     * 根据坐标将地图移动到指定位置
     * 设置搜索条件里面的坐标
     * 设置地图上面的target图标
     *
     * @param location
     */
    private void upDateMapByLat(LatLng location) {
        searchHospitalModel.setLatLngLocal(new LatLngLocal(location.latitude, location.longitude));
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(location);
        if (mBaiDuMap != null) {
            mBaiDuMap.animateMapStatus(u);
        }
    }

    /**
     * 根据缩放等级，缩放地图
     */
    private void upDateMapByZoom(LatLng latLng, float zoom) {
        MapStatus status = new MapStatus.Builder().target(latLng).zoom(zoom).build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(status);
        if (mBaiDuMap != null) {
            mBaiDuMap.animateMapStatus(msu);
        }
    }

    public void onGetSearchResult(CloudSearchResult result, int error) {
        //在此处理相应的检索结果
        List<School> schools = new SchoolParser().parserArray(result.poiList);

        if (schools.size() == 0) {
            Util.showCustomMsg("附近暂无驾校");
            schoolAdapter.addAll(new ArrayList<School>());
        } else {
            schoolAdapter.addAll(schools);
        }
    }

    @Override
    public void onGetDetailSearchResult(DetailSearchResult detailSearchResult, int i) {
        //在此处理相应的检索结果
    }

    /**
     * 查询整个市的医疗资源
     */
    public void getMedicalResourceOfCity() {
        NearbySearchInfo info = new NearbySearchInfo();
        info.location = searchHospitalModel.getLatLngLocal().getLongitude() + "," + searchHospitalModel.getLatLngLocal().getLatitude();
        info.ak = AK;
        //此处info.ak为服务端ak，非Adnroid sdk端ak， 且此服务端ak和Adnroid sdk端ak 是在同一个账户。
        info.geoTableId = TABLE_ID;
        // info.geoTableId 是存储在于info.ak相同开发账户中。
        info.q = KEY;
        info.radius = RADIUS;
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

    /**
     * 接收markerManagerAdapter里面的marker点击事件
     *
     * @param event
     */
    public void onEventMainThread(MarkerOnClickEvent event) {
        getSchoolDetail(event.getSchool());
    }

    /**
     * 跳转到驾校主页
     */
    public void getSchoolDetail(School school) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, school);
        startActivity(SchoolActivity.class, bundle);
    }

    /**
     * 启动百度地图步行路线规划
     */
    public void startRoutePlanWalking(School hospital) {
        if (mBaiDuMap != null) {
            RouteParaOption para = new RouteParaOption()
                    .startPoint(new LatLng(mBaiDuMap.getLocationData().latitude, mBaiDuMap.getLocationData().longitude))
                    .endPoint(hospital.getLatLngLocal().getBaiduLatLngByLocal())
                    .cityName(SharePreferencesUtil.getInstance().readCity().getCity());

            try {
                BaiduMapRoutePlan.openBaiduMapWalkingRoute(para, getActivity());
            } catch (Exception e) {
                e.printStackTrace();
                showDialog();
            }
        }
    }

    /**
     * 提示未安装百度地图app或app版本过低
     */
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                OpenClientUtil.getLatestBaiduMapApp(getActivity());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        // 退出时销毁定位
        if (mLocClient != null) {
            mLocClient.stop();
        }
        // 关闭定位图层
        if (mBaiDuMap != null) {
            mBaiDuMap.clear();
            mBaiDuMap.setMyLocationEnabled(false);
            mBaiDuMap = null;
        }
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
        }
        super.onDestroy();
    }

    /**
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putSerializable("searchHospitalModel", searchHospitalModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
