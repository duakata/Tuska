package com.example.blackhack_machine.tuska.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.blackhack_machine.tuska.R;


public class HomeFragment extends Fragment {

  GridView gridView;

  String[] values = {
    "Order Android",
          "Order Desktop",
          "Order Website",
          "Open Ticket",
          "Store Social",
          "Point Saya",
          "Bantuan"
  };

  int[] images = {
    R.drawable.ic_android_green,
          R.drawable.ic_desktop_blue,
          R.drawable.ic_website_orange,
          R.drawable.ic_opentiket_brown,
          R.drawable.ic_storesocial_purple,
          R.drawable.ic_point_blue,
          R.drawable.ic_help_pink
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
    return view;
  }
}