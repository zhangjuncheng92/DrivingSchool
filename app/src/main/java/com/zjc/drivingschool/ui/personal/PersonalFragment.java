package com.zjc.drivingschool.ui.personal;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.bigkoo.pickerview.TimePopupWindow;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.image.ImageLoader;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.db.parser.UserInfoParser;
import com.zjc.drivingschool.eventbus.ActionCameraEvent;
import com.zjc.drivingschool.eventbus.ActionMultiPhotoEvent;
import com.zjc.drivingschool.utils.Constants;
import com.zjc.drivingschool.utils.ConstantsParams;
import com.zjc.drivingschool.widget.MultiPhotoDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename PersonalFragment.java
 * @Date 2016.06.06
 * @description 个人中心详情界面
 */
public class PersonalFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    private EditText tvName;
    private EditText tvEmail;
    private EditText tvQQ;
    private TextView tvSex;
    private TextView tvBirthday;
    private EditText tvAddress;
    private EditText tvPhone;
    private EditText tvIdCard;
    private SimpleDraweeView sdIcon;

    private TimePopupWindow birthOptions;

    private OptionsPopupWindow SexOptions;
    private ArrayList<String> optionsItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.personal_detail_title);
        mToolbar.setOnMenuItemClickListener(OnMenuItemClick);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_personal_center, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private Toolbar.OnMenuItemClickListener OnMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.action_explain) {
                String birth = tvBirthday.getText().toString().trim();
                String sex = tvSex.getText().toString().trim();
                String name = tvName.getText().toString().trim();
                String email = tvEmail.getText().toString().trim();
                String qq = tvQQ.getText().toString().trim();
                String address = tvAddress.getText().toString().trim();
                String phone = tvPhone.getText().toString().trim();
                String idCard = tvIdCard.getText().toString().trim();

                if (TextUtils.isEmpty(birth) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(name) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(qq) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(idCard)) {
                    Util.showCustomMsg("请输入完整信息");
                    return true;
                }

                final UserInfo userInfo = SharePreferencesUtil.getInstance().readUser();
                userInfo.setUid(SharePreferencesUtil.getInstance().readUser().getUid());
                userInfo.setNickname(name);
                userInfo.setGender((Boolean) tvSex.getTag());
                userInfo.setBirthday(birth);
                userInfo.setPhone(phone);
                userInfo.setEmail(email);
                userInfo.setQq(qq);
                userInfo.setAddress(address);
                userInfo.setIdentityno(idCard);

                ApiHttpClient.getInstance().updateUserBaseInfo(userInfo, new ResultResponseHandler(getActivity(), "正在保存") {
                    @Override
                    public void onResultSuccess(String result) {
                        SharePreferencesUtil.getInstance().saveUser(userInfo);
                        getActivity().finish();
                    }
                });
            }
            return true;
        }
    };

    @Override
    protected int inflateContentView() {
        return R.layout.personal_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        initBirthOptions();
        initSexOptions();
        setPersonInfo(SharePreferencesUtil.getInstance().readUser());
    }

    private void initView() {
        tvName = (EditText) rootView.findViewById(R.id.personal_frg_tv_name);
        tvEmail = (EditText) rootView.findViewById(R.id.personal_frg_tv_email);
        tvQQ = (EditText) rootView.findViewById(R.id.personal_frg_tv_QQ);
        tvSex = (TextView) rootView.findViewById(R.id.personal_frg_tv_sex);
        tvBirthday = (TextView) rootView.findViewById(R.id.personal_frg_tv_birthday);
        tvAddress = (EditText) rootView.findViewById(R.id.personal_frg_tv_address);
        tvPhone = (EditText) rootView.findViewById(R.id.personal_frg_tv_phone);
        tvIdCard = (EditText) rootView.findViewById(R.id.personal_frg_tv_id_card);
        sdIcon = (SimpleDraweeView) rootView.findViewById(R.id.personal_main_frg_sd_icon);

        tvBirthday.setOnClickListener(this);
        tvSex.setOnClickListener(this);
    }

    private void setPersonInfo(UserInfo userInfo) {
        tvName.setText(userInfo.getNickname());
        if (userInfo.isGender()) {
            tvSex.setText(R.string.personal_sex_men);
            tvSex.setTag(true);
        } else {
            tvSex.setText(R.string.personal_sex_women);
            tvSex.setTag(false);
        }
        tvBirthday.setText(userInfo.getBirthday() + "");
        tvAddress.setText(userInfo.getAddress());
        tvEmail.setText(userInfo.getEmail());
        tvPhone.setText(userInfo.getPhone());
        tvIdCard.setText(userInfo.getIdentityno());
        tvQQ.setText(userInfo.getQq());
        ImageLoader.getInstance().displayImage(sdIcon, Constants.BASE_IP + userInfo.getPhotourl());

        sdIcon.setOnClickListener(this);
    }

    private void initBirthOptions() {
        birthOptions = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
        birthOptions.setRange(1900, 2050);
        birthOptions.setTime(new Date());

        birthOptions.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date da = new Date();
                String time = sdf.format(da);
                String timePick = sdf.format(date);
                if (Integer.valueOf(time.substring(0, 4)).intValue() < Integer.valueOf(timePick.substring(0, 4)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else if (Integer.valueOf(time.substring(5, 7)).intValue() < Integer.valueOf(timePick.substring(5, 7)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else if (Integer.valueOf(time.substring(8, 10)).intValue() < Integer.valueOf(timePick.substring(8, 10)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else {
                    tvBirthday.setText(timePick);
                }
            }
        });
    }


    private void initSexOptions() {
        SexOptions = new OptionsPopupWindow(getActivity());
        optionsItems.add(getResources().getString(R.string.personal_sex_men));
        optionsItems.add(getResources().getString(R.string.personal_sex_women));
        SexOptions.setPicker(optionsItems, null, null, true);
//        SexOptions.setSelectOptions(0);
        SexOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItems.get(options1);
                tvSex.setText(tx);
                if (options1 == 0) {
                    tvSex.setTag(true);
                } else {
                    tvSex.setTag(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        int i = v.getId();
        if (i == R.id.personal_main_frg_sd_icon) {
            MultiPhotoDialogFragment dialogFragment1 = new MultiPhotoDialogFragment();
            dialogFragment1.setMax(1);
            dialogFragment1.setAction(ConstantsParams.PHOTO_TYPE_USER);
            dialogFragment1.show(getFragmentManager(), null);
        }
        switch (i) {
            case R.id.personal_frg_tv_birthday:
                //隐藏虚拟键盘
                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                birthOptions.showAtLocation(tvBirthday, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.personal_frg_tv_sex:
                //隐藏虚拟键盘
                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                SexOptions.showAtLocation(tvSex, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    public void onEvent(ActionMultiPhotoEvent event) {
        if (event.getAction() == ConstantsParams.PHOTO_TYPE_USER) {
            sdIcon.setTag(event.getUris().get(0));
            updateUserBaseInfo(event.getUris().get(0));
        }
    }

    public void onEvent(ActionCameraEvent event) {
        if (event.getAction() == ConstantsParams.PHOTO_TYPE_USER) {
            sdIcon.setTag(event.getUri());
            updateUserBaseInfo(event.getUri());
        }
    }

    private void updateUserBaseInfo(String uri) {
        String id = SharePreferencesUtil.getInstance().readUser().getUid();
        ApiHttpClient.getInstance().upLoadUserImg(id, uri, new ResultResponseHandler(getActivity(), "上传中") {
            @Override
            public void onResultSuccess(String result) {
                //更新用户头像
                UserInfo beforeUserInfo = SharePreferencesUtil.getInstance().readUser();
                UserInfo userInfo = new UserInfoParser().parseResultMessage(result);
                beforeUserInfo.setPhotourl(userInfo.getPhotourl());
                SharePreferencesUtil.getInstance().saveUser(beforeUserInfo);

                ImageLoader.getInstance().displayImage(sdIcon, Constants.BASE_IP + userInfo.getPhotourl());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
