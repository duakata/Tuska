package com.example.blackhack_machine.tuska.Keranjang;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class StatusTransaksiFragment extends Fragment {
    private static final String TAG = "CameraFragment";

    ListView listView_transaksi;
    List<StatusTransaksiItem> transaksiList;
    String getEmail;
    SessionManager sessionManager;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_transaksi, container, false);
        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        getEmail = user.get(SessionManager.EMAIL);

        listView_transaksi = (ListView) view.findViewById(R.id.listView_transaksi);
        transaksiList = new ArrayList<>();
        getUserDetail();
        return view;

    }




  private void getUserDetail() {
 /* final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage("Loading....");
    progressDialog.show();*/

      StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicURL.URL_KERANJANG_LIST_PEMBAYARAN,
              new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      // progressDialog.dismiss();
                      Log.i(TAG, response.toString());
                      try {
                          JSONArray array = new JSONArray(response);
                          for (int i = 0; i < array.length(); i++) {
                              JSONObject object = array.getJSONObject(i);
                              String statusnya = "Transaksi belum lunas...";
                              StatusTransaksiItem statusTransaksiItem = new StatusTransaksiItem(
                                      object.getString("INVOICE"),
                                      object.getString("PEMBAYARAN"),
                                      object.getString("TGLPESAN"),
                                      object.getString("NO_ORDER"),
                                      object.getString("PICT_PROJECT"),
                                      statusnya);
                              transaksiList.add(statusTransaksiItem);
                          }
                          StatusTransaksiListAdapter adapter = new StatusTransaksiListAdapter(transaksiList, getActivity());
                          listView_transaksi.setAdapter(adapter);
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



}
