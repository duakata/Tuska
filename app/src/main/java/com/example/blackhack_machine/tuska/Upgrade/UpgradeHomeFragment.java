package com.example.blackhack_machine.tuska.Upgrade;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.blackhack_machine.tuska.Home.GridAdapter;
import com.example.blackhack_machine.tuska.R;


public class UpgradeHomeFragment extends Fragment {
    ViewPager viewPager;
    SwipeImageHome adapter;
    private Context mContext = getActivity();
    GridView gridView;

    String[] values = {
            "Buat Toko",
            "Kelola Toko",
            "Upgrade Toko",
            "Store Social",
            "Tukar Point",
            "Open Tiket",
            "Bantuan"
    };

    int[] images = {
            R.drawable.ic_store_small,
            R.drawable.ic_kelola_small,
            R.drawable.ic_upgrade_small,
            R.drawable.ic_storesocial_small,
            R.drawable.ic_point_small,
            R.drawable.ic_tiket_small,
            R.drawable.ic_help_small
    };
    private static final String ARG_PARAM1 = "params";

    public UpgradeHomeFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upgrade_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        adapter = new SwipeImageHome(this.getActivity());
        viewPager.setAdapter(adapter);

        //gridView = (GridView) view.findViewById(R.id.gridUpgradeHome);

       // GridAdapter gridAdapter = new GridAdapter(getActivity(), values, images);
        //gridView.setAdapter(gridAdapter);
        return view;
    }

}