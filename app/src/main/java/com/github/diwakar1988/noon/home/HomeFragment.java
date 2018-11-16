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
public class HomeFragment extends NoonFragment {
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
    private FragmentHomeBinding binding;
    private ToolbarActionsAdapter toolbarActionsAdapter;
    private SectionsAdapter sectionsAdapter;
    private ClientConfigReceiver configReceiver = new ClientConfigReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            switch (intent.getAction()){
                case ACTION_CONFIG_UPDATED:
                    sectionsAdapter = new SectionsAdapter(AppPreferences.getInstance().getConfigurations().getSections());
                    binding.sections.setAdapter(sectionsAdapter);
                    binding.progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    };
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
        binding.collapsingToolbar.setScrimAnimationDuration(0);

        //main actions
        toolbarActionsAdapter = new ToolbarActionsAdapter(getContext());
        binding.mainActions.setHasFixedSize(true);
        binding.mainActions.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.mainActions.setAdapter(toolbarActionsAdapter);

        //sections list
        ClientConfigurations configurations = AppPreferences.getInstance().getConfigurations();
        if (configurations!=null){
            sectionsAdapter = new SectionsAdapter(configurations.getSections());
        }else{
            sectionsAdapter = new SectionsAdapter(null);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        binding.sections.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        binding.sections.addItemDecoration(verticalDecoration);
        binding.sections.setAdapter(sectionsAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(configReceiver);
        binding.mainActions.setAdapter(null);
        binding.sections.setAdapter(null);
        binding.unbind();
        binding=null;
    }
}
