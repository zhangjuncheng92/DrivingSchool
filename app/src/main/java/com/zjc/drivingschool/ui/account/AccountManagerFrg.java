package com.zjc.drivingschool.ui.account;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.parser.AccountParser;
import com.zjc.drivingschool.eventbus.pay.PayAliAccountResultEvent;
import com.zjc.drivingschool.eventbus.pay.PayWXAccountResultEvent;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename AccountManagerFrg.java
 * @Date 2015.11.13
 * @description 我的账户 我的账户管理界面
 */
public class AccountManagerFrg extends ZBaseToolBarFragment implements View.OnClickListener {
    private TextView tvAccount;
    private Button tvRecharge;
    private Button tvRechargeHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_account_cost, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.account_cost) {
//                AccountPayHistoryFrg fragment = new AccountPayHistoryFrg();
//                replaceFrg(fragment, null);
            }
            return true;
        }
    };

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_account);
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.account_manager_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initEmptyLayout(rootView);
        initView();
        setListener();
//        getMyAccount();
    }

    private void initView() {
        tvAccount = (TextView) rootView.findViewById(R.id.account_paytype_frg_rest);
        tvRecharge = (Button) rootView.findViewById(R.id.account_frg_recharge);
        tvRechargeHistory = (Button) rootView.findViewById(R.id.account_frg_withdraw);
    }

    private void setListener() {
        tvRecharge.setOnClickListener(this);
        tvRechargeHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.account_frg_recharge) {
            //充值界面
//            AccountRechargeFragment fragment = new AccountRechargeFragment();
//            replaceFrg(fragment, null);
        } else if (i == R.id.account_frg_withdraw) {
            //提现界面
//            AccountWithDrawFragment fragment = AccountWithDrawFragment.newInstance(Double.valueOf(tvAccount.getText().toString()));
//            replaceFrg(fragment, null);
        }
    }

    private void getMyAccount() {
        ApiHttpClient.getInstance().getMyAccount(SharePreferencesUtil.getInstance().readUser().getId(), new ResultResponseHandler(getActivity(), getEmptyLayout(), new AccountParser()) {

            @Override
            public void onResultSuccess(String result) {
//                tvAccount.setText(result.get(0).getTotal() + "");
            }
        });
    }

    @Override
    public void sendRequestData() {
        getMyAccount();
    }

    public void onEventMainThread(PayAliAccountResultEvent event) {

    }

    public void onEventMainThread(PayWXAccountResultEvent event) {
        //接收微信支付成功消息，刷新界面
        getMyAccount();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
