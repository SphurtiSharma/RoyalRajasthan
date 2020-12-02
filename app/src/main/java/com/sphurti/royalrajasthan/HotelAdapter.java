package com.sphurti.royalrajasthan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelHolder>{

    List<String[]> hotelsList;
    String cityName;
    Context context;


    public HotelAdapter(List<String[]> hotelsList, String cityName, Context context) {
        this.hotelsList = hotelsList;
        this.cityName = cityName;
        this.context = context;
    }

    @NonNull
    @Override
    public HotelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_layout, parent, false);
        final HotelAdapter.HotelHolder viewHolder = new HotelAdapter.HotelHolder(itemView);
        viewHolder.hotelImage = itemView.findViewById(R.id.imageViewHotel);
        viewHolder.textViewHotelName = itemView.findViewById(R.id.textViewHotelDescription);
        viewHolder.buttonBookNow = itemView.findViewById(R.id.buttonBookNow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelHolder holder, int position) {
        holder.textViewHotelName.setText(hotelsList.get(position)[0]);
        String url = "";
        switch (hotelsList.get(position)[0]){

            case "Hotel Jal Mahal Palace": url = "https://i.ibb.co/1TxGy55/jaipur1-Small.jpg";
                break;

            case "Hotel Jaipur Marriott": url = "https://i.ibb.co/QbbbN1V/jaipur2-Small.jpg";
                break;

            case "Hotel Taj Fateh Prakash": url = "https://i.ibb.co/GVntJz9/udaipur1-Small.jpg";
                break;

            case "Hotel Radisson Blu Udaipur Palace": url = "https://i.ibb.co/tH5PpHP/udaipur2-Small.jpg";
                break;

            case "Padmavati lake Resort": url = "https://i.ibb.co/6J8T1CK/chittorgarh1-Small.jpg";
                break;

            case "Hotel Castle Bijaipur": url = "https://i.ibb.co/jTnTD6y/chittorgarh2-Small.jpg";
                break;

            case "The Laxmi Niwas Palace": url = "https://i.ibb.co/nMMv2Jd/bikaner1-Small.jpg";
                break;

            case "Hotel Bhanwar Niwas": url = "https://i.ibb.co/6DGtSHQ/bikaner2-Small.jpg";
                break;

            case "Hotel RAAS": url = "https://i.ibb.co/VpJMK5N/jodhpur1-Small.jpg";
                break;

            case "Hotel Taj Hari Mahal": url = "https://i.ibb.co/T4039ty/jodhpur2-Small.jpg";
                break;
        }
        Glide.with(holder.itemView)
                .load(url)
                .centerCrop()
                .into(holder.hotelImage);

        holder.buttonBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hotelName = hotelsList.get(position)[0];
                Bundle bundle = new Bundle();
                bundle.putString("HotelName",hotelName);
                bundle.putString("CityName",cityName);
                bundle.putInt("hotelIndex",position);
                context.startActivity(new Intent(context,TripsActivity.class).putExtra("HotelBundle",bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelsList.size();
    }

    public class HotelHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView hotelImage;
        TextView textViewHotelName;
        TextView textViewHotelWebsite;
        Button buttonBookNow;

        public HotelHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

        }
    }
}


