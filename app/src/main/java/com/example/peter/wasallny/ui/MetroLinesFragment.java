package com.example.peter.wasallny.ui;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
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

import com.example.peter.wasallny.R;
//import com.example.peter.wasallny.databinding.FragmentMetroLinesBinding;
import com.example.peter.wasallny.databinding.FragmentMetroLinesBinding;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MetroLinesFragment extends Fragment implements ShakeDetector.ShakeListener {

    List<String> stations = Arrays.asList("New El Marg", "El Marg", "Ezbet El Nakhl", "Ain Shams", "El Matareyya", "Helmeyet El Zaitoun",
            "Hadayeq El Zaitoun", "Saray El Qobba", "Hammamat El Qobba", "Kobri El Qobba", "Manshiet El Sadr", "El Demerdash",
            "Ghamra", "Al Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al Sayeda Zeinab", "El Malek El Saleh", "Mar Girgis",
            "El Zahraa", "Dar El Salam", "Hadayek El Maadi", "Maadi", "Sakanat El Maadi", "Tora El Balad", "Kozzika", "Tora El Asmant",
            "El Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan", "Shubra El Kheima", "Kolleyyet El Zeraa",
            "Mezallat", "Khalafawy", "St. Teresa", "Rod El Farag", "Masarra", "Attaba", "Mohamed Naguib", "Opera", "Dokki", "El Bohoth",
            "Cairo University", "Faisal", "Giza", "Omm El Masryeen", "Sakiat Mekky", "El Mounib", "El Shams Club", "Alf Maskan", "Heliopolis",
            "Haroun", "Al Ahram", "Koleyet El Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El Geish", "Bab El Shaaria");

    ArrayAdapter<String> adapter;

    LinearLayoutManager layoutManager;

    static FragmentMetroLinesBinding binding;
    StationsViewModel stationsViewModel;

    SubRecyclerAdapter suub=new SubRecyclerAdapter();

    ArrayList<String> blankArrayList = new ArrayList<>();

    @Override
    public void onDestroy() {
            Sensey.getInstance().stopShakeDetection(this);
            Sensey.getInstance().stop();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_metro_lines, container, false);
        stationsViewModel = ViewModelProviders.of(getActivity()).get(StationsViewModel.class);
        binding.setViewModel(stationsViewModel);
        binding.setLifecycleOwner(getActivity());

        View view = binding.getRoot();

        Sensey.getInstance().init(getActivity());
        Sensey.getInstance().startShakeDetection(this);

        layoutManager = new LinearLayoutManager(getActivity());

        binding.stationlist.setLayoutManager(layoutManager);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stations);

        binding.fromText.setThreshold(1);
        binding.toText.setThreshold(1);
        binding.fromText.setAdapter(adapter);
        binding.toText.setAdapter(adapter);


        stationsViewModel.stationsNames.observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> strings) {
                binding.stationlist.setAdapter(suub);
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

                            stationsViewModel.showMap();
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
                        stationsViewModel.getNearestStation();
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
}

