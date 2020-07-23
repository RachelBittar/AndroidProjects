package com.hfad.restaurantapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.hfad.restaurantapp.database.AppDatabase;
import com.hfad.restaurantapp.database.CreditCardEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    private static final String TAG = MainViewModel.class.getSimpleName();


    private List<CreditCardEntry>tasks;

    public MainViewModel(Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        tasks = database.taskDao().loadAllTasks();
    }


    public List<CreditCardEntry> getTasks() {
        return tasks;
    }
}
