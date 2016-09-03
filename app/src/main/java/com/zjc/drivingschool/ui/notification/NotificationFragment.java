package com.zjc.drivingschool.ui.notification;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.mobo.mobolibrary.ui.widget.empty.EmptyLayout;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.MessageItem;
import com.zjc.drivingschool.db.parser.MessageListResponseParser;
import com.zjc.drivingschool.db.response.MessageListResponse;
import com.zjc.drivingschool.eventbus.JPushNotificationStateEvent;
import com.zjc.drivingschool.eventbus.JpushNotificationEvent;
import com.zjc.drivingschool.ui.login.LoginActivity;
import com.zjc.drivingschool.ui.notification.adapter.NotificationsItemAdapter;
import com.zjc.drivingschool.utils.Constants;
import com.zjc.drivingschool.utils.ConstantsParams;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename NotificationFragment.java
 * @Date 2016.06.13
 * @description 转诊单通知
 */
public class NotificationFragment extends ZBaseFragment implements ZBaseRecyclerViewAdapter.OnItemClickListener, ZBaseRecyclerViewAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private String noticeStatus;
    private NotificationsItemAdapter mAdapter;
    private EasyRecyclerView mRecyclerView;
    private MessageItem messageItem;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static NotificationFragment newInstance(String bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            noticeStatus = (String) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected int inflateContentView() {
        return R.layout.comm_recycler_view_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        initAdapter();
        getNotice(getEmptyLayout());
    }

    private void initView() {
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .sizeResId(R.dimen.comm_divider_line)
                .colorResId(R.color.transparent)
                .build());
        mRecyclerView.setRefreshListener(this);
    }

    private void initAdapter() {
        mAdapter = new NotificationsItemAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        ApiHttpClient.getInstance().getMessageByTags(SharePreferencesUtil.getInstance().readUser().getUid(), ConstantsParams.PAGE_START, noticeStatus, new ResultResponseHandler(getActivity(), mRecyclerView) {

            @Override
            public void onResultSuccess(String result) {
                MessageListResponse orderListResponse = new MessageListResponseParser().parseResultMessage(result);
                mAdapter.clear();
                mAdapter.addAll(orderListResponse.getMsgitems());
                isLoadFinish(orderListResponse.getMsgitems().size());
            }
        });
    }

    private void getNotice(EmptyLayout emptyLayout) {
        ApiHttpClient.getInstance().getMessageByTags(SharePreferencesUtil.getInstance().readUser().getUid(), ConstantsParams.PAGE_START, noticeStatus, new ResultResponseHandler(getActivity(), emptyLayout) {
            @Override
            public void onResultSuccess(String result) {
                MessageListResponse orderListResponse = new MessageListResponseParser().parseResultMessage(result);
                mAdapter.addAll(orderListResponse.getMsgitems());
                isLoadFinish(orderListResponse.getMsgitems().size());
            }
        });
    }

    @Override
    public void onLoadMore() {
        int start = mAdapter.getCount();
        ApiHttpClient.getInstance().getMessageByTags(SharePreferencesUtil.getInstance().readUser().getUid(), start, noticeStatus, new ResultResponseHandler(getActivity()) {

            @Override
            public void onResultSuccess(String result) {
                MessageListResponse orderListResponse = new MessageListResponseParser().parseResultMessage(result);
                mAdapter.addAll(orderListResponse.getMsgitems());
                isLoadFinish(orderListResponse.getMsgitems().size());
            }
        });
    }

    /**
     * 加载完成
     */
    public boolean isLoadFinish(int size) {
        if (size < ConstantsParams.PAGE_SIZE) {
            mAdapter.stopMore();
            mAdapter.setNoMore(R.layout.view_nomore);
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {
        Gson gson = new Gson();
        messageItem = ((MessageItem) mAdapter.getItem(position));


        //如果消息未读，则修改状态
        if (!messageItem.getIsread()) {
            updateMessagesReadState(messageItem);
        }
    }

    @Override
    public void sendRequestData() {
        if (SharePreferencesUtil.getInstance().isLogin()) {
            getNotice(getEmptyLayout());
        } else {
            startActivity(LoginActivity.class);
        }
    }

    /**
     * 收到消息通知广播
     *
     * @param event
     */
    public void onEvent(JpushNotificationEvent event) {
        if (SharePreferencesUtil.getInstance().isLogin()) {
            if (mAdapter.getCount() > 0) {
                getEmptyLayout().setVisibility(View.GONE);
                onRefresh();
            } else {
                getNotice(getEmptyLayout());
            }
        }
    }

    /**
     * 更新消息状态为已读
     *
     * @param item
     */
    private void updateMessagesReadState(final MessageItem item) {
//        ApiHttpClient.getInstance().updateMessagesReadState(SharePreferencesUtil.getInstance().readUser().getId(), item.getMid(), new ResultResponseHandler(getActivity(), "加载详情") {
//            @Override
//            public void onResultSuccess(String result) {
//
//                EventBus.getDefault().post(new JPushNotificationStateEvent(item));
//            }
//        });
    }

    /**
     * 更新消息状态广播
     *
     * @param event
     */
    public void onEventMainThread(JPushNotificationStateEvent event) {
//        int count = mAdapter.getCount();
//        for (int i = 0; i < count; i++) {
//            JPushNotification jPushNotification = (JPushNotification) mAdapter.getItem(i);
//            if (jPushNotification.getId() == event.getJPushNotification().getId()) {
//                jPushNotification.setState(ConstantsParams.NOTIFICATION_YES);
//                mAdapter.notifyItemChanged(i);
//                break;
//            }
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().unregister(mAdapter);
    }

    /**
     * 获取上转转诊单详情
     *
     * @param id
     */
    private void getReferralUpById(String id) {

//        ApiHttpClient.getInstance().getReferralUpById(id, new ResultResponseHandlerOfDialog<HmsUpReferral>(getActivity(), "请稍等", new HmsUpReferralParser()) {
//
//            @Override
//            public void onResultSuccess(List<HmsUpReferral> result) {
//                //我的上转接诊
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constants.ARGUMENT, result.get(0));
//                startActivity(ReceptionDetailActivity.class, bundle);
//            }
//        });
    }
}
