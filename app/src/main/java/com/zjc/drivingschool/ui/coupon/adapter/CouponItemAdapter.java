package com.zjc.drivingschool.ui.coupon.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.response.Coupon;

/**
 * @author Z
 * @Filename NotificationsListAdapter.java
 * @Date 2015.11.25
 * @description 通知类型适配器
 */
public class CouponItemAdapter extends ZBaseRecyclerViewAdapter {

    public CouponItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MainServiceViewHolder(parent);
    }

    class MainServiceViewHolder extends BaseViewHolder<Coupon.VouchersEntity> {
        private TextView tvTitle;
        private TextView tvFee;
        private TextView tvTime;
        private TextView tvCondition;

        public MainServiceViewHolder(ViewGroup parent) {
            super(parent, R.layout.coupon_item);
            tvTitle = $(R.id.coupon_item_tv_title);
            tvFee = $(R.id.coupon_item_tv_fee);
            tvTime = $(R.id.coupon_item_tv_time);
            tvCondition = $(R.id.coupon_item_tv_condition);
        }

        @Override
        public void setData(final Coupon.VouchersEntity service) {
            tvTitle.setText(service.getVname());
            tvTime.setText("有效期至：" + service.getEndtime());
            tvFee.setText("" + service.getAmount());
            tvCondition.setText("订单满：" + service.getLimitamount() + "可用");
        }
    }
}
