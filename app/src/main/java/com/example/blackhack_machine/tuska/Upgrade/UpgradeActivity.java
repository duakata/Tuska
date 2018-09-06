package com.example.blackhack_machine.tuska.Upgrade;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.blackhack_machine.tuska.Home.HomeFragment;
import com.example.blackhack_machine.tuska.Home.TrafficFragment;
import com.example.blackhack_machine.tuska.Keranjang.StatusProjectFragment;
import com.example.blackhack_machine.tuska.Profile.ProfileFragment;
import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.SessionManager;
import com.example.blackhack_machine.tuska.Utils.BottomNavigationViewHelper;
import com.example.blackhack_machine.tuska.Utils.SectionsPagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class UpgradeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    String getID;
    ImageView backArrow;
    BottomNavigationViewEx bottomNavigationView;
    private static final String TAG = "HomeActivity";
    private static  final int ACTIVITY_NUM = 0;
    private Context mContext = UpgradeActivity.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        backArrow = findViewById(R.id.backArrowUpgrade);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onCLick: navigating back");
                finish();
            }
        });
        setupViewPager();
    }



    private void setupViewPager(){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpgradeHomeFragment()); //index 0
        adapter.addFragment(new TrafficFragment()); // index 1
        adapter.addFragment(new StatusProjectFragment()); // index 2
        adapter.addFragment(new ProfileFragment()); // index 2
        ViewPager viewpager = (ViewPager) findViewById(R.id.container_upgrade);
        viewpager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_upgrade);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("Home");
        tabLayout.getTabAt(1).setText("Template");
        tabLayout.getTabAt(2).setText("Plugin");
        tabLayout.getTabAt(3).setText("Bost Up");
    }
}
