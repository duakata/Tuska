package com.example.blackhack_machine.tuska.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

import com.example.blackhack_machine.tuska.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    private final String [] values;
    private final int[] images;
    View view;
    LayoutInflater layoutInflater;


    public GridAdapter(Context context, String[] values,  int[] images) {
        this.values = values;
        this.context = context;
        this.images = images;
    }


    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           if (convertView == null){
               view = new View(context);
               view = layoutInflater.inflate(R.layout.single_item, null);
               CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.imageGrid);
               TextView textView = (TextView) view.findViewById(R.id.gridText);
               circleImageView.setImageResource(images[position]);
               textView.setText(values[position]);

           }

           return view;
    }
}
