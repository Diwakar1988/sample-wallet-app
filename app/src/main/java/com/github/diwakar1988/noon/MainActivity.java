package com.github.diwakar1988.noon;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.github.diwakar1988.noon.common.NoonBaseActivity;
import com.github.diwakar1988.noon.databinding.ActivityMainBinding;
import com.github.diwakar1988.noon.home.HomeFragment;
import com.github.diwakar1988.noon.home.ToolbarActionsAdapter;
import com.github.diwakar1988.noon.net.ClientConfigurationService;
import com.github.diwakar1988.noon.pojo.ClientConfigurations;
import com.github.diwakar1988.noon.utils.NavigationManager;
import com.github.diwakar1988.noon.utils.Utils;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class MainActivity extends NoonBaseActivity implements ClientConfigurationService.OnClientConfigurationsLoadListener {

    private ActivityMainBinding binding;
    private ToolbarActionsAdapter toolbarActionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NavigationManager.initialize(this);
        ClientConfigurationService.loadFromServer(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.collapsingToolbar.setScrimAnimationDuration(0);
        binding.profileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "User Profile Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        //main actions
        toolbarActionsAdapter = new ToolbarActionsAdapter(this);
        binding.mainActions.setHasFixedSize(true);
        binding.mainActions.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.mainActions.setAdapter(toolbarActionsAdapter);

        //for demo(and time saving) only adding single fragment otherwise we can add here a view pager of fragments also
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.mainActions.setAdapter(null);
    }
}
