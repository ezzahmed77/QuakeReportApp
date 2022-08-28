package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    //***Global Variables***
    private  String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl= url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }

}

