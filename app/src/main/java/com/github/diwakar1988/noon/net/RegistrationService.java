package com.github.diwakar1988.noon.net;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class RegistrationService extends BaseApiService<String> {

    public static class RequestData {
        public String countryISO;
        public String phoneCode;
        public String number;
        public String password;
        public String email;
    }
    private RequestData data;

    public RegistrationService(RequestData data) {
        this.data = data;
    }
    @Override
    public Request request() {
        //Since our server is a mock server this part is only for demo
        return new Request.Builder()
                .url(URLConstants.URL_SIGN_UP)
                .headers(getDefaultHeaders())
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),getGSONParser().toJson(data,RequestData.class)))
                .build();
    }

    @Override
    public String parse(String response) {
        return getGSONParser().fromJson(response,String.class);
    }
}
