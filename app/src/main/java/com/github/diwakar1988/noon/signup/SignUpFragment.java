package com.github.diwakar1988.noon.signup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.diwakar1988.noon.MainActivity;
import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonFragment;
import com.github.diwakar1988.noon.common.OnInputChangeListener;
import com.github.diwakar1988.noon.databinding.FragmentLoginBinding;
import com.github.diwakar1988.noon.databinding.FragmentSignUpBinding;
import com.github.diwakar1988.noon.net.APIResponseListener;
import com.github.diwakar1988.noon.net.ApiServiceException;
import com.github.diwakar1988.noon.net.LoginService;
import com.github.diwakar1988.noon.net.RegistrationService;
import com.github.diwakar1988.noon.pojo.OTP;
import com.github.diwakar1988.noon.utils.KeyboardUtils;
import com.github.diwakar1988.noon.utils.NavigationManager;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class SignUpFragment extends NoonFragment implements OnInputChangeListener, View.OnClickListener {
    private FragmentSignUpBinding binding;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.phoneNumberView.setCountryCode("+971");
        binding.phoneNumberView.setPhoneCode("55");

        binding.phoneNumberView.setOnInputChangeListener(this);
        binding.passwordView.setOnInputChangeListener(this);
        binding.back.setOnClickListener(this);
        binding.buttonContinue.setOnClickListener(this);
        binding.tvSignin.setOnClickListener(this);
        binding.terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onInputChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
        binding = null;
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
        binding.buttonContinue.setEnabled(!TextUtils.isEmpty(binding.phoneNumberView.getCountryCode())
                && isValidNumber()
                && !TextUtils.isEmpty(binding.passwordView.getPassword())
                && binding.terms.isChecked());
    }

    private boolean isValidNumber() {
        return binding.phoneNumberView.getNumber().trim().length() == 10;
    }

    private void signIn() {
        showProgress(getString(R.string.registering));
        RegistrationService.RequestData data = new RegistrationService.RequestData();
        data.countryCode = binding.phoneNumberView.getCountryCode();
        data.phoneCode = binding.phoneNumberView.getPhoneCode();
        data.number = binding.phoneNumberView.getNumber().trim();
        data.password = binding.passwordView.getPassword();
        data.email = binding.email.getText().toString();
        new RegistrationService(data).execute(new APIResponseListener<OTP>() {
            @Override
            public void onFail(ApiServiceException e) {
                hideProgress();
                Toast.makeText(getContext(), String.format("Error, please try again. Details= %s" + e), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(OTP response) {
                hideProgress();
                //show OTP screen here

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == binding.buttonContinue) {
            signIn();
        } else if (view == binding.tvSignin || view == binding.back) {
            NavigationManager.getInstance().onBackPressed();
        }
    }
}
