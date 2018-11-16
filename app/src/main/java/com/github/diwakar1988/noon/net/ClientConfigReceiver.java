package com.github.diwakar1988.noon.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.github.diwakar1988.noon.BuildConfig;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public abstract class ClientConfigReceiver extends BroadcastReceiver {
    public static final String ACTION_CONFIG_UPDATED = BuildConfig.APPLICATION_ID+".action.config.updated";
    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
