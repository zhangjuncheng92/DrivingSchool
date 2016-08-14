package com.mobo.mobolibrary.ui.widget.FullLayoutManager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/26  14:15.
 * Description:
 * Modification  History:
 * Date             Author                Version            Description
 * -----------------------------------------------------------------------------------
 * 2015/2/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FullyLinearLayoutManager extends LinearLayoutManager {
    private int dividerHeight;
    private boolean mShowFirstDivider = false;
    private boolean mShowLastDivider = false;

    private static final String TAG = FullyLinearLayoutManager.class.getSimpleName();

    public FullyLinearLayoutManager(Context context) {
        super(context);
    }

    public FullyLinearLayoutManager(Context context, int dividerHeight, boolean mShowFirstDivider, boolean mShowLastDivider) {
        super(context);
        this.dividerHeight = dividerHeight;
        this.mShowFirstDivider = mShowFirstDivider;
        this.mShowLastDivider = mShowLastDivider;
    }

    public FullyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    private int[] mMeasuredDimension = new int[2];

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {

        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

//        Logs.i(TAG, "onMeasure called. \nwidthMode " + widthMode
//                + " \nheightMode " + heightSpec
//                + " \nwidthSize " + widthSize
//                + " \nheightSize " + heightSize
//                + " \ngetItemCount() " + getItemCount());

        int width = 0;
        int height = 0;
        for (int i = 0; i < getItemCount(); i++) {
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension);
            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0] + dividerHeight;
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                height = height + mMeasuredDimension[1] + dividerHeight;
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }

        //如果没有头部分割线，则减去头部分割线
        if (mShowFirstDivider) {
            if (getOrientation() == HORIZONTAL) {
                width = width + dividerHeight;
            } else {
                height = height + dividerHeight;
            }
        }

//        if (!mShowLastDivider) {
//            if (getOrientation() == HORIZONTAL) {
//                width = width - dividerHeight;
//            } else {
//                height = height - dividerHeight;
//            }
//        }

        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width, height);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        //修改测量高度方式，当position的值正常的时候，选择当前控件高度作为衡量标准，否则选第0个
        View view = null;
        try {
            if (position < getItemCount()) {
                view = recycler.getViewForPosition(position);
            } else {
                view = recycler.getViewForPosition(0);//fix 动态添加时报IndexOutOfBoundsException
            }
        } catch (Exception e) {
            e.printStackTrace();
            view = recycler.getViewForPosition(0);//fix 动态添加时报IndexOutOfBoundsException
        } finally {
            if (view != null) {
                RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();

                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                        getPaddingLeft() + getPaddingRight(), p.width);

                int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                        getPaddingTop() + getPaddingBottom(), p.height);

                view.measure(childWidthSpec, childHeightSpec);
                measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                recycler.recycleView(view);
            }
        }
    }
}