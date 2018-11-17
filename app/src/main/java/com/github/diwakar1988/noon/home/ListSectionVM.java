package com.github.diwakar1988.noon.home;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.diwakar1988.noon.common.NoonViewModel;
import com.github.diwakar1988.noon.pojo.Section;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class ListSectionVM extends NoonViewModel<Section> implements ItemClickListener<Section.Action>{
    private SectionActionAdapter listAdapter;
    public ListSectionVM(Section item) {
        super(item);
    }

    @Override
    public void initialize() {
        if (getItem().isList()){
            listAdapter = new SectionActionAdapter(getItem().getActions(),this);
        }
    }

    @Override
    public void clean() {
        listAdapter = null;
    }
    public String getTitle(){
        return getItem().getTitle();
    }
    public String getDescription(){
        return getItem().getDescription();
    }
    public SectionActionAdapter getSectionActionsAdapter() {
        return listAdapter;
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView rv, SectionActionAdapter adapter) {
        if (rv.getLayoutManager()==null){
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext(),LinearLayoutManager.HORIZONTAL,false));
            rv.setHasFixedSize(true);
        }
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(View view, Section.Action item) {
        Toast.makeText(view.getContext(), String.format("Section= %s, Action= %s clicked",getItem().getTitle(),item.getTitle()), Toast.LENGTH_SHORT).show();
    }
}
