package com.sphurti.royalrajasthan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> implements RecyclerViewItemSwipe.TripItemTouchHelper{

    int deletePosition;

    List<String[]> tripsList;
    Context context;

    public long getCountOfDays() {
        return countOfDays;
    }

    public void setCountOfDays(long countOfDays) {
        this.countOfDays = countOfDays;
    }

    long countOfDays;

    public void setList(List<String[]> tripsList) {
        this.tripsList = tripsList;
    }

    public TripAdapter(List<String[]> tripsList, Context context, long countOfDays) {

        this.tripsList = tripsList;
        this.context = context;
        this.countOfDays = countOfDays;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card_layout, parent, false);
        TripAdapter.TripViewHolder viewHolder = new TripViewHolder(itemView);
        viewHolder.imageView = itemView.findViewById(R.id.trip_card_image);
        viewHolder.textViewTripLocation = itemView.findViewById(R.id.trip_to_location);
        viewHolder.textViewTripDate = itemView.findViewById(R.id.textViewTripDate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        String tripLocation= tripsList.get(position)[3] + " in " + tripsList.get(position)[0];
        String tripDate = tripsList.get(position)[1] + " to " + tripsList.get(position)[2];
        Log.d("Cost", String.valueOf(countOfDays));
        holder.textViewTripLocation.setText(tripDate+"\nEstimated Cost: "+tripsList.get(position)[4]);
        holder.textViewTripDate.setText(tripLocation);

        switch (tripsList.get(position)[0]){

            case "Jaipur":
                Glide.with(holder.itemView)
                        .load("https://i.ibb.co/rb9BbYL/Pink-city-Small.jpg")
                        .centerCrop()
                        .into(holder.imageView);
                break;

            case "Udaipur":
                Glide.with(holder.itemView)
                    .load("https://i.ibb.co/qRDrcS4/Lake-City-Small.jpg")
                    .centerCrop()
                    .into(holder.imageView);
                break;

            case "Chittorgarh":
                Glide.with(holder.itemView)
                    .load("https://i.ibb.co/DKtPZBZ/Fort1-Small.jpg")
                    .centerCrop()
                    .into(holder.imageView);
                break;

            case "Bikaner":
                Glide.with(holder.itemView)
                    .load("https://i.ibb.co/drCCgrR/junagarh-fort4-Small.jpg")
                    .centerCrop()
                    .into(holder.imageView);
                break;

            case "Jodhpur":
                Glide.with(holder.itemView)
                        .load("https://i.ibb.co/3v1cy7F/Blue-City1-Small.jpg")
                        .centerCrop()
                        .into(holder.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        Log.d("cardToDelete", String.valueOf(tripsList.get(position)[0]));
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.deleteTrip(tripsList.get(position)[0],tripsList.get(position)[1],tripsList.get(position)[2],tripsList.get(position)[3]);
        tripsList.remove(position);
        notifyDataSetChanged();
    }


    public static class TripViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageView;
        TextView textViewTripLocation, textViewTripDate;
        public TripViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

    }
}
