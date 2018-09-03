package com.example.blackhack_machine.tuska.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.blackhack_machine.tuska.Keranjang.KeranjangActivity;
import com.example.blackhack_machine.tuska.Keranjang.StatusProjectFragment;
import com.example.blackhack_machine.tuska.Profile.ProfileFragment;
import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.SessionManager;
import com.example.blackhack_machine.tuska.Utils.BottomNavigationViewHelper;
import com.example.blackhack_machine.tuska.Utils.SectionsPagerAdapter;
import com.example.blackhack_machine.tuska.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    //private TextView name, email;
    //private Button btn_logout;
    Toolbar toolbar;
    SessionManager sessionManager;
    String getID;
    BottomNavigationViewEx bottomNavigationView;
    private static final String TAG = "HomeActivity";
    private static  final int ACTIVITY_NUM = 0;
    private Context mContext = HomeActivity.this;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        ImageView keranjangIMage = (ImageView) findViewById(R.id.shopImage);
        keranjangIMage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d(TAG, "OnClick: navigating to account settings.");
                Intent intent = new Intent(mContext, KeranjangActivity.class);
                startActivity(intent);
            }
        });

/*
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_logout = findViewById(R.id.btn_logout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("iki");
        toolbar.setLogo(android.R.drawable.ic_menu_camera);
        toolbar.setTitle("");


        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);

        name.setText(mName);
        email.setText(mEmail);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sessionManager.logout();
            }
        });*/
        Log.d(TAG, "onCreate: starting.");
        initImageLoader();
        //setupBottomNavigationView();
        setupViewPager();

    }


    private void initImageLoader (){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    //RESPONSE FOR ADDING 3 TABS : CAMERA, HOME, MESSAGE
    private void setupViewPager(){
    SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment()); //index 0
        adapter.addFragment(new CameraFragment()); // index 1
        adapter.addFragment(new StatusProjectFragment()); // index 2
        adapter.addFragment(new ProfileFragment()); // index 2
        ViewPager viewpager = (ViewPager) findViewById(R.id.container_home);
        viewpager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("Home");
        tabLayout.getTabAt(1).setText("My Traffic");
        tabLayout.getTabAt(2).setText("My Store");
        tabLayout.getTabAt(3).setText("My Profile");
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_traffic_white);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_store_white);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_account_white);
    }

    

    //BOTTOM NAVIGATION SETUP
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }



}
