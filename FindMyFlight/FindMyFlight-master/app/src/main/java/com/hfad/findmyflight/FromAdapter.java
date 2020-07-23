package com.hfad.findmyflight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class FromAdapter extends RecyclerView.Adapter<FromAdapter.ViewHolder> {

    private Context mContext;
    private List<Route> mEntries;


    public FromAdapter(Context context, List<Route> route) {
        mContext = context;
        mEntries = route;
    }
    @NonNull
    @Override
    public FromAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FromAdapter.ViewHolder viewHolder, int i) {

        Route entry = mEntries.get(i);

        String from = entry.getFrom();
       // String to = entry.getTo();

        viewHolder.taskFromView.setText(from);
      //  viewHolder.taskToView.setText(to);

    }

    @Override
    public int getItemCount() {
        if (mEntries == null) {
            return 0;
        }
        return mEntries.size();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public void setTasks(List<Route> taskEntries) {
        mEntries = taskEntries;
        notifyDataSetChanged();
    }


    public List<Route> getTasks(){

        return mEntries;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView taskFromView;
        TextView taskToView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskFromView = itemView.findViewById(R.id.listCity);
            taskToView = itemView.findViewById(R.id.listAirp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
