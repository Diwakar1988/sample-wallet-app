package com.github.diwakar1988.noon.home;

import android.os.Bundle;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonBaseActivity;
import com.github.diwakar1988.noon.utils.NavigationManager;
/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class MainActivity extends NoonBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NavigationManager.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NavigationManager.clean();
    }
}
