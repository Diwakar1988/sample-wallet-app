package com.github.diwakar1988.noon.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.databinding.PasswordBinding;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class PasswordView extends FrameLayout implements View.OnClickListener {
    private PasswordBinding binding;
    private OnInputChangeListener onInputChangeListener;
    public PasswordView(Context context) {
        super(context);
        init();
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.password,null,false);
        binding.show.setOnClickListener(this);
        binding.passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (getOnInputChangeListener()!=null){
                    getOnInputChangeListener().onInputChanged();
                }
            }
        });
        addView(binding.getRoot());
    }

    public String getPassword() {
        return binding.passwordInput.getText().toString();
    }

    public void setOnInputChangeListener(OnInputChangeListener onInputChangeListener) {
        this.onInputChangeListener = onInputChangeListener;
    }

    public OnInputChangeListener getOnInputChangeListener() {
        return onInputChangeListener;
    }
    @Override
    public void onClick(View view) {
        if (view==binding.show){
            showHidePassword();
        }
    }
    private void showHidePassword() {
        EditText et = binding.passwordInput;
        if (et.getText().toString().trim().length()==0){
            return;
        }
        if (et.getInputType()== InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
            //hide password
            binding.show.setText(R.string.show);
            et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            //show password
            et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            binding.show.setText(R.string.hide);
        }
        et.setSelection(et.length());
    }
}
