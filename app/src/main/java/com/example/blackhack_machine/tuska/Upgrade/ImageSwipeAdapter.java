package com.example.blackhack_machine.tuska.Upgrade;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.ImageView;

import com.example.blackhack_machine.tuska.R;
public class ImageSwipeAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {
            R.drawable.promo1,
            R.drawable.promo2,
            R.drawable.promo3,
            R.drawable.promo4,
            R.drawable.promo5,
    };

    public ImageSwipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount(){
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.upgradehome_layout_swipe, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_upgradehome);
        imageView.setImageResource(images[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       ViewPager vp = (ViewPager) container;
       View view = (View) object;
        vp.removeView(view);
    }

}
