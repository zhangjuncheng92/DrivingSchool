package com.mobo.mobolibrary.ui.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mobo.mobolibrary.R;
import com.mobo.mobolibrary.ui.widget.empty.EmptyLayout;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Administrator on 2015/7/22.
 */
public abstract class ZBaseActivity extends AppCompatActivity {
    private EmptyLayout emptyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("isDestroy")) {
                boolean isDestroy = savedInstanceState.getBoolean("isDestroy");
                if (isDestroy) {
                    return;
                }
            }
        }
        initBaseView();
    }

    protected void initBaseView() {
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initStatusBar() {
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(R.color.main_title);
    }

    public void setTitle(Toolbar toolbar, int res) {
        setTitle(toolbar, getResources().getString(res));
    }

    public void setTitle(Toolbar toolbar, String name) {
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setText(name);
        //设置toolbar默认标题为空
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        int num = fm.getBackStackEntryCount();
        try {
            if (num > 1) {
                fm.popBackStack();
            } else {
                //退出程序
                this.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class OnBackPressClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            int num = fm.getBackStackEntryCount();
            try {
                if (num > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 初始化emptyView，并设置点击回调方法
     */
    public void initEmptyLayout() {
        emptyLayout = (EmptyLayout) findViewById(R.id.error_layout);
        emptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestData();
            }
        });
    }

    public EmptyLayout getEmptyLayout() {
        return emptyLayout;
    }

    /**
     * emptyView的监听回调方法
     */
    public void sendRequestData() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isDestroy", true);
    }
}
