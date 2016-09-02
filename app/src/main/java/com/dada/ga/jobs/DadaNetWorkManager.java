package com.dada.ga.jobs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dada.ga.App;

/**
 * Created by steeve on 4/9/16.
 */
public class DadaNetWorkManager {

    public static boolean isNetWork(Context context) {
        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
