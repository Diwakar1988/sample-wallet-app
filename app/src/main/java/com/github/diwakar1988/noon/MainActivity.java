package com.github.diwakar1988.noon;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.github.diwakar1988.noon.common.NoonBaseActivity;
import com.github.diwakar1988.noon.databinding.ActivityMainBinding;
import com.github.diwakar1988.noon.home.HomeFragment;
import com.github.diwakar1988.noon.net.ClientConfigurationService;
import com.github.diwakar1988.noon.pojo.ClientConfigurations;
import com.github.diwakar1988.noon.utils.NavigationManager;
import com.github.diwakar1988.noon.utils.Utils;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class MainActivity extends NoonBaseActivity implements ClientConfigurationService.OnClientConfigurationsLoadListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NavigationManager.initialize(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ClientConfigurationService.loadFromServer(this);
        NavigationManager.getInstance().addFragment(HomeFragment.newInstance(), false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkInternet();
    }

    private void checkInternet() {
        if (!Utils.isInternetAvailable(this)) {

            final Snackbar snackBar = Snackbar.make(binding.getRoot(), getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE);
            snackBar.setAction(R.string.dismiss, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackBar.dismiss();
                }
            });
            snackBar.show();
        }
    }

    @Override
    public void onClientConfigurationsLoaded(ClientConfigurations configurations) {

    }
}
