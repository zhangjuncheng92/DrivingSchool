package com.zjc.drivingschool.ui.collect.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.mobo.mobolibrary.util.image.ImageLoader;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.utils.Constants;

/**
 * Created by Administrator on 2016/8/18.
 */
public class CollectManagerAdapter extends ZBaseRecyclerViewAdapter {
    public CollectManagerAdapter(Context context) {
        super(context);
    }


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new CollectManagerFrgViewHolder(parent);
    }

    class CollectManagerFrgViewHolder extends BaseViewHolder<UserInfo> {
        private TextView mTvName;
        private TextView mTvContent;
        private RatingBar rbScore;
        private SimpleDraweeView mImage;

        public CollectManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.collect_manager_adapter);
            rbScore = $(R.id.assess_detail_list_rb_score);
            mImage = $(R.id.personal_center_img_person);
            mTvName = $(R.id.collect_name);
            mTvContent = $(R.id.collect_content);


        }

        @Override
        public void setData(UserInfo service) {
            if (service == null) {
                return;
            }
//            ImageLoader.getInstance().displayImage(mImage, Constants.BASE_URL_DOWNLOAD + userInfo.getLogo());
            //// TODO: 2016/8/18
            //设置评分条是否只能看，不能改
            rbScore.setIsIndicator(true);
            rbScore.setOnRatingBarChangeListener(null);
//            rbScore.setRating(Float.parseFloat(String.valueOf(shoppingHealthOrganList.getScore())));
//            rbScore.setTag(shoppingHealthOrganList);
            rbScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                    ShoppingHealthOrganList details = (ShoppingHealthOrganList) ratingBar.getTag();
//                    details.setScore(Double.parseDouble(String.valueOf(rating)));
                }
            });

        }
    }
}
