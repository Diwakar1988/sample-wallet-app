package com.github.diwakar1988.noon.common;

import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.diwakar1988.noon.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class NoonFragment extends Fragment{

    private static final int LOCK_REQUEST_CODE = 221;
    private static final int SECURITY_SETTING_REQUEST_CODE = 233;

    private ProgressDialog mProgressDialog;
    protected final void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(getContext(), "",
                    message, true);
            ProgressBar spinner = new android.widget.ProgressBar(
                    getContext(),
                    null,
                    android.R.attr.progressBarStyle);
            Drawable drawable= spinner.getIndeterminateDrawable();
            drawable.setColorFilter(getResources().getColor(R.color.noon), PorterDuff.Mode.MULTIPLY);
            mProgressDialog.setIndeterminateDrawable(drawable);
        } else if (mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
        } else {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(true);
    }

    protected final void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * We can use this method multiple times like during SEND_MONEY etc.
     */
    protected void askBioMetricAuthentication() {
//        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(getActivity());
//        if (!fingerprintManager.isHardwareDetected() || !fingerprintManager.hasEnrolledFingerprints()){
//            promptForOtherDeviceAuthMechenism();
//        }
        promptForOtherDeviceAuthMechenism();
    }

    private void promptForOtherDeviceAuthMechenism() {
        //Get the instance of KeyGuardManager
        KeyguardManager keyguardManager = (KeyguardManager) getContext().getSystemService(Context.KEYGUARD_SERVICE);

        //Check if the device version is greater than or equal to Lollipop(21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Create an intent to open device screen lock screen to authenticate
            //Pass the Screen Lock screen Title and Description
            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.biometric_title), getResources().getString(R.string.biometric_subtitle));
            try {
                //Start activity for result
                startActivityForResult(i, LOCK_REQUEST_CODE);
            } catch (Exception e) {

                //If some exception occurs means Screen lock is not set up please set screen lock
                //Open Security screen directly to enable patter lock
                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                try {

                    //Start activity for result
                    startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE);
                } catch (Exception ex) {

                    Toast.makeText(getContext(), "unable to find any Security settings, please set screen lock manually.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    protected void onDeviceAuthenticationSuccessful() { }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //If screen lock authentication is success update text
                    onDeviceAuthenticationSuccessful();
                }
                break;
            case SECURITY_SETTING_REQUEST_CODE:
                //When user is enabled Security settings then we don't get any kind of RESULT_OK
                //So we need to check whether device has enabled screen lock or not
                if (isDeviceSecure()) {
                    askBioMetricAuthentication();
                } else {
                    Toast.makeText(getContext(), "Device lock is required, please setup.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    /**
     * method to return whether device has screen lock enabled or not
     **/
    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getContext().getSystemService(Context.KEYGUARD_SERVICE);

        //this method only work whose api level is greater than or equal to Jelly_Bean (16)
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();
        //You can also use keyguardManager.isDeviceSecure(); but it requires API Level 23

    }

}
