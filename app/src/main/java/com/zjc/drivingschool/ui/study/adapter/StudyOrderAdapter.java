package com.zjc.drivingschool.ui.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.model.OrderItem;
import com.zjc.drivingschool.eventbus.StudyOrderCancelEvent;
import com.zjc.drivingschool.eventbus.StudyOrderUnSubjectEvent;
import com.zjc.drivingschool.utils.ConstantsParams;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/17.
 */
public class StudyOrderAdapter extends ZBaseRecyclerViewAdapter {
    public StudyOrderAdapter(Context context) {
        super(context);
    }


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new OrderManagerFrgViewHolder(parent);
    }

    class OrderManagerFrgViewHolder extends BaseViewHolder<OrderItem> {
        private TextView mTvName;
        private TextView mTvStatus;
        private TextView mTvNumber;
        private TextView mTvTime;
        private TextView mTvPay;
        private TextView mTvCancel;
        private TextView mTvDiscuss;
        private TextView tvUnSubject;
        private TextView mTvMoney;

        public OrderManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.study_order_item);
            mTvName = $(R.id.order_name);
            mTvStatus = $(R.id.order_status);
            mTvNumber = $(R.id.order_number);
            mTvTime = $(R.id.order_time);
            mTvPay = $(R.id.order_pay);
            mTvCancel = $(R.id.order_cancel);
            mTvDiscuss = $(R.id.order_discuss);
            tvUnSubject = $(R.id.order_unsubject);
            mTvMoney = $(R.id.order_money);
        }

        @Override
        public void setData(OrderItem service) {
            if (service == null) {
                return;
            }
            mTvName.setText(service.getTitle());
            mTvNumber.setText(service.getOrderid());
            mTvTime.setText(service.getOrdertime());
            mTvMoney.setText(service.getTotal() + "");
            mTvStatus.setText(ConstantsParams.getStatus(service.getState()));
            //	1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消
            if (service.getState().equals(ConstantsParams.STUDY_ORDER_ONE)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.VISIBLE);
                mTvDiscuss.setVisibility(View.GONE);
                tvUnSubject.setVisibility(View.GONE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_TWO)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.GONE);
                tvUnSubject.setVisibility(View.VISIBLE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_SEVEN)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.VISIBLE);
                tvUnSubject.setVisibility(View.GONE);
            } else {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.GONE);
                tvUnSubject.setVisibility(View.GONE);
            }

            mTvCancel.setTag(service);
            mTvCancel.setOnClickListener(new CancelOnClickListener());
            tvUnSubject.setTag(service);
            tvUnSubject.setOnClickListener(new UnSubjectOnClickListener());
        }
    }

    /**
     * 取消点击事件
     */
    class CancelOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderItem item = (OrderItem) v.getTag();
            cancelStudyOrder(item.getUid(), item.getOrid());
        }
    }

    private void cancelStudyOrder(String uid, final String orid) {
        ApiHttpClient.getInstance().cancelStudyOrder(uid, orid, new ResultResponseHandler(getContext(), "正在取消订单") {

            @Override
            public void onResultSuccess(String result) {
                if (EventBus.getDefault().hasSubscriberForEvent(StudyOrderCancelEvent.class)) {
                    EventBus.getDefault().post(new StudyOrderCancelEvent(orid));
                }
            }
        });
    }

    /**
     * 退订点击事件
     */
    class UnSubjectOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderItem item = (OrderItem) v.getTag();
            unSubjectStudyOrder(item.getUid(), item.getOrid());
        }
    }

    private void unSubjectStudyOrder(String uid, final String orid) {
        ApiHttpClient.getInstance().unSubjectStudyOrder(uid, orid, new ResultResponseHandler(getContext(), "正在退订订单") {

            @Override
            public void onResultSuccess(String result) {
                if (EventBus.getDefault().hasSubscriberForEvent(StudyOrderUnSubjectEvent.class)) {
                    EventBus.getDefault().post(new StudyOrderUnSubjectEvent(orid));
                }
            }
        });
    }
}
