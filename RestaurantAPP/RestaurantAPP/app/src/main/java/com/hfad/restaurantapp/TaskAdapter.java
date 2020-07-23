package com.hfad.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hfad.restaurantapp.database.CreditCardEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {


    private TaskAdapter.ItemClickListener mItemClickListener = null;
    private List<CreditCardEntry> mTaskEntries;
    private Context mContext;


    public TaskAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, viewGroup, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder taskViewHolder, int i) {

        CreditCardEntry taskEntry = mTaskEntries.get(i);

        String name = taskEntry.getFirstName();
        String lastname = taskEntry.getLastName();

        taskViewHolder.taskNameView.setText(name);
        taskViewHolder.taskNumberview.setText(lastname);

    }

    @Override
    public int getItemCount() {
        if (mTaskEntries == null) {
            return 0;
        }
        return mTaskEntries.size();
    }


    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public void setTasks(List<CreditCardEntry> taskEntries) {
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }


    public List<CreditCardEntry> getTasks(){

        return mTaskEntries;
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView taskNameView;
        TextView taskNumberview;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskNameView = itemView.findViewById(R.id.taskName);
            taskNumberview = itemView.findViewById(R.id.taskNumber);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int elementId = mTaskEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);


        }
    }
}
