package com.github.diwakar1988.noon.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonViewHolder;
import com.github.diwakar1988.noon.databinding.HomePageCarouselSectionItemBinding;
import com.github.diwakar1988.noon.databinding.HomePageListSectionItemBinding;
import com.github.diwakar1988.noon.pojo.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class SectionsAdapter extends RecyclerView.Adapter<NoonViewHolder<Section>>{
    private static final int TYPE_CAROUSEL=1;
    private static final int TYPE_LIST=2;
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
    public NoonViewHolder<Section> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_CAROUSEL){
            HomePageCarouselSectionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_page_carousel_section_item, parent, false);
            return new CarouselSectionViewHolder(binding);
        }else {
            HomePageListSectionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_page_list_section_item, parent, false);
            return new ListSectionViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NoonViewHolder<Section> holder, int position) {
        holder.bind(sections.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull NoonViewHolder<Section> holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (sections.get(position).isCarousel()){
            return TYPE_CAROUSEL;
        }else if (sections.get(position).isList()){
            return TYPE_LIST;
        }
        return super.getItemViewType(position);
    }

    public static class CarouselSectionViewHolder extends NoonViewHolder<Section>{

        private HomePageCarouselSectionItemBinding binding;

        public CarouselSectionViewHolder(HomePageCarouselSectionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.tabLayout.setupWithViewPager(binding.viewPager, true);
        }

        @Override
        public void bind(Section data) {
            if (binding.getSectionVM()!=null){
                binding.getSectionVM().update(data);
            }else {
                binding.setSectionVM(new CarouselSectionVM(data));
            }
        }

        @Override
        public void unbind() {
            binding.getSectionVM().clean();
        }
    }
    public static class ListSectionViewHolder extends NoonViewHolder<Section>{

        private HomePageListSectionItemBinding binding;
        public ListSectionViewHolder(HomePageListSectionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Section data) {
            if (binding.getSectionVM()!=null){
                binding.getSectionVM().update(data);
            }else {
                binding.setSectionVM(new ListSectionVM(data));
            }
        }

        @Override
        public void unbind() {
            binding.getSectionVM().clean();
        }
    }
}
