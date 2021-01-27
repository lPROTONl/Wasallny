package com.example.peter.wasallny.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindViewModel extends ViewModel {

    List<String> stations = Arrays.asList("New El Marg",  "El Marg",    "Ezbet El Nakhl",    "Ain Shams",    "El Matareyya",    "Helmeyet El Zaitoun",    "Hadayeq El Zaitoun",     "Saray El Qobba",    "Hammamat El Qobba",    "Kobri El Qobba",     "Manshiet El Sadr",    "El Demerdash",    "Ghamra",    "Al Shohadaa",    "Orabi",      "Nasser",    "Sadat",     "Saad Zaghloul",   "Al Sayeda Zeinab",     "El Malek El Saleh",    "Mar Girgis",    "El Zahraa",     "Dar El Salam",     "Hadayek El Maadi",     "Maadi",     "Sakanat El Maadi",    "Tora El Balad",     "Kozzika",     "Tora El Asmant",    "El Maasara",    "Hadayek Helwan",     "Wadi Hof",    "Helwan University",     "Ain Helwan",    "Helwan",     "Shubra El Kheima",     "Kolleyyet El Zeraa",      "Mezallat",     "Khalafawy",     "St. Teresa",      "Rod El Farag",     "Masarra",     "Al Shohadaa",     "Attaba",      "Mohamed Naguib",     "Sadat",      "Opera",     "Dokki",      "El Bohoth",     "Cairo University",     "Faisal",      "Giza",      "Omm El Masryeen",    "Sakiat Mekky",       "El Mounib",     "Adly Mansour",      "Huckstep",      "Omar Ibn El Khataab",     "Qibaa",     "Hesham Barakat",      "Nozha",         "El Shams Club",    "Alf Maskan",     "Heliopolis",   "Haroun",      "Al Ahram",     "Koleyet El Banat",      "Stadium",     "Fair Zone",     "Abbassiya",     "Abdou Pasha",     "El Geish",     "Bab El Shaaria",     "Attaba");
    List<Double> stationsLat=Arrays.asList(30.163649,      30.152086,    30.139288,          30.131043,       30.121374,        30.114349,                 30.105198,               30.098078,            30.090337,              30.086967,            30.082214,             30.077256,         30.068970,   30.061848,        30.057330,   30.053555,    30.044352,   30.036560,         30.029236,              30.016876,              30.005849,       29.995243,       29.982033,          29.970105,              29.959830,   29.952835,             29.946332,           29.936170,     29.925850,           29.906112,       29.897193,            29.879440,     29.869025,               29.862657,       29.84888,     30.122483,              30.113778,                 30.105007,      30.097967,       30.088336,         30.080570,          30.071045,     30.061852,         30.052418,     30.045411,            30.044352,    30.042063,   30.038231,    30.035756,       30.025994,              30.017281,     30.010606,   30.005194,            29.995470,            29.981332,       30.146965,           30.143783,       30.140202,                 30.134463,   30.130618,             30.128125,       30.125034,          30.118770,        30.107975,      30.102088,     30.091293,      30.083676,               30.072948,     30.073319,       30.072786,       30.065437,         30.062275,      30.055129,            30.052413);
    List<Double> stationsLong=Arrays.asList(31.338364,     31.335757,    31.324497,          31.319073,       31.313977,        31.313891,                 31.309932,               31.304750,            31.298114,              31.293823,            31.287842,             31.277810,         31.264678,   31.246112,        31.242534,   31.238800,    31.235597,   31.238086,         31.235388,              31.230963,              31.229525,       31.231526,       31.242201,          31.250661,              31.258069,   31.263471,             31.273582,           31.281736,     31.287696,           31.299729,       31.304069,            31.313408,     31.320371,               31.325012,       31.334206,    31.244610,              31.248660,                 31.246739,      31.245393,       31.245457,         31.245425,          31.245033,     31.246111,         31.246863,     31.244255,            31.235597,    31.225169,   31.211999,    31.200198,       31.201190,              31.204001,     31.207091,   31.208008,            31.208620,            31.211822,       31.421234,           31.404322,       31.393667,                 31.383258,   31.372274,             31.360526,       31.348327,          31.340183,        31.338155,      31.333691,     31.326572,      31.328815,               31.317390,     31.301151,       31.284093,       31.276942,         31.267956,      31.257608,            31.246868);


    ArrayList<Float>distanceList=new ArrayList<>();
    Address address;
    float distance;
    List<Address> fromLocationName;

    MutableLiveData<String> nearDestStationMutabel= new MutableLiveData<>();
    MutableLiveData<String> nearLocStationMutabel= new MutableLiveData<>();


    public void findNearestStation(){

            if (Build.VERSION.SDK_INT >= 23){
                if (FindFragment.binding.typedestText.getText().toString().equals("")||FindFragment.binding.typedestText.getText().toString().equals(" ")){
                    Toast.makeText(FindFragment.context, "Please type street name", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake).duration(500).repeat(2).playOn(FindFragment.binding.typedestText);
                    return;
                }
                else if (MainActivity.connectivity.getActiveNetwork()==null){
                    Toast.makeText(FindFragment.context, "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    String destinationName = FindFragment.binding.typedestText.getText().toString();
                    Geocoder geocoder = new Geocoder(FindFragment.context);
                    try {
                        fromLocationName = geocoder.getFromLocationName(destinationName, 10);
                        try {
                            address=fromLocationName.get(0);
                        } catch (Exception e) {
                            Toast.makeText(FindFragment.context, "Try it in English Or try to add st after the street name", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
//                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    Location startPoint;
                    Location endPoint;

                    // Get Near Station To User Destination.
                    try {
                        startPoint = new Location("locationA");
                        startPoint.setLatitude(address.getLatitude());
                        startPoint.setLongitude(address.getLongitude());


                        endPoint = new Location("locationA");
                        for (int j = 0; j < stations.size(); j++) {
                            if (j < stations.size()) {
                                endPoint.setLatitude(stationsLat.get(j));
                                endPoint.setLongitude(stationsLong.get(j));
                                distance = (startPoint.distanceTo(endPoint)) / 1000;
                                distanceList.add(distance);
                            } else {
                                break;
                            }
                            Float min = Collections.min(distanceList);

                            nearDestStationMutabel.setValue(stations.get(distanceList.indexOf(min)));
                        }
                    } catch (Exception e) {
//                Toast.makeText(getActivity(), "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    // Get Near Station To User Location.
                    try {
                        distanceList.clear();

                        startPoint = new Location("locationA");
                        startPoint.setLatitude(MainActivity.latitude);
                        startPoint.setLongitude(MainActivity.longitude);

                        endPoint = new Location("locationA");

                        for (int i = 0; i < stations.size(); i++) {

                                endPoint.setLatitude(stationsLat.get(i));
                                endPoint.setLongitude(stationsLong.get(i));
                                distance = (startPoint.distanceTo(endPoint)) / 1000;
                                distanceList.add(distance);

                            Float min2 = Collections.min(distanceList);

                            nearLocStationMutabel.setValue(stations.get(distanceList.indexOf(min2)));
                        }
                    } catch (Exception e) {
//                Toast.makeText(getActivity(), "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }


            if (Build.VERSION.SDK_INT < 23){
                if (FindFragment.binding.typedestText.getText().toString().equals("")||FindFragment.binding.typedestText.getText().toString().equals(" ")){
                    Toast.makeText(FindFragment.context, "Please type street name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (MainActivity.connectivity.getActiveNetworkInfo()==null){
                    Toast.makeText(FindFragment.context, "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    String destinationName = FindFragment.binding.typedestText.getText().toString();
                    Geocoder geocoder = new Geocoder(FindFragment.context);
                    try {
                        fromLocationName = geocoder.getFromLocationName(destinationName, 10);
                        try {
                            address=fromLocationName.get(0);
                        } catch (Exception e) {
                            Toast.makeText(FindFragment.context, "Try it in English Or try to add st after the street name", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
//                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    Location startPoint;
                    Location endPoint;

                    // Get Near Station To User Destination.
                    try {
                        distanceList.clear();
                        startPoint = new Location("locationA");
                        startPoint.setLatitude(address.getLatitude());
                        startPoint.setLongitude(address.getLongitude());

                        endPoint = new Location("locationA");

                        for (int j = 0; j < stations.size(); j++) {

                                endPoint.setLatitude(stationsLat.get(j));
                                endPoint.setLongitude(stationsLong.get(j));
                                distance = (startPoint.distanceTo(endPoint)) / 1000;
                                distanceList.add(distance);

                            Float min = Collections.min(distanceList);

                            nearDestStationMutabel.setValue(stations.get(distanceList.indexOf(min)));
                        }
                    } catch (Exception e) {
//                Toast.makeText(getActivity(), "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    // Get Near Station To User Location.
                    try {
                        distanceList.clear();
                        startPoint = new Location("locationA");
                        startPoint.setLatitude(MainActivity.latitude);
                        startPoint.setLongitude(MainActivity.longitude);

                        endPoint = new Location("locationA");
                        for (int i = 0; i < stations.size(); i++) {
                            if (i < stations.size()) {
                                endPoint.setLatitude(stationsLat.get(i));
                                endPoint.setLongitude(stationsLong.get(i));
                                distance = (startPoint.distanceTo(endPoint)) / 1000;
                                distanceList.add(distance);
                            } else {
                                break;
                            }
                            Float min = Collections.min(distanceList);

                           nearLocStationMutabel.setValue(stations.get(distanceList.indexOf(min)));

                        }
                    } catch (Exception e) {
//                Toast.makeText(getActivity(), "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
    }
}
