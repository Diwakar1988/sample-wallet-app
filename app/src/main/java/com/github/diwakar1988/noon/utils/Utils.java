package com.github.diwakar1988.noon.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class Utils {
    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return  (activeNetwork != null && activeNetwork.isConnected());
        }
        return false;
    }
}
