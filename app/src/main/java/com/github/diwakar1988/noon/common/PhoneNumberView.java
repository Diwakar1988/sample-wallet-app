package com.github.diwakar1988.noon.common;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.databinding.CountryBottomSheetBinding;
import com.github.diwakar1988.noon.databinding.PhoneNumberBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class PhoneNumberView extends FrameLayout implements View.OnClickListener {
    private PhoneNumberBinding binding;

    public PhoneNumberView(Context context) {
        super(context);
        init();
    }

    public PhoneNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.phone_number,null,false);
        binding.countryCode.setOnClickListener(this);
        binding.phoneCode.setOnClickListener(this);
        addView(binding.getRoot());
    }

    public CountryBottomSheetItemAdapter.CountryItem getCountryItem(){
        if (binding.countryCode.getTag()==null || !(binding.countryCode.getTag() instanceof CountryBottomSheetItemAdapter.CountryItem)){
            return null;
        }
        return  (CountryBottomSheetItemAdapter.CountryItem) binding.countryCode.getTag();
    }
    public String getNumber() {
        return binding.numberInput.getText().toString();
    }
    public String getCountryCode() {
        return binding.countryCode.getText().toString();
    }
    public void setCountryCode(String countryCode) {
        binding.countryCode.setText(countryCode);
    }
    public String getPhoneCode() {
        return binding.phoneCode.getText().toString();
    }
    public void setPhoneCode(String code) {
        binding.phoneCode.setText(code);
    }

    @Override
    public void onClick(View view) {
        if (view==binding.countryCode){
            openBottomSheet(view);
        }
        else if (view==binding.phoneCode){
            openDropDown(view);
        }
    }

    public void hidePhoneCodeSpinner(){
        binding.phoneCode.setVisibility(GONE);
    }

    private void openDropDown(View view) {
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        popup.getMenuInflater()
                .inflate(R.menu.phone_code, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                binding.phoneCode.setText(item.getTitle());
                return true;
            }
        });
        popup.show();
    }

    private void openBottomSheet(View v) {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getContext());
        final CountryBottomSheetBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.country_bottom_sheet, null, false);
        binding.rvBottomSheet.setHasFixedSize(true);
        binding.rvBottomSheet.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getContext(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        binding.rvBottomSheet.addItemDecoration(verticalDecoration);
        List<CountryBottomSheetItemAdapter.CountryItem> items = createBottomSheetItems(getContext());
        //select default
        for (CountryBottomSheetItemAdapter.CountryItem item :
                items) {
            if (item.getPhoneCode().equals(this.getCountryCode())){
                item.setSelected(true);
                break;
            }
        }
        binding.rvBottomSheet.setAdapter(new CountryBottomSheetItemAdapter(items, new CountryBottomSheetItemAdapter.BottomSheetItemListener() {
            @Override
            public void onItemClick(CountryBottomSheetItemAdapter.CountryItem item) {
                if (mBottomSheetDialog != null) {
                    mBottomSheetDialog.dismiss();
                }
                PhoneNumberView.this.binding.countryCode.setText(item.getPhoneCode());
                PhoneNumberView.this.binding.countryCode.setTag(item);
            }
        }));

        mBottomSheetDialog.setContentView(binding.getRoot());
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });

        binding.close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetDialog != null) {
                    mBottomSheetDialog.dismiss();
                }
            }
        });
    }
    private List<CountryBottomSheetItemAdapter.CountryItem> createBottomSheetItems(Context context) {
        //we can use our CountryUtils when there are multiple countries but for DEMO I am adding only 3 countries
        ArrayList<CountryBottomSheetItemAdapter.CountryItem> items = new ArrayList<>();
        items.add(new CountryBottomSheetItemAdapter.CountryItem("\uD83C\uDDE6\uD83C\uDDEA", "AE","United Arab Emirates", "+971"));
        items.add(new CountryBottomSheetItemAdapter.CountryItem("\uD83C\uDDF8\uD83C\uDDE6", "SA","Saudi Arabia", "+966"));
        items.add(new CountryBottomSheetItemAdapter.CountryItem("\uD83C\uDDF0\uD83C\uDDFC", "KW","Kuwait", "+965"));
        return items;
    }

}
