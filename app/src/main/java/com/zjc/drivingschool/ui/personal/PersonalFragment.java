package com.zjc.drivingschool.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
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
    private EditText tvSex;
    private EditText tvBirthday;
    private EditText tvAddress;
    private EditText tvPhone;
    private EditText tvIdCard;
    private SimpleDraweeView sdIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.personal_detail_title);
    }

    @Override
    protected int inflateContentView() {
        return R.layout.personal_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        setPersonInfo(SharePreferencesUtil.getInstance().readUser());
    }

    private void initView() {
        tvName = (EditText) rootView.findViewById(R.id.personal_frg_tv_name);
        tvEmail = (EditText) rootView.findViewById(R.id.personal_frg_tv_email);
        tvQQ = (EditText) rootView.findViewById(R.id.personal_frg_tv_QQ);
        tvSex = (EditText) rootView.findViewById(R.id.personal_frg_tv_sex);
        tvBirthday = (EditText) rootView.findViewById(R.id.personal_frg_tv_birthday);
        tvAddress = (EditText) rootView.findViewById(R.id.personal_frg_tv_address);
        tvPhone = (EditText) rootView.findViewById(R.id.personal_frg_tv_phone);
        tvIdCard = (EditText) rootView.findViewById(R.id.personal_frg_tv_id_card);
        sdIcon = (SimpleDraweeView) rootView.findViewById(R.id.personal_main_frg_sd_icon);
    }

    private void setPersonInfo(UserInfo userInfo) {
        tvName.setText(userInfo.getNickname());
        if (userInfo.isGender()) {
            tvSex.setText(R.string.personal_sex_men);
        } else {
            tvSex.setText(R.string.personal_sex_women);
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


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.personal_main_frg_sd_icon) {
            MultiPhotoDialogFragment dialogFragment1 = new MultiPhotoDialogFragment();
            dialogFragment1.setMax(1);
            dialogFragment1.setAction(ConstantsParams.PHOTO_TYPE_USER);
            dialogFragment1.show(getFragmentManager(), null);
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
