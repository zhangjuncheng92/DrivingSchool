package com.zjc.drivingschool.ui.school;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mobo.mobolibrary.logs.Logs;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.ui.divideritem.HorizontalDividerItemDecoration;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.model.School;
import com.zjc.drivingschool.ui.study.adapter.StudyOrderAdapter;
import com.zjc.drivingschool.utils.Constants;

/**
 * @author Z
 * @Filename SchoolFragment.java
 * @Date 2016.06.26
 * @description 驾校主页
 */
public class SchoolFragment extends ZBaseToolBarFragment implements ZBaseRecyclerViewAdapter.OnItemClickListener {
    private School school;
    private EasyRecyclerView mRecyclerView;
    private StudyOrderAdapter mAdapter;

    /**
     * 传入需要的参数，设置给arguments
     *
     * @param bean
     */
    public static SchoolFragment newInstance(School bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        SchoolFragment fragment = new SchoolFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            school = (School) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_school);
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
        if (school != null) {
            getSchoolInfo();
        }
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (EasyRecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.comm_divider)
                .sizeResId(R.dimen.comm_divider_line)
                .build());
    }

    private void initAdapter() {
        mAdapter = new StudyOrderAdapter(getActivity());
        mAdapter.setOnItemClickLitener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public void getSchoolInfo() {
        ApiHttpClient.getInstance().getSchoolInfo(school.getPushid(), school.getSchoolid(), new ResultResponseHandler(getActivity(), "请稍等") {

            @Override
            public void onResultSuccess(String result) {
                Logs.i(result);
            }
        });
    }
}
