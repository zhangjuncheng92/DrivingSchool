package com.mobo.mobolibrary.ui.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.mobo.mobolibrary.R;


/**
 * Created by Administrator on 2015/7/22.
 */
public abstract class ZBaseFragmentDialog extends DialogFragment {
    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != rootView) {
        } else {
            rootView = onCreateChildView(inflater, container, savedInstanceState);
            getDialog().getWindow().setBackgroundDrawable(new
                    ColorDrawable(Color.TRANSPARENT));
            layoutInit(inflater, container, savedInstanceState);
        }
        return rootView;
    }

    protected View onCreateChildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(inflateContentView(), container, false);
        return view;
    }

    /**
     * 得到布局id
     *
     * @return
     */
    protected abstract int inflateContentView();

    protected abstract void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public class OnBackPressClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            int num = fm.getBackStackEntryCount();
            try {
                if (num > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    getActivity().finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 替换fragment
     *
     * @param fragment 要显示的fragment
     */
    public void replaceFrg(Fragment fragment) {
        FragmentTransaction trans = getFragmentTransaction();
        trans.replace(getParentLayout(), fragment).commit();
    }

    /**
     * 替换fragment并添加到退回栈
     *
     * @param fragment     要显示的fragment
     * @param backStackTag 将fragment添加退回栈中
     */
    public void replaceFrg(Fragment fragment, String backStackTag) {
        FragmentTransaction trans = getFragmentTransaction();
        trans.addToBackStack(backStackTag);
        trans.replace(getParentLayout(), fragment).commit();
    }

    public void replaceFrgStateLoss(Fragment fragment, String backStackTag) {
        FragmentTransaction trans = getFragmentTransaction();
        trans.addToBackStack(backStackTag);
        trans.replace(getParentLayout(), fragment).commitAllowingStateLoss();
    }

    /**
     * 替换fragment并设置tag，添加到退回栈中
     *
     * @param fragment     要显示的fragment
     * @param backStackTag 将fragment添加退回栈中
     * @param frgTag       fragment的tag
     */
    public void replaceFrg(Fragment fragment, String backStackTag, String frgTag) {
        FragmentTransaction trans = getFragmentTransaction();
        trans.addToBackStack(backStackTag);
        trans.replace(getParentLayout(), fragment, frgTag).commit();
    }

    private FragmentTransaction getFragmentTransaction() {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.setCustomAnimations(R.anim.activity_open_enter_slide_in_left, R.anim.activity_open_exit_fade_back, R.anim.activity_close_enter_fade_forward, R.anim.activity_close_exit_slide_out_right);
        return trans;
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    private int getParentLayout() {
        return R.id.root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //虚拟键盘隐藏 判断view是否为空
        View view = this.getView();
        if (view != null) {
            //隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
