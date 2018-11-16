package com.github.diwakar1988.noon;

import android.os.Bundle;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonBaseActivity;
import com.github.diwakar1988.noon.home.HomeFragment;
import com.github.diwakar1988.noon.net.ClientConfigurationService;
import com.github.diwakar1988.noon.pojo.ClientConfigurations;
import com.github.diwakar1988.noon.utils.NavigationManager;
/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class MainActivity extends NoonBaseActivity implements ClientConfigurationService.OnClientConfigurationsLoadListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NavigationManager.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientConfigurationService.loadFromServer(this);
        NavigationManager.getInstance().addFragment(HomeFragment.newInstance(),false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NavigationManager.clean();
    }

    @Override
    public void onClientConfigurationsLoaded(ClientConfigurations configurations) {

    }
}
