package com.github.diwakar1988.noon.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonViewHolder;
import com.github.diwakar1988.noon.databinding.SectionActionItemBinding;
import com.github.diwakar1988.noon.pojo.Section;

import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class SectionActionAdapter extends RecyclerView.Adapter<SectionActionAdapter.SectionActionViewHolder> {

    private List<Section.Action> actions;
    private ItemClickListener<Section.Action> itemClickListener;

    public SectionActionAdapter(List<Section.Action> actions, ItemClickListener<Section.Action> itemClickListener) {

        this.actions = actions;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public SectionActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SectionActionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.section_action_item, parent, false);
        return new SectionActionViewHolder(binding,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionActionViewHolder holder, int position) {
        holder.bind(actions.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SectionActionViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }
    public static class SectionActionViewHolder extends NoonViewHolder<Section.Action> implements View.OnClickListener{
        private SectionActionItemBinding binding;
        private ItemClickListener<Section.Action> itemClickListener;

        public SectionActionViewHolder(SectionActionItemBinding binding, ItemClickListener<Section.Action> itemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemClickListener = itemClickListener;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void bind(Section.Action data) {
            if (binding.getActionVM()!=null){
                binding.getActionVM().update(data);
            }else{
                binding.setActionVM(new SectionActionVM(data));
            }
        }

        @Override
        public void unbind() {
            binding.getActionVM().clean();
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                itemClickListener.onItemClicked(v,binding.getActionVM().getItem());
            }
        }
    }
}
