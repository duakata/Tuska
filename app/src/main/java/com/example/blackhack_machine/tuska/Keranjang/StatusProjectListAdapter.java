package com.example.blackhack_machine.tuska.Keranjang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackhack_machine.tuska.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class StatusProjectListAdapter extends ArrayAdapter<StatusProjectItem> {


    //the hero list that will be displayed
    private List<StatusProjectItem> projectList;

    //the context object
    private Context mCtx;
    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public StatusProjectListAdapter(List<StatusProjectItem> projectList, Context mCtx) {
        super(mCtx, R.layout.project_list_item, projectList);
        this.projectList = projectList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.project_list_item, null, true);
        //getting text views
        TextView txtOrderNum = listViewItem.findViewById(R.id.orderNumText);
        TextView txtLastUpdate = listViewItem.findViewById(R.id.lastUpdateText);
        TextView txtStatusProject = listViewItem.findViewById(R.id.statusProjectText);
        TextView txtPicName = listViewItem.findViewById(R.id.picName);
        TextView txtAttch = listViewItem.findViewById(R.id.linkAttch);
        ImageView imgProject = listViewItem.findViewById(R.id.projectImage);

        //Getting the hero for the specified position
        StatusProjectItem statusProjectItem = projectList.get(position);

        //setting hero values to textviews
        txtOrderNum.setText(statusProjectItem.getOrderNumText());
        txtLastUpdate.setText(statusProjectItem.getLastUpdateText());
        txtStatusProject.setText(statusProjectItem.getstatusProjectText());
        txtPicName.setText(statusProjectItem.getpicName());
        txtAttch.setText(statusProjectItem.getlinkAttch());
        Picasso.get().load(statusProjectItem.getprojectImage())
                .placeholder(R.drawable.ic_wait)
                .resize(100, 90)
                .error(R.drawable.ic_wait)
                .into(imgProject);

        //Picasso.get().load("http://duakata-dev.com/media/img/project/opac.png").into((Target) imgTransaksi);

        // the listitem
        return listViewItem;
    }
}
