package com.zjc.drivingschool.ui.study.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;

/**
 * Created by Administrator on 2016/8/17.
 */
public class StudyAddressAdapter extends ZBaseRecyclerViewAdapter {
    public StudyAddressAdapter(Context context) {
        super(context);
    }


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new OrderManagerFrgViewHolder(parent);
    }

    class OrderManagerFrgViewHolder extends BaseViewHolder<CloudPoiInfo> {
        private TextView tvAddress;

        public OrderManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.learn_address_item);
            tvAddress = $(R.id.address_add_list_tvaddress);
        }

        @Override
        public void setData(CloudPoiInfo service) {
            tvAddress.setText(service.title);
        }
    }
}
