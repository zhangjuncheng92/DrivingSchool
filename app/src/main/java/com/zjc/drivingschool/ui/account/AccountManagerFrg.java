package com.zjc.drivingschool.ui.account;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobo.mobolibrary.logs.Logs;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.AccountBalance;
import com.zjc.drivingschool.db.parser.AccountBalanceParser;
import com.zjc.drivingschool.eventbus.pay.PayAliAccountResultEvent;
import com.zjc.drivingschool.eventbus.pay.PayWXAccountResultEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.beecloud.BCPay;
import cn.beecloud.async.BCCallback;
import cn.beecloud.async.BCResult;
import cn.beecloud.entity.BCPayResult;
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
        getAccount();
    }

    private void initView() {
        tvAccount = (TextView) rootView.findViewById(R.id.account_manager_frg_tv_total);
        tvRecharge = (Button) rootView.findViewById(R.id.account_frg_recharge);
        tvRechargeHistory = (Button) rootView.findViewById(R.id.account_frg_withdraw);

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

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.account_frg_recharge) {
            //充值界面
//            AccountRechargeFragment fragment = new AccountRechargeFragment();
//            replaceFrg(fragment, null);
            toWXPay();
        } else if (i == R.id.account_frg_withdraw) {
            //提现界面
//            AccountWithDrawFragment fragment = AccountWithDrawFragment.newInstance(Double.valueOf(tvAccount.getText().toString()));
//            replaceFrg(fragment, null);
        }
    }

    private void getAccount() {
        ApiHttpClient.getInstance().getMyAccount(SharePreferencesUtil.getInstance().readUser().getUid(), new ResultResponseHandler(getActivity(), getEmptyLayout()) {

            @Override
            public void onResultSuccess(String result) {
                AccountBalance balance = new AccountBalanceParser().parseResultMessage(result);
                tvAccount.setText(balance.getBalance() + "");
            }
        });
    }

    @Override
    public void sendRequestData() {
        getAccount();
    }

    private void toWXPay() {
        //对于微信支付, 手机内存太小会有OutOfResourcesException造成的卡顿, 以致无法完成支付
        //这个是微信自身存在的问题
        Map<String, String> mapOptional = new HashMap<>();

        mapOptional.put("testkey1", "测试value值1");

        if (BCPay.isWXAppInstalledAndSupported() && BCPay.isWXPaySupported()) {

            BCPay.getInstance(getActivity()).reqWXPaymentAsync(
                    "安卓微信支付测试",               //订单标题
                    1,                           //订单金额(分)
                    System.currentTimeMillis() + "",  //订单流水号
                    mapOptional,            //扩展参数(可以null)
                    bcCallback);            //支付完成后回调入口
        } else {
            Util.showCustomMsg("您尚未安装微信或者安装的微信版本不支持");
        }
    }

    private String toastMsg;
    //支付结果返回入口
    BCCallback bcCallback = new BCCallback() {
        @Override
        public void done(final BCResult bcResult) {
            final BCPayResult bcPayResult = (BCPayResult) bcResult;
            //此处关闭loading界面
//            loadingDialog.dismiss();

            //根据你自己的需求处理支付结果
            String result = bcPayResult.getResult();

            /*
              注意！
              所有支付渠道建议以服务端的状态金额为准，此处返回的RESULT_SUCCESS仅仅代表手机端支付成功
            */
            Message msg = mHandler.obtainMessage();
            //单纯的显示支付结果
            msg.what = 2;
            if (result.equals(BCPayResult.RESULT_SUCCESS)) {
                toastMsg = "用户支付成功";
            } else if (result.equals(BCPayResult.RESULT_CANCEL))
                toastMsg = "用户取消支付";
            else if (result.equals(BCPayResult.RESULT_FAIL)) {
                toastMsg = "支付失败, 原因: " + bcPayResult.getErrCode() +
                        " # " + bcPayResult.getErrMsg() +
                        " # " + bcPayResult.getDetailInfo();

                /**
                 * 你发布的项目中不应该出现如下错误，此处由于支付宝政策原因，
                 * 不再提供支付宝支付的测试功能，所以给出提示说明
                 */
                if (bcPayResult.getErrMsg().equals("PAY_FACTOR_NOT_SET") &&
                        bcPayResult.getDetailInfo().startsWith("支付宝参数")) {
                    toastMsg = "支付失败：由于支付宝政策原因，故不再提供支付宝支付的测试功能，给您带来的不便，敬请谅解";
                }

                /**
                 * 以下是正常流程，请按需处理失败信息
                 */
                Logs.i(toastMsg);

                if (bcPayResult.getErrMsg().equals(BCPayResult.FAIL_PLUGIN_NOT_INSTALLED)) {
                    //银联需要重新安装控件
                    msg.what = 1;
                }

            } else if (result.equals(BCPayResult.RESULT_UNKNOWN)) {
                //可能出现在支付宝8000返回状态
                toastMsg = "订单状态未知";
            } else {
                toastMsg = "invalid return";
            }

            mHandler.sendMessage(msg);


            if (bcPayResult.getId() != null) {
                //你可以把这个id存到你的订单中，下次直接通过这个id查询订单
//                Log.w(TAG, "bill id retrieved : " + bcPayResult.getId());

//                //根据ID查询，此处只是演示如何通过id查询订单，并非支付必要部分
//                getBillInfoByID(bcPayResult.getId());
            }

        }
    };


    // Defines a Handler object that's attached to the UI thread.
    // 通过Handler.Callback()可消除内存泄漏警告
    private Handler mHandler = new Handler(new Handler.Callback() {
        /**
         * Callback interface you can use when instantiating a Handler to avoid
         * having to implement your own subclass of Handler.
         *
         * handleMessage() defines the operations to perform when
         * the Handler receives a new Message to process.
         */
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    });

    public void onEventMainThread(PayAliAccountResultEvent event) {

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
