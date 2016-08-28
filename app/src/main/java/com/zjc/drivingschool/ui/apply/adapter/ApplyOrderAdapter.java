package com.zjc.drivingschool.ui.apply.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.OrderItem;
import com.zjc.drivingschool.utils.ConstantsParams;

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
            super(parent, R.layout.apply_order_item);
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

            mTvStatus.setText(ConstantsParams.getStatus(service.getState()));
            //	1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消
            if (service.getState().equals(ConstantsParams.STUDY_ORDER_ONE)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.GONE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_TWO)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.VISIBLE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_SEVEN)) {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.VISIBLE);
            } else {
                mTvPay.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                mTvDiscuss.setVisibility(View.GONE);
            }

            mTvDiscuss.setTag(service);
            mTvDiscuss.setOnClickListener(new OrderOnClickListener());
        }

        /**
         * 预约体检点击事件
         */
        class OrderOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                OrderItem item = (OrderItem) v.getTag();
                order(item);
            }
        }

        private void order(final OrderItem item) {
            ApiHttpClient.getInstance().completeHospital(SharePreferencesUtil.getInstance().readUser().getUid(), item.getOrid(), new ResultResponseHandler(getContext(), "正在提交") {

                @Override
                public void onResultSuccess(String result) {
                    item.setState(ConstantsParams.STUDY_ORDER_EIGHT);
                    ApplyOrderAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }
}
