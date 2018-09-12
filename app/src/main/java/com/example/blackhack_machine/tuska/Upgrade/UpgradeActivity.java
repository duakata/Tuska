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
import android.widget.GridView;
import android.widget.ImageView;

import com.example.blackhack_machine.tuska.Upgrade.GridAdapterUpgradeHome;
import com.example.blackhack_machine.tuska.Home.HomeFragment;
import com.example.blackhack_machine.tuska.Home.TrafficFragment;
import com.example.blackhack_machine.tuska.Keranjang.StatusProjectFragment;
import com.example.blackhack_machine.tuska.Profile.ProfileFragment;
import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.SessionManager;
import com.example.blackhack_machine.tuska.Utils.BottomNavigationViewHelper;
import com.example.blackhack_machine.tuska.Utils.SectionsPagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.example.blackhack_machine.tuska.Upgrade.ImageSwipeAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;
import android.support.v4.content.ContextCompat;


public class UpgradeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    String getID;
    ImageView backArrow;
    BottomNavigationViewEx bottomNavigationView;
    private static final String TAG = "HomeActivity";
    private static  final int ACTIVITY_NUM = 0;
    private Context mContext = UpgradeActivity.this;
    ViewPager viewPager;
    SwipeImageHome adapter;
    GridView gridView;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;


    String[] values = {
            "Template",
            "Promote",
            "Plugin",
            "Batch",
            "WishList",
            "My Upgrade",
    };

    int[] images = {
            R.drawable.ic_template_small,
            R.drawable.ic_promote_small,
            R.drawable.ic_plugin_small,
            R.drawable.ic_batch_small,
            R.drawable.ic_wishlist_small,
            R.drawable.ic_myupgrade_small
    };
    private static final String ARG_PARAM1 = "params";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        gridView = (GridView) findViewById(R.id.gridUpgadeHome);
        GridAdapterUpgradeHome gridAdapterUpgradeHome = new GridAdapterUpgradeHome(this, values, images);
        gridView.setAdapter(gridAdapterUpgradeHome);

        viewPager = (ViewPager) findViewById(R.id.view_pager_upgradehome);
        sliderDotspanel = (LinearLayout) findViewById(R.id.dotSlide);

       ImageSwipeAdapter imageSwipeAdapter = new ImageSwipeAdapter(this);
       viewPager.setAdapter(imageSwipeAdapter);


        dotscount = imageSwipeAdapter.getCount();
        dots = new ImageView[dotscount];
        for(int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        backArrow = findViewById(R.id.backArrowUpgrade);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onCLick: navigating back");
                finish();
            }
        });
    }


    public class MyTimerTask extends TimerTask{
        @Override
        public void run() {

            UpgradeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }

}



      /*  backArrow = findViewById(R.id.backArrowUpgrade);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onCLick: navigating back");
                finish();
            }
        });
        //setupViewPager();
    }




/*
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
}*/
