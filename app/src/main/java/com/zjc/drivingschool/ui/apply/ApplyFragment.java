package com.zjc.drivingschool.ui.apply;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.bigkoo.pickerview.OptionsPopupWindow;
import com.bigkoo.pickerview.TimePopupWindow;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.UtilDate;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.request.SignupOrderRequest;
import com.zjc.drivingschool.eventbus.StudyAddressChooseEvent;
import com.zjc.drivingschool.ui.study.StudyAddressFragment;

import java.util.ArrayList;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename ApplyFragment.java
 * @Date 2016.06.26
 * @description 报名学车
 */
public class ApplyFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private EditText edtName;
    private EditText edtPhone;
    private TextView tvBirth;
    private TextView tvSex;
    private TextView tvNext;
    private TextView tvEducation;
    private TextView tvAddress;

    private OptionsPopupWindow sexOptions;
    private ArrayList<String> sexItems = new ArrayList<>();

    private OptionsPopupWindow educationOptions;
    private ArrayList<String> educationItems = new ArrayList<>();

    private TimePopupWindow birthOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_apply);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.hierarchical_create_resident_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        initSexOptions();
        initEducationOptions();
        initBirthOptions();
    }

    private void initView() {
        edtName = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_name);
        edtPhone = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_phone);
        tvSex = (TextView) rootView.findViewById(R.id.hierarchical_create_resident_frg_tv_sex);
        tvNext = (TextView) rootView.findViewById(R.id.tv_next);
        tvEducation = (TextView) rootView.findViewById(R.id.hierarchical_create_resident_frg_tv_education);
        tvBirth = (TextView) rootView.findViewById(R.id.hierarchical_create_resident_frg_tv_age);
        tvAddress = (TextView) rootView.findViewById(R.id.tv_locale);

        tvBirth.setOnClickListener(this);
        tvEducation.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    private void initSexOptions() {
        sexOptions = new OptionsPopupWindow(getActivity());
        sexItems.add("女");
        sexItems.add("男");
        sexOptions.setPicker(sexItems, null, null, true);
        sexOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String education = sexItems.get(options1);
                tvSex.setText(education);
                if (options1 == 0) {
                    tvSex.setTag(false);
                } else {
                    tvSex.setTag(true);
                }
            }
        });
    }

    private void initEducationOptions() {
        educationOptions = new OptionsPopupWindow(getActivity());
        educationItems.add("小学");
        educationItems.add("初中");
        educationItems.add("高中");
        educationItems.add("专科");
        educationItems.add("本科");
        educationItems.add("硕士");
        educationItems.add("博士");
        educationOptions.setPicker(educationItems, null, null, true);
        educationOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String education = educationItems.get(options1);
                tvEducation.setText(education);
            }
        });
    }

    private void initBirthOptions() {
        birthOptions = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
        birthOptions.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                //选择时间必须大于现在3小时
                if (System.currentTimeMillis() - date.getTime() > 0) {
                    tvBirth.setText(UtilDate.formatDate(date));
                } else {
                    Util.showCustomMsg("时间不正确，请重新选择");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (i == R.id.hierarchical_create_resident_frg_tv_education) {
            inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
            educationOptions.showAtLocation(tvEducation, Gravity.BOTTOM, 0, 0);
        } else if (i == R.id.hierarchical_create_resident_frg_tv_age) {
            inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
            birthOptions.showAtLocation(tvBirth, Gravity.BOTTOM, 0, 0);
        } else if (i == R.id.hierarchical_create_resident_frg_tv_sex) {
            inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
            sexOptions.showAtLocation(tvSex, Gravity.BOTTOM, 0, 0);
        } else if (i == R.id.tv_locale) {
            StudyAddressFragment fragment = new StudyAddressFragment();
            replaceFrg(fragment, null);
        } else if (i == R.id.tv_next) {
            //提交信息
            createResident();
        }
    }

    public void onEventMainThread(StudyAddressChooseEvent event) {
        CloudPoiInfo poiInfo = event.getPoiInfo();
        tvAddress.setText(poiInfo.title);
        tvAddress.setTag(poiInfo);
    }

    private void createResident() {
        String name = edtName.getEditableText().toString().trim();
        String phone = edtPhone.getEditableText().toString().trim();
        String sex = tvSex.getText().toString();
        String birth = tvBirth.getText().toString().trim();
        String address = tvAddress.getText().toString().trim();//练车地点

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(birth) || TextUtils.isEmpty(address)) {
            Util.showCustomMsg("请填写完整信息");
            return;
        }


//        优惠券ID	vid	string	（非必传，格式:多个ID用','分割）
        SignupOrderRequest signupOrder = new SignupOrderRequest();

        CloudPoiInfo poiInfo = (CloudPoiInfo) tvAddress.getTag();
        signupOrder.setLatitude(poiInfo.latitude);
        signupOrder.setLongitude(poiInfo.longitude);

        signupOrder.setUid(SharePreferencesUtil.getInstance().readUser().getUid());
        signupOrder.setUname(name);
        signupOrder.setUphone(phone);

        signupOrder.setEducation(tvEducation.getText().toString());
        signupOrder.setGender((boolean) tvSex.getTag());
        signupOrder.setBirthday(birth);
        signupOrder.setIsreplace(false);//需要动态获取
        signupOrder.setContactsphone(phone);
        signupOrder.setContactsname(name);

        ApiHttpClient.getInstance().startApply(signupOrder, new ResultResponseHandler(getActivity(), "正在报名，请稍等") {
            @Override
            public void onResultSuccess(String result) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
