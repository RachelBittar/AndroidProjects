package com.hfad.findmyflight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    Airport airportFrom;
    Airport airportTo;
    Boolean isSaved=false;
    TextView fromtxt = null;
    TextView totxt = null;
    ArrayList<Airport> airports = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromtxt = (TextView) findViewById(R.id.getFromtxt);
        totxt = (TextView) findViewById(R.id.getToTxt);

        Intent intent = new Intent(this, GetRouteService.class);
        startService(intent);

        Log.d("Main", "onCreate");

        if (savedInstanceState !=null) {
            isSaved = savedInstanceState.getBoolean("Saved");
            Log.d("Main", "savedInstanceState !=null" + isSaved);
        }
        Log.d("Main", "savedInstanceState  -> " + savedInstanceState);

        Intent intent1 = getIntent();
        if ((intent1.getExtras() != null)) {
           //    airportFrom = intent1.getParcelableExtra("from");
               airports = intent1.getParcelableArrayListExtra("from_to");
               if(airports!=null) {
                 airportTo = airports.get(1);
            }

        }


        if(savedInstanceState==null){
            getReadFrom();

            Log.d("Main ", " getReadFrom() " + savedInstanceState);
        }
        else{

            Log.d("Main From", " getReadTo() " + savedInstanceState);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        isSaved = true;
        savedInstanceState.putBoolean("Saved", isSaved);
        Log.d("Main", "onSaveInstanceState(Bundle savedInstanceState) " + savedInstanceState.getBoolean("Saved")) ;
        super.onSaveInstanceState(savedInstanceState);

    }



    public void getReadFrom() {

        if (airportFrom != null) {
            fromtxt.setText(airportFrom.getIATA3());
            if(airportTo!=null){
                fromtxt.setText(airportFrom.getIATA3());
                totxt.setText("000");
            }
        }
    }




    public void getReadTo() {

        Intent intent2 = getIntent();
        if ((intent2.getExtras() != null)) {
            airports = intent2.getParcelableArrayListExtra("from_to");
            totxt.setText(airports.toString());
        }

    }




    public void getFrom(View view) {
        Intent intent = new Intent(this, From.class);
        startActivity(intent);
    }


    public void getTo(View view) {
        Intent intent2 = new Intent(this, To.class);
        intent2.putExtra("from_to", airportFrom);
        startActivity(intent2);



    }


    @Override
    protected void onResume() {
        super.onResume();
        getReadFrom();


    }





    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();

    }


}
