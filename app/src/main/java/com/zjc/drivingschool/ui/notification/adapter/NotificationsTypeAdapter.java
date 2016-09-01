package com.zjc.drivingschool.ui.notification.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.MessageItem;
import com.zjc.drivingschool.widget.ZExpandableLayout;

/**
 * @author Z
 * @Filename NotificationsListAdapter.java
 * @Date 2015.11.25
 * @description 通知类型适配器
 */
public class NotificationsTypeAdapter extends ZBaseRecyclerViewAdapter {

    public NotificationsTypeAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MainServiceViewHolder(parent);
    }

    class MainServiceViewHolder extends BaseViewHolder<MessageItem> {
        private ZExpandableLayout expandableLayout;

        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;
        private TextView tvNoticeCount;

        private ImageView tvIcon;
        private ImageView ImgIsRead;

        public MainServiceViewHolder(ViewGroup parent) {
            super(parent, R.layout.notification_main_item);
            expandableLayout = $(R.id.expandableLayout);
            ImgIsRead = $(R.id.notification_type_item_icon);
            tvTitle = $(R.id.notification_type_item_tv_title);
            tvContent = $(R.id.notification_type_item_tv_content);
            tvTime = $(R.id.notification_type_item_tv_time);
        }

        @Override
        public void setData(final MessageItem service) {
            expandableLayout.init(NotificationsTypeAdapter.this,service,this.getAdapterPosition());
            ImgIsRead.setSelected(!service.getIsread());

            tvTitle.setText(service.getTitle());
            tvTime.setText(service.getCreatdate());
        }
    }
}
