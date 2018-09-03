package com.example.blackhack_machine.tuska.Keranjang;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.SessionManager;
import com.example.blackhack_machine.tuska.Utils.SectionsPagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class KeranjangActivity extends AppCompatActivity {
    SessionManager sessionManager;
    String getID;
    BottomNavigationViewEx bottomNavigationView;
    private static final String TAG = "KeranjangActivity";
    ImageView backArrow;
    TextView pembayaranTab;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        Log.d(TAG, "onCreate: starting.");

        backArrow = findViewById(R.id.backArrowKeranjang);
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
        adapter.addFragment(new PemesananFragment()); //index 0
        adapter.addFragment(new WishListFragment()); //index 1
        adapter.addFragment(new StatusPembayaranFragment()); //index 1
        adapter.addFragment(new StatusTransaksiFragment()); //index 1
        final ViewPager viewpager = (ViewPager) findViewById(R.id.container_keranjang);
        viewpager.setAdapter(adapter);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_keranjang);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("KERANJANG");
        tabLayout.getTabAt(1).setText("WISHLIST");
        tabLayout.getTabAt(2).setText("TAGIHAN");
        tabLayout.getTabAt(3).setText("TRANSAKSI");


    }
}
