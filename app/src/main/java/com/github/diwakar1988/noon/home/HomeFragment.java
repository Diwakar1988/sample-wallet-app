package com.github.diwakar1988.noon.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.diwakar1988.noon.R;
import com.github.diwakar1988.noon.common.NoonFragment;
import com.github.diwakar1988.noon.databinding.FragmentHomeBinding;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class HomeFragment extends NoonFragment {
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
    private FragmentHomeBinding binding;
    private ToolbarActionsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ToolbarActionsAdapter(getContext());
        binding.mainActions.setHasFixedSize(true);
        binding.mainActions.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.mainActions.setAdapter(adapter);
        binding.collapsingToolbar.setScrimAnimationDuration(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.mainActions.setAdapter(null);
        binding.unbind();
        binding=null;
    }
}
