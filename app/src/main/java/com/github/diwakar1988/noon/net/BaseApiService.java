package com.github.diwakar1988.noon.net;

import android.text.TextUtils;

import com.github.diwakar1988.noon.utils.AsyncUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class BaseApiService<T> implements IApiService<T>{
    private static OkHttpClient client;
    private static Gson gson;
    private static Headers defaultHeaders;
    static {
        //I am using default client, you can configure this client as per your need
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .build();
        gson = new Gson();
        defaultHeaders = new Headers.Builder()
                .add("Content-Type","application/json; charset=UTF-8")
                .add("Accept","application/json; charset=UTF-8")
                .build();
    }

    private Call call;
    private WeakReference<APIResponseListener<T>> listener;

    @Override
    public final void cancel() {
        if (call!=null){
            call.cancel();
        }
    }

    @Override
    public final void execute(final APIResponseListener<T> listener) {
        this.listener = new WeakReference<>(listener);
        AsyncUtil.run(this);
    }

    @Override
    public final void run() {
        try {
            Request request  =request();
            call = client.newCall(request);
            Response response = call.execute();
            if (response.isSuccessful()){
                notifySuccess(parse(response.body().string()));
            }else{
                String msg = response.message();
                if (TextUtils.isEmpty(msg)){
                    msg = "error unknown.";
                }
                notifyFailed(new ApiServiceException(response.code(),msg));
            }
        } catch (Exception e) {
            notifyFailed(new ApiServiceException(-1,e));
        }
    }
    private void notifyFailed(final ApiServiceException e){
        if (listener==null){
            return;
        }
        AsyncUtil.runOnUi(new Runnable() {
            @Override
            public void run() {
                if (listener.get()!=null){
                    listener.get().onFail(e);
                }
            }
        });
    }
    private void notifySuccess(final T response){
        if (listener==null){
            return;
        }
        AsyncUtil.runOnUi(new Runnable() {
            @Override
            public void run() {
                if (listener.get()!=null){
                    listener.get().onSuccess(response);
                }
            }
        });

    }
    protected Headers getDefaultHeaders(){
        return defaultHeaders;
    }

    protected Gson getGSONParser() {
        return gson;
    }
}
