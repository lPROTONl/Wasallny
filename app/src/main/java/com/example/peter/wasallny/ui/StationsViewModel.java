package com.example.peter.wasallny.ui;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StationsViewModel extends ViewModel {

    List<String> line1 = Arrays.asList("New El Marg", "El Marg", "Ezbet El Nakhl", "Ain Shams", "El Matareyya", "Helmeyet El Zaitoun", "Hadayeq El Zaitoun", "Saray El Qobba", "Hammamat El Qobba", "Kobri El Qobba", "Manshiet El Sadr", "El Demerdash", "Ghamra", "Al Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al Sayeda Zeinab", "El Malek El Saleh", "Mar Girgis", "El Zahraa", "Dar El Salam", "Hadayek El Maadi", "Maadi", "Sakanat El Maadi", "Tora El Balad", "Kozzika", "Tora El Asmant", "El Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan");
    List<String> line2 = Arrays.asList("Shubra El Kheima", "Kolleyyet El Zeraa", "Mezallat", "Khalafawy", "St. Teresa", "Rod El Farag", "Masarra", "Al Shohadaa", "Attaba", "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University", "Faisal", "Giza", "Omm El Masryeen", "Sakiat Mekky", "El Mounib");
    List<String> line3 = Arrays.asList("El Shams Club", "Alf Maskan", "Heliopolis", "Haroun", "Al Ahram", "Koleyet El Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El Geish", "Bab El Shaaria", "Attaba");

    List<String> stations = Arrays.asList("New El Marg",     "El Marg",     "Ezbet El Nakhl",     "Ain Shams",     "El Matareyya",     "Helmeyet El Zaitoun",     "Hadayeq El Zaitoun",     "Saray El Qobba",     "Hammamat El Qobba",      "Kobri El Qobba",       "Manshiet El Sadr",      "El Demerdash",       "Ghamra",       "Al Shohadaa",       "Orabi",       "Nasser",       "Sadat",       "Saad Zaghloul",       "Al Sayeda Zeinab",       "El Malek El Saleh",      "Mar Girgis",      "El Zahraa",      "Dar El Salam",       "Hadayek El Maadi",      "Maadi",      "Sakanat El Maadi",      "Tora El Balad",      "Kozzika",       "Tora El Asmant",      "El Maasara",      "Hadayek Helwan",     "Wadi Hof",      "Helwan University",      "Ain Helwan",       "Helwan",      "Shubra El Kheima",      "Kolleyyet El Zeraa",       "Mezallat",      "Khalafawy",      "St. Teresa",       "Rod El Farag",      "Masarra",      "Attaba",      "Mohamed Naguib",      "Opera",      "Dokki",     "El Bohoth",      "Cairo University",      "Faisal",      "Giza",      "Omm El Masryeen",      "Sakiat Mekky",      "El Mounib",      "El Shams Club",      "Alf Maskan",      "Heliopolis",       "Haroun",      "Al Ahram",      "Koleyet El Banat",      "Stadium",      "Fair Zone",       "Abbassiya",       "Abdou Pasha",      "El Geish",     "Bab El Shaaria");
    List<Double> stationsLat = Arrays.asList(30.163649,       30.152086,     30.139288,            30.131043,       30.121374,          30.114349,                 30.105198,                30.098078,            30.090337,                30.086967,              30.082214,               30.077256,            30.068970,      30.061848,           30.057330,     30.053555,      30.044352,     30.036560,             30.029236,                30.016876,                30.005849,         29.995243,        29.982033,            29.970105,               29.959830,    29.952835,               29.946332,            29.936170,       29.925850,             29.906112,         29.897193,            29.879440,       29.869025,                29.862657,          29.84888,      30.122483,               30.113778,                  30.105007,       30.097967,        30.088336,          30.080570,           30.071045,      30.052418,     30.045411,             30.042063,    30.038231,   30.035756,        30.025994,               30.017281,     30.010606,   30.005194,              29.995470,           29.981332,        30.125034,            30.118770,         30.108072,          30.102088,     30.091293,       30.083676,               30.072948,      30.073319,         30.072786,         30.065437,          30.062275,      30.055129);
    List<Double> stationsLong = Arrays.asList(31.338364,      31.335757,     31.324497,            31.319073,       31.313977,          31.313891,                 31.309932,                31.304750,            31.298114,                31.293823,              31.287842,               31.277810,            31.264678,      31.246112,           31.242534,     31.238800,      31.235597,     31.238086,             31.235388,                31.230963,                31.229525,         31.231526,        31.242201,            31.250661,               31.258069,    31.263471,               31.273582,            31.281736,       31.287696,             31.299729,         31.304069,            31.313408,       31.320371,                31.325012,          31.334206,     31.244610,               31.248660,                  31.246739,       31.245393,        31.245457,          31.245425,           31.245033,      31.246863,     31.244255,             31.225169,    31.211999,   31.200198,        31.201190,                31.204001,    31.207091,   31.208008,              31.208620,           31.211822,        31.348327,            31.340183,         31.338235,          31.333691,     31.326572,       31.328815,               31.317390,      31.301151,         31.284093,         31.276942,          31.267956,      31.257608);

    ArrayList<String> sub = new ArrayList<>();
    ArrayList<String> sub2 = new ArrayList<>();
    ArrayList<String> sub3 = new ArrayList<>();

    float distance;

    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.context);

    Location startPoint;
    Location endPoint;
    ArrayList<Float> distanceList = new ArrayList<>();

    static MutableLiveData<ArrayList<String>> stationsNames = new MutableLiveData<>();

    public void showMap() {

         if (Build.VERSION.SDK_INT >= 23) {
             if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("") || MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase(" ")) {
                 Toast.makeText(MainActivity.context, "Please type your To Station", Toast.LENGTH_LONG).show();
                 return;
             } else if (MainActivity.connectivity.getActiveNetwork() == null) {
                 Toast.makeText(MainActivity.context, "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                 return;
             } else {
                 Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + stationsLat.get(stations.indexOf(MetroLinesFragment.binding.toText.getText().toString())) + "," + stationsLong.get(stations.indexOf(MetroLinesFragment.binding.toText.getText().toString()))));
                 MainActivity.context.startActivity(in);
             }
         } else if (Build.VERSION.SDK_INT < 23) {
             if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("") || MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase(" ")) {
                 Toast.makeText(MainActivity.context, "Please type your To Station", Toast.LENGTH_LONG).show();
                 return;
             } else if (MainActivity.connectivity.getActiveNetworkInfo() == null) {
                 Toast.makeText(MainActivity.context, "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                 return;
             } else {
                 Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + stationsLat.get(stations.indexOf(MetroLinesFragment.binding.toText.getText().toString())) + "," + stationsLong.get(stations.indexOf(MetroLinesFragment.binding.toText.getText().toString()))));
                 MainActivity.context.startActivity(in);
             }

         }
    }
//
//
    public void getNearestStation() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (MainActivity.connectivity.getActiveNetwork() == null) {
                Toast.makeText(MainActivity.context, "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                return;
            } else {
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

                        MetroLinesFragment.binding.nearText.setText("Nearest Station to you is " + stations.get(distanceList.indexOf(min)));

                    }
                } catch (Exception e) {
//                                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        } else if (Build.VERSION.SDK_INT < 23) {

            if (MainActivity.connectivity.getActiveNetworkInfo() == null) {
                Toast.makeText(MainActivity.context, "Check your Internet Connetion", Toast.LENGTH_SHORT).show();
                return;
            } else {
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

                        MetroLinesFragment.binding.nearText.setText("Nearest Station to you is " + stations.get(distanceList.indexOf(min)));

                    }
                } catch (Exception e) {
//                                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        }

    }
    public void getStations() {

                if ((MetroLinesFragment.binding.fromText.getText().toString().equals("") || MetroLinesFragment.binding.fromText.getText().toString().equals(" "))
                        && (MetroLinesFragment.binding.toText.getText().toString().equals("") || MetroLinesFragment.binding.toText.getText().toString().equals(" "))) {
                    Toast.makeText(MainActivity.context, "Please Type Station Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (MetroLinesFragment.binding.fromText.getText().toString().equals("") || MetroLinesFragment.binding.fromText.getText().toString().equals(" ")) {
                    Toast.makeText(MainActivity.context, "Please Type Station Name in From Station", Toast.LENGTH_SHORT).show();
                    return;
                } else if (MetroLinesFragment.binding.toText.getText().toString().equals("") || MetroLinesFragment.binding.toText.getText().toString().equals(" ")) {
                    Toast.makeText(MainActivity.context, "Please Type Station Name in To Station", Toast.LENGTH_SHORT).show();
                    return;
                }

        sub.clear();
        sub2.clear();
        sub3.clear();

//      First Line Marg To Helwan.
        if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()))) {
            sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
            stationsNames.setValue(sub);
//            Toast.makeText(MetroLinesFragment.context, "1 IF", Toast.LENGTH_SHORT).show();
            if (sub.size() <= 9 && sub.size() > 0)
                builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
            else if (sub.size() > 9 && sub.size() <= 16)
                builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
            else if (sub.size() > 16)
                builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();

        }

//      First Line Helwan To Marg.
        else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()))) {
                sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub);
//                Toast.makeText(MetroLinesFragment.context, "2 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//      Second Line Shubra To El Mounib.
        else if (line2.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()))) {
            sub.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
//            Toast.makeText(MetroLinesFragment.context, "3 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//      Second Line El Mounib To Shubra.
        else if (line2.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()))) {
            sub.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
            Collections.reverse(sub);
//            Toast.makeText(MetroLinesFragment.context, "4 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//      Third Line El Ahram To Attaba.
        else if (line3.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()))) {
            sub.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
//            Toast.makeText(MetroLinesFragment.context, "5 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//      Third Line Attaba To El Ahram.
        else if (line3.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()))) {
            sub.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
            Collections.reverse(sub);
//            Toast.makeText(MetroLinesFragment.context, "6 IF", Toast.LENGTH_SHORT).show();
            stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//      Fisrt Line To Second Line Marg To El Mounib.
        else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line2.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line1.indexOf("Al Shohadaa"))) {
            sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line1.indexOf("Al Shohadaa") + 1));
            sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
//            Toast.makeText(MetroLinesFragment.context, "7 IF", Toast.LENGTH_SHORT).show();
                stationsNames.setValue(sub);
                    if (sub.size() <= 9 && sub.size() > 0)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub.size() > 9 && sub.size() <= 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub.size() > 16)
                        builder.setMessage("Stations count  " + sub.size() + "\n" + "Estimated time to your destination " + "\n" + (sub.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Fisrt Line To Second Line Marg To Shubra.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line2.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line1.indexOf("Ghamra") + 1));
                    sub2.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line2.indexOf("Al Shohadaa") + 1));
                    Collections.reverse(sub2);
                    sub3.addAll(sub);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "8 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Second Line To First Line El Mounib To Marg.
                else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line2.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line2.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line1.indexOf("Al Shohadaa") + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "9 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Second Line To First Line Shubra To Marg.
                else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line2.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line1.indexOf("Ghamra") + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line2.indexOf("Al Shohadaa") + 1));
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "10 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              First Line To Second Line Helwan To El Mounib.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line2.contains(MetroLinesFragment.binding.toText.getText().toString())
                        && ((line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Al Shohadaa")) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Sadat"))) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf("Opera"), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                    sub3.addAll(sub);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "11 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              First Line To Second Line Orabi Or Nasser To El Mounib.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line2.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Al Shohadaa")) &&
                        (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line1.indexOf("Sadat"))) {
//                  Toast.makeText(MetroLinesFragment.context, "12 IF", Toast.LENGTH_SHORT).show();
                    if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Orabi") && line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf("Nasser") + 1));
                        sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Orabi") && line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                        sub2.addAll(line2.subList(line2.indexOf("Al Shohadaa"), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Nasser") && line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Nasser"), line1.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Nasser") && line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line2.indexOf("Sadat")) {
                        sub.addAll(line1.subList(line1.indexOf("Nasser"), line1.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line2.indexOf("Sadat") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Second Line To First Line Attaba Or Naguib To Helwan.
                else if (line2.contains(MetroLinesFragment.binding.fromText.getText().toString())
                && line1.contains(MetroLinesFragment.binding.toText.getText().toString())
                && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Al Shohadaa"))
                && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line2.indexOf("Al Shohadaa"))
                && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line2.indexOf("Sadat"))) {
//              Toast.makeText(MetroLinesFragment.context, "13 IF", Toast.LENGTH_SHORT).show();
                    if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Attaba") && line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf("Sadat")));
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Attaba") && line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf("Sadat")));
                        sub2.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line1.indexOf("Sadat") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Mohamed Naguib") && line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Mohamed Naguib") && line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line1.indexOf("Sadat") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              First Line To Second Line Helwan To Attaba Or Naguib.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line2.contains(MetroLinesFragment.binding.toText.getText().toString())
                        && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Al Shohadaa")) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Al Shohadaa")) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Sadat"))) {
//                  Toast.makeText(MetroLinesFragment.context, "14 IF", Toast.LENGTH_SHORT).show();
                    if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("Attaba") && line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("Mohamed Naguib") && line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Sadat")) {
                        sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                        Collections.reverse(sub);
                        sub2.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }


//              First Line To Second Line Helwan To Shubra.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line2.contains(MetroLinesFragment.binding.toText.getText().toString())
                        && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line2.indexOf("Al Shohadaa")));
                    Collections.reverse(sub2);
                    sub3.addAll(sub);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "15 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Second Line To Fisrt Line El Mounib To Helwan.
                else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line2.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line2.indexOf("Sadat")) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                    sub2.addAll(line2.subList(line2.indexOf("Opera"), line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "16 IF", Toast.LENGTH_SHORT).show();
                     stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Second Line To First Line Shubra To Helwan.
                else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line2.contains(MetroLinesFragment.binding.fromText.getText().toString())
                && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line2.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                    sub2.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line2.indexOf("Al Shohadaa") + 1));
                    sub3.addAll(sub2);
                    sub3.addAll(sub);
//                  Toast.makeText(MetroLinesFragment.context, "17 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              First Line To Third Line Marg To Al Ahram.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line3.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line1.indexOf("Al Shohadaa")));
                    sub.addAll(line2.subList(line2.indexOf("Al Shohadaa"), line2.indexOf("Attaba")));
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "18 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//              First Line To Third Line Helwan To Al Ahram.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line3.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub);
                    sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "19 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//              First Line To Third Line Orabi Or Nasser To Al Ahram.
                else if (line1.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line3.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) < line1.indexOf("Sadat"))) {
//              Toast.makeText(MetroLinesFragment.context, "20 IF", Toast.LENGTH_SHORT).show();
                    if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Orabi")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Orabi") + 1));
                        Collections.reverse(sub);
                        sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf("Attaba") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.fromText.getText().toString().equalsIgnoreCase("Nasser")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Nasser") + 1));
                        Collections.reverse(sub);
                        sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf("Attaba") + 1));
                        Collections.reverse(sub2);
                        sub3.addAll(sub);
                        sub3.addAll(sub2);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//              Third Line To First Line Al Ahram To Orabi Or Nasser.
                else if (line3.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line1.contains(MetroLinesFragment.binding.toText.getText().toString())
                        && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Sadat"))) {
//                  Toast.makeText(MetroLinesFragment.context, "21 IF", Toast.LENGTH_SHORT).show();
                    if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("Orabi")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Orabi") + 1));
                        sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf("Attaba") + 1));
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    } else if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("Nasser")) {
                        sub.addAll(line1.subList(line1.indexOf("Al Shohadaa"), line1.indexOf("Nasser") + 1));
                        sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf("Attaba") + 1));
                        sub3.addAll(sub2);
                        sub3.addAll(sub);
                        stationsNames.setValue(sub3);
                    }
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                }

//      Second Line To First Line Mounib to Orabi Or Nasser
        else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line2.contains(MetroLinesFragment.binding.fromText.getText().toString())
                && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Al Shohadaa")) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Sadat"))) {
//            Toast.makeText(MetroLinesFragment.context, "22 IF", Toast.LENGTH_SHORT).show();
            if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("Orabi")) {
                sub.addAll(line1.subList(line1.indexOf("Orabi"), line1.indexOf("Sadat")));
                Collections.reverse(sub);
                sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                Collections.reverse(sub2);
                sub3.addAll(sub2);
                sub3.addAll(sub);
                stationsNames.setValue(sub3);
            } else if (MetroLinesFragment.binding.toText.getText().toString().equalsIgnoreCase("Nasser")) {
                sub.addAll(line1.subList(line1.indexOf("Nasser"), line1.indexOf("Sadat")));
                Collections.reverse(sub);
                sub2.addAll(line2.subList(line2.indexOf("Sadat"), line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                Collections.reverse(sub2);
                sub3.addAll(sub2);
                sub3.addAll(sub);
                stationsNames.setValue(sub3);
            }
            if (sub3.size() <= 9 && sub3.size() > 0)
                builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 3 Pounds").create().show();
            else if (sub3.size() > 9 && sub3.size() <= 16)
                builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
            else if (sub3.size() > 16)
                builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
        }

//              Third Line To First Line Al Ahram To Helwan.
                else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line3.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line1.indexOf("Sadat"))) {
                    sub.addAll(line1.subList(line1.indexOf("Sadat"), line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                    Collections.reverse(sub);
                    sub.addAll(line2.subList(line2.indexOf("Mohamed Naguib"), line2.indexOf("Sadat")));
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "23 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//              Third Line To First Line Al Ahram To Marg.
                else if (line1.contains(MetroLinesFragment.binding.toText.getText().toString()) && line3.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()) < line1.indexOf("Al Shohadaa"))) {
                    sub.addAll(line1.subList(line1.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line1.indexOf("Ghamra") + 1));
                    sub.addAll(line2.subList(line2.indexOf("Al Shohadaa"), line2.indexOf("Attaba")));
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf("Attaba") + 1));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                   Toast.makeText(MetroLinesFragment.context, "24 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//                Second Line To Third Line Shubra To Al Ahram.
                else if (line2.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line3.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) <= line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line2.indexOf("Attaba") + 1));
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "25 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//              Second Line To Third Line El Mounib To Al Ahram.
                else if (line2.contains(MetroLinesFragment.binding.fromText.getText().toString()) && line3.contains(MetroLinesFragment.binding.toText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) > line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(MetroLinesFragment.binding.fromText.getText().toString()) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "26 IF", Toast.LENGTH_SHORT).show();
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//              Third Line To Second Line Al Ahram To El Mounib.
                else if (line2.contains(MetroLinesFragment.binding.toText.getText().toString()) && line3.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) > line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf("Attaba"), line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) + 1));
                    Collections.reverse(sub);
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "27 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }

//              Third Line To Second Line Al Ahram To Shubra.
                else if (line2.contains(MetroLinesFragment.binding.toText.getText().toString()) && line3.contains(MetroLinesFragment.binding.fromText.getText().toString()) && (line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()) <= line2.indexOf("Al Shohadaa"))) {
                    sub.addAll(line2.subList(line2.indexOf(MetroLinesFragment.binding.toText.getText().toString()), line2.indexOf("Attaba") + 1));
                    sub2.addAll(line3.subList(line3.indexOf(MetroLinesFragment.binding.fromText.getText().toString()), line3.indexOf("Attaba")));
                    sub3.addAll(sub);
                    Collections.reverse(sub2);
                    sub3.addAll(sub2);
//                  Toast.makeText(MetroLinesFragment.context, "28 IF", Toast.LENGTH_SHORT).show();
                    Collections.reverse(sub3);
                    stationsNames.setValue(sub3);
                    if (sub3.size() <= 9 && sub3.size() > 0)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 5 Pounds").create().show();
                    else if (sub3.size() > 9 && sub3.size() <= 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 7 Pounds").create().show();
                    else if (sub3.size() > 16)
                        builder.setMessage("Stations count  " + sub3.size() + "\n" + "Estimated time to your destination " + "\n" + (sub3.size() * 2) + " Minutes" + "\n" + "Ticket price is 10 Pounds").create().show();
                }
    }
}
