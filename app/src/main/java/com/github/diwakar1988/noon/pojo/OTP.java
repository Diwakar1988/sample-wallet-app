package com.github.diwakar1988.noon.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class OTP {
    @SerializedName("otp")
    @Expose
    private String otp;

    public OTP(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }
}
