package com.example.peter.wasallny.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.peter.wasallny.R;
import com.example.peter.wasallny.databinding.FragmentFindBinding;


/**
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends Fragment  {

    FindViewModel findViewModel;
    static FragmentFindBinding binding;
    static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getContext();

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_find, container, false);
        findViewModel= ViewModelProviders.of(getActivity()).get(FindViewModel.class);
        binding.setViewModel(findViewModel);
        binding.setLifecycleOwner(getActivity());

        View view = binding.getRoot();

        findViewModel.nearLocStationMutabel.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.mylocationText.setText(s);
            }
        });
        findViewModel.nearDestStationMutabel.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.mydestText.setText(s);
            }
        });

        return view;
    }

}
