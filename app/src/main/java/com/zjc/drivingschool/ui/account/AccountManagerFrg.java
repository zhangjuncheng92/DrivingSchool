package com.zjc.drivingschool.ui.account;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.AccountBalance;
import com.zjc.drivingschool.db.parser.AccountBalanceParser;
import com.zjc.drivingschool.eventbus.pay.PayAliAccountResultEvent;
import com.zjc.drivingschool.eventbus.pay.PayWXAccountResultEvent;

import java.util.ArrayList;
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
    private EditText edtFee;
    private Button tvRecharge;
    private Button tvRechargeHistory;
    private AccountBalance accountBalance;

    private RadioButton rb100;//数字代表充值的金额
    private RadioButton rb200;
    private RadioButton rb500;
    private RadioButton rb1000;
    private RadioButton rb2000;
    private RadioButton rb5000;
    private List<RadioButton> radioButtonList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        getAccount();
    }

    private void initView() {
        tvAccount = (TextView) rootView.findViewById(R.id.account_manager_frg_tv_total);
        tvRecharge = (Button) rootView.findViewById(R.id.account_frg_recharge);
        tvRechargeHistory = (Button) rootView.findViewById(R.id.account_frg_withdraw);
        edtFee = (EditText) rootView.findViewById(R.id.account_manager_frg_edt_fee);

        rb100 = (RadioButton) rootView.findViewById(R.id.account_manager_frg_100);
        rb200 = (RadioButton) rootView.findViewById(R.id.account_manager_frg_200);
        rb500 = (RadioButton) rootView.findViewById(R.id.account_manager_frg_500);
        rb1000 = (RadioButton) rootView.findViewById(R.id.account_manager_frg_1000);
        rb2000 = (RadioButton) rootView.findViewById(R.id.account_manager_frg_2000);
        rb5000 = (RadioButton) rootView.findViewById(R.id.account_manager_frg_5000);

        radioButtonList.add(rb100);
        radioButtonList.add(rb200);
        radioButtonList.add(rb500);
        radioButtonList.add(rb1000);
        radioButtonList.add(rb2000);
        radioButtonList.add(rb5000);
    }

    private void setListener() {
        tvRecharge.setOnClickListener(this);
        tvRechargeHistory.setOnClickListener(this);

        /**保证只能点击一个*/
        for (int i = 0; i < radioButtonList.size(); i++) {
            final int finalI = i;
            radioButtonList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (radioButtonList.get(finalI).isChecked()) {
                        toggle(compoundButton.getId());
                    }
                }
            });
        }
    }

    /**
     * 保证只能点击一个
     */
    private void toggle(int viewId) {
        for (int i = 0; i < radioButtonList.size(); i++) {
            if (radioButtonList.get(i).getId() != viewId) {
                radioButtonList.get(i).setChecked(false);
            }
        }
    }

    private int getSum() {
        int sum = 0;
        for (int i = 0; i < radioButtonList.size(); i++) {
            if (radioButtonList.get(i).isChecked()) {
                sum = Integer.parseInt(radioButtonList.get(i).getText().toString());
            }
        }
        sum = sum + Integer.parseInt(edtFee.getEditableText().toString());
        return sum;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.account_frg_recharge) {
            //支付类型选择界面
            int sum = getSum();
            AccountRechargeFragment fragment = AccountRechargeFragment.newInstance(sum);
            replaceFrg(fragment, null);
        } else if (i == R.id.account_frg_withdraw) {
        }
    }

    private void getAccount() {
        ApiHttpClient.getInstance().getMyAccount(SharePreferencesUtil.getInstance().readUser().getUid(), new ResultResponseHandler(getActivity(), getEmptyLayout()) {

            @Override
            public void onResultSuccess(String result) {
                accountBalance = new AccountBalanceParser().parseResultMessage(result);
                tvAccount.setText(accountBalance.getBalance() + "");
            }
        });
    }

    @Override
    public void sendRequestData() {
        getAccount();
    }


    public void onEventMainThread(PayAliAccountResultEvent event) {
        getAccount();
    }

    public void onEventMainThread(PayWXAccountResultEvent event) {
        //接收微信支付成功消息，刷新界面
        getAccount();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
