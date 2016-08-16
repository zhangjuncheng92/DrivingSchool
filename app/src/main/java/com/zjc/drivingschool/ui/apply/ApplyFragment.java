package com.zjc.drivingschool.ui.apply;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alertdialogpro.AlertDialogPro;
import com.bigkoo.pickerview.TimePopupWindow;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.db.parser.UserInfoParser;

import java.util.List;

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
    private TimePopupWindow birthOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_apply);
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

        edtAge = (EditText) rootView.findViewById(R.id.hierarchical_create_resident_frg_edt_age);
    }

    private void initSpSex() {
        String[] list = getResources().getStringArray(R.array.referral_sex);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.referral_spinner_item, list);
        adapter.setDropDownViewResource(R.layout.referral_spinner_dropdown_item);
        spSex.setAdapter(adapter);
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
        String phone = edtPhone.getEditableText().toString().trim();
        int sex = spSex.getSelectedItemPosition();

        if (TextUtils.isEmpty(name)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.hierarchical_resident_create_hint_name));
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Util.showCustomMsg(getContext().getResources().getString(R.string.hierarchical_resident_create_hint_phone));
            return;
        }


        ApiHttpClient.getInstance().createResident(null, new ResultResponseHandler(getActivity(), "正在新建档案，请稍等") {
            @Override
            public void onResultSuccess(String result) {
                getActivity().finish();
            }
        });
    }
}
