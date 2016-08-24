package com.zjc.drivingschool.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.ui.base.ZBaseToolBarFragment;
import com.mobo.mobolibrary.util.image.ImageLoader;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.TeacherCollectItem;
import com.zjc.drivingschool.utils.Constants;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/8/24.
 */
public class CollectDetailFragment extends ZBaseToolBarFragment {
    TeacherCollectItem teacherCollectItem;

    private SimpleDraweeView sdIcon;
    private RatingBar rbScore;
    private TextView tvDes;
    private TextView tvTelephone;
    private TextView tvSchoolName;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvAge;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static CollectDetailFragment newInstance(TeacherCollectItem bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT, bean);
        CollectDetailFragment fragment = new CollectDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            teacherCollectItem = (TeacherCollectItem) bundle.getSerializable(Constants.ARGUMENT);
        }
    }

    @Override
    protected void setTitle() {
        setTitle(mToolbar, "教练详情");
    }

    @Override
    protected int inflateContentView() {
        return R.layout.collect_detail_frg;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        sdIcon = (SimpleDraweeView) rootView.findViewById(R.id.personal_main_frg_sd_icon);
        ImageLoader.getInstance().displayImage(sdIcon, Constants.BASE_IP + teacherCollectItem.getPhoto());
        rbScore = (RatingBar) rootView.findViewById(R.id.assess_detail_list_rb_score);
        rbScore.setIsIndicator(true);
        rbScore.setRating(Float.parseFloat(String.valueOf(teacherCollectItem.getStars())));
        tvDes = (TextView) rootView.findViewById(R.id.collect_detail_des);
//        tvDes.setText(teacherCollectItem.get);没有字段
        tvTelephone = (TextView) rootView.findViewById(R.id.collect_detail_telephone);
        tvTelephone.setText(teacherCollectItem.getPhone());
        tvSchoolName = (TextView) rootView.findViewById(R.id.collect_detail_schoolname);
        tvSchoolName.setText(teacherCollectItem.getSchoolname());
        tvName = (TextView) rootView.findViewById(R.id.collect_detail_name);
        tvName.setText(teacherCollectItem.getTeachername());
        tvSex = (TextView) rootView.findViewById(R.id.collect_detail_sex);
//        if (teacherCollectItem.getGender()){
//            tvSex.setText("男");不知道true和flase代表的是男是女
//        }else {
//            tvSex.setText("女");
//        }
        tvAge = (TextView) rootView.findViewById(R.id.collect_detail_age);
//        tvAge.setText(teacherCollectItem.get);没有字段
    }
}
