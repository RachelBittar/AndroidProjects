package com.hfad.findmyflight;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.net.URL;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class From extends AppCompatActivity {

       private static final String AIRPORT_REQUEST_URL1 = "https://jsonkeeper.com/b/0ZAF";
       private static final String AIRPORT_REQUEST_URL2 = "https://jsonkeeper.com/b/PRZ1";

    private AirportAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);
        new AirportQueryTask().execute(AIRPORT_REQUEST_URL1, AIRPORT_REQUEST_URL2);


        mRecyclerView = findViewById(R.id.recyclefrom);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
    }


    private class AirportQueryTask extends AsyncTask<String, Void, List<Airport>> {

        @Override
        protected List<Airport> doInBackground(String... AIRPORT_REQUEST_URL) {

            URL url = Utils.buildUrl(AIRPORT_REQUEST_URL[0]);
            URL url1 = Utils.buildUrl(AIRPORT_REQUEST_URL[1]);
            List<Airport> airports = Utils.fetchAirportData(url, url1);
            return airports;
        }

        @Override
        protected void onPostExecute(List<Airport> airports) {

            mAdapter = new AirportAdapter(getApplicationContext(), airports);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setTasks(airports);
            List<Airport> cc = mAdapter.getTasks();

            DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
            mRecyclerView.addItemDecoration(decoration);

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }
                @Override
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                    int position = viewHolder.getAdapterPosition();
                    List<Airport> cc = mAdapter.getTasks();
                    mAdapter.setTasks(cc);
                }
            }).attachToRecyclerView(mRecyclerView);

        }

        }


}
