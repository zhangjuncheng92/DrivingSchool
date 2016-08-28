package com.zjc.drivingschool.ui.account;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobo.mobolibrary.logs.Logs;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.eventbus.pay.PayAliAccountResultEvent;
import com.zjc.drivingschool.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import cn.beecloud.BCPay;
import cn.beecloud.async.BCCallback;
import cn.beecloud.async.BCResult;
import cn.beecloud.entity.BCPayResult;
import de.greenrobot.event.EventBus;


/**
 * @author Z
 * @Filename AccountRechargeFragment.java
 * @Date 2015.11.13
 * @description 个人中心-账户充值
 */
public class AccountRechargeFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private TextView tvAli;
    private TextView tvWeChat;
    private double sum;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static AccountRechargeFragment newInstance(double bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        AccountRechargeFragment fragment = new AccountRechargeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            sum = (double) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected int inflateContentView() {
        return R.layout.account_recharge_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        tvAli = (TextView) rootView.findViewById(R.id.account_recharge_frg_tv_ali);
        tvWeChat = (TextView) rootView.findViewById(R.id.account_recharge_frg_tv_wechat);

        tvAli.setOnClickListener(this);
        tvWeChat.setOnClickListener(this);
    }


    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.account_recharge_title);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.account_recharge_frg_tv_ali) {
            //提交支付方式
            toALIPay();
        } else if (i == R.id.account_recharge_frg_tv_wechat) {
            //提交支付方式
            toWXPay();
        }
    }

    private void toALIPay() {
        Map<String, String> mapOptional = new HashMap<String, String>();
        mapOptional.put("客户端", "安卓");
        mapOptional.put("consumptioncode", "consumptionCode");
        mapOptional.put("money", "2");

        BCPay.getInstance(getActivity()).reqAliPaymentAsync(
                "安卓支付宝支付测试",
                (int) (sum * 100),
                System.currentTimeMillis() + "",
                mapOptional,
                bcCallback);
    }


    private void toWXPay() {
        //对于微信支付, 手机内存太小会有OutOfResourcesException造成的卡顿, 以致无法完成支付
        //这个是微信自身存在的问题
        Map<String, String> mapOptional = new HashMap<>();

        mapOptional.put("testkey1", "测试value值1");

        if (BCPay.isWXAppInstalledAndSupported() && BCPay.isWXPaySupported()) {

            BCPay.getInstance(getActivity()).reqWXPaymentAsync(
                    "安卓微信支付测试",               //订单标题
                    (int) (sum * 100),//订单金额(分)
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
                EventBus.getDefault().post(new PayAliAccountResultEvent());
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


}
