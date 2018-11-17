package com.github.diwakar1988.noon.home;

import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.databinding.CarouselPagerItemBinding;
import com.github.diwakar1988.noon.pojo.Section;

import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class CarouselSectionAdapter extends PagerAdapter {
    List<Section.Action> items;

    public CarouselSectionAdapter(List<Section.Action> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public Section.Action getItem(int pos) {
        return items.get(pos);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CarouselPagerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.carousel_pager_item, container, false);
        View view = binding.getRoot();
        binding.setActionVM(new CarouselVM(getItem(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
