package com.zjc.drivingschool.ui.learn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alertdialogpro.AlertDialogPro;
import com.bigkoo.pickerview.TimePopupWindow;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.UtilDate;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.OrderItem;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.eventbus.pay.PayAliAccountResultEvent;

import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename LearnApplyFragment.java
 * @Date 2016.06.26
 * @description 学车申请
 */
public class LearnApplyFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private EditText edtName;
    private EditText edtIdCard;
    private EditText edtPhone;
    private EditText edtMedical;
    private EditText edtAge;
    private EditText edtPlace;
    private TextView tvAddress;
    private EditText etPatient;


    private Spinner spSex;
    private TextView tvCommunity;
    private TextView tvBirthday;

    private RelativeLayout mRelayCommunity;
    private RelativeLayout mRelayBirthday;

    private OrderItem orderItem;

    private TimePopupWindow birthOptions;

    private static int ID_CARD_NUM = 18;
    private static int ID_CARD_NUM_OLD = 15;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_learn);
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_hierarchical_create, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.menu_action_create) {
                //新建患者
                showSureInfoDialog();
            }
            return true;
        }
    };


    @Override
    protected int inflateContentView() {
        return R.layout.learn_apply_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        initSpSex();
        initBirthOptions();
        orderItem = new OrderItem();
    }

    private void initView() {
        edtName = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_name);
        edtPhone = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_phone);
        spSex = (Spinner) rootView.findViewById(R.id.hierarchical_create_resident_frg_sp_sex);

        edtAge = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_age);
        tvAddress = (TextView) rootView.findViewById(R.id.hierarchical_create_resident_frg_tv_address);
        tvBirthday = (TextView) rootView.findViewById(R.id.hierarchical_create_resident_frg_tv_birthday);

        mRelayBirthday = (RelativeLayout) rootView.findViewById(R.id.hierarchical_create_resident_frg_rl_birthday);

        mRelayBirthday.setOnClickListener(this);
    }

    private void initSpSex() {
        String[] list = getResources().getStringArray(R.array.referral_sex);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.referral_spinner_item, list);
        adapter.setDropDownViewResource(R.layout.referral_spinner_dropdown_item);
        spSex.setAdapter(adapter);
    }

    private void initBirthOptions() {
        birthOptions = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
        birthOptions.setRange(1900, 2050);
        birthOptions.setTime(new Date());

        birthOptions.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                if (Util.getAge(date) < 0) {
                    Util.showCustomMsg(R.string.login_register_age_small);
                    return;
                }
                tvBirthday.setText(UtilDate.formatDate(date) + "");
                edtAge.setText(Util.getAge(date) + "");
            }
        });
    }


    @Override
    public void onClick(View v) {
        UserInfo userInfo = SharePreferencesUtil.getInstance().readUser();
        int i = v.getId();
        if (i == R.id.hierarchical_create_resident_frg_rl_birthday) {
            birthOptions.showAtLocation(mRelayCommunity, Gravity.BOTTOM, 0, 0);
        }
    }

    private void showSureInfoDialog() {
        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getActivity());
        builder.setTitle("温馨提示");
        builder.setMessage("您确认所填患者信息？");
        builder.setNegativeButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        createResident();
                    }
                });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.show();
    }

    private void createResident() {
        //必填
        String name = edtName.getEditableText().toString().trim();
        String idCard = edtIdCard.getEditableText().toString().trim();
        String phone = edtPhone.getEditableText().toString().trim();
        int sex = spSex.getSelectedItemPosition();
        String medical = edtMedical.getEditableText().toString().trim();//医保卡
        String patient = etPatient.getEditableText().toString().trim();//就诊卡号
        String birthday = tvBirthday.getText().toString().trim();
        String age = edtAge.getEditableText().toString().trim();
        String place = edtPlace.getEditableText().toString().trim();
        String address = tvAddress.getEditableText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.hierarchical_resident_create_hint_name));
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.hierarchical_resident_create_hint_phone));
            return;
        }

//        * 开始位置经度	longitude	number	必填
//        项目类型ID	subjectid	string	必填
//        项目类型名称	subjectname	string	必填
//        是否VIP	isvip	boolean	必填
//        开始位置纬度	latitude	number	必填
//        车型名称	carsname	string	必填
//        是否代人下单	isreplace	boolean	必填
//        下单用户ID	uid	string	必填
//        联系人姓名	contactsname	string	isreplace为true时必传
//        联系人电话	contactsphone	string	isreplace为true时必传
//        数量 单位：小时	number	number	必填
//        开始时间 格式：yyyy-MM-dd hh:mm:ss	starttime	string	必填
//        车型ID	carsid	string	必填
//        下单用户名	loginname	string	必填
//        下单用户昵称	nickname	string	必填
//        优惠券ID	vid	string	非必传，格式:多个ID用','分割

//        orderItem.set(name);
//        orderItem.setSex(sex);
//        orderItem.setIdcard(idCard);
//        orderItem.setPhone(phone);
//        orderItem.setMedicalNumber(medical);
//        orderItem.setBirthday(birthday);
//        orderItem.setJzkh(patient);

        ApiHttpClient.getInstance().learnApply(orderItem, new ResultResponseHandler(getActivity(), "正在提交，请稍等") {
            @Override
            public void onResultSuccess(String result) {
                getActivity().finish();
            }
        });
    }

    public void onEventMainThread(PayAliAccountResultEvent event) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
