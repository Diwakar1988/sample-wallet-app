package com.github.diwakar1988.noon.net;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.github.diwakar1988.noon.BuildConfig;
import com.github.diwakar1988.noon.NoonApplication;
import com.github.diwakar1988.noon.db.AppPreferences;
import com.github.diwakar1988.noon.pojo.ClientConfigurations;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class ClientConfigurationService extends BaseApiService<ClientConfigurations> {

    public interface OnClientConfigurationsLoadListener{
        void onClientConfigurationsLoaded(ClientConfigurations configurations);
    }
    private static class RequestData {
        public String osVersion=Build.VERSION.RELEASE;
        public String manufacturer=Build.MANUFACTURER;
        public String model = Build.MODEL;
        public String appVersion= BuildConfig.VERSION_NAME;
    }
    private ClientConfigurationService() {

    }
    @Override
    public Request request() {
        //Since our server is a mock server this part is only for demo
        RequestData data  = new RequestData();
        return new Request.Builder()
                .url(URLConstants.URL_CLIENT_CONFIG)
                .headers(getDefaultHeaders())
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),getGSONParser().toJson(data,RequestData.class)))
                .build();
    }

    @Override
    public ClientConfigurations parse(String response) {
        return getGSONParser().fromJson(response,ClientConfigurations.class);
    }
    public static void loadFromServer(final OnClientConfigurationsLoadListener listener){
        new ClientConfigurationService().execute(new APIResponseListener<ClientConfigurations>() {
            @Override
            public void onFail(ApiServiceException e) {
                Log.d(ClientConfigurationService.class.getSimpleName(),"***** ClientConfigurationService failed, ERROR="+e);
            }

            @Override
            public void onSuccess(ClientConfigurations response) {
                Log.d(ClientConfigurationService.class.getSimpleName(),"***** ClientConfigurationService Success");
                //save fetched configs in db for later use
                AppPreferences.getInstance().saveConfigurations(response);
                //cache latest config to avoid app preference reading
                NoonApplication.getInstance().setClientConfigurations(response);
                LocalBroadcastManager.getInstance(NoonApplication.getInstance()).sendBroadcast(new Intent(ClientConfigReceiver.ACTION_CONFIG_UPDATED));
                if (listener!=null){
                    listener.onClientConfigurationsLoaded(response);
                }
            }
        });
    }
    public static ClientConfigurations loadFromFile() throws IOException {
        InputStream is = NoonApplication.getInstance().getAssets().open("config.json");
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        String data=scanner.hasNext()?scanner.next():"";
        ClientConfigurations configurations =  new Gson().fromJson(data, ClientConfigurations.class);
        AppPreferences.getInstance().saveConfigurations(configurations);
        return configurations;

    }
}
