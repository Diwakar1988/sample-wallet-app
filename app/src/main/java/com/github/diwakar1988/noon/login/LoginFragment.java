package com.github.diwakar1988.noon.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonFragment;
import com.github.diwakar1988.noon.common.OnInputChangeListener;
import com.github.diwakar1988.noon.databinding.FragmentLoginBinding;
import com.github.diwakar1988.noon.utils.KeyboardUtils;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class LoginFragment extends NoonFragment implements OnInputChangeListener{
    private FragmentLoginBinding binding;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.phoneNumberView.hidePhoneCodeSpinner();
        binding.phoneNumberView.setCountryCode("+971");
        binding.phoneNumberView.setPhoneCode("55");

        binding.phoneNumberView.setOnInputChangeListener(this);
        binding.passwordView.setOnInputChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
        binding=null;
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyboardUtils.hideSoftKeyboard(getActivity());
    }

    @Override
    public void onInputChanged() {
        enableSignInButtonIfRequired();
    }

    private void enableSignInButtonIfRequired() {
        binding.buttonLogin.setEnabled(!TextUtils.isEmpty(binding.phoneNumberView.getCountryCode())
                && isValidNumber()
                && !TextUtils.isEmpty(binding.passwordView.getPassword()));
    }

    private boolean isValidNumber() {
        return binding.phoneNumberView.getNumber().trim().length()==10;
    }
}
