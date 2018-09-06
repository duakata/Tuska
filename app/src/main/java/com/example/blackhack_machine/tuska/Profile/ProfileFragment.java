package com.example.blackhack_machine.tuska.Profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackhack_machine.tuska.LoginActivity;
import com.example.blackhack_machine.tuska.PublicURL;
import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.SessionManager;
import com.example.blackhack_machine.tuska.Utils.BottomNavigationViewHelper;
import com.example.blackhack_machine.tuska.Utils.GridImageAdapter;
import com.example.blackhack_machine.tuska.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileActivity";

    BottomNavigationViewEx bottomNavigationView;
    SessionManager sessionManager;
    private static  final int ACTIVITY_NUM = 4;
    private Context mContext = getActivity();
    private ProgressBar mProgressBar;
    private ImageView profilePhoto, pictVerified;
    CircleImageView profileImage;

    private TextView displayName, topBarName, phoneNumberAcc, emailAcc, statusAcc, countProject, countProses, countDone;
    String getID, getEmail;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activy_profile, container, false);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        displayName = view.findViewById(R.id.display_name);
        //topBarName = view.findViewById(R.id.profileName);
        profileImage = view.findViewById(R.id.profile_image);
        phoneNumberAcc = view.findViewById(R.id.phoneNumberAcc);
        emailAcc = view.findViewById(R.id.emailAcc);
        statusAcc = view.findViewById(R.id.statusAcc);
        countProject = view.findViewById(R.id.countProject);
        countProses = view.findViewById(R.id.countProses);
        countDone = view.findViewById(R.id.countDone);
        pictVerified = view.findViewById(R.id.pictVerified);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        getID = user.get(SessionManager.ID);
        getEmail = user.get(SessionManager.EMAIL);
        //String mEmail = user.get(sessionManager.EMAIL);
        //topBarName.setText("My Profile");
        Log.d(TAG, "onCreate: started.");
       mProgressBar = (ProgressBar) view.findViewById(R.id.profileProgressBar);
       mProgressBar.setVisibility(View.GONE);

        ImageView img = (ImageView) view.findViewById(R.id.transaksiImage);
        Context c = getActivity().getApplicationContext();




        getCountDone();
       getCountProjects();
       getUserDetail();
       getCountProses();
       // setupToolbar();
       // setupActivityWidgets();
       //setProfileImage();
        //tempGridSetup();
        return view;

    }






    private void getUserDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicURL.URL_PROFILE_GETDETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject((response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strPhone = object.getString("phonenum").trim();
                                    String strStatus = object.getString("status").trim();
                                    String strPict = object.getString("pict").trim();

                                    displayName.setText(strName);
                                    phoneNumberAcc.setText(strPhone);
                                    emailAcc.setText(strEmail);

                                    if(strStatus.equals("Aktif")){
                                        statusAcc.setText(strStatus);
                                        statusAcc.setTextColor(getResources().getColor(R.color.colorGreen));
                                        statusAcc.setTypeface(statusAcc.getTypeface(), Typeface.BOLD);
                                        pictVerified.setVisibility(View.VISIBLE);

                                    }else{
                                        statusAcc.setText(strStatus);
                                        statusAcc.setTextColor(getResources().getColor(R.color.colorRed));
                                        statusAcc.setTypeface(statusAcc.getTypeface(), Typeface.BOLD);
                                        pictVerified.setVisibility(View.GONE);
                                    }

                                    //regDateAcc.setText(strDateReg);

                                    Picasso.get().load(strPict)
                                            .fit()
                                            .error(R.drawable.ic_wait)
                                            .into(profileImage);
                                }
                            }
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
                params.put("email", getEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getCountProjects() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Tunggu Sebentar....");
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicURL.URL_PROFILE_COUNT_PROJECT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String strCountProject = object.getString("_project").trim();
                                if(strCountProject.equals("0")){
                                    countProject.setText("150");

                                }else{
                                    countProject.setText(strCountProject);
                                }

                            }

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
                       // progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error Reading "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", getEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }




    private void getCountProses() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Tunggu Sebentar....");
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicURL.URL_PROFILE_COUNT_PROSES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String strCountProses = object.getString("_proses").trim();
                                if(strCountProses.equals("0")){
                                    countProses.setText("1250");

                                }else{
                                    countProses.setText(strCountProses);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                           // progressDialog.dismiss();
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
                params.put("email", getEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getCountDone() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Tunggu Sebentar....");
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicURL.URL_PROFILE_COUNT_DONE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String strCountDone = object.getString("_done").trim();

                                if(strCountDone.equals("0")){
                                    //countDone.setText("350");
                                    countDone.setText(strCountDone);

                                }else{
                                    countDone.setText(strCountDone);
                                }
                            }

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
                params.put("email", getEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile photo.");
        String imgURL = "www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "https://" );
    }

/*

    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("");

        setupImageGrid(imgURLs);
    }


    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView) getView().findViewById(R.id.gridView);
        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adapter);
    }



    private void setupActivityWidgets(){
       // mProgressBar = (ProgressBar) getView().findViewById(R.id.profileProgressBar);
      //  mProgressBar.setVisibility(View.GONE);
       // profilePhoto = (ImageView) getView().findViewById(R.id.profile_image);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.profileToolBar);
        //getView().setSupportActionBar(toolbar);

        ImageView profile = (ImageView) getView().findViewById(R.id.profileMenu);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d(TAG, "OnClick: navigating to account settings.");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });


        TextView logOut = (TextView) getView().findViewById(R.id.logOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d(TAG, "OnClick: navigating to account settings.");
                sessionManager.logout();
                getActivity().finish();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
*/


}
