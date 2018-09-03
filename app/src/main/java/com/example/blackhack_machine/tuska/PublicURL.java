package com.example.blackhack_machine.tuska;

import android.app.Activity;
import android.os.Bundle;

public class PublicURL extends Activity {

    public static String URL_PROFILE_GETDETAIL = "http://duakata-dev.com/android_test/get-detailuser.php";
    public static String URL_PROFILE_COUNT_PROJECT = "http://duakata-dev.com/android_test/get-countproject.php";
    public static String URL_PROFILE_COUNT_PROSES = "http://duakata-dev.com/android_test/profile_get-countproses.php";
    public static String URL_PROFILE_COUNT_DONE = "http://duakata-dev.com/android_test/profile_get-countdone.php";
    public static String URL_KERANJANG_LIST_PEMBAYARAN = "http://duakata-dev.com/android_test/keranjang_get-list-pembayaran.php";
    public static String URL_KERANJANG_LIST_PROJECT = "http://duakata-dev.com/android_test/keranjang_get-list-project.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
