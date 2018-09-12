package com.example.blackhack_machine.tuska.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class PreviewWebActivity extends AppCompatActivity {
    private static final String TAG = "LikesActivity";
    private static  final int ACTIVITY_NUM = 3;
    private Context mContext = PreviewWebActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadweb);
        String url = "";

        WebView web = (WebView) findViewById(R.id.webView);
        web.loadUrl(url);

    }




}