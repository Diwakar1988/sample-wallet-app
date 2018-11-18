package com.github.diwakar1988.noon;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
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

public class MainActivity extends NoonBaseActivity implements ClientConfigurationService.OnClientConfigurationsLoadListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private ToolbarActionsAdapter toolbarActionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NavigationManager.initialize(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkInternet();
        //check config change on every foreground run, we can optimize here to put restrictions like fetch config in every 12 ours etc.
        ClientConfigurationService.loadFromServer(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_home:
                Toast.makeText(this, String.format("%s clicked",menuItem.getTitle()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_send:
                Toast.makeText(this, String.format("%s clicked",menuItem.getTitle()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_pay_qr:
                Toast.makeText(this, String.format("%s clicked",menuItem.getTitle()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_offers:
                Toast.makeText(this, String.format("%s clicked",menuItem.getTitle()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_wallet:
                Toast.makeText(this, String.format("%s clicked",menuItem.getTitle()), Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
