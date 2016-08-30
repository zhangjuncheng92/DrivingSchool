package com.zjc.drivingschool.ui.study;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alertdialogpro.AlertDialogPro;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.parser.OrderDetailResponseParser;
import com.zjc.drivingschool.db.response.OrderDetailResponse;
import com.zjc.drivingschool.utils.Constants;
import com.zjc.drivingschool.utils.ConstantsParams;


/**
 * @author Z
 * @Filename StudyDetailFragment.java
 * @Date 2015.10.28
 * @description 预约详情界面
 */
public class StudyDetailFragment extends ZBaseToolBarFragment implements View.OnClickListener, BaiduMap.OnMapClickListener {
    private String orderid;
    private OrderDetailResponse orderDetail;
    private Button btnCommit;

    private TextView tvStatus;
    private TextView txtStatus;
    private ImageView iconStatus;

    private TextView tvSubject;
    private TextView tvCart;
    private TextView tvStudent;
    private TextView tvLength;
    private TextView tvStudentPhone;
    private TextView tvFee;

    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView tvSchool;
    private TextView tvTeacher;

    private TextView tvOrderNo;
    private TextView tvOrderTime;
    private MapView mMapView;
    private BaiduMap mBaiduMap;


    /**
     * 传入需要的参数，设置给arguments
     */
    public static StudyDetailFragment newInstance(String bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        StudyDetailFragment fragment = new StudyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            orderid = (String) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "预约详情");
    }

    @Override
    protected int inflateContentView() {
        return R.layout.order_detail_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (orderid != null) {
            initEmptyLayout(rootView);
            initView();
            initBaiduMap();
            getDetailById();
            getTeacherLocal();
        }
    }

    private void initView() {

//        iconStatus = (ImageView) rootView.findViewById(R.id.registration_detail_frg_icon_status);
        tvStatus = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_status);
        tvSubject = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_subject);
        tvCart = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_cart);
        tvLength = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_length);
        tvFee = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_fee);


        tvStudent = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_student);
        tvStudentPhone = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_student_phone);
        tvStartTime = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_start_time);
        tvEndTime = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_end_time);
        tvSchool = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_school);
        tvTeacher = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_teacher);

        tvOrderNo = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_NO);
        tvOrderTime = (TextView) rootView.findViewById(R.id.order_detail_frg_tv_order_time);
    }

    private void initBaiduMap() {
        // 获取地图控件引用
        mMapView = (MapView) rootView.findViewById(R.id.order_detail_frg_map);
        // 获得地图的实例
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setTrafficEnabled(false);
        //武汉坐标
        LatLng currentLatlng = new LatLng(30.543622, 114.433890);
        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatus status = new MapStatus.Builder().target(currentLatlng).zoom(12).build();
        MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(status);
        mBaiduMap.animateMapStatus(msu);

        mBaiduMap.setOnMapClickListener(this);
    }

    private void getDetailById() {
        ApiHttpClient.getInstance().getOrderDetailById(SharePreferencesUtil.getInstance().readUser().getUid(), orderid, new ResultResponseHandler(getActivity(), getEmptyLayout()) {
            @Override
            public void onResultSuccess(String result) {
                orderDetail = new OrderDetailResponseParser().parseResultMessage(result);
                setInfo();
            }
        });
    }

    private void getTeacherLocal() {
        ApiHttpClient.getInstance().getOrderDetailById(SharePreferencesUtil.getInstance().readUser().getUid(), orderid, new ResultResponseHandler(getActivity(), getEmptyLayout()) {
            @Override
            public void onResultSuccess(String result) {
                LatLng currentLatlng = new LatLng(30.543622, 114.433890);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(currentLatlng);
                mBaiduMap.animateMapStatus(u);
            }
        });
    }


    private void setTextView(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setText("");
        } else {
            textView.setText(text);
        }
    }

    private void setInfo() {
        tvStatus.setText(ConstantsParams.getStatus(orderDetail.getState()));

        setTextView(tvCart, orderDetail.getCarsname());
        setTextView(tvSubject, orderDetail.getSubjectname());
        setTextView(tvLength, orderDetail.getEndtime());
        tvFee.setText(orderDetail.getTotal() + "");

        setTextView(tvStudent, orderDetail.getContactsname());
        setTextView(tvStudentPhone, orderDetail.getContactsphone());
        setTextView(tvStartTime, orderDetail.getStarttime());
        setTextView(tvEndTime, orderDetail.getEndtime());
        setTextView(tvSchool, orderDetail.getSname());
        setTextView(tvTeacher, orderDetail.getTname());

        setTextView(tvOrderNo, orderDetail.getOrid());
        setTextView(tvOrderTime, orderDetail.getOrdertime());
    }


    private void showCancelDialog() {
        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getActivity());
        builder.setTitle("温馨提示");
        builder.setMessage("是否确定取消预约单？");
        builder.setNegativeButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        cancelAppointmentRegistration();
                    }
                });
        builder.setPositiveButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void cancelAppointmentRegistration() {
//        ApiHttpClient.getInstance().cancelAppointmentRegistration(appointmentRegistration.getId(), appointmentRegistration.getPatientId(), new ResultResponseHandlerOfDialog<AppointmentRegistration>(getActivity(), "请稍等", new AppointmentRegistrationParser()) {
//
//            @Override
//            public void onResultSuccess(List<AppointmentRegistration> result) {
//                appointmentRegistration = result.get(0);
//                if (appointmentRegistration.getPayStatus() == ConstantsParams.PAYMENT_STATUS_SUCCESS) {
//                    Util.showCustomMsg("已经成功为您取消预约单,预约金已退回至您的账户余额");
//                } else {
//                    Util.showCustomMsg("已经成功为您取消预约单");
//                }
//                setInfo();
//                if (EventBus.getDefault().hasSubscriberForEvent(AppointmentRegistrationCancelEvent.class)) {
//                    EventBus.getDefault().post(new AppointmentRegistrationCancelEvent(appointmentRegistration));
//                }
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
}
