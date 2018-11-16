package com.github.diwakar1988.noon.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonBaseActivity;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class NavigationManager {

    private static NavigationManager mInstance=new NavigationManager();
    private static NoonBaseActivity activity;

    private NavigationManager() {
    }

    public static void initialize(NoonBaseActivity activity) {

        NavigationManager.activity = activity;
    }

    public static NavigationManager getInstance() {
        if (activity==null){
            throw new IllegalStateException("NavigationManager.initialize() should be called before calling getInstance()");
        }
        return mInstance;
    }

    public static NoonBaseActivity getActivity() {
        return activity;
    }

    public static void clean() {
        mInstance = null;
        activity = null;
    }

    public void addFragment(Fragment fragment, boolean addToStack) {

        FragmentTransaction ft = activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if (addToStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }

    public void popUpBackStackInclusive(String addToStackTransactionName) {
        activity.getSupportFragmentManager().popBackStack(addToStackTransactionName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void removeFragment(Fragment fragment) {

        if (fragment != null) {
            activity.getSupportFragmentManager().beginTransaction().remove(fragment)
                    .commitAllowingStateLoss();
        }
    }

    public void removeFragment(String fragmentTag) {

        removeFragment(activity.getSupportFragmentManager().findFragmentByTag(fragmentTag));
    }

    public void onBackPressed() {
        activity.onBackPressed();
    }
}
