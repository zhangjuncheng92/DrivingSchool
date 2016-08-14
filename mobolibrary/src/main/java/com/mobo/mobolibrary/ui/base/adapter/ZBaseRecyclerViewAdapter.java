package com.mobo.mobolibrary.ui.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
public abstract class ZBaseRecyclerViewAdapter<T> extends RecyclerArrayAdapter<T> {

    BaseViewHolder viewHolder;

    public ZBaseRecyclerViewAdapter(Context context) {
        super(context);
    }

    public ZBaseRecyclerViewAdapter(Context context, T[] objects) {
        super(context, objects);
    }

    public ZBaseRecyclerViewAdapter(Context context, List<T> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = getViewHolder(parent, viewType);
        onClick(viewHolder);
        onLongClick(viewHolder);
        return viewHolder;
    }

    /**
     * 为item添加长按回调
     *
     * @param holder
     */
    private void onLongClick(final BaseViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mOnItemLongClickListener) {
                    int pos = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, pos);
                }
                return true;
            }
        });
    }


    private void onClick(final BaseViewHolder holder) {
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }

    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    public interface OnItemClickListener {
        /**
         * 点击
         *
         * @param view
         * @param position
         */

        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {

        /**
         * 长按
         */
        public void onItemLongClick(View view, int position);
    }

    public interface OnItemSubViewClickListener {

        /**
         * Item 内部View点击
         */
        public void onItemSubViewClickListener(View view, int postion);
    }


    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
