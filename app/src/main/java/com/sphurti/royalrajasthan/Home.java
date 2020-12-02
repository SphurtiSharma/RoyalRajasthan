package com.sphurti.royalrajasthan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.smarteist.autoimageslider.IndicatorView.draw.drawer.Drawer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        List<String> categoryList = new ArrayList<>(Arrays.asList("Major Cities","Palace on Wheels","Explore!"));
        List<String> categoryDescriptionList = new ArrayList<>(Arrays.asList("Feel the majesty","Exotic Rail Journey","Visit your Favorite Destination!"));
        List<String> imageList = new ArrayList<>(Arrays.asList("https://i.ibb.co/nBth3JZ/Major-Cities.jpg","https://i.ibb.co/G0dmdx7/Palace-on-Wheels.jpg","https://i.ibb.co/X3sWyLd/Rajasthan-Tradition-1.png","https://i.ibb.co/cDdCDYP/Welcome.jpg"));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeActivity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridAdapterRecyclerView gridAdapterRecyclerView = new GridAdapterRecyclerView(this,categoryList, categoryDescriptionList, imageList,(v, position) -> {
            switch (Integer.parseInt(String.valueOf(position))){
                case 0: startActivity(new Intent(Home.this, MajorCitiesActivity.class));
                break;
                case 1:  startActivity(new Intent(Home.this, TrainActivity.class));
                break;
                case 2:  startActivity(new Intent(Home.this, TripsActivity.class));
                break;
            }

        });
        recyclerView.setAdapter(gridAdapterRecyclerView);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int FLAG = sharedPref.getInt("hotelsLoaded", 0);
        Log.d("Shared", String.valueOf(FLAG));
        if(FLAG == 0){
            LoadHotels();
        }
    }

    public void LoadHotels(){
        List<String[]> hotelDetails = ReadCSV();
        dbHelper = new DBHelper(this);

        for (int i = 0; i < hotelDetails.size(); i++){
            String hotelLocation = hotelDetails.get(i)[0];
            String hotelName = hotelDetails.get(i)[1];
            double rating = Double.parseDouble(hotelDetails.get(i)[2]);
            dbHelper.addHotel(hotelLocation,hotelName,rating);
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("hotelsLoaded",1);
        editor.apply();
    }

    //overridden method for back button, asks the user to confirm exit, gets called automatically
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> Home.super.onBackPressed())
                .setNegativeButton("No", null)
                .show();
    }
    private List<String[]> ReadCSV(){
        List<String[]> resultList = new ArrayList<>();

        InputStream inputStream = getResources()
                .openRawResource(R.raw.hotel_data);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        try {
            String csvLine;
            while((csvLine = reader.readLine()) != null){
                String[] row = csvLine.split(",");

                String hotelLocation = row[0];
                String hotelName = row[1];
                String rating = row[2];

                String[] hotelDetails = {hotelLocation,hotelName,rating};

                resultList.add(hotelDetails);
            }
        } catch (IOException ex){
            throw new RuntimeException("Error reading csv file: " + ex);

        } catch (NumberFormatException ex) {
            //this is for parseInt
            throw new RuntimeException("Error in parsing");
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException ex){
                throw new RuntimeException("Error while closing stream"
                        + ex);
            }
        }
        return resultList;
    }
}