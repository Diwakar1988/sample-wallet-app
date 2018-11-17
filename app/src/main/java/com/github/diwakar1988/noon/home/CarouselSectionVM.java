package com.github.diwakar1988.noon.home;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;

import com.github.diwakar1988.noon.common.NoonViewModel;
import com.github.diwakar1988.noon.pojo.Section;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class CarouselSectionVM extends NoonViewModel<Section>{
    private CarouselSectionAdapter listAdapter;
    public CarouselSectionVM(Section item) {
        super(item);
    }

    @Override
    public void initialize() {
        if (getItem().isCarousel()){
            listAdapter = new CarouselSectionAdapter(getItem().getActions());
        }
    }

    @Override
    public void clean() {
        listAdapter = null;
    }
    public CarouselSectionAdapter getSectionActionsAdapter() {
        return listAdapter;
    }

    @BindingAdapter("adapter")
    public static void setAdapter(ViewPager viewPager, CarouselSectionAdapter adapter) {
        viewPager.setAdapter(adapter);
    }
}
