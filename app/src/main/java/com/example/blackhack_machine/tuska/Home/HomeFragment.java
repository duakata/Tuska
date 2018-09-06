package com.example.blackhack_machine.tuska.Home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.blackhack_machine.tuska.Keranjang.KeranjangActivity;
import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.Upgrade.UpgradeActivity;


public class HomeFragment extends Fragment {
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


  public static HomeFragment newInstance(String params) {
    HomeFragment fragment = new HomeFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, params);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    //imageUrls = getArguments().getString(ARG_PARAM1);
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    gridView = (GridView) view.findViewById(R.id.gridHome);

    GridAdapter gridAdapter = new GridAdapter(getActivity(), values, images);
    gridView.setAdapter(gridAdapter);

    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
          case 0: //BUKA TOKO
              Intent intent0 = new Intent(getActivity(), KeranjangActivity.class);
              getActivity().startActivity(intent0);
              break;
          case 1: //KELOLA TOKO
              break;
          case 2: //UPGRADE TOKO
            Intent intent2 = new Intent(getActivity(), UpgradeActivity.class);
            getActivity().startActivity(intent2);
              break;
          case 3: //STORE SOCIAL
              break;
          case 4: //TUKAR POINT
            break;
          case 5: //OPEN TIKET
            break;
          case 6: //BANTUAN
            break;
        }
      }
    });

    return view;


  }



}