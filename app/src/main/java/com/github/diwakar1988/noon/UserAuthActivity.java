package com.github.diwakar1988.noon;

import android.os.Bundle;

import com.github.diwakar1988.noon.common.NoonBaseActivity;
import com.github.diwakar1988.noon.login.LoginFragment;
import com.github.diwakar1988.noon.net.ClientConfigurationService;
import com.github.diwakar1988.noon.utils.NavigationManager;

public class UserAuthActivity extends NoonBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ClientConfigurationService.loadFromServer(null);
        NavigationManager.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);
        NavigationManager.getInstance().addFragment(LoginFragment.newInstance(),false);
    }
}
