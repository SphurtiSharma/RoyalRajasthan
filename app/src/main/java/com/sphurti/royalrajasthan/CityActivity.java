package com.sphurti.royalrajasthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.marozzi.roundbutton.RoundButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    RecyclerView recyclerViewCityHotels;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewCityHotels = findViewById(R.id.recyclerViewCityHotels);
        dbHelper = new DBHelper(this);

        ArrayList<SliderItem> sliderItem = new ArrayList<SliderItem>();

        sliderItem = this.getIntent().getParcelableArrayListExtra("sliderItemList");

        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapter adapter = new SliderAdapter(this);
        adapter.addItem(sliderItem.get(0));
        adapter.addItem(sliderItem.get(1));
        adapter.addItem(sliderItem.get(2));
        adapter.addItem(sliderItem.get(3));
        adapter.addItem(sliderItem.get(4));

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(12);
        sliderView.startAutoCycle();

        SliderItem sliderItem1 = new SliderItem();
        sliderItem1 = sliderItem.get(0);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerViewCityHotels.setLayoutManager(gridLayoutManager);
        HotelAdapter hotelAdapter = new HotelAdapter(dbHelper.searchHotels(sliderItem1.getCityName()),sliderItem1.getCityName(),this);
        recyclerViewCityHotels.setAdapter(hotelAdapter);
    }
}