package com.github.diwakar1988.noon.home;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonFragment;
import com.github.diwakar1988.noon.databinding.FragmentHomeBinding;
import com.github.diwakar1988.noon.db.AppPreferences;
import com.github.diwakar1988.noon.net.ClientConfigReceiver;
import com.github.diwakar1988.noon.pojo.ClientConfigurations;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class HomeFragment extends NoonFragment{
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
    private FragmentHomeBinding binding;
    private SectionsAdapter sectionsAdapter;
    private ClientConfigReceiver configReceiver = new ClientConfigReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            switch (intent.getAction()){
                case ACTION_CONFIG_UPDATED:
                    fillSections(AppPreferences.getInstance().getConfigurations());
                    break;
            }
        }
    };

    private void fillSections(ClientConfigurations configurations) {
        sectionsAdapter = new SectionsAdapter(configurations.getSections());
        addUploadNationalIdViewIfRequired();
        binding.sections.setAdapter(sectionsAdapter);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void addUploadNationalIdViewIfRequired() {
        if (AppPreferences.getInstance().getUser().isUploadedNationalId()){
            //national id already uploaded, don't show upload view
            return;
        }
        sectionsAdapter.setHeaderView(R.layout.upload_national_id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(configReceiver,new IntentFilter(ClientConfigReceiver.ACTION_CONFIG_UPDATED));
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //sections list
        binding.sections.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        binding.sections.addItemDecoration(verticalDecoration);

        ClientConfigurations configurations = AppPreferences.getInstance().getConfigurations();
        if (configurations!=null){
            fillSections(configurations);
        }else{
            sectionsAdapter = new SectionsAdapter(null);
            addUploadNationalIdViewIfRequired();
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.sections.setAdapter(sectionsAdapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(configReceiver);
        binding.sections.setAdapter(null);
        binding.unbind();
        binding=null;
    }
}
