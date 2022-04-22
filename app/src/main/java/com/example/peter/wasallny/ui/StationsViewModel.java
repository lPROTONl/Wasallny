package com.example.peter.wasallny.ui;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import androidx.appcompat.app.AlertDialog;

import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.peter.wasallny.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StationsViewModel extends ViewModel {

    List<String> line1 = Arrays.asList("New El Marg", "El Marg", "Ezbet El Nakhl", "Ain Shams", "El Matareyya", "Helmeyet El Zaitoun", "Hadayeq El Zaitoun", "Saray El Qobba", "Hammamat El Qobba", "Kobri El Qobba", "Manshiet El Sadr", "El Demerdash", "Ghamra", "Al Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al Sayeda Zeinab", "El Malek El Saleh", "Mar Girgis", "El Zahraa", "Dar El Salam", "Hadayek El Maadi", "Maadi", "Sakanat El Maadi", "Tora El Balad", "Kozzika", "Tora El Asmant", "El Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan");
    List<String> line2 = Arrays.asList("Shubra El Kheima", "Kolleyyet El Zeraa", "Mezallat", "Khalafawy", "St. Teresa", "Rod El Farag", "Masarra", "Al Shohadaa", "Attaba", "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University", "Faisal", "Giza", "Omm El Masryeen", "Sakiat Mekky", "El Mounib");
    List<String> line3 = Arrays.asList("Adly Mansour","Huckstep","Omar Ibn El Khataab","Qibaa","Hesham Barakat","Nozha","El Shams Club", "Alf Maskan", "Heliopolis", "Haroun", "Al Ahram", "Koleyet El Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El Geish", "Bab El Shaaria", "Attaba");

    List<String> stations = Arrays.asList("New El Marg",     "El Marg",     "Ezbet El Nakhl",     "Ain Shams",     "El Matareyya",     "Helmeyet El Zaitoun",     "Hadayeq El Zaitoun",     "Saray El Qobba",     "Hammamat El Qobba",      "Kobri El Qobba",       "Manshiet El Sadr",      "El Demerdash",       "Ghamra",       "Al Shohadaa",       "Orabi",       "Nasser",       "Sadat",       "Saad Zaghloul",       "Al Sayeda Zeinab",       "El Malek El Saleh",      "Mar Girgis",      "El Zahraa",      "Dar El Salam",       "Hadayek El Maadi",      "Maadi",      "Sakanat El Maadi",      "Tora El Balad",      "Kozzika",       "Tora El Asmant",      "El Maasara",      "Hadayek Helwan",     "Wadi Hof",      "Helwan University",      "Ain Helwan",       "Helwan",      "Shubra El Kheima",      "Kolleyyet El Zeraa",       "Mezallat",      "Khalafawy",      "St. Teresa",       "Rod El Farag",      "Masarra",      "Attaba",      "Mohamed Naguib",      "Opera",      "Dokki",     "El Bohoth",      "Cairo University",      "Faisal",      "Giza",      "Omm El Masryeen",      "Sakiat Mekky",      "El Mounib",      "Adly Mansour",      "Huckstep",      "Omar Ibn El Khataab",     "Qibaa",     "Hesham Barakat",      "Nozha",         "El Shams Club",      "Alf Maskan",      "Heliopolis",       "Haroun",      "Al Ahram",      "Koleyet El Banat",      "Stadium",      "Fair Zone",       "Abbassiya",       "Abdou Pasha",      "El Geish",     "Bab El Shaaria");
    List<Double> stationsLat = Arrays.asList(30.163649,       30.152086,     30.139288,            30.131043,       30.121374,          30.114349,                 30.105198,                30.098078,            30.090337,                30.086967,              30.082214,               30.077256,            30.068970,      30.061848,           30.057330,     30.053555,      30.044352,     30.036560,             30.029236,                30.016876,                30.005849,         29.995243,        29.982033,            29.970105,               29.959830,    29.952835,               29.946332,            29.936170,       29.925850,             29.906112,         29.897193,            29.879440,       29.869025,                29.862657,          29.84888,      30.122483,               30.113778,                  30.105007,       30.097967,        30.088336,          30.080570,           30.071045,      30.052418,     30.045411,             30.042063,    30.038231,   30.035756,        30.025994,               30.017281,     30.010606,   30.005194,              29.995470,           29.981332,       30.146965,            30.143783,       30.140202,                 30.134463,   30.130618,             30.128125,       30.125034,            30.118770,         30.108072,          30.102088,     30.091293,       30.083676,               30.072948,      30.073319,         30.072786,         30.065437,          30.062275,      30.055129);
    List<Double> stationsLong = Arrays.asList(31.338364,      31.335757,     31.324497,            31.319073,       31.313977,          31.313891,                 31.309932,                31.304750,            31.298114,                31.293823,              31.287842,               31.277810,            31.264678,      31.246112,           31.242534,     31.238800,      31.235597,     31.238086,             31.235388,                31.230963,                31.229525,         31.231526,        31.242201,            31.250661,               31.258069,    31.263471,               31.273582,            31.281736,       31.287696,             31.299729,         31.304069,            31.313408,       31.320371,                31.325012,          31.334206,     31.244610,               31.248660,                  31.246739,       31.245393,        31.245457,          31.245425,           31.245033,      31.246863,     31.244255,             31.225169,    31.211999,   31.200198,        31.201190,                31.204001,    31.207091,   31.208008,              31.208620,           31.211822,       31.421234,            31.404322,       31.393667,                 31.383258,   31.372274,             31.360526,       31.348327,            31.340183,         31.338235,          31.333691,     31.326572,       31.328815,               31.317390,      31.301151,         31.284093,         31.276942,          31.267956,      31.257608);

    ArrayList<String> sub = new ArrayList<>();
    ArrayList<String> sub2 = new ArrayList<>();
    ArrayList<String> sub3 = new ArrayList<>();

    float distance;

    AlertDialog.Builder builder;

    Location startPoint;
    Location endPoint;
    ArrayList<Float> distanceList = new ArrayList<>();

    MutableLiveData<ArrayList<String>> stationsNames = new MutableLiveData<>();
    MutableLiveData<String> nearStationName = new MutableLiveData<>();



    public void showMap(Context context, ConnectivityManager connectivity, String fromText,double latitude, double longitude) {
        
         if (Build.VERSION.SDK_INT >= 23) {
              if (connectivity.getActiveNetwork() == null) {
                 Toast.makeText(context, R.string.Check_your_Internet_Connection, Toast.LENGTH_SHORT).show();
                 return;
             } else {
                 Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" +
                         latitude  + "," + longitude+"&daddr="+
                         stationsLat.get(stations.indexOf(fromText))
                         + "," + stationsLong.get(stations.indexOf(fromText))));
                 context.startActivity(in);
             }
         } else if (Build.VERSION.SDK_INT < 23) {
              if (connectivity.getActiveNetworkInfo() == null) {
                 Toast.makeText(context, R.string.Check_your_Internet_Connection, Toast.LENGTH_SHORT).show();
                 return;
             } else {
                 Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" +
                         latitude  + "," + longitude+"&daddr="+
                         stationsLat.get(stations.indexOf(fromText))
                         + "," + stationsLong.get(stations.indexOf(fromText))+ "&mode=d"));
                 context.startActivity(in);
             }
         }
    }
//
//
    public void getNearestStation(Context context, ConnectivityManager connectivity, double latitude, double longitude) {

        if (Build.VERSION.SDK_INT >= 23) {
            if (connectivity.getActiveNetwork() == null) {
                Toast.makeText(context, R.string.Check_your_Internet_Connection, Toast.LENGTH_SHORT).show();
                return;
            } else {
                try {
                    distanceList.clear();
                    startPoint = new Location("locationA");
                    startPoint.setLatitude(latitude);
                    startPoint.setLongitude(longitude);


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

                        nearStationName.postValue(stations.get(distanceList.indexOf(min)));
                        nearStationName.postValue(stations.get(distanceList.indexOf(min)));

                    }
                } catch (Exception e) {
//                                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        } else if (Build.VERSION.SDK_INT < 23) {

            if (connectivity.getActiveNetworkInfo() == null) {
                Toast.makeText(context, R.string.Check_your_Internet_Connection, Toast.LENGTH_SHORT).show();
                return;
            } else {
                try {
                    distanceList.clear();
                    startPoint = new Location("locationA");
                    startPoint.setLatitude(latitude);
                    startPoint.setLongitude(longitude);


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

                        nearStationName.postValue(stations.get(distanceList.indexOf(min)));
                    }
                } catch (Exception e) {
//                                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        }

    }
    public void getStations(Context context,String fromText, String toText) {

        builder=new AlertDialog.Builder(context);

        sub.clear();
        sub2.clear();
        sub3.clear();

//      First Line Marg To Helwan.
        if (line1.contains(fromText) && (line1.indexOf(fromText) < line1.indexOf(toText))) {
            sub.addAll(line1.subList(line1.indexOf(fromText), line1.indexOf(toText) + 1));
            stationsNames.setValue(sub);
//            Toast.makeText(MetroLinesFragment.context, "1 IF", Toast.LENGTH_SHORT).show();
            if (sub.size() <= 9 && sub.size() > 0)
                builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Helwan Direction").create().show();
            else if (sub.size() > 9 && sub.size() <= 16)
                builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Helwan Direction").create().show();
            else if (sub.size() > 16)
                builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Helwan Direction").create().show();

        }

//      First Line Helwan To Marg.
        else if (line1.contains(toText) && (line1.indexOf(fromText) > line1.indexOf(toText))) {
                sub.addAll(line1.subList(line1.indexOf(toText), line1.indexOf(fromText) + 1));
                    Collections.reverse(sub);
//                Toast.makeText(MetroLinesFragment.context, "2 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"El Marg Direction").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Marg Direction").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Marg Direction").create().show();
                }

//      Second Line Shubra To El Mounib.
        else if (line2.contains(fromText) && (line2.indexOf(fromText) < line2.indexOf(toText))) {
            sub.addAll(line2.subList(line2.indexOf(fromText), line2.indexOf(toText) + 1));
//            Toast.makeText(MetroLinesFragment.context, "3 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Mounib Direction").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Mounib Direction").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Mounib Direction").create().show();
                }

//      Second Line El Mounib To Shubra.
        else if (line2.contains(toText) && (line2.indexOf(fromText) > line2.indexOf(toText))) {
            sub.addAll(line2.subList(line2.indexOf(toText), line2.indexOf(fromText) + 1));
            Collections.reverse(sub);
//            Toast.makeText(MetroLinesFragment.context, "4 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Shubra Direction").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Shubra Direction").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Shubra Direction").create().show();
                }

//      Third Line El Ahram To Attaba.
        else if (line3.contains(fromText) && (line3.indexOf(fromText) < line3.indexOf(toText))) {
            sub.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf(toText) + 1));
//            Toast.makeText(MetroLinesFragment.context, "5 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Attaba Direction").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Attaba Direction").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Attaba Direction").create().show();
                }

//      Third Line Attaba To Adly Mansour.
        else if (line3.contains(toText) && (line3.indexOf(fromText) > line3.indexOf(toText))) {
            sub.addAll(line3.subList(line3.indexOf(toText), line3.indexOf(fromText) + 1));
            Collections.reverse(sub);
//            Toast.makeText(MetroLinesFragment.context, "6 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Adly Mansour Direction").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Adly Mansour Direction").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Adly Mansour Direction").create().show();
                }

//      Fisrt Line To Second Line Marg To El Mounib.
        else if (line1.contains(fromText) && line2.contains(toText) && (line2.indexOf(toText) > line2.indexOf("Al Shohadaa")) && (line1.indexOf(fromText) < line1.indexOf("Al Shohadaa"))) {
            sub.addAll(line1.subList(line1.indexOf(fromText), line1.indexOf("Al Shohadaa") + 1));
            sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(toText) + 1));
//            Toast.makeText(MetroLinesFragment.context, "7 IF", Toast.LENGTH_SHORT).show();
                stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To Mounib Direction").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To Mounib Direction").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To Mounib Direction").create().show();
                }

//              Fisrt Line To Second Line Marg To Shubra.
                else if (line1.contains(fromText) && line2.contains(toText) && (line2.indexOf(toText) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(fromText) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(fromText), line1.indexOf("Ghamra") + 1));
                    sub2.addAll(line2.subList(line2.indexOf(toText), line2.indexOf("Al Shohadaa") + 1));
                    Collections.reverse(sub2);
                    sub3.addAll(sub);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "8 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To Shubra Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To Shubra Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To Shubra Direction").create().show();
                }

//              Second Line To First Line El Mounib To Marg.
                else if (line1.contains(toText) && line2.contains(fromText) && (line2.indexOf(fromText) > line2.indexOf("Al Shohadaa")) && (line1.indexOf(toText) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(toText), line1.indexOf("Al Shohadaa") + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(fromText) + 1));
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "9 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Shubra Direction To Al Shohadaa Station Then Go Up Stairs To El Marg Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Shubra Direction To Al Shohadaa Station Then Go Up Stairs To El Marg Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Shubra Direction To Al Shohadaa Station Then Go Up Stairs To El Marg Direction").create().show();
                }

//              Second Line To First Line Shubra To Marg.
                else if (line1.contains(toText) && line2.contains(fromText) && (line2.indexOf(fromText) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(toText) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(toText), line1.indexOf("Ghamra") + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf(fromText), line2.indexOf("Al Shohadaa") + 1));
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "10 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"El Mounib Direction To Al Shohadaa Station Then Go Up Stairs To El Marg Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Mounib Direction To Al Shohadaa Station Then Go Up Stairs To El Marg Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Mounib Direction To Al Shohadaa Station Then Go Up Stairs To El Marg Direction").create().show();
                }

//              First Line To Second Line Helwan To El Mounib.
                else if (line1.contains(fromText) && line2.contains(toText)
                        && ((line2.indexOf(toText) > line2.indexOf("Al Shohadaa")) && (line2.indexOf(toText) > line2.indexOf("Sadat"))) && (line1.indexOf(fromText) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(fromText) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf("Opera"), line2.indexOf(toText) + 1));
                    sub3.addAll(sub);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "11 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"El Marg Direction To Sadat Station Then Go Down Stairs To El Mounib Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Marg Direction To Sadat Station Then Go Down Stairs To El Mounib Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Marg Direction To Sadat Station Then Go Down Stairs To El Mounib Direction").create().show();
                }

//           **   First Line To Second Line Orabi Or Nasser To El Mounib.
                else if (line1.contains(fromText) && line2.contains(toText) && (line2.indexOf(toText) > line2.indexOf("Al Shohadaa")) &&
                        (line1.indexOf(fromText) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(fromText) < line1.indexOf("Sadat"))) {
//                  Toast.makeText(MetroLinesFragment.context, "12 IF", Toast.LENGTH_SHORT).show();
                    if (fromText.equalsIgnoreCase("Orabi") && line2.indexOf(toText) > line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf("Nasser") + 1));
                        sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(toText) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Orabi") && line2.indexOf(toText) < line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf(fromText) + 1));
                        sub2.addAll(line2.subList(line2.indexOf("Al Shohadaa"), line2.indexOf(toText) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Nasser") && line2.indexOf(toText) > line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Nasser"), line1.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(toText) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Nasser") && line2.indexOf(toText) < line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Nasser"), line1.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line2.subList(line2.indexOf(toText), line2.indexOf("Sadat") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Helwan Direction To Sadat Then Go Down Stairs Mounib Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Helwan Direction To Sadat Then Go Down Stairs Mounib Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Helwan Direction To Sadat Then Go Down Stairs Mounib Direction").create().show();
                }

//          **    Second Line To First Line Attaba Or Naguib To Helwan.
                else if (line2.contains(fromText)
                && line1.contains(toText)
                && (line1.indexOf(toText) > line1.indexOf("Al Shohadaa"))
                && (line2.indexOf(fromText) > line2.indexOf("Al Shohadaa"))
                && (line2.indexOf(fromText) < line2.indexOf("Sadat"))) {
//              Toast.makeText(MetroLinesFragment.context, "13 IF", Toast.LENGTH_SHORT).show();
                    if (fromText.equalsIgnoreCase("Attaba") && line1.indexOf(toText) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf("Sadat")));
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(toText) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Attaba") && line1.indexOf(toText) < line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf("Sadat")));
                        sub2.addAll(line1.subList(line1.indexOf(toText), line1.indexOf("Sadat") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Mohamed Naguib") && line1.indexOf(toText) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(toText) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Mohamed Naguib") && line1.indexOf(toText) < line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf(toText), line1.indexOf("Sadat") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Mounib Direction To Sadat Then Go Up Stairs Helwan Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Mounib Direction To Sadat Then Go Up Stairs Helwan Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Mounib Direction To Sadat Then Go Up Stairs Helwan Direction").create().show();
                }

//          **    First Line To Second Line Helwan To Attaba Or Naguib.
                else if (line1.contains(fromText) && line2.contains(toText)
                        && (line1.indexOf(fromText) > line1.indexOf("Al Shohadaa")) && (line2.indexOf(toText) > line2.indexOf("Al Shohadaa")) && (line2.indexOf(toText) < line1.indexOf("Sadat"))) {
//                  Toast.makeText(MetroLinesFragment.context, "14 IF", Toast.LENGTH_SHORT).show();
                    if (toText.equalsIgnoreCase("Attaba") && line1.indexOf(fromText) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(fromText) + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    } else if (toText.equalsIgnoreCase("Mohamed Naguib") && line1.indexOf(fromText) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(fromText) + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Marg Direction To Sadat Then Go Down Stairs Shubra Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Marg Direction To Sadat Then Go Down Stairs Shubra Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Marg Direction To Sadat Then Go Down Stairs Shubra Direction").create().show();
                }


//              First Line To Second Line Helwan To Shubra.
                else if (line1.contains(fromText) && line2.contains(toText)
                        && (line2.indexOf(toText) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(fromText) > line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf(fromText) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf(toText), line2.indexOf("Al Shohadaa")));
                    Collections.reverse(sub2);
                    sub3.addAll(sub);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "15 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"El Marg Direction To Al Shohadaa Station Then Go Down Stairs To Shubra Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Marg Direction To Al Shohadaa Station Then Go Down Stairs To Shubra Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Marg Direction To Al Shohadaa Station Then Go Down Stairs To Shubra Direction").create().show();
                }

//              Second Line To Fisrt Line El Mounib To Helwan.
                else if (line1.contains(toText) && line2.contains(fromText) && (line2.indexOf(fromText) > line2.indexOf("Sadat")) && (line1.indexOf(toText) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(toText) + 1));
                    sub2.addAll(line2.subList(line2.indexOf("Opera"), line2.indexOf(fromText) + 1));
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "16 IF", Toast.LENGTH_SHORT).show();
                     stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Shubra Direction To Sadat Station Then Go Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Shubra Direction To Sadat Station Then Go Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Shubra Direction To Sadat Station Then Go Up Stairs To Helwan Direction").create().show();
                }

//              Second Line To First Line Shubra To Helwan.
                else if (line1.contains(toText) && line2.contains(fromText)
                && (line2.indexOf(fromText) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(toText) > line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf(toText) + 1));
                    sub2.addAll(line2.subList(line2.indexOf(fromText), line2.indexOf("Al Shohadaa") + 1));
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "17 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"El Mounib Direction To Al Shohadaa Station Then Go Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Mounib Direction To Al Shohadaa Station Then Go Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Mounib Direction To Al Shohadaa Station Then Go Up Stairs To Helwan Direction").create().show();
                }

//              First Line To Third Line Marg To Adly Mansour.
                else if (line1.contains(fromText) && line3.contains(toText) && (line1.indexOf(fromText) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(fromText), line1.indexOf("Al Shohadaa")));
                    sub.addAll(line2.subList(line2.indexOf("Al Shohadaa"), line2.indexOf("Attaba")));
                    sub2.addAll(line3.subList(line3.indexOf(toText), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "18 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To El Mounib Direction To Attaba Station Then Down Stairs To The Third Line").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To El Mounib Direction To Attaba Station Then Down Stairs To The Third Line").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Helwan Direction To Al Shohadaa Station Then Go Down Stairs To El Mounib Direction To Attaba Station Then Down Stairs To The Third Line").create().show();
                }

//              First Line To Third Line Helwan To Adly Mansour.
                else if (line1.contains(fromText) && line3.contains(toText) && (line1.indexOf(fromText) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(fromText) + 1));
                    Collections.reverse(sub);
                    sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                    sub2.addAll(line3.subList(line3.indexOf(toText), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "19 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Marg Direction To Sadat Station Then Go Down Stairs To Shubra Direction To Attaba Station Then Down Stairs To The Third Line").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Marg Direction To Sadat Station Then Go Down Stairs To Shubra Direction To Attaba Station Then Down Stairs To The Third Line").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"El Marg Direction To Sadat Station Then Go Down Stairs To Shubra Direction To Attaba Station Then Down Stairs To The Third Line").create().show();
                }

//          **    First Line To Third Line Orabi Or Nasser To Adly Mansour.
                else if (line1.contains(fromText) && line3.contains(toText) && (line1.indexOf(fromText) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(fromText) < line1.indexOf("Sadat"))) {
//              Toast.makeText(MetroLinesFragment.context, "20 IF", Toast.LENGTH_SHORT).show();
                    if (fromText.equalsIgnoreCase("Orabi")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Orabi") + 1));
                        Collections.reverse(sub);
                        sub2.addAll(line3.subList(line3.indexOf(toText), line3.indexOf("Attaba") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (fromText.equalsIgnoreCase("Nasser")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Nasser") + 1));
                        Collections.reverse(sub);
                        sub2.addAll(line3.subList(line3.indexOf(toText), line3.indexOf("Attaba") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Marg Direction To Al Shohadaa Then Go Down Stairs Mounib Direction To Attaba Then Go Down Stairs To Third Line").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Marg Direction To Al Shohadaa Then Go Down Stairs Mounib Direction To Attaba Then Go Down Stairs To Third Line").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Marg Direction To Al Shohadaa Then Go Down Stairs Mounib Direction To Attaba Then Go Down Stairs To Third Line").create().show();
                }

//          **    Third Line To First Line Adly Mansour To Orabi Or Nasser.
                else if (line3.contains(fromText) && line1.contains(toText)
                        && (line1.indexOf(toText) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(toText) < line1.indexOf("Sadat"))) {
//                  Toast.makeText(MetroLinesFragment.context, "21 IF", Toast.LENGTH_SHORT).show();
                    if (toText.equalsIgnoreCase("Orabi")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Orabi") + 1));
                        sub2.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf("Attaba") + 1));
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    } else if (toText.equalsIgnoreCase("Nasser")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Nasser") + 1));
                        sub2.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf("Attaba") + 1));
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Attaba Direction To Attaba Then Go Up Stairs Shubra Direction To Al Shoahadaa Then Go Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Attaba Direction To Attaba Then Go Up Stairs Shubra Direction To Al Shoahadaa Then Go Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Attaba Direction To Attaba Then Go Up Stairs Shubra Direction To Al Shoahadaa Then Go Up Stairs To Helwan Direction").create().show();
                }

//    **  Second Line To First Line Mounib to Orabi Or Nasser
        else if (line1.contains(toText) && line2.contains(fromText)
                && (line1.indexOf(toText) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(toText) < line1.indexOf("Sadat"))) {
//            Toast.makeText(MetroLinesFragment.context, "22 IF", Toast.LENGTH_SHORT).show();
            if (toText.equalsIgnoreCase("Orabi")) {
                sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf("Sadat")));
                Collections.reverse(sub);
                sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(fromText) + 1));
                Collections.reverse(sub2);
                sub3.addAll(sub2);
                sub3.addAll(sub);
                stationsNames.setValue(sub3);
            } else if (toText.equalsIgnoreCase("Nasser")) {
                sub.addAll(line1.subList(line1.indexOf("Nasser"), line1.indexOf("Sadat")));
                Collections.reverse(sub);
                sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(fromText) + 1));
                Collections.reverse(sub2);
                sub3.addAll(sub2);
                sub3.addAll(sub);
                stationsNames.setValue(sub3);
            }
            if (sub3.size() <= 9 && sub3.size() > 0)
                builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds"+"\n"+"Shubra Direction To Sadat Then Go Up Stairs El Marg Direction").create().show();
            else if (sub3.size() > 9 && sub3.size() <= 16)
                builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Shubra Direction To Sadat Then Go Up Stairs El Marg Direction").create().show();
            else if (sub3.size() > 16)
                builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Shubra Direction To Sadat Then Go Up Stairs El Marg Direction").create().show();
        }

//              Third Line To First Line Adly Mansour To Helwan.
                else if (line1.contains(toText) && line3.contains(fromText) && (line1.indexOf(toText) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(toText) + 1));
                    Collections.reverse(sub);
                    sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                    sub2.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "23 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs To Mounib Direction To Sadat Station Then Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs To Mounib Direction To Sadat Station Then Up Stairs To Helwan Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs To Mounib Direction To Sadat Station Then Up Stairs To Helwan Direction").create().show();
                }

//              Third Line To First Line Adly Mansour To Marg.
                else if (line1.contains(toText) && line3.contains(fromText) && (line1.indexOf(toText) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(toText), line1.indexOf("Ghamra") + 1));
                    sub.addAll(line2.subList(line2.indexOf("Al Shohadaa"), line2.indexOf("Attaba")));
                    sub2.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                   Toast.makeText(MetroLinesFragment.context, "24 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs To Shubra Direction To Al Shohadaa Station Then Up Stairs To El Marg Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs To Shubra Direction To Al Shohadaa Station Then Up Stairs To El Marg Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs To Shubra Direction To Al Shohadaa Station Then Up Stairs To El Marg Direction").create().show();
                }

//                Second Line To Third Line Shubra To Adly Mansour.
                else if (line2.contains(fromText) && line3.contains(toText) && (line2.indexOf(fromText) <= line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf(fromText), line2.indexOf("Attaba") + 1));
                    sub2.addAll(line3.subList(line3.indexOf(toText), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "25 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"El Mounib Direction To Attaba Station Then Go Down Stairs To Third Line").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"El Mounib Direction To Attaba Station Then Go Down Stairs To Third Line").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"El Mounib Direction To Attaba Station Then Go Down Stairs To Third Line").create().show();
                }

//              Second Line To Third Line El Mounib To Adly Mansour.
                else if (line2.contains(fromText) && line3.contains(toText) && (line2.indexOf(fromText) > line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(fromText) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line3.subList(line3.indexOf(toText), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "26 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Shubra Direction To Attaba Station Then Go Down Stairs To Third Line").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Shubra Direction To Attaba Station Then Go Down Stairs To Third Line").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Shubra Direction To Attaba Station Then Go Down Stairs To Third Line").create().show();
                }

//              Third Line To Second Line Adly Mansour To El Mounib.
                else if (line2.contains(toText) && line3.contains(fromText) && (line2.indexOf(toText) > line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(toText) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "27 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs El Mounib Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs El Mounib Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs El Mounib Direction").create().show();
                }

//              Third Line To Second Line Adly Mansour To Shubra.
                else if (line2.contains(toText) && line3.contains(fromText) && (line2.indexOf(toText) <= line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf(toText), line2.indexOf("Attaba") + 1));
                    sub2.addAll(line3.subList(line3.indexOf(fromText), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "28 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs Shubra Direction").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs Shubra Direction").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds"+"\n"+"Attaba Direction Then Go Up Stairs Shubra Direction").create().show();
                }
    }
}
