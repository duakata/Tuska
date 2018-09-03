package com.example.blackhack_machine.tuska.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.Utils.BottomNavigationViewHelper;
import com.example.blackhack_machine.tuska.Utils.SectionsStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {
   private static final String TAG = "AccountSettingsActivity";
    private static  final int ACTIVITY_NUM = 4;
   private Context mContext;

   private SectionsStatePagerAdapter pagerAdapter;
   private ViewPager mViewPager;
   private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);
       mContext = AccountSettingsActivity.this;
        Log.d(TAG,"onCreate: started.");
        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);


        setupSettingList();
        setupFragments();
        setupBottomNavigationView();
        //SETUP BACKARROW
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onCLick: navigating back");
                finish();
            }
        });

        //SETUP SIGN OUT

    }

    private void setupFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); //fragment 0
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); //fragment 1
    }

    private void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG,"setViewPager: Navigating to fragment': " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);

    }

    private void setupSettingList(){
        Log.d(TAG,"setupSettingList: initializing 'Account Settings list.'");
        ListView listView = (ListView) findViewById(R.id.lvAccountSetting);
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); //fragment 0
        options.add(getString(R.string.sign_out_fragment)); // fragment 1

        ArrayAdapter adapter = new ArrayAdapter(mContext,
                android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"onItemClick : Navigating to fragment': " + position);
setViewPager(position);
            }
        });
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
