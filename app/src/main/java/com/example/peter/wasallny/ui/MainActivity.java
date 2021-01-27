package com.example.peter.wasallny.ui;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import com.example.peter.wasallny.R;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager manager;
    static double longitude;
    static double latitude;
    static List<Address> addressList;

    static SharedPreferences pref;
    static ConnectivityManager connectivity;

    static Context context;



    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        connectivity= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        getSharedPreferences();

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[]perm={Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,perm,0);
        }
        else{
            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            try {
                manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Geocoder geocoder=new Geocoder(this);


        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
//            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position==0){
                FindFragment fragment=new FindFragment();
                return  fragment;
            }
            else {
                MetroLinesFragment fragment=new MetroLinesFragment();
                return  fragment;
            }
//            else{
//                 TrainLinesFragment fragment=new TrainLinesFragment();
//                 return fragment;
//             }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            if (position==0)
                return  "Find";

            else
                return "Metro Lines";

//            else
//                return "Train Lines";
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public void getSharedPreferences(){


        getSharedPreferences("metrotrain",MODE_PRIVATE).edit().clear().commit();

        pref=getSharedPreferences("metrotrain",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("New El Marg", "موقف المرج للمحافظات(الأسكندرية-الفيوم-اسيوط-سوهاج-قنا-ملوي-المنيا-بني سويف-السويس-بورتو السخنة-الأسماعيلية-فايد-العريش-رفح-القصاصين-القنطرة-التل الكبير-بورسعيد-الطور-نوبيع-العاشر من رمضان-العبور-الخانكة)-الطريق الدائري.");
        editor.putString("El Marg", "نادي المرج الرياضي-مكتب توثيق الشهر العقاري-شارع الحج حسن الفقي-شارع محمد نجيب.");
        editor.putString("Ezbet El Nakhl","شارع الشيخ منصور-شارع يوسف الدجوي-شارع الفريد-شارع عرب الطويلة-شارع الاربعين-شارع عين شمس.");
        editor.putString("Ain Shams","شارع عين شمس-مزلقان العشرين-شارع احمد عصمت-نادي الشمس-شارع الزهراء-كلية الهندسة المطرية-محطة قطار عين شمس.");
        editor.putString("El Matareyya","نادي المطرية الرياضي-شارع المطراوي-اشرفكو-مكتب بريد المطرية.");
        editor.putString("Helmeyet El Zaitoun","ميدان المطرية-جسر السويس-شارع سليم الاول-شارع طمان باي.");
        editor.putString("Hadayeq El Zaitoun","جسر السويس-شارع سليم الاول-شارع طمان باي-شارع العزيز بالله.");
        editor.putString("Saray El Qobba","جسر السويس-مكتب بريد ساري القبة.");
        editor.putString("Hammamat El Qobba","شارع ابن سندر-حديقة ابن سندر-شارع المقريزي.");
        editor.putString("Kobri El Qobba","شارع مصر و السودان-موقف الويلي و الزاوية.");
        editor.putString("Manshiet El Sadr","شارع القائد-شارع غبد الرؤوف السيوفي-شارع الجامع-شارع النعماني-جامعة عين شمس-المدينة الجامعية للطلبة.");
        editor.putString("El Demerdash","جامعة عين شمس-كلية طب جامعة عين شمس-مستشفي الدمرداش-شارع لطفي السيد-شارع احمد سعيد-شارع رمسيس.");
        editor.putString("Ghamra","قصر السكاكيني باشا-مبني التراث الوطني التاريخي-شارع بورسعيد.");
        editor.putString("Al Shohadaa","محطة مصر-الفجالة-شارع كلودبيك-الازبكية-شارع عماد الدين-التوفيقية لقطع غيار السيارات-نفق السبتية.");
        editor.putString("Orabi","نقابة المهن الزاعية-نقابة المهندسين الفرعية-سنيما زاوية--شارع عرابي-شارع رمسيس.");
        editor.putString("Nasser","اسعاف القاهرة-نقابة المحامين-شارع 26يوليو-سينما ميامي-كشري ابو طارق-مول طلعت حرب-شارع طلعت حرب.");
        editor.putString("Sadat","ميدان التحرير-المتحف المصري-كافية ريش-شارع باب اللوق-جريك كامبس-كوبري قصر النيل-فندق نيل ريتز كارلتون-فندق سميراميس.");
        editor.putString("Saad Zaghloul","شارع اسماعيل اباظة-متحف بيت الامة-فندق كمبنسكي-فندق فور سيزونس.");
        editor.putString("Al Sayeda Zeinab","مستشفي القصر العيني-نقابة الاطباء-كلية صيدلة جامعة القاهرة-كلية طب اسنان جامعة القاهرة-قصر الامير محمد علي-المعهد القومي لابحاث الامراض المتوطنة و الكبد-مستشفي 57357.");
        editor.putString("El Malek El Saleh","سور مجري العيون-حديقة الفسطاط.");
        editor.putString("Mar Girgis","مكتب بريد مصر القديمة-درب 1718 للفنون المعاصرة.");
        editor.putString("El Zahraa","المتحف الجيولجي المصري.");
        editor.putString("Dar El Salam","مكتب بريد دار السلام-اكادمية طيبة-كوبري دار السلام-المصرية للاتصالات-نادي الجزيرة.");
        editor.putString("Hadayek El Maadi","المركز المصري لاطفال الانابيب.");
        editor.putString("Maadi","فاميلي لاند-نادي اليخت--شارع 9-.");
        editor.putString("Sakanat El Maadi","ميدان دجلة-مركز دجلة الطبي-الكلية الامريكية بالقاهرة.");
        editor.putString("Kozzika","شارع النفق-مصنع النشا و الجلوكوز.");
        editor.putString("El Maasara","شارع رمضان عبد المنعم-شارع مساكن السكة الحديد-شارع الشيخ سليمان.");
        editor.putString("Hadayek Helwan","مكتب بريد حدائق حلوان.");
        editor.putString("Wadi Hof","شركة النصر للسيارات.");
        editor.putString("Helwan University","جامعة حلوان-المدينة الجامعية لطلبة جامعة حلوان.");
        editor.putString("Helwan","مكتب بريد حلوان-الحديقة اليابانية-مكتب الشهر العقاري.");
        editor.putString("Shubra El Kheima","-مكتب بريد مساكن الفرنواني-ميدان المؤسسة.");
        editor.putString("Kolleyyet El Zeraa","كلية الزراعة جامعة عين شمس-منتزه محمد علي.");
        editor.putString("Mezallat","سينما نيو مودرن-شارع سعيد سالم.");
        editor.putString("Khalafawy","متكب بريد الخلفاوي-عمارات اغاخان-شارع شفيق باشا-شارع جسر البحر-مكتب بريد شبرا.");
        editor.putString("St. Teresa","ميدان المنتزه-شارع المنتزه-شارع ابو طاقية-شارع عبدالخالق وصفي.");
        editor.putString("Rod El Farag","شارع خلوصي.");
        editor.putString("Masarra","شارع الترعة البولاقية-شارع شبرا-مكتب التامينات الاجتماعية.");
        editor.putString("Attaba","حديقة الازبكية-جراج الاوبرا-شارع عبد الخالق ثروت-فندق وندسور القاهرة-مكتب التامينات الاجتماعية-المسرح القومي.");
        editor.putString("Mohamed Naguib","شارع رشدي-شارع محمد فريد-ميدان الجمهورية-مسرح الجمهورية.");
        editor.putString("Opera","حديقة الحرية-دار الاوبرا المصرية-متحف محمود مختار-نادي القاهرة الرياضي.");
        editor.putString("Dokki","المركز الثقافي الروسي.");
        editor.putString("El Bohoth","شارع السودان.");
        editor.putString("Cairo University","جامعة القاهرة-كلية الفنون التطبيقية جامعة حلوان-المجلس الاعلي للجامعات.");
        editor.putString("Faisal","كلية الزراعة جامعة القاهرة-المدينة الجامعية لطالبات جامعة القاهرة-مستشفي الطلبة جامعة القاهرة-كلية الطب البيطري جامعة القاهرة-شارع احمد زويل-شارع الملك فيصل.");
        editor.putString("Giza","مسجد نصر الدين-مكتب الصحة.");
        editor.putString("Omm El Masryeen","شارع البحبوحي-شارع العبد-شارع الشهيد الحي.");
        editor.putString("El Mounib","المصرية للاتصالات-شارع القصبجي.");
        editor.putString("El Shams Club","شارع الفريق عزيز المصري-شارع مصطفي حافظ-شارع فلسطين.");
        editor.putString("Alf Maskan","شارع اسماعيل الفنجري-ميدان الالف مسكن-شارع جسر السويس-شارع الشهيد فريد سميكة-شارع عبدالحميد بدوي.");
        editor.putString("Heliopolis","شارع الحجاز-شارع عبدالعزيز فهمي.");
        editor.putString("Haroun","شارع هارون-شارع ابو بكر الصديق-شارع عمر بن الخطاب.");
        editor.putString("Al Ahram","شارع الاهرام-شارع عثمان ابن عفان.");
        editor.putString("Koleyet El Banat","كلية البنات جامعة عين شمس-شارع احمد تيسير-حديقة الجولف-المصرية للاتصالات-اميدايست.");
        editor.putString("Stadium","شارع يوسف عباس-ستاد القاهرة الدولي.");
        editor.putString("Fair Zone","كوبري الفنجري-شارع صلاح سالم-معرض القاهرة الدولي.");
        editor.putString("Abbassiya","برج نقابة التطبيقين-كلية الطب جامعة عين شمس-موقف اتوبيس العباسية.");
        editor.putString("Abdou Pasha","مكتب العمل-كلية هندسة جامعة عين شمس.");
        editor.putString("El Geish","ميدان الجيش-مسجد السلام.");
        editor.putString("Bab El Shaaria","ميدان باب الشعرية.");
        editor.putString("El-Sad El-Aly","السد العالي.");
        editor.putString("Aswan","جزيرة إلفنتين- متحف النوبة-معبد الفيلة-المسلة الناقصة-دير القديس سيمون-مقابر النبلاء-ضريح آغا خان-فندق كاتاراكت القديم-معبد ابوسيمبل-دير الأنبا سمعان-جزيرة النباتات-متحف النيل-جزيرة سهيل-قبة الهوا.");
        editor.putString("El Khattarah","");
        editor.putString("El Gaafra","نجع الشيخ جراد-نجع الفلاليح.");
        editor.putString("Blana","");
        editor.putString("Daraw","");
        editor.putString("Kom Ombo","منطقة الد إدكاب-معبد سيتي-اديرة البويب-معبد حور محب-هرم الكولا.");
        editor.putString("Klabsha","معبد كلابشة.");
        editor.putString("Idfu","معبد حورس البطلمي-مدينة الكاب-مخازن وصوامع الغلال-الكوم الاحمر-هيكل تحوت-هرم الكولا-دير باخوميوس.");
        editor.putString("El-Sibaiya","قصر ثقافة السباعية.");
        editor.putString("Isna","معبد اسنا-المسجد العمري-مسجد الخن-مسجد السلايمة-كنيسة العذراء مريم والأم دولاجي-دير القديس متاؤس الفاخوري-حديقة الخالدين-الحديقة الدولية-حديقة الكامب-معبد خنوم.");
        editor.putString("Luxor","معبد الكرنك-وادي الملوك و الملكات-معبد الاقصر-معبد الملكة حتشبسوت-متحف الاقصر-معبد هابو-مقابر النبللاء-معبد سيتي الاول-مقبرة توت عنخ امون-تمثالي ممنون- المعبد الجنائزي لأمنحتب الثالث-معبد الأقصر-الرمسيوم-متحف التحنيط-جزيرة الموز-منزل هاورد كارتر.");
        editor.putString("Qift","");
        editor.putString("Qena","معبد حتحور-معبد دندرة-معبد ابيدوس-معبد رمسيس الثاني-هرم احمس-مسجد سيدي عبد الرحمن-دير الشلويط.");
        editor.putString("Dishna","");
        editor.putString("Nag Hammadi"," قصر البرنس يوسف كمال.");
        editor.putString("El-Balyana","معبد سيتي الأول-معبد الاوزريون-معبد رمسيس الثاني- كوم السلطان-شونة الزبيب.");
        editor.putString("Girga","");
        editor.putString("El-Monshaa","البواريك");
        editor.putString("Sohag","معبد ابيدوس-دار الاحمر-ميريت آمون-مسجد العارف بالله-مسجد الفرشوطي-الجبل الغربي.");
        editor.putString("El-Maragha","طريق العارف-بيت الثقافة");
        editor.putString("Tahta","مول سيتي سنتر");
        editor.putString("Tema","الوعاضلة.");
        editor.putString("Sidfa","");
        editor.putString("Abu Tig","حديقة ناصر.");
        editor.putString("Asyut","أثار مير- آثار قصير العمارنة- آثار جبل أسيوط الغربي-آثار دير ريفا-آثار شطب-دير المحرق بالقوصية-حمام ثابت بمدينة أسيوط.");
        editor.putString("Mangbad","");
        editor.putString("Manfalut","");
        editor.putString("El-Qorgas","");
        editor.putString("Dairut","");
        editor.putString("Nazlet abd-Ellah","");
        editor.putString("Deer Mwass","");
        editor.putString("Mallawi","تونه الجبل.");
        editor.putString("Abu Qorgas","");
        editor.putString("El Minya","مقبرة ايزادورا.");
        editor.putString("El Minya Univeristy","");
        editor.putString("Samalut","");
        editor.putString("Matai","");
        editor.putString("Bani Mazar","");
        editor.putString("Aba EL-Wakf","");
        editor.putString("Maghagha","");
        editor.putString("El-Fashn","");
        editor.putString("Beni Suef","هرم ميدوم أولى-جبانة آثار أبو صير-آثار دشاشة-متحف محافظة بنى سويف-كهف سنور-مقبرة مروان بن محمد-دير الحمام.");
        editor.putString("Naser","");
        editor.putString("El Fayoum","هرم هوارة-قرية تونس-مدينة كرانيس الأثرية-قصر قارون-محمية وادي الحيتان-متحف آثار كوم أوشيم-المسجد المعلق-سواقي الفيوم-محمية وشلالات وادي الريان-حدائق عين السيليين السياحية.");
        editor.putString("El-Wasta","");
        editor.putString("Kafr Amar","");
        editor.putString("El-Aiat","");
        editor.putString("El-Mazghona","");
        editor.putString("El-Hawamdia","");
        editor.putString("El-Giza","الأهرامات-أبو الهول-التحف المصري الكبير-هرم سقارة المدرج-أهرامات دهشور-القرية الفرعونية-متحف مركب الشمس-متحف الخزف الإسلامي-متحف مصر للبرديات-المتحف الزراعي-حديقة الأورمان-حديقة الحيوان-حديقة الأسماك.");
        editor.putString("Mahtet Masr","المتحف المصري-متحف الفن الإسلامي-المتحف القبطي-قصر عابدين-مسجد عمرو بن العاص-الكنيسة المُعلقة-مسجد أحمد ابن طولون-شارع المعز-قلعة صلاح الدين-باب زويلة-حديقة الأزهر-خان الخليلي.");
        editor.putString("Qalyub","");
        editor.putString("Shibin El-Qanater","");
        editor.putString("Belbes","");
        editor.putString("El-Zqazyq","");
        editor.putString("Abo Hamad","");
        editor.putString("El-Tal El-Kabeer","");
        editor.putString("Abu Sower","");
        editor.putString("Abu Kebeer","");
        editor.putString("Kafr Saqr","");
        editor.putString("Senbelawain","");
        editor.putString("EL Mansoura","");
        editor.putString("Shrbin","");
        editor.putString("Kafr Saad","");
        editor.putString("Domyat","");
        editor.putString("Menouf","");
        editor.putString("El Bagour","");
        editor.putString("Dahhaak","");
        editor.putString("Qaha","");
        editor.putString("Tukh","");
        editor.putString("Sandanhour station","");
        editor.putString("Banha","");
        editor.putString("El Metras Station","");
        editor.putString("Moharam Pik","الحدية الدولية-موقف اتوبيسات شرق و غرب الدلتا-نادي الصيد.");
        editor.putString("Sidi Gaber","سبورتج-البان سويسرا-مكتبة الاسكندرية.");
        editor.putString("El Sook","");
        editor.putString("El Raml El Meeri","متحف محمود سعيد-سان ستيفانو-متحف قصر المجوهرات المليكة-المتحف البحري القومي-فكتوريا تكنولوجي مول-جليم.");
        editor.putString("Seedi Beshr","شاطئ سيدي بشر-جزيرة الدهب.");
        editor.putString("Al Asafara","");
        editor.putString("El Mandara","");
        editor.putString("El Montaza","حديقة المنتزه-قصر المنتزه-شاطئ المعمورة.");
        editor.putString("El Maamora","");
        editor.putString("Abu Qir","طابية البرج.");
        editor.putString("Alexandria Station","السمرح الرماني-متحف الفنون الجميلة-استاد الاسكندرية-المتحف الروماني-متحف الاسكندرية القومي-حديقة حيوان النزهة-مزرعة المطار.");
        editor.putString("Marsa Matrouh","شاطئ الابيض-شاطئ عجيبة-شاطئ كليوبترا-شاطئ البوسيت-شاطئ روميل-شاطئ الغرام-الكورنيش-شلرع علم الروم.");
        editor.putString("Adco","");
        editor.putString("Al Busayli","");
        editor.putString("Rashid","متحف رشيد-نادي رضيد الرياضي.");
        editor.putString("Tanta","");
        editor.putString("Kafr El-Zaiat","الدلجمون-كفر شماخ-كفر الهواشم-كفر النصرية.");
        editor.putString("Itai El-Barud","عزبة زرزورة-عزبةالشيخ احمد-ربع شنديد-عزبة المجاورة-عزبة دويب-عزبة ظهر الجمل-عزبة نشو.");
        editor.putString("Damnhour","استاد دمنهور-نادي العاب دمنهور الرياضي.");
        editor.putString("Abu Homoss","");
        editor.putString("Kafr El-Dauwar","عزبة خليل-عزبة شكري-الوسطانية-العكريشة-عزبة حسبو.");
        editor.putString("Mahlet Rouh","");
        editor.putString("El Mahalla El Kubra","");
        editor.putString("Talkha","");
        editor.putString("Birkat El-Saba","");
        editor.putString("Quesna","");
        editor.putString("Mnyh Alqmh","");
        editor.putString("Mit Ghamr","");
        editor.putString("Zifta","");
        editor.putString("El-Santa","");
        editor.putString("Agrod","");
        editor.putString("Suez","");
        editor.putString("jenifa","");
        editor.putString("Al AsmalYah","");
        editor.putString("El-Shikh Zayed","");
        editor.putString("East Qantara","");
        editor.putString("Gelbana","");
        editor.putString("Baloza","");
        editor.putString("Bir Al-Abed","");
        editor.putString("El-Shikh Zayed","");
        editor.putString("Algrbyh","");
        editor.putString("Port Said","");

        editor.commit();

        editor.commit();

    }

}
