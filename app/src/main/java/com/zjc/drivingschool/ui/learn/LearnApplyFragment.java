package com.zjc.drivingschool.ui.learn;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.bigkoo.pickerview.TimePopupWindow;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.api.ApiHttpClient;
import com.zjc.drivingschool.api.ResultResponseHandler;
import com.zjc.drivingschool.db.model.OrderItem;
import com.zjc.drivingschool.eventbus.pay.PayAliAccountResultEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * @author Z
 * @Filename LearnApplyFragment.java
 * @Date 2016.06.26
 * @description 学车申请
 */
public class LearnApplyFragment extends ZBaseToolBarFragment implements View.OnClickListener {
    ToggleButton jpushButton;
    private TextView tv_time;
    private TextView tv_locale;
    private TextView tv_timeLength;
    private TextView tv_subject;
    private TextView tv_style;
    private TextView tv_telephone;
    private TextView tv_next;
    private TextView tv_money;
    private int index;

    private OptionsPopupWindow timeLengthOptions;
    private ArrayList<String> optionsItems = new ArrayList<>();

    private OptionsPopupWindow tvSubjectOptions;
    private ArrayList<String> optionsItemsSubject = new ArrayList<>();//培训科目集合

    private OptionsPopupWindow tvStyleOptions;
    private ArrayList<String> optionsItemsStyle = new ArrayList<>();

    private OrderItem orderItem;

    private TimePopupWindow birthOptions;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, R.string.title_learn);
    }

    private void initSubjectOptions() {
        tvSubjectOptions = new OptionsPopupWindow(getActivity());
        //假数据 需要从后台获取数据
        optionsItemsSubject.add("科目二培训");
        optionsItemsSubject.add("市内陪练");
        optionsItemsSubject.add("科目三培训");
        tvSubjectOptions.setPicker(optionsItemsSubject, null, null, true);
        tvSubjectOptions.setSelectOptions(0);
        tvSubjectOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItemsSubject.get(options1);
                tv_subject.setText(tx);
            }
        });
    }

    private void initStyleOptions() {
        tvStyleOptions = new OptionsPopupWindow(getActivity());
        optionsItemsStyle.add("VIP训练");
        optionsItemsStyle.add("普通训练");
        tvStyleOptions.setPicker(optionsItemsStyle, null, null, true);
        tvStyleOptions.setSelectOptions(0);
        tvStyleOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItemsStyle.get(options1);
                tv_style.setText(tx);
            }
        });
    }

    private void initTimeLengthOptions() {
        timeLengthOptions = new OptionsPopupWindow(getActivity());
        optionsItems.add("1小时");
        optionsItems.add("2小时");
        optionsItems.add("3小时");
        timeLengthOptions.setPicker(optionsItems, null, null, true);
        timeLengthOptions.setSelectOptions(0);
        timeLengthOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItems.get(options1);
                tv_timeLength.setText(tx);
            }
        });
    }

    @Override
    protected int inflateContentView() {
        return R.layout.learn_apply_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        initBirthOptions();
        initTimeLengthOptions();
        initStyleOptions();
        initSubjectOptions();
        orderItem = new OrderItem();
    }

    private void initView() {
        jpushButton = (ToggleButton) rootView.findViewById(R.id.personal_center_set_frg_jpush_button2);
        tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        tv_locale = (TextView) rootView.findViewById(R.id.tv_locale);
        tv_timeLength = (TextView) rootView.findViewById(R.id.tv_timeLength);
        tv_subject = (TextView) rootView.findViewById(R.id.tv_subject);
        tv_style = (TextView) rootView.findViewById(R.id.tv_style);
        tv_telephone = (TextView) rootView.findViewById(R.id.tv_telephone);
        tv_next = (TextView) rootView.findViewById(R.id.tv_next);
        tv_money = (TextView) rootView.findViewById(R.id.tv_money);

        tv_time.setOnClickListener(this);
        tv_locale.setOnClickListener(this);
        tv_timeLength.setOnClickListener(this);
        tv_subject.setOnClickListener(this);
        tv_style.setOnClickListener(this);
        tv_telephone.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        jpushButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    setIndex(1);
                    jpushButton.setChecked(true);
                    jpushButton.setBackgroundResource(R.drawable.toggle_on);
                    tv_telephone.setVisibility(View.VISIBLE);
                } else {
                    jpushButton.setChecked(false);
                    setIndex(0);
                    jpushButton.setBackgroundResource(R.drawable.toggle_off);
                    tv_telephone.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (v.getId()) {
            case R.id.tv_next:
                createResident();
                break;
            case R.id.tv_time:
                //隐藏虚拟键盘
                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                birthOptions.showAtLocation(tv_time, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_locale://练车地点

                break;
            case R.id.tv_timeLength:
                //隐藏虚拟键盘
                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                timeLengthOptions.showAtLocation(tv_timeLength, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_subject:
                //隐藏虚拟键盘
                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                tvSubjectOptions.showAtLocation(tv_subject, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_style:
                //隐藏虚拟键盘
                inputmanger.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                tvStyleOptions.showAtLocation(tv_style, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_telephone://跳转联系人

                break;
        }
    }


    private void initBirthOptions() {
        birthOptions = new TimePopupWindow(getActivity(), TimePopupWindow.Type.ALL);
        birthOptions.setRange(1900, 2050);
        birthOptions.setTime(new Date());

        birthOptions.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                Date da = new Date();
                String time = sdf.format(da);
                String timePick = sdf.format(date);
                if (Integer.valueOf(time.substring(0, 4)).intValue() > Integer.valueOf(timePick.substring(0, 4)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else if (Integer.valueOf(time.substring(5, 7)).intValue() > Integer.valueOf(timePick.substring(5, 7)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else if (Integer.valueOf(time.substring(8, 10)).intValue() > Integer.valueOf(timePick.substring(8, 10)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else if (Integer.valueOf(time.substring(11, 13)).intValue() > Integer.valueOf(timePick.substring(11, 13)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else if (Integer.valueOf(time.substring(14, 16)).intValue() > Integer.valueOf(timePick.substring(14, 16)).intValue()) {
                    Util.showCustomMsg("日期选择不正确，请重新选择");
                    return;
                } else {
                    tv_time.setText(timePick);
                }
            }
        });
    }


    private void createResident() {
        //必填
        String tvTime = tv_time.getText().toString().trim();//预约时间
        String tvLocale = tv_locale.getText().toString().trim();//练车地点
        String tvTimeLength = tv_timeLength.getText().toString().trim();//练车时长
        String tvSubject = tv_subject.getText().toString().trim();//培训科目
        String tvStyle = tv_style.getText().toString().trim();//服务类型
        String tvTelephone = tv_telephone.getText().toString().trim();//联系方式
        String tvMoney = tv_money.getText().toString().trim();//价格

        if (TextUtils.isEmpty(tvTime) || TextUtils.isEmpty(tvLocale) || TextUtils.isEmpty(tvTimeLength) || TextUtils.isEmpty(tvSubject) || TextUtils.isEmpty(tvStyle)) {
            Util.showCustomMsg("请输入完整信息");
            return;
        }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
