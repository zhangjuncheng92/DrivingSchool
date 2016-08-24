package com.zjc.drivingschool.ui.collect;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
 * Created by Administrator on 2016/8/18.
 */
public class CollectManagerFragment extends ZBaseToolBarFragment implements SwipeRefreshLayout.OnRefreshListener, ZBaseRecyclerViewAdapter.OnLoadMoreListener, ZBaseRecyclerViewAdapter.OnItemClickListener {
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
}
