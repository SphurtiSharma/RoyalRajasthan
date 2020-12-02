package com.sphurti.royalrajasthan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.marozzi.roundbutton.RoundButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripsActivity extends AppCompatActivity implements SwipeInterface {
    private int day, month, yearval;
    Spinner hotelSpinner;
    Spinner citySpinner;
    DBHelper dbHelper;
    EditText dateFrom, dateTo;
    RoundButton buttonPlan;
    RecyclerView tripsRecyclerView;
    Bundle bundle;
    String hotelName;
    String cityName;
    int hotelPosition, position;
    ItemTouchHelper recyclerViewItemSwipe;
    EditText editTextGuestCount;
    long dayDifference, countOfDays = 0, roomcount = 1;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);
        hotelSpinner = findViewById(R.id.spinner);
        citySpinner = findViewById(R.id.spinnerCities);
        dateFrom = findViewById(R.id.editTextDate);
        dateTo = findViewById(R.id.editTextDate2);
        buttonPlan = findViewById(R.id.planButton);
        editTextGuestCount = findViewById(R.id.editTextGuestCount);
        tripsRecyclerView = findViewById(R.id.recyclerViewTrips);
        try {
            bundle = getIntent().getBundleExtra("HotelBundle");
            hotelName = bundle.getString("HotelName");
            cityName = bundle.getString("CityName");
            hotelPosition = bundle.getInt("hotelIndex");
        } catch (Exception ex) {
            Log.d("BUNDLE", "Empty Bundle" + ex.getMessage());
        }
        if (!(bundle == null)) {
            position = 0;
            switch (cityName) {
                case "Jaipur":
                    position = 0;
                    break;
                case "Udaipur":
                    position = 1;
                    break;
                case "Chittorgarh":
                    position = 2;
                    break;
                case "Bikaner":
                    position = 3;
                    break;
                case "Jodhpur":
                    position = 4;
                    break;
            }
            citySpinner.setSelection(position);
            hotelSpinner.setSelection(hotelPosition);
        }
        dateFrom.setFocusable(false);
        dateTo.setFocusable(false);
        dateFrom.setOnClickListener(v -> ShowDatePicker(dateFrom));
        dateTo.setOnClickListener(v -> ShowDatePicker(dateTo));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        tripsRecyclerView.setLayoutManager(gridLayoutManager);

        TripAdapter tripAdapter = new TripAdapter(dbHelper.browseTrips(), this, countOfDays);
        tripsRecyclerView.setAdapter(tripAdapter);
        ItemTouchHelper.Callback callback = new RecyclerViewItemSwipe(tripAdapter);
        recyclerViewItemSwipe = new ItemTouchHelper(callback);
        recyclerViewItemSwipe.attachToRecyclerView(tripsRecyclerView);

        LoadSpinner();

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoadSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String location = String.valueOf(citySpinner.getSelectedItem());
                    String hotelName = String.valueOf(hotelSpinner.getSelectedItem());
                    String fromDate = String.valueOf(dateFrom.getText());
                    String toDate = String.valueOf(dateTo.getText());
                    roomcount = Long.parseLong(String.valueOf(editTextGuestCount.getText()));
                    if(fromDate.equals("") || toDate.equals("")){
                        throw new Exception();
                    }

                    countOfDays = getDiff(String.valueOf(dateFrom.getText()), String.valueOf(dateTo.getText()));
                    if (countOfDays >= 0) {
                        double totalCost;
                        if (countOfDays > 0) {
                            totalCost = ((200 * countOfDays) * roomcount) + (0.12 * (200 * countOfDays)) + (0.07 * (200 * countOfDays));
                        } else {
                            totalCost = 200 + (0.12 * 200) + (0.07 * 200);
                        }
                        try {
                            NumberFormat formatter = NumberFormat.getCurrencyInstance();
                            String moneyString = formatter.format(totalCost);
                            dbHelper.addTrip(location, fromDate, toDate, hotelName, String.valueOf(moneyString), TripsActivity.this);
                        } catch (Exception e) {
                            Log.d("ADDTRIP", e.getMessage());
                        } finally {
                            tripAdapter.setList(dbHelper.browseTrips());
                            tripAdapter.notifyDataSetChanged();
                        }
                    }
                    else{
                        Toast.makeText(TripsActivity.this, "Enter correct dates!", Toast.LENGTH_SHORT).show();
                    }
                    
                } catch (Exception ex) {
                    Toast.makeText(TripsActivity.this, "Please enter all required details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void LoadSpinner() {
        String selectedCity = String.valueOf(citySpinner.getSelectedItem());
        List<String[]> hotelNames = dbHelper.searchHotels(selectedCity);
        String[] hotelArray = new String[2];
        for (int i = 0; i < hotelNames.size(); i++) {
            hotelArray[i] = hotelNames.get(i)[0];
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item,
                        hotelArray);
        hotelSpinner.setAdapter(spinnerArrayAdapter);
        Log.d("Spin", String.valueOf(position));
        hotelSpinner.setSelection(hotelPosition);
    }

    private List<String[]> ReadCSV() {
        List<String[]> resultList = new ArrayList<>();

        InputStream inputStream = getResources()
                .openRawResource(R.raw.hotel_data);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                String hotelLocation = row[0];
                String hotelName = row[1];
                String rating = row[2];
                String[] hotelDetails = {hotelLocation, hotelName, rating};
                resultList.add(hotelDetails);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error reading csv file: " + ex);
        } catch (NumberFormatException ex) {
            //this is for parseInt
            throw new RuntimeException("Error in parsing");
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException("Error while closing stream"
                        + ex);
            }
        }
        return resultList;
    }

    public void ShowDatePicker(EditText date) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            day = dayOfMonth;
            month = monthOfYear;
            yearval = year;
            try{countOfDays = getDiff(String.valueOf(dateFrom.getText()),String.valueOf(dateTo.getText()));}
            catch (Exception e){}
        }, yearval, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {

    }

    public long getDiff(String fromDate, String toDate) {
        try {
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");

            date1 = dates.parse(fromDate);
            date2 = dates.parse(toDate);

            long difference = date2.getTime() - date1.getTime();
            
            if(difference < 0){
                dayDifference= -1;
            }
            else{
                dayDifference = difference / (24 * 60 * 60 * 1000);
            }

        } catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);
        }
        return dayDifference;
        
    }
}