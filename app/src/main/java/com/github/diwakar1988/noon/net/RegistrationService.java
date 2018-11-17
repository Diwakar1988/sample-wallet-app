package com.github.diwakar1988.noon.net;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.diwakar1988.noon.pojo.OTP;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class RegistrationService extends BaseApiService<OTP> {

    public static class RequestData implements Parcelable{
        public String countryCode;
        public String phoneCode;
        public String number;
        public String password;
        public String email;

        public RequestData() {
        }

        protected RequestData(Parcel in) {
            countryCode = in.readString();
            phoneCode = in.readString();
            number = in.readString();
            password = in.readString();
            email = in.readString();
        }

        public static final Creator<RequestData> CREATOR = new Creator<RequestData>() {
            @Override
            public RequestData createFromParcel(Parcel in) {
                return new RequestData(in);
            }

            @Override
            public RequestData[] newArray(int size) {
                return new RequestData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(countryCode);
            parcel.writeString(phoneCode);
            parcel.writeString(number);
            parcel.writeString(password);
            parcel.writeString(email);
        }
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
    public OTP parse(String response) {
        return getGSONParser().fromJson(response,OTP.class);
    }
}
