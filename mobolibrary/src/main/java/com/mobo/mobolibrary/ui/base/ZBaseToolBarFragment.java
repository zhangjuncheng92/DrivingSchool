package com.mobo.mobolibrary.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mobo.mobolibrary.R;


/**
 * Created by Administrator on 2015/7/22.
 */
public abstract class ZBaseToolBarFragment extends ZBaseFragment {
    protected Toolbar mToolbar;

    protected abstract void setTitle();

    @Nullable
    @Override
    public View onCreateChildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootChildView = (ViewGroup) inflater.inflate(R.layout.base_toolbar_fragment, container, false);
        LinearLayout containerChild = (LinearLayout) rootChildView.findViewById(R.id.base_container);
        View child = inflater.inflate(inflateContentView(), containerChild, false);
        containerChild.addView(child);

        mToolbar = (Toolbar) rootChildView.findViewById(R.id.toolbar);
        return rootChildView;
    }

    @Override
    protected void initToolBar() {
        mToolbar.setNavigationIcon(R.drawable.comm_back);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        OnBackPressClick onBackPressClick = new OnBackPressClick();
        mToolbar.setNavigationOnClickListener(onBackPressClick);
        setTitle();
    }
}
