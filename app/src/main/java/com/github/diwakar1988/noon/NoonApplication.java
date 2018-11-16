package com.github.diwakar1988.noon;

import android.app.Application;
import android.util.Log;

import com.github.diwakar1988.noon.utils.AsyncUtil;
import com.github.diwakar1988.noon.utils.CountryUtils;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class NoonApplication extends Application {
    private static NoonApplication instance;

    public static NoonApplication getInstance() {
        if (instance==null){
            throw new IllegalStateException("NoonApplication was not initialized, make sure its added in AndroidManifest.xml");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AsyncUtil.run(new Runnable() {
            @Override
            public void run() {
                try {
                    CountryUtils.load(instance);
                    Log.d(NoonApplication.class.getSimpleName(),"**** countries data loaded.");
                } catch (Exception e) {
                    Log.d(NoonApplication.class.getSimpleName(),"**** countries data loading failed.");
                }
            }
        });
    }
}
