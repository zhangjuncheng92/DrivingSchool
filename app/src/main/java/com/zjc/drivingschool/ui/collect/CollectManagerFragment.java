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
import com.zjc.drivingschool.ui.collect.adapter.CollectManagerAdapter;
import com.zjc.drivingschool.ui.order.adapter.OrderManagerAdapter;

/**
 * Created by Administrator on 2016/8/18.
 */
public class CollectManagerFragment extends ZBaseToolBarFragment implements SwipeRefreshLayout.OnRefreshListener, ZBaseRecyclerViewAdapter.OnLoadMoreListener, ZBaseRecyclerViewAdapter.OnItemClickListener {
    private EasyRecyclerView mRecyclerView;
    private CollectManagerAdapter mAdapter;

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "我的关注");
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

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
