package com.example.peter.wasallny.ui;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.peter.wasallny.R;
//import com.example.peter.wasallny.databinding.FragmentMetroLinesBinding;
import com.example.peter.wasallny.databinding.FragmentMetroLinesBinding;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.Util;
import com.ornach.nobobutton.NoboButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MetroLinesFragment extends Fragment implements ShakeDetector.ShakeListener, LocationListener {

    List<String> stations = Arrays.asList("New El Marg", "El Marg", "Ezbet El Nakhl", "Ain Shams", "El Matareyya", "Helmeyet El Zaitoun",
            "Hadayeq El Zaitoun", "Saray El Qobba", "Hammamat El Qobba", "Kobri El Qobba", "Manshiet El Sadr", "El Demerdash",
            "Ghamra", "Al Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al Sayeda Zeinab", "El Malek El Saleh", "Mar Girgis",
            "El Zahraa", "Dar El Salam", "Hadayek El Maadi", "Maadi", "Sakanat El Maadi", "Tora El Balad", "Kozzika", "Tora El Asmant",
            "El Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan", "Shubra El Kheima", "Kolleyyet El Zeraa",
            "Mezallat", "Khalafawy", "St. Teresa", "Rod El Farag", "Masarra", "Attaba", "Mohamed Naguib", "Opera", "Dokki", "El Bohoth",
            "Cairo University", "Faisal", "Giza", "Omm El Masryeen", "Sakiat Mekky", "El Mounib", "Adly Mansour", "Huckstep", "Omar Ibn El Khataab",
            "Qibaa", "Hesham Barakat", "Nozha", "El Shams Club", "Alf Maskan", "Heliopolis", "Haroun", "Al Ahram", "Koleyet El Banat",
            "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El Geish", "Bab El Shaaria");

    ArrayAdapter<String> adapter;

    LinearLayoutManager layoutManager;

    FragmentMetroLinesBinding binding;
    StationsViewModel stationsViewModel;

    SubRecyclerAdapter suub;

    ArrayList<String> blankArrayList = new ArrayList<>();

    Context context;
    ConnectivityManager connectivity;
    double longitude;
    double latitude;
    List<Address> addressList;

    NoboButton destinationButton;

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

        context = getActivity();
        connectivity= (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_metro_lines, container, false);
        stationsViewModel = ViewModelProviders.of(getActivity()).get(StationsViewModel.class);
        binding.setViewModel(stationsViewModel);
        binding.setLifecycleOwner(getActivity());

        suub=new SubRecyclerAdapter(context, stationsViewModel.stationsNames);

        View view = binding.getRoot();
        destinationButton = view.findViewById(R.id.destinationButton);

        Sensey.getInstance().init(getActivity());
        Sensey.getInstance().startShakeDetection(this);

        layoutManager = new LinearLayoutManager(getActivity());

        binding.stationlist.setLayoutManager(layoutManager);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stations);

        binding.fromText.setThreshold(1);
        binding.toText.setThreshold(1);
        binding.fromText.setAdapter(adapter);
        binding.toText.setAdapter(adapter);

        manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[]perm={Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(getActivity(),perm,0);
        }
        else{
            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }

        destinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((binding.fromText.getText().toString().equals("") || binding.fromText.getText().toString().equals(" "))
                        && (binding.toText.getText().toString().equals("") || binding.toText.getText().toString().equals(" "))) {
                    Toast.makeText(getActivity(), "Please Type Stations Name", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(binding.fromText);
                    YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(binding.toText);
                    return;
                } else if (binding.fromText.getText().toString().equals("") || binding.fromText.getText().toString().equals(" ")) {
                    Toast.makeText(getActivity(), "Please Type Station Name in From Station", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(binding.fromText);
                    return;
                } else if (binding.toText.getText().toString().equals("") || binding.toText.getText().toString().equals(" ")) {
                    Toast.makeText(getActivity(), "Please Type Station Name in To Station", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(binding.toText);
                    return;
                }
                stationsViewModel.getStations(context,binding.fromText.getText().toString(), binding.toText.getText().toString());
            }
        });

        stationsViewModel.stationsNames.observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> strings) {
                binding.stationlist.setAdapter(suub);
            }
        });

        stationsViewModel.nearStationName.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.nearText.setText("Nearest Station to you is " + s);
            }
        });


        for (int i = 1; i < binding.bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder1 = new TextOutsideCircleButton.Builder().isRound(false).shadowCornerRadius(Util.dp2px(20))
                    .buttonCornerRadius(Util.dp2px(20))
                    .normalText("Map")
                    .textSize(14)
                    .normalImageRes(R.drawable.map)
                    .listener(new OnBMClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onBoomButtonClick(int index) {
                            if (binding.fromText.getText().toString().equalsIgnoreCase("")
                                    || binding.fromText.getText().toString().equalsIgnoreCase(" ")) {
                                Toast.makeText(context, "Please type your From Station", Toast.LENGTH_LONG).show();
                                YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(binding.fromText);
                                return;
                            }
                            stationsViewModel.showMap(context,connectivity,binding.fromText.getText().toString(), latitude, longitude);
                        }
                    });
            binding.bmb.addBuilder(builder1);
        }


        TextOutsideCircleButton.Builder builder2 = new TextOutsideCircleButton.Builder().isRound(false).shadowCornerRadius(Util.dp2px(20))
                .buttonCornerRadius(Util.dp2px(20))
                .normalText("Nearest Station")
                .textSize(14)
                .normalImageRes(R.drawable.near)
                .listener(new OnBMClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onBoomButtonClick(int index) {
                        stationsViewModel.getNearestStation(context, connectivity, latitude, longitude);
                    }
                });

        binding.bmb.addBuilder(builder2);

        return view;
    }

    @Override
    public void onShakeDetected() {

    }

    @Override
    public void onShakeStopped() {
        binding.fromText.setText("");
        binding.toText.setText("");
        stationsViewModel.stationsNames.setValue(blankArrayList);
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

