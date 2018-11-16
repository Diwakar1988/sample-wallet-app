package com.github.diwakar1988.noon.home;

import android.view.View;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public interface ItemClickListener<T> {
    void onItemClicked(View view , T item);
}
