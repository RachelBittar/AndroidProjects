package com.hfad.findmyflight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AirportAdapter  extends RecyclerView.Adapter<AirportAdapter.ViewHolder> {

    Context mContext;
    List<Airport> mEntries;

    public AirportAdapter(Context applicationContext, List<Airport> airports) {
        mContext = applicationContext;
        mEntries = airports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout, viewGroup, false);
        return new AirportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Airport entry = mEntries.get(i);

        String city = entry.getCity();
        String name = entry.getName();
        String cntry = entry.getCountry();


        viewHolder.taskCtyView.setText(city);
        viewHolder.taskToView.setText(name);
        viewHolder.taskContryView.setText(cntry);


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

    public void setTasks(List<Airport> taskEntries) {
        mEntries = taskEntries;
        notifyDataSetChanged();
    }


    public List<Airport> getTasks(){

        return mEntries;
    }


    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView taskCtyView;
        TextView taskToView;
        TextView taskContryView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskCtyView = itemView.findViewById(R.id.listCity);
            taskToView = itemView.findViewById(R.id.listAirp);
            taskContryView = itemView.findViewById(R.id.listCountry);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
