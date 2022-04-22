package com.example.peter.wasallny.ui;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.peter.wasallny.R;
import com.example.peter.wasallny.databinding.FragmentFindBinding;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */

public class FindFragment extends Fragment implements LocationListener {

    FindViewModel findViewModel;
    FragmentFindBinding binding;
    Context context;
    Message message;

//    LocationManager manager;
    double longitude;
    double latitude;
    List<Address> addressList;
    ConnectivityManager connectivity;

    LocationManager manager;
//    @Override
//    public void onDestroy() {
//        Sensey.getInstance().stopShakeDetection(this);
//        Sensey.getInstance().stop();
//        super.onDestroy();
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
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

        binding.langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, Locale.getDefault().toString(), Toast.LENGTH_SHORT).show();
                switch (binding.langSpinner.getSelectedItem().toString()){
                    case "English":
//                        Toast.makeText(context, "Eng", Toast.LENGTH_SHORT).show();
                        binding.mylocationText.setGravity(Gravity.RIGHT);
                        binding.mydestText.setGravity(Gravity.RIGHT);
                        if (message == null && !Locale.getDefault().toString().contains("en")){
//                            Toast.makeText(context, "us"+ message, Toast.LENGTH_SHORT).show();
                            message = new Message("en");
                            EventBus.getDefault().post(message);
                        }
                        else if (message != null && !Locale.getDefault().toString().contains("en")){
//                            Toast.makeText(context, "us", Toast.LENGTH_SHORT).show();
                            message.setMessage("en");
                            EventBus.getDefault().post(message);
                        }
                        break;
                    case "عربي":
                        binding.mylocationText.setGravity(Gravity.LEFT);
                        binding.mydestText.setGravity(Gravity.LEFT);
//                        Toast.makeText(context, "arb", Toast.LENGTH_SHORT).show();
                        if (message == null && !Locale.getDefault().toString().equals("ar")){
//                            Toast.makeText(context, Locale.getDefault().toString(), Toast.LENGTH_SHORT).show();
                            message = new Message("ar");
                            EventBus.getDefault().post(message);
                        }
                        else if (message != null && !Locale.getDefault().toString().equals("ar")){
//                            Toast.makeText(context, Locale.getDefault().toString(), Toast.LENGTH_SHORT).show();
                            message.setMessage("ar");
                            EventBus.getDefault().post(message);
                        }
                        break;
                    default :
                        binding.mylocationText.setGravity(Gravity.RIGHT);
                        binding.mydestText.setGravity(Gravity.RIGHT);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        View view = binding.getRoot();

//        Sensey.getInstance().init(getActivity());
//        Sensey.getInstance().startShakeDetection(this);
//

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

//    @Override
//    public void onShakeDetected() {
//
//    }
//
//    @Override
//    public void onShakeStopped() {
//        binding.typedestText.setText("");
//        binding.mylocationText.setText("");
//        binding.mydestText.setText("");
//    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
//
//        Geocoder geocoder=new Geocoder(getActivity());
//
//
//        try {
//            addressList = geocoder.getFromLocation(latitude, longitude, 1);
//        } catch (IOException e) {
////            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
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

    class Message {
        public String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
