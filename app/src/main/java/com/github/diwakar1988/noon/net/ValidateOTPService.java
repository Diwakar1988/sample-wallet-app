package com.github.diwakar1988.noon.net;

import com.github.diwakar1988.noon.pojo.OTP;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class ValidateOTPService extends BaseApiService<String> {
    private OTP otp;

    public ValidateOTPService(String otp) {
        this.otp = new OTP(otp);
    }


    @Override
    public Request request() {
        //Since our server is a mock server this part is only for demo
        return new Request.Builder()
                .url(URLConstants.URL_VALIDATE_OTP)
                .headers(getDefaultHeaders())
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),getGSONParser().toJson(otp,OTP.class)))
                .build();
    }

    @Override
    public String parse(String response) {
        return response;
    }
}
