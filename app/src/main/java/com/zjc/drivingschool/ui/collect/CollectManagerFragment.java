package com.zjc.drivingschool.ui.collect;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.TeacherCollectItem;
import com.zjc.drivingschool.db.parser.TeacherCollectListParser;
import com.zjc.drivingschool.db.response.TeacherCollectListResponse;
import com.zjc.drivingschool.ui.collect.adapter.CollectManagerAdapter;
import com.zjc.drivingschool.utils.ConstantsParams;

/**
 * @author Z
 * @Filename CollectManagerFragment.java
 * @Date 2016.08.30
 * @description 收藏界面
 */
public class CollectManagerFragment extends ZBaseToolBarFragment implements SwipeRefreshLayout.OnRefreshListener, ZBaseRecyclerViewAdapter.OnLoadMoreListener, ZBaseRecyclerViewAdapter.OnItemClickListener, ZBaseRecyclerViewAdapter.OnItemLongClickListener {
    private EasyRecyclerView mRecyclerView;
    private CollectManagerAdapter mAdapter;

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_collect);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.order_manager_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        initAdapter();
        findCollectList();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
        mRecyclerView.setRefreshListener(this);
    }

    private void initAdapter() {
        mAdapter = new CollectManagerAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mAdapter.setOnItemLongClickListener(this);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //跳转到详情
        TeacherCollectItem teacherCollectItem = (TeacherCollectItem) mAdapter.getItem(position);
        CollectDetailFragment collectDetailFragment = CollectDetailFragment.newInstance(teacherCollectItem);
        replaceFrg(collectDetailFragment, null);
    }

    @Override
    public void onRefresh() {
        ApiHttpClient.getInstance().getCollects(SharePreferencesUtil.getInstance().readUser().getUid(), ConstantsParams.PAGE_START, new ResultResponseHandler(getActivity(), mRecyclerView) {

            @Override
            public void onResultSuccess(String result) {
                TeacherCollectListResponse response = new TeacherCollectListParser().parseResultMessage(result);
                mAdapter.clear();
                mAdapter.addAll(response.getTcitems());
                isLoadFinish(response.getTcitems().size());
            }
        });
    }

    private void findCollectList() {
        ApiHttpClient.getInstance().getCollects(SharePreferencesUtil.getInstance().readUser().getUid(), ConstantsParams.PAGE_START, new ResultResponseHandler(getActivity(), getEmptyLayout()) {

            @Override
            public void onResultSuccess(String result) {
                TeacherCollectListResponse response = new TeacherCollectListParser().parseResultMessage(result);
                mAdapter.addAll(response.getTcitems());
                isLoadFinish(response.getTcitems().size());
            }
        });
    }

    @Override
    public void onLoadMore() {
        int start = mAdapter.getCount();
        ApiHttpClient.getInstance().getCollects(SharePreferencesUtil.getInstance().readUser().getUid(), start, new ResultResponseHandler(getActivity()) {

            @Override
            public void onResultSuccess(String result) {
                TeacherCollectListResponse response = new TeacherCollectListParser().parseResultMessage(result);
                mAdapter.addAll(response.getTcitems());
                isLoadFinish(response.getTcitems().size());
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
    public void onItemLongClick(View view, int position) {
        mRecyclerView.setTag(position);
        showSureInfoDialog();
    }

    private void showSureInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("您确定取消对此教练的收藏吗？");
        builder.setNegativeButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        cancelCollectTeacher();
                    }
                });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.show();
    }

    private void cancelCollectTeacher() {
        final int position = (int) mRecyclerView.getTag();
        TeacherCollectItem teacherCollectItem = (TeacherCollectItem) mAdapter.getItem(position);
        ApiHttpClient.getInstance().cancelCollectTeacher(SharePreferencesUtil.getInstance().readUser().getUid(), teacherCollectItem.getTcid(), new ResultResponseHandler(getActivity(), "正在取消收藏") {

            @Override
            public void onResultSuccess(String result) {
                mAdapter.remove(mAdapter.getItem(position));
                mAdapter.notifyItemRemoved(position);
            }
        });
    }
}
