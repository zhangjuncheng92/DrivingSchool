package com.zjc.drivingschool.ui.apply.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.OrderItem;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ApplyOrderAdapter extends ZBaseRecyclerViewAdapter {
    public ApplyOrderAdapter(Context context) {
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
        private TextView mTvMoney;

        public OrderManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.order_manager_list_adapter);
            mTvName = $(R.id.order_name);
            mTvStatus = $(R.id.order_status);
            mTvNumber = $(R.id.order_number);
            mTvTime = $(R.id.order_time);
            mTvPay = $(R.id.order_pay);
            mTvCancel = $(R.id.order_cancel);
            mTvDiscuss = $(R.id.order_discuss);
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
            mTvStatus.setText(service.getState());
            //	1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消
            if (service.getState().equals(8) || service.getState().equals(7) || service.getState().equals(6)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.VISIBLE);
            } else if (service.getState().equals(1)) {
                mTvPay.setVisibility(View.VISIBLE);
                mTvCancel.setVisibility(View.VISIBLE);
                mTvDiscuss.setVisibility(View.GONE);
            } else {
                //// TODO: 2016/8/20
                mTvPay.setVisibility(View.VISIBLE);
                mTvCancel.setVisibility(View.VISIBLE);
                mTvDiscuss.setVisibility(View.GONE);
            }

        }
    }
}
