package com.github.diwakar1988.noon.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonViewHolder;
import com.github.diwakar1988.noon.databinding.HomePageSectionItemBinding;
import com.github.diwakar1988.noon.pojo.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.SectionViewHolder>{
    private List<Section> sections;

    public SectionsAdapter(List<Section> sections) {
        this.sections = sections;
        if (this.sections==null){
            this.sections = new ArrayList<>();
        }
    }

    public void setSections(List<Section> sections) {
        this.sections.clear();
        this.sections = sections;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomePageSectionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_page_section_item, parent, false);
        return new SectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        holder.bind(sections.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SectionViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public static class SectionViewHolder extends NoonViewHolder<Section>{

        private HomePageSectionItemBinding binding;
        public SectionViewHolder(HomePageSectionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Section data) {
            if (binding.getSectionVM()!=null){
                binding.getSectionVM().update(data);
            }else {
                binding.setSectionVM(new SectionVM(data));
            }
        }

        @Override
        public void unbind() {
            binding.getSectionVM().clean();
        }
    }
}
