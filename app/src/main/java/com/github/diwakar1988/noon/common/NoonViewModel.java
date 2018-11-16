package com.github.diwakar1988.noon.common;

import android.databinding.BaseObservable;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public abstract class NoonViewModel<T> extends BaseObservable implements ViewModel{
    protected T item;

    public NoonViewModel(T item) {
        this.item = item;
        initialize();
    }

    public final T getItem() {
        return item;
    }

    /**
     * called when ViewModel is going to update with new T(data)
     */

    public abstract void update(T item);

    /**
     * do all initialization here
     */
    public abstract void initialize();
}
