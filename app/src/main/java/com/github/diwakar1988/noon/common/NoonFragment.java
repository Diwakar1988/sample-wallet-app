package com.github.diwakar1988.noon.common;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import com.github.diwakar1988.noon.R;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class NoonFragment extends Fragment {
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
}
