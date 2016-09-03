package com.zjc.drivingschool.ui.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.image.ImageLoader;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.OrderItem;
import com.zjc.drivingschool.db.parser.OrderDetailResponseParser;
import com.zjc.drivingschool.db.response.OrderDetail;
import com.zjc.drivingschool.eventbus.StudyOrderAssessEvent;
import com.zjc.drivingschool.utils.Constants;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename OrderAssessFrg.java
 * @Date 2015-07-20
 * @description 订单评价
 */

public class StudyOrderAssessFrg extends ZBaseToolBarFragment implements View.OnClickListener {
    private OrderItem orderItem;
    private OrderDetail orderDetail;
    private EditText edtDesc;
    private RatingBar ratingBar;
    private TextView tvCommit;
    private TextView tvName;
    private SimpleDraweeView sdIcon;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static StudyOrderAssessFrg newInstance(OrderItem bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        StudyOrderAssessFrg fragment = new StudyOrderAssessFrg();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            orderItem = (OrderItem) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.study_history_assess);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.study_order_assess_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        getTeacherInfo();
    }


    private void initView() {
        edtDesc = (EditText) rootView.findViewById(R.id.study_order_assess_frg_edt_desc);
        ratingBar = (RatingBar) rootView.findViewById(R.id.study_order_assess_frg_rating);
        tvCommit = (TextView) rootView.findViewById(R.id.study_order_assess_frg_tv_commit);
        tvName = (TextView) rootView.findViewById(R.id.study_order_assess_frg_tv_name);
        sdIcon = (SimpleDraweeView) rootView.findViewById(R.id.study_order_assess_frg_sd_teacher);

        tvCommit.setOnClickListener(this);
    }

    private void getTeacherInfo() {
        ApiHttpClient.getInstance().getOrderDetailById(SharePreferencesUtil.getInstance().readUser().getUid(), orderItem.getOrid(), new ResultResponseHandler(getActivity(), getEmptyLayout()) {
            @Override
            public void onResultSuccess(String result) {
                orderDetail = new OrderDetailResponseParser().parseResultMessage(result);
                tvName.setText(orderDetail.getTname());
                ImageLoader.getInstance().displayImage(sdIcon, Constants.BASE_IP + orderDetail.getTphoto());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.study_order_assess_frg_tv_commit:
                assessStudyOrder();
                break;
        }
    }

    private void assessStudyOrder() {
        String desc = edtDesc.getEditableText().toString().trim();
        String oid = orderItem.getOrid();
        float stars = ratingBar.getRating();
        String tid = orderDetail.getTid();
        String uid = SharePreferencesUtil.getInstance().readUser().getUid();

        ApiHttpClient.getInstance().assessStudyOrder(desc, oid, stars, tid, uid, new ResultResponseHandler(getContext(), "正在评价") {

            @Override
            public void onResultSuccess(String result) {
                if (EventBus.getDefault().hasSubscriberForEvent(StudyOrderAssessEvent.class)) {
                    EventBus.getDefault().post(new StudyOrderAssessEvent(orderDetail.getOrid()));
                }
                getActivity().finish();
            }
        });
    }
}
