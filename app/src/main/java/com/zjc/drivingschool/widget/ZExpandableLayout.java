package com.zjc.drivingschool.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.alertdialogpro.AlertDialogPro;
import com.andexert.expandablelayout.library.ExpandableLayout;
import com.google.gson.JsonArray;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.MessageItem;
import com.zjc.drivingschool.db.parser.MessageDetailParser;
import com.zjc.drivingschool.db.response.MessageDetail;
import com.zjc.drivingschool.ui.notification.adapter.NotificationsItemAdapter;

/**
 * Created by asus1 on 2016/9/1.
 */
public class ZExpandableLayout extends ExpandableLayout implements View.OnClickListener, View.OnLongClickListener {
    private MessageDetail messageDetail;
    private NotificationsItemAdapter mAdapter;
    private int position;

    public ZExpandableLayout(Context context) {
        super(context);
    }

    public ZExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZExpandableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(NotificationsItemAdapter notificationsItemAdapter, int adapterPosition) {
        this.position = adapterPosition;
        this.mAdapter = notificationsItemAdapter;
        getHeaderRelativeLayout().setOnClickListener(this);
        getHeaderRelativeLayout().setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isOpened()) {
            hide();
        } else {
            show();
            if (messageDetail == null) {
                getNoticeDetail((MessageItem) mAdapter.getItem(position));
            }
        }
    }

    public void getNoticeDetail(MessageItem messageItem) {
        ApiHttpClient.getInstance().getNoticeDetail(SharePreferencesUtil.getInstance().readUser().getUid(), messageItem.getMid(), new ResultResponseHandler(getContext()) {
            @Override
            public void onResultSuccess(String result) {
                messageDetail = new MessageDetailParser().parseResultMessage(result);
                setDetail(messageDetail);
//                setIsRead();
            }
        });
    }

    public void setDetail(MessageDetail messageDetail) {
        TextView tvContent = (TextView) findViewById(R.id.notification_content_item_tv);
        tvContent.setText(messageDetail.getContext());
    }


    public void setIsRead() {
        ((MessageItem) mAdapter.getItem(position)).setIsread(true);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public boolean onLongClick(View view) {
        showDeleteDialog();
        return true;
    }

    private void showDeleteDialog() {
        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getContext());
        builder.setTitle("温馨提示：");
        builder.setMessage("确认删除该条通知消息？");
        builder.setNegativeButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteMessages((MessageItem) mAdapter.getItem(position));
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


    private void deleteMessages(MessageItem item) {
        JsonArray array = new JsonArray();
        array.add(item.getMid());

        ApiHttpClient.getInstance().deleteNotice(SharePreferencesUtil.getInstance().readUser().getUid() + "", array, new ResultResponseHandler(getContext(), "请稍等") {

            @Override
            public void onResultSuccess(String result) {
                //更新接口数据
                mAdapter.remove(mAdapter.getItem(position));
                mAdapter.notifyItemRemoved(position);
            }
        });
    }
}
