package com.zjc.drivingschool.ui.login;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.UserCode;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.db.parser.UserCodeParser;
import com.zjc.drivingschool.db.parser.UserInfoParser;
import com.zjc.drivingschool.utils.ConstantsParams;
import com.zjc.drivingschool.utils.MessageCountTimer;

import cn.jpush.android.api.JPushInterface;


/**
 * @author Z
 * @Filename LoginRegisterFragment.java
 * @Date 2015.10.25
 * @description 注册界面
 */
public class LoginRegisterFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private TextView tvCommit;
    private TextView tvCode;
    private EditText edtPhone;
    private EditText edtCode;
//    private EditText edtPawd;
//    private EditText edtConfirmPawd;

    private TextView tvAccept;
    private TextView tvYstk;

    private CheckBox cbAccept;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 得到布局id
     *
     * @return
     */
    @Override
    protected int inflateContentView() {
        return R.layout.login_register_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        tvAccept = (TextView) rootView.findViewById(R.id.login_main_frg_tv_accept);
        tvYstk = (TextView) rootView.findViewById(R.id.login_register_frg_tv_ystk);
        cbAccept = (CheckBox) rootView.findViewById(R.id.login_main_frg_cb_accept);

        tvCommit = (TextView) rootView.findViewById(R.id.login_register_frg_tv_commit);
        tvCode = (TextView) rootView.findViewById(R.id.login_register_frg_tv_code);
        edtPhone = (EditText) rootView.findViewById(R.id.login_register_frg_edtphone);
        edtCode = (EditText) rootView.findViewById(R.id.login_register_frg_edtcode);
//        edtPawd = (EditText) rootView.findViewById(R.id.login_register_frg_edtone);
//        edtConfirmPawd = (EditText) rootView.findViewById(R.id.login_register_frg_edttwo);

        tvCode.setOnClickListener(this);
        tvCommit.setOnClickListener(this);

        tvAccept.setOnClickListener(this);
        tvYstk.setOnClickListener(this);


        edtCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    checkPhoneCode();
                }
                return false;
            }
        });

        tvAccept.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvAccept.getPaint().setAntiAlias(true);//抗锯齿
        tvYstk.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvYstk.getPaint().setAntiAlias(true);//抗锯齿
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_register_frg_tv_code:
                if (!TextUtils.isEmpty(edtPhone.getEditableText().toString())
                        && Util.isMobileNO(edtPhone.getEditableText().toString().trim())) {
                    getSMSCode();
                    setTimeOut();
                } else if (TextUtils.isEmpty(edtPhone.getEditableText().toString())) {
                    Util.showCustomMsg("手机号码不能为空");
                } else {
                    Util.showCustomMsg("您输的手机号码有误");
                }
                break;
            case R.id.login_register_frg_tv_commit:
                checkPhoneCode();
                break;
            case R.id.login_main_frg_tv_accept:
                LoginCredittermsFragment loginCredittermsFragment = new LoginCredittermsFragment();
                replaceFrg(loginCredittermsFragment, null);
                break;
            case R.id.login_register_frg_tv_ystk:
                LoginCredittermsFragment loginCredittermsFragment1 = new LoginCredittermsFragment();
                replaceFrg(loginCredittermsFragment1, null);
                break;
        }
    }

    private void setTimeOut() {
        MessageCountTimer timeCount = new MessageCountTimer(tvCode, 0xfff30008, 0xff969696);//传入了文字颜色值
        timeCount.start();
    }

    public void getSMSCode() {
        ApiHttpClient.getInstance().getVerificationCode(edtPhone.getEditableText().toString(), ConstantsParams.USER_REGISTER, new ResultResponseHandler(getActivity(), "请稍等") {

            @Override
            public void onResultSuccess(String result) {
                UserCode userCode = new UserCodeParser().parseResultMessage(result);
                tvCode.setTag(userCode);
            }
        });
    }


    private void checkPhoneCode() {
        String phone = edtPhone.getEditableText().toString();
        final String code = edtCode.getEditableText().toString();
        if (!cbAccept.isChecked()) {
            Util.showCustomMsg("请勾选服务条款");
            return;
        }

        if (TextUtils.isEmpty(edtPhone.getEditableText().toString()) || TextUtils.isEmpty(edtCode.getEditableText().toString())) {
            Util.showCustomMsg("请输入完整信息");
            return;
        }
        if (tvCode.getTag() == null) {
            Util.showCustomMsg("验证码不正确，请重新获取验证码");
            return;
        } else {
            UserCode userCode = (UserCode) tvCode.getTag();
            if (!phone.equals(userCode.getPhone()) || !code.equals(userCode.getValcode())) {
                Util.showCustomMsg("验证码不正确，请重新获取验证码");
                return;
            }
        }

        ApiHttpClient.getInstance().userRegister(phone,code, new ResultResponseHandler(getActivity(), "请稍等") {
            @Override
            public void onResultSuccess(String result) {
                //注册之后，需要去登陆界面重新登录
                UserInfo userInfo = new UserInfoParser().parseResultMessage(result);
                SharePreferencesUtil.getInstance().savePhone(edtPhone.getEditableText().toString());
                SharePreferencesUtil.getInstance().savePwd(edtCode.getEditableText().toString());
                SharePreferencesUtil.getInstance().saveUser(userInfo);
                SharePreferencesUtil.getInstance().setLogin(true);
                setAlias();
                getActivity().finish();
            }
        });
    }

    public void setAlias() {
        //调用JPush API设置Alias
        if (JPushInterface.isPushStopped(getActivity())) {
            JPushInterface.resumePush(getActivity());
        }
        if (!TextUtils.isEmpty(SharePreferencesUtil.getInstance().readPhone())) {
            JPushInterface.setAlias(getActivity(), SharePreferencesUtil.getInstance().readPhone(), null);
        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.login_register);
    }
}
