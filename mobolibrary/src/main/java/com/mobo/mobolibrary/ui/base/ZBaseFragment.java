package com.mobo.mobolibrary.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mobo.mobolibrary.R;
import com.mobo.mobolibrary.ui.widget.empty.EmptyLayout;


/**
 * Created by Administrator on 2015/7/22.
 */
public abstract class ZBaseFragment extends Fragment {
    protected View rootView;
    private EmptyLayout emptyLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != rootView) {
            initToolBar();
        } else {
            rootView = onCreateChildView(inflater, container, savedInstanceState);
            layoutInit(inflater, container, savedInstanceState);
            initToolBar();
        }
        return rootView;
    }

    protected View onCreateChildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(inflateContentView(), container, false);
        return view;
    }

    protected void initToolBar() {
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
        if (getFragmentManager() == null) {
            return;
        }
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
        if (getFragmentManager() == null) {
            return;
        }
        FragmentTransaction trans = getFragmentTransaction();
        trans.addToBackStack(backStackTag);
        trans.replace(getParentLayout(), fragment).commit();
    }

    public void replaceFrgStateLoss(Fragment fragment, String backStackTag) {
        if (getFragmentManager() == null) {
            return;
        }
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
        if (getFragmentManager() == null) {
            return;
        }
        FragmentTransaction trans = getFragmentTransaction();
        trans.addToBackStack(backStackTag);
        trans.replace(getParentLayout(), fragment, frgTag).commit();
    }

    public FragmentTransaction getFragmentTransaction() {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.setCustomAnimations(R.anim.activity_open_enter_slide_in_left, R.anim.activity_open_exit_fade_back, R.anim.activity_close_enter_fade_forward, R.anim.activity_close_exit_slide_out_right);
        return trans;
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    public void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivity(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
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

    protected void setTitle(Toolbar toolbar, int res) {
        setTitle(toolbar, getResources().getString(res));
    }

    protected void setTitle(Toolbar toolbar, String name) {
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(name);
        //设置toolbar默认标题为空
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void setTitle(Toolbar toolbar, String name, int textSize) {
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(name);
        textView.setTextSize(textSize);
        //设置toolbar默认标题为空
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 初始化emptyView，并设置点击回调方法
     *
     * @param view
     */
    public void initEmptyLayout(View view) {
        emptyLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
        emptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestData();
            }
        });
    }

    /**
     * emptyView的监听回调方法
     * 一般用于加载失败情况下，点击再次加载
     */
    public void sendRequestData() {
    }

    public EmptyLayout getEmptyLayout() {
        return emptyLayout;
    }
}
