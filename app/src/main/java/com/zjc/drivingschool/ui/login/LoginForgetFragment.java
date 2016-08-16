package com.zjc.drivingschool.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

import java.util.List;

/**
 * @author Z
 * @Filename LoginForgetFragment.java
 * @Date 2015-08-13
 * @description 忘记密码界面
 */
public class LoginForgetFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private TextView tvCommit;
    private TextView tvCode;
    private EditText edtPhone;
    private EditText edtCode;
    private EditText edtPawd;
    private EditText edtConfirmPawd;

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
        return R.layout.login_forget_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        setListener();
    }

    private void initView() {

        tvCommit = (TextView) rootView.findViewById(R.id.login_forget_frg_tvcommit);
        tvCode = (TextView) rootView.findViewById(R.id.login_forget_frg_tvcode);
        edtPhone = (EditText) rootView.findViewById(R.id.login_forget_frg_edtphone);
        edtCode = (EditText) rootView.findViewById(R.id.login_forget_frg_edtcode);
        edtPawd = (EditText) rootView.findViewById(R.id.login_forget_frg_edtone);
        edtConfirmPawd = (EditText) rootView.findViewById(R.id.login_forget_frg_edttwo);
        if (!TextUtils.isEmpty(SharePreferencesUtil.getInstance().readPhone())) {
            edtPhone.setText(SharePreferencesUtil.getInstance().readPhone());
        }

    }

    private void setListener() {
        tvCommit.setOnClickListener(this);
        tvCode.setOnClickListener(this);
        edtConfirmPawd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    checkPhoneCode();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_forget_frg_tvcode:
                String phone = edtPhone.getEditableText().toString().trim();
                if (Util.isMobileNO(phone)) {
                    getSMSCode();
                    setTimeOut();
                } else if (TextUtils.isEmpty(edtPhone.getEditableText().toString())) {
                    Util.showCustomMsg("手机号码不能为空");
                } else {
                    Util.showCustomMsg("您输的手机号码有误");
                }
                break;
            case R.id.login_forget_frg_tvcommit:
                checkPhoneCode();
                break;

        }
    }

    public void getSMSCode() {
        ApiHttpClient.getInstance().getVerificationCode(edtPhone.getEditableText().toString(), ConstantsParams.USER_FORGET, new ResultResponseHandler(getActivity(), "请稍等") {

            @Override
            public void onResultSuccess(String result) {
                UserCode userCode = new UserCodeParser().parseResultMessage(result);
                tvCode.setTag(userCode);
                Util.showCustomMsg("获取成功请注意查收");
            }

        });
    }

    private void setTimeOut() {
        MessageCountTimer timeCount = new MessageCountTimer(tvCode, 0xfff30008, 0xff969696);//传入了文字颜色值
        timeCount.start();
    }

    private void checkPhoneCode() {
        String phone = edtPhone.getEditableText().toString();
        String code = edtCode.getEditableText().toString();

        if (TextUtils.isEmpty(edtPhone.getEditableText().toString())
                || TextUtils.isEmpty(edtPawd.getEditableText().toString())
                || TextUtils.isEmpty(edtConfirmPawd.getEditableText().toString())
                || TextUtils.isEmpty(edtCode.getEditableText().toString())) {

            Util.showCustomMsg("请输入完整信息");
            return;
        }
        if (tvCode.getTag() == null) {
            Util.showCustomMsg("验证码不正确，请重新获取验证码");
            return;
        } else {
            UserCode userCode = (UserCode) tvCode.getTag();
            if (!phone.equals(userCode.getPhone()) || !code.equals(userCode.getCode())) {
                Util.showCustomMsg("验证码不正确，请重新获取验证码");
                return;
            }
        }
        if (!edtPawd.getEditableText().toString().equals(edtConfirmPawd.getEditableText().toString())) {
            Util.showCustomMsg(getActivity(), "密码不一致");
            return;
        }
        if (edtPawd.getEditableText().toString().length() < 6) {
            Util.showCustomMsg("密码长度不可小于6位");
            return;
        }
        if (edtPawd.getEditableText().toString().length() > 18) {
            Util.showCustomMsg("密码长度大于18位");
            return;
        }

        ApiHttpClient.getInstance().forgetPwd(phone, edtConfirmPawd.getEditableText().toString(), new ResultResponseHandler(getActivity(), "请稍等") {
            @Override
            public void onResultSuccess(String result) {
                Util.showCustomMsg("修改成功");
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.login_update_pwd);
    }
}
