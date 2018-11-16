package com.github.diwakar1988.noon.common;

import android.databinding.BaseObservable;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public abstract class NoonViewModel<T> extends BaseObservable implements ViewModel{
    protected T item;

    public NoonViewModel(T item) {
        this.item = item;
    }

    public final T getItem() {
        return item;
    }

    public abstract void update(T item);
}
