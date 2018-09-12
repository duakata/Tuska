package com.example.blackhack_machine.tuska.Keranjang;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackhack_machine.tuska.Home.PreviewWebActivity;
import com.example.blackhack_machine.tuska.PublicURL;
import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatusProjectFragment extends Fragment {
  private static final String TAG = "CameraFragment";

  ListView listView;
  List<StatusProjectItem> projectList;
  String getEmail;
  SessionManager sessionManager;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.fragment_project, container, false);
    sessionManager = new SessionManager(getActivity());
    sessionManager.checkLogin();
    HashMap<String, String> user = sessionManager.getUserDetail();
    String mName = user.get(sessionManager.NAME);
    getEmail = user.get(SessionManager.EMAIL);

    listView = (ListView) view.findViewById(R.id.listView);
    projectList = new ArrayList<>();
    getUserDetail();
/*
    listView.setOnClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public  void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          String Templistview = listViewItem[position].toString();
      }
      }

    }*/



    return view;

  }

  private void getUserDetail() {
   /* final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage("Loading....");
    progressDialog.show();*/

    StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicURL.URL_KERANJANG_LIST_PROJECT,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
               // progressDialog.dismiss();
                Log.i(TAG, response.toString());
                try {

                  JSONArray array = new JSONArray(response);


                  for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    StatusProjectItem statusProjectItem = new StatusProjectItem(object.getString("NO_ORDER"),
                            "Last Update : " + object.getString("LAST_UPDATE"),
                            object.getString("STATUS"),
                            "PIC : " + object.getString("PIC_PROJECT"),
                            object.getString("LINK"),
                            object.getString("PICT_PROJECT"));
                    projectList.add(statusProjectItem);
                  }

                  StatusProjectListAdapter adapter = new StatusProjectListAdapter(projectList, getActivity());
                  listView.setAdapter(adapter);

                } catch (JSONException e) {
                  e.printStackTrace();
                  //progressDialog.dismiss();
                  Toast.makeText(getActivity(), "Error Reading "+e.toString(), Toast.LENGTH_SHORT).show();
                }
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error Reading "+error.toString(), Toast.LENGTH_SHORT).show();
              }
            }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("id", getEmail);
        return params;
      }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(stringRequest);
  }


  public void loadWebPage (View v){
    Intent intent = new Intent(getActivity(), PreviewWebActivity.class);
    startActivity(intent);
  }
}

