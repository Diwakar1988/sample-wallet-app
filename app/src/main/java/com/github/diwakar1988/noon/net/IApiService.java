package com.github.diwakar1988.noon.net;

import okhttp3.Request;

public interface IApiService<T> extends Runnable{
    void cancel();
    void execute(APIResponseListener<T> listener);
    Request request();
    T parse(String response);
}
