package com.zjc.drivingschool.ui.apply;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;

/**
 * @author Z
 * @Filename ApplyFragment.java
 * @Date 2016.06.26
 * @description 报名学车
 */
public class ApplyFragment extends ZBaseToolBarFragment {
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtAge;
    private Spinner spSex;
    private TextView tvNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
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
        initSpSex();
    }

    private void initView() {
        edtName = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_name);
        edtPhone = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_phone);
        spSex = (Spinner) rootView.findViewById(R.id.hierarchical_create_resident_frg_sp_sex);
        tvNext = (TextView) rootView.findViewById(R.id.tv_next);
        edtAge = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_age);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提交信息
                createResident();
            }
        });
    }

    private void initSpSex() {
        String[] list = getResources().getStringArray(R.array.referral_sex);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.referral_spinner_item, list);
        adapter.setDropDownViewResource(R.layout.referral_spinner_dropdown_item);
        spSex.setAdapter(adapter);
    }

    private void createResident() {
        String name = edtName.getEditableText().toString().trim();
        String phone = edtPhone.getEditableText().toString().trim();
        int sex = spSex.getSelectedItemPosition();
        String age = edtAge.getEditableText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || sex < 0 || TextUtils.isEmpty(age)) {
            Util.showCustomMsg("请填写完整信息");
            return;
        }



        ApiHttpClient.getInstance().startApply(null, new ResultResponseHandler(getActivity(), "正在新建档案，请稍等") {
            @Override
            public void onResultSuccess(String result) {
                getActivity().finish();
            }
        });
    }
}
