package com.hfad.restaurantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.EditText;
import android.widget.TextView;

import com.hfad.restaurantapp.database.AppDatabase;
import com.hfad.restaurantapp.database.CreditCardEntry;

import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class CreditCardTasks extends AppCompatActivity {


    private AppDatabase mydbo;
    private TaskAdapter mAdapter;
    private RecyclerView mRecyclerView;
    TextView mNameText;
    TextView mNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_tasks);
        initViews();

        mRecyclerView = findViewById(R.id.recyclerViewTasks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TaskAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
               AppExecutors.getInstance().diskIO().execute(new Runnable() {
                   @Override
                   public void run() {

                       int position = viewHolder.getAdapterPosition();
                       List<CreditCardEntry> cc = mAdapter.getTasks();
                       mydbo.taskDao().deleteTask(cc.get(position));
                       retrieveTasks();

                   }
               });
            }
        }).attachToRecyclerView(mRecyclerView);


        mydbo = AppDatabase.getInstance(getApplicationContext());

    }

    private void initViews() {
       mNameText = findViewById(R.id.cc_name);
       mNumberText = findViewById(R.id.cc_number);
    }



    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<CreditCardEntry> tasks = mydbo.taskDao().loadAllTasks();
                // We will be able to simplify this once we learn more
                // about Android Architecture Components
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setTasks(tasks);
                    }
                });
            }
        });
    }


    protected void onResume(){
        super.onResume();
        retrieveTasks();


    }
}
