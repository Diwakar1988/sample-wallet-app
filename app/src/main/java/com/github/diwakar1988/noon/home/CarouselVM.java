package com.github.diwakar1988.noon.home;

import android.view.View;
import android.widget.Toast;

import com.github.diwakar1988.noon.common.NoonViewModel;
import com.github.diwakar1988.noon.pojo.Section;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class CarouselVM extends NoonViewModel<Section.Action> {
    public CarouselVM(Section.Action item) {
        super(item);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void clean() {

    }
    public String getTitle(){
        return getItem().getTitle();
    }
    public String getDescription(){
        return getItem().getDescription();
    }

    public void learnMoreClicked(View view){
        Toast.makeText(view.getContext(), String.format("Learn More with ID=%d",getItem().getId()), Toast.LENGTH_SHORT).show();
    }
}
