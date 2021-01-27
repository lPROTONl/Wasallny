package com.example.peter.wasallny.ui;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.peter.wasallny.R;
import com.example.peter.wasallny.databinding.FragmentFindBinding;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;


/**
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends Fragment implements ShakeDetector.ShakeListener {

    FindViewModel findViewModel;
    static FragmentFindBinding binding;
    static Context context;

    @Override
    public void onDestroy() {
        Sensey.getInstance().stopShakeDetection(this);
        Sensey.getInstance().stop();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getContext();

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_find, container, false);
        findViewModel= ViewModelProviders.of(getActivity()).get(FindViewModel.class);
        binding.setViewModel(findViewModel);
        binding.setLifecycleOwner(getActivity());

        View view = binding.getRoot();

        Sensey.getInstance().init(getActivity());
        Sensey.getInstance().startShakeDetection(this);


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

    @Override
    public void onShakeDetected() {

    }

    @Override
    public void onShakeStopped() {
        binding.typedestText.setText("");
        binding.mylocationText.setText("");
        binding.mydestText.setText("");
    }
}
