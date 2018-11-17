package com.github.diwakar1988.noon.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
public class SectionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CAROUSEL=1;
    private static final int TYPE_LIST=2;
    private List<Section> sections;
    private View headerView;

    public SectionsAdapter(List<Section> sections) {
        this.sections = sections;
        if (this.sections==null){
            this.sections = new ArrayList<>();
        }
    }

    public void addHeader(View headerView) {
        this.headerView = headerView;
    }

    public View getHeaderView() {
        return headerView;
    }

    public boolean isHavingHeader() {
        return headerView!=null;
    }
    public void setSections(List<Section> sections) {
        this.sections.clear();
        this.sections = sections;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_CAROUSEL){
            HomePageCarouselSectionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_page_carousel_section_item, parent, false);
            return new CarouselSectionViewHolder(binding);
        }else if (viewType==TYPE_LIST){
            HomePageListSectionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_page_list_section_item, parent, false);
            return new ListSectionViewHolder(binding);
        }
        return new RecyclerView.ViewHolder(headerView) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoonViewHolder){
            if (isHavingHeader()){
                position-=1;
            }
            ((NoonViewHolder<Section>)holder).bind(sections.get(position));
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof NoonViewHolder){
            ((NoonViewHolder<Section>)holder).unbind();
        }
    }

    @Override
    public int getItemCount() {
        int count = sections.size();
        if (isHavingHeader()){
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0 && isHavingHeader()){
            return TYPE_HEADER;
        }
        if (isHavingHeader()){
            position-=1;
        }
        if (sections.get(position).isCarousel()){
            return TYPE_CAROUSEL;
        }
        return TYPE_LIST;
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
