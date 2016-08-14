package com.mobo.mobolibrary.ui.widget.empty;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobo.mobolibrary.R;
import com.mobo.mobolibrary.util.TDevice;


public class EmptyLayout extends LinearLayout implements
        View.OnClickListener {// , ISkinUIObserver {

    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NODATA = 3;
    public static final int NODATA_ENABLE_CLICK = 5;
    private ProgressBar animProgress;
    private boolean clickEnable = true;
    private Context context;
    private ImageView img;
    private OnClickListener listener;
    private int mErrorState;
    private RelativeLayout mLayout;
    private String strNoDataContent = "";
    private TextView tv;
    private Animation mAnim;

    public EmptyLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.base_view_error_layout, null);
        img = (ImageView) view.findViewById(R.id.img_error_layout);
        tv = (TextView) view.findViewById(R.id.tv_error_layout);
        mLayout = (RelativeLayout) view.findViewById(R.id.pageerrLayout);
        animProgress = (ProgressBar) view.findViewById(R.id.animProgress);
        mAnim = AnimationUtils.loadAnimation(context, R.anim.progressbar);
        setBackgroundResource(R.color.windowBackground);
        setOnClickListener(this);
        img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clickEnable) {
                    //setErrorType(NETWORK_LOADING);
                    if (listener != null)
                        listener.onClick(v);
                }
            }
        });
        addView(view);
        changeErrorLayoutBgMode(context);
    }

    public void changeErrorLayoutBgMode(Context context1) {
        // mLayout.setBackgroundColor(SkinsUtil.getColor(context1,
        // "bgcolor01"));
        // tv.setTextColor(SkinsUtil.getColor(context1, "textcolor05"));
    }

    public void dismiss() {
        mErrorState = HIDE_LAYOUT;
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return mErrorState;
    }

    public boolean isLoadError() {
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading() {
        return mErrorState == NETWORK_LOADING;
    }

    @Override
    public void onClick(View v) {
        if (clickEnable) {
            //setErrorType(NETWORK_LOADING);
            if (listener != null)
                listener.onClick(v);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onSkinChanged();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onSkinChanged() {
    }

    public void setErrorMessage(String msg) {
        tv.setText(msg);
    }

    public void setErrorType(int i) {
        setVisibility(View.VISIBLE);
        switch (i) {
            case NETWORK_ERROR:
                mErrorState = NETWORK_ERROR;
                if (TDevice.hasInternet()) {
                    tv.setText(R.string.error_view_click_to_refresh);
                    img.setBackgroundResource(R.drawable.page_icon_error);
                } else {
                    tv.setText(R.string.error_view_network_error_click_to_refresh);
                    img.setBackgroundResource(R.drawable.page_icon_network);
                }
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                animProgress.clearAnimation();
                clickEnable = true;
                break;
            case NETWORK_LOADING:
                mErrorState = NETWORK_LOADING;
                animProgress.setVisibility(View.VISIBLE);
//                animProgress.clearAnimation();
//                animProgress.startAnimation(mAnim);
                img.setVisibility(View.GONE);
                tv.setText(R.string.error_view_loading);
                clickEnable = false;
                break;
            case NODATA:
                mErrorState = NODATA;
                img.setBackgroundResource(R.drawable.page_icon_empty);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                animProgress.clearAnimation();
                setTvNoDataContent();
                clickEnable = false;
                break;
            case HIDE_LAYOUT:
                setVisibility(View.GONE);
                animProgress.clearAnimation();
                break;
            case NODATA_ENABLE_CLICK:
                mErrorState = NODATA_ENABLE_CLICK;
                img.setBackgroundResource(R.drawable.page_icon_empty);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                animProgress.clearAnimation();
                setTvNoDataContent();
                clickEnable = true;
                break;
            default:
                break;
        }
    }

    public void setNoDataContent(String noDataContent) {
        strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if (!strNoDataContent.equals(""))
            tv.setText(strNoDataContent);
        else
            tv.setText(R.string.error_view_no_data);
    }

    public ImageView getImg() {
        return img;
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }

    public String getMessage() {
        if (tv != null) {
            return tv.getText().toString();
        }
        return "";
    }
}
