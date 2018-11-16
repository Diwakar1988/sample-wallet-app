package com.github.diwakar1988.noon.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public abstract class NoonViewHolder<T> extends RecyclerView.ViewHolder {
    public NoonViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(T data);
    public abstract void unbind();
}
