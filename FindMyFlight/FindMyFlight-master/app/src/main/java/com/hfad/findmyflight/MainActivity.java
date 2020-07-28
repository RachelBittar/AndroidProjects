package com.hfad.findmyflight;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import com.hfad.findmyflight.data.*;
import com.hfad.findmyflight.utilities.*;


public class MainActivity extends AppCompatActivity {


    Airport airportFrom, airportTo;

    TextView  from_airport_txt = null;
    TextView  to_airport_txt = null;
    ArrayList<Airport> airports = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from_airport_txt = (TextView) findViewById(R.id.getFromtxt);
        to_airport_txt = (TextView) findViewById(R.id.getToTxt);

        loadAirportData();

    }

    private void loadAirportData() {

        String airport = FligthsPreferences.getDefaulAirportLocation(this);
        new FetchAirportTask().execute(airport);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }



    public class FetchAirportTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            String airport = params[0];
            URL airportRequestUrl = NetworkUtils.buildUrl(airport);  //build url

            try {
                String jsonDataResponse = NetworkUtils.getResponseFromHttpUrl(airportRequestUrl);
                String[] simpleJsonData = OpenJsonUtils.getSimpleAirportStringsFromJson(MainActivity.this, jsonDataResponse);

                return simpleJsonData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


    }
}
