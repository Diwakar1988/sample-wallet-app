package com.github.diwakar1988.noon.common;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.databinding.CountryBottomSheetItemBinding;

import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class CountryBottomSheetItemAdapter extends RecyclerView.Adapter<CountryBottomSheetItemAdapter.CountryViewHolder> {

    private List<CountryItem> mItems;
    private BottomSheetItemListener mListener;

    public CountryBottomSheetItemAdapter(List<CountryItem> items, BottomSheetItemListener listener) {
        mItems = items;
        mListener = listener;
    }

    public void setListener(BottomSheetItemListener listener) {
        mListener = listener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CountryBottomSheetItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.country_bottom_sheet_item, parent, false);
        return new CountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CountryViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class CountryViewHolder extends NoonViewHolder<CountryItem> implements View.OnClickListener {

        private CountryBottomSheetItemBinding binding;

        public CountryViewHolder(CountryBottomSheetItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void bind(CountryItem item) {
            binding.setCountryVM(item);
        }

        @Override
        public void unbind() {
            binding.getCountryVM().clean();
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(binding.getCountryVM());
            }
        }
    }

    public interface BottomSheetItemListener {
        void onItemClick(CountryItem item);
    }
    public static class CountryItem extends BaseObservable implements ViewModel {

        private String flag;
        private String isoCode;
        private String name;
        private String phoneCode;
        private boolean selected;

        public CountryItem(String flag, String isoCode, String name, String phoneCode) {
            this.flag = TextUtils.isEmpty(flag) ?"\uD83C\uDDEB\uD83C\uDDF0":flag;
            this.isoCode = isoCode;
            this.name = name;
            this.phoneCode = phoneCode;
        }

        public String getFlag() {
            return flag;
        }

        public String getIsoCode() {
            return isoCode;
        }

        public String getName() {
            return name;
        }

        public String getPhoneCode() {
            return phoneCode;
        }

        public String getTitle() {
            return String.format("%s (%s)",getName(),getPhoneCode());
        }

        @Override
        public void clean() {

        }

        @Bindable
        public boolean getSelected() {
            return selected;
        }
        @BindingAdapter("select")
        public static void select(CheckBox checkbox, boolean isSelected) {
            checkbox.setChecked(isSelected);
        }
        public void setSelected(boolean selected) {
            this.selected = selected;
            notifyChange();
        }
    }
}

