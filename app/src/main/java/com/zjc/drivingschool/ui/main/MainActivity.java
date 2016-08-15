package com.zjc.drivingschool.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initMap();
    }

    private void initMap() {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        HealingMapFragment healingMapFragment = new HealingMapFragment();
        trans.add(R.id.main_map, healingMapFragment, "healingMapFragment").show(healingMapFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifyIsLogin();
    }

    /**
     * 验证是否登录
     */
    private void verifyIsLogin() {
        if (SharePreferencesUtil.getInstance().isLogin()) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        } else {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到登录
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    SharePreferencesUtil.getInstance().setLogin(true);
                }
            });
        }
        toolbar.setNavigationIcon(R.drawable.tab_icon_user_normal);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_action_account) {
            // Handle the camera action
        } else if (id == R.id.main_action_collect) {

        } else if (id == R.id.main_action_history) {

        } else if (id == R.id.main_action_notice) {

        } else if (id == R.id.main_action_more) {

        }
        drawer.closeDrawer(GravityCompat.START);
        SharePreferencesUtil.getInstance().setLogin(false);
        return true;
    }
}
