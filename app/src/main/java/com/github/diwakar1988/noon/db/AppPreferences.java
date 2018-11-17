package com.github.diwakar1988.noon.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.github.diwakar1988.noon.NoonApplication;
import com.github.diwakar1988.noon.pojo.ClientConfigurations;
import com.google.gson.Gson;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class AppPreferences {
    private static final String TAG = AppPreferences.class.getSimpleName();
    private static final String NAME = "pref_noonPay";
    private static AppPreferences _instance = new AppPreferences();
    private static final Gson GSON = new Gson();

    protected static final String KEY_CONFIGURATIONS = "settings";
    protected static final String KEY_USER = "user";

    private SharedPreferences preferences;

    private AppPreferences() {
        preferences = NoonApplication.getInstance().getSharedPreferences(
                NAME, Context.MODE_PRIVATE);
    }

    public static AppPreferences getInstance() {
        return _instance;
    }


    /**
     * This Method Clear shared preference.
     */
    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void save() {
        preferences.edit().apply();
    }

    private void setString(String key, String value) {
        if (key != null && value != null) {
            try {
                if (preferences != null) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(key, value);
                    editor.commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String getString(String key, String defaultValue) {
        if (preferences != null && key != null && preferences.contains(key)) {
            return preferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public void saveConfigurations(ClientConfigurations configurations){
        if (configurations==null){
            return;
        }
        String str = new Gson().toJson(configurations,ClientConfigurations.class);
        setString(KEY_CONFIGURATIONS,str);
    }
    public ClientConfigurations getConfigurations(){
        String str = getString(KEY_CONFIGURATIONS,"");
        ClientConfigurations configurations;
        if (TextUtils.isEmpty(str)){
            configurations=new ClientConfigurations();
        }else{
            configurations = new Gson().fromJson(str,ClientConfigurations.class);
        }
        return configurations;
    }
    public void saveUser(User user){
        if (user==null){
            return;
        }
        //Encrypt password before saving
        String str = new Gson().toJson(user,User.class);
        setString(KEY_USER,str);
    }
    public User getUser(){
        String str = getString(KEY_USER,"");
        User user;
        if (TextUtils.isEmpty(str)){
            user=new User("Default User","","","","");
            //for demo
            //user.setUploadedNationalId(true);
        }else{
            user = new Gson().fromJson(str,User.class);
        }
        return user;
    }
}
