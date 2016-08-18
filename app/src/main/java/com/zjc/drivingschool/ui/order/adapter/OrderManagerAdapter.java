package com.zjc.drivingschool.ui.order.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.UserInfo;

/**
 * Created by Administrator on 2016/8/17.
 */
public class OrderManagerAdapter extends ZBaseRecyclerViewAdapter {
    public OrderManagerAdapter(Context context) {
        super(context);
    }


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new OrderManagerFrgViewHolder(parent);
    }

    class OrderManagerFrgViewHolder extends BaseViewHolder<UserInfo> {
        private TextView mTvName;
        private TextView mTvStatus;
        private TextView mTvNumber;
        private TextView mTvTime;
        private TextView mTvPay;
        private TextView mTvCancel;
        private TextView mTvDiscuss;

        public OrderManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.order_manager_list_adapter);
            mTvName = $(R.id.order_name);
            mTvStatus = $(R.id.order_status);
            mTvNumber = $(R.id.order_number);
            mTvTime = $(R.id.order_time);
            mTvPay = $(R.id.order_pay);
            mTvCancel = $(R.id.order_cancel);
            mTvDiscuss = $(R.id.order_discuss);

        }

        @Override
        public void setData(UserInfo service) {
            if (service == null) {
                return;
            }


        }
    }
}
