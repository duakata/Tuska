package com.example.blackhack_machine.tuska.Upgrade;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.blackhack_machine.tuska.R;

public class SwipeImageHome extends PagerAdapter {
    private int[] image_resource = {R.drawable.promo4, R.drawable.promo3, R.drawable.promo2, R.drawable.promo1};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public SwipeImageHome(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.upgradehome_layout_swipe, container, false);
        ImageView imageview = (ImageView) item_view.findViewById(R.id.image_view);
        //TextView textView = (TextView)  item_view.findViewById(R.id.image_count);
        imageview.setImageResource(image_resource[position]);
        imageview.setScaleType(ImageView.ScaleType.FIT_XY);
        //textView.setText("" + position);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}