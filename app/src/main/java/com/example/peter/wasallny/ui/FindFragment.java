package com.example.peter.wasallny.ui;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import androidx.databinding.DataBindingUtil;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
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

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends Fragment implements ShakeDetector.ShakeListener, LocationListener {

    FindViewModel findViewModel;
    FragmentFindBinding binding;
    Context context;

//    LocationManager manager;
    double longitude;
    double latitude;
    List<Address> addressList;
    ConnectivityManager connectivity;

    LocationManager manager;
    @Override
    public void onDestroy() {
        Sensey.getInstance().stopShakeDetection(this);
        Sensey.getInstance().stop();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            try {
                manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        connectivity= (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_find, container, false);
        findViewModel= ViewModelProviders.of(getActivity()).get(FindViewModel.class);
        binding.setViewModel(findViewModel);

        manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[]perm={Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(getActivity(),perm,0);
        }
        else{
            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }

        binding.setLifecycleOwner(getActivity());
        binding.GetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.typedestText.getText().toString().equalsIgnoreCase("")){
                    YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(binding.typedestText);
                    return;
                }
                findViewModel.findNearestStation(binding.typedestText.getText().toString(),context, connectivity, latitude, longitude);
            }
        });

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




//        manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            String[]perm={Manifest.permission.ACCESS_FINE_LOCATION};
//            ActivityCompat.requestPermissions(getActivity(),perm,0);
//        }
//        else{
//            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
//        }

        return view;
    }

    @Override
    public void onDestroyView() {
        binding.GetButton.setOnClickListener(null);
        binding = null;
        findViewModel = null;
        super.onDestroyView();
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

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Geocoder geocoder=new Geocoder(getActivity());


        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
//            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
