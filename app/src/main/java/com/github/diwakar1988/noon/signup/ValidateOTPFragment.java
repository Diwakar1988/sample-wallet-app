package com.github.diwakar1988.noon.signup;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonFragment;
import com.github.diwakar1988.noon.common.OnInputChangeListener;
import com.github.diwakar1988.noon.databinding.FragmentOtpBinding;
import com.github.diwakar1988.noon.login.LoginFragment;
import com.github.diwakar1988.noon.net.APIResponseListener;
import com.github.diwakar1988.noon.net.ApiServiceException;
import com.github.diwakar1988.noon.net.RegistrationService;
import com.github.diwakar1988.noon.net.ValidateOTPService;
import com.github.diwakar1988.noon.pojo.OTP;
import com.github.diwakar1988.noon.utils.KeyboardUtils;
import com.github.diwakar1988.noon.utils.NavigationManager;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class ValidateOTPFragment extends NoonFragment implements OnInputChangeListener, View.OnClickListener {
    private static final String KEY_SIGN_UP_DATA = "data";
    private FragmentOtpBinding binding;
    private RegistrationService.RequestData signUpData;

    public static ValidateOTPFragment newInstance(RegistrationService.RequestData signUpData) {
        ValidateOTPFragment fragment=new ValidateOTPFragment();
        Bundle args=new Bundle();
        args.putParcelable(KEY_SIGN_UP_DATA,signUpData);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpData = getArguments().getParcelable(KEY_SIGN_UP_DATA);
        binding.back.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        binding.resendCode.setOnClickListener(this);
        binding.otpDescription.setText(String.format(getString(R.string.otp_description),signUpData.number.substring(5)));
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
        binding.submit.setEnabled(false);
    }

    private void validateOTP() {
        showProgress(getString(R.string.validating));
        String otp="";
        new ValidateOTPService(otp).execute(new APIResponseListener<String>() {
            @Override
            public void onFail(ApiServiceException e) {
                hideProgress();
                Toast.makeText(getContext(), String.format("Error, please try again. Details= %s" , e), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String response) {
                hideProgress();
                //show OTP screen here
                NavigationManager.getInstance().popUpBackStackInclusive(SignUpFragment.class.getSimpleName());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == binding.submit) {
            validateOTP();
        } else if (view == binding.resendCode) {
            resendCode();
        } else if (view == binding.back) {
            NavigationManager.getInstance().onBackPressed();
        }
    }

    private void resendCode() {
        showProgress(getString(R.string.sending));
        new RegistrationService(signUpData).execute(new APIResponseListener<OTP>() {
            @Override
            public void onFail(ApiServiceException e) {
                hideProgress();
                Toast.makeText(getContext(), String.format("Error, please try again. Details= %s" , e), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(OTP response) {
                hideProgress();
                //show OTP screen here
                Toast.makeText(getContext(), "OTP sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
