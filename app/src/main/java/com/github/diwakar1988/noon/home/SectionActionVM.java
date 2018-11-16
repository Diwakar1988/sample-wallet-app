package com.github.diwakar1988.noon.home;

import com.github.diwakar1988.noon.common.NoonViewModel;
import com.github.diwakar1988.noon.pojo.Section;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class SectionActionVM extends NoonViewModel<Section.Action> {
    public SectionActionVM(Section.Action item) {
        super(item);
    }

    @Override
    public void update(Section.Action item) {
        this.item = item;
        initialize();
        notifyChange();
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
}
