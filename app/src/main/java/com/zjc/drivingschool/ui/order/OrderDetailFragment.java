package com.zjc.drivingschool.ui.order;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alertdialogpro.AlertDialogPro;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.parser.OrderDetailResponseParser;
import com.zjc.drivingschool.db.response.OrderDetailResponse;
import com.zjc.drivingschool.utils.Constants;


/**
 * @author Z
 * @Filename OrderDetailFragment.java
 * @Date 2015.10.28
 * @description 预约详情界面
 */
public class OrderDetailFragment extends ZBaseToolBarFragment {
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


    /**
     * 传入需要的参数，设置给arguments
     */
    public static OrderDetailFragment newInstance(String bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        OrderDetailFragment fragment = new OrderDetailFragment();
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
    protected int inflateContentView() {
        return R.layout.order_detail_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (orderid != null) {
            initEmptyLayout(rootView);
            initView();
            getDetailById();
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

    private void getDetailById() {
        ApiHttpClient.getInstance().getOrderDetailById(SharePreferencesUtil.getInstance().readUser().getUid(), orderid, new ResultResponseHandler(getActivity(), getEmptyLayout()) {
            @Override
            public void onResultSuccess(String result) {
                orderDetail = new OrderDetailResponseParser().parseResultMessage(result);
                setInfo();
            }
        });
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "预约详情");
    }

    private void setTextView(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setText("");
        } else {
            textView.setText(text);
        }
    }

    private void setInfo() {
        setStatus();

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

    private void setStatus() {
//        if (appointmentRegistration.getBussinessStatus() == ConstantsParams.APPOINTMENT_REGISTRATION_APPLICATION) {
//            //预约成功
//            tvStatus.setText(ConstantsParams.PAYMENT_STATUS_WAIT_PAY_TEXT);
//            tvStatus.setTextColor(getResources().getColor(R.color.comm_red));
//            txtStatus.setTextColor(getResources().getColor(R.color.comm_red));
//            iconStatus.setImageResource(R.drawable.icon_registration_wait);
//        } else if (appointmentRegistration.getBussinessStatus() == ConstantsParams.APPOINTMENT_REGISTRATION_SUCCESS) {
//            //支付成功
//            tvStatus.setText(ConstantsParams.APPOINTMENT_REGISTRATION_PAID_TEXT);
//            iconStatus.setImageResource(R.drawable.icon_registration_success);
//        } else if (appointmentRegistration.getBussinessStatus() == ConstantsParams.APPOINTMENT_REGISTRATION_ALREADYTAKE) {
//            //取号成功
//            tvStatus.setText(ConstantsParams.APPOINTMENT_REGISTRATION_ALREADYTAKE_TEXT);
//            tvStatus.setTextColor(getResources().getColor(R.color.comm_black));
//            txtStatus.setTextColor(getResources().getColor(R.color.comm_black));
//            iconStatus.setImageResource(R.drawable.icon_registration_success);
//            if (appointmentRegistration.getCommentStatus() == ConstantsParams.COMMENT_STATUS_SUCCESS) {
//                tvStatus.setText(ConstantsParams.COMMENT_STATUS_SUCCESS_TEXT);
//            }
////            else {
////                tvStatus.setText(ConstantsParams.COMMENT_STATUS_TODO_TEXT);
////            }
//        } else if (appointmentRegistration.getBussinessStatus() == ConstantsParams.APPOINTMENT_REGISTRATION_CANCEL) {
//            //取消预约单
//            tvStatus.setText(ConstantsParams.APPOINTMENT_REGISTRATION_CANCEL_TEXT);
//            tvStatus.setTextColor(getResources().getColor(R.color.comm_gary));
//            txtStatus.setTextColor(getResources().getColor(R.color.comm_gary));
//            iconStatus.setImageResource(R.drawable.icon_registration_cancel);
//        } else if (appointmentRegistration.getBussinessStatus() == ConstantsParams.APPOINTMENT_REGISTRATION_TIMEOUT) {
//            //预约单失效
//            tvStatus.setTextColor(getResources().getColor(R.color.comm_gary));
//            txtStatus.setTextColor(getResources().getColor(R.color.comm_gary));
//            iconStatus.setImageResource(R.drawable.icon_registration_cancel);
//            tvStatus.setText(ConstantsParams.APPOINTMENT_REGISTRATION_TIMEOUT_TEXT);
//        }
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
}
