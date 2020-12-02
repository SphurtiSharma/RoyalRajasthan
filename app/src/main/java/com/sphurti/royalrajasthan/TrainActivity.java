package com.sphurti.royalrajasthan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainActivity extends AppCompatActivity {

    ArrayList<SliderItem> trainSliderItem = new ArrayList<SliderItem>();
    TextView trainWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //makes the back button visible in action bar

        trainWebsite  = findViewById(R.id.textView14);

        trainWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String website = String.valueOf("https://www.palacesonwheels.com/booking.php");
                Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                startActivity(openBrowserIntent);
            }
        });

        List<String> trainImageList = new ArrayList<>(Arrays.asList("https://i.ibb.co/3SWtWy1/Train1-Small.jpg",
                                                                    "https://i.ibb.co/NZL8v8k/train2-Small.jpg",
                                                                    "https://i.ibb.co/zJqNRbn/train3-Small.jpg",
                                                                    "https://i.ibb.co/fQ3MKQK/train4-Small.jpg",
                                                                    "https://i.ibb.co/SygGzpX/train5-Small.jpg",
                                                                    "https://i.ibb.co/80XZTKz/train7-Small.jpg",
                                                                    "https://i.ibb.co/Qch7M0P/train8-Small.jpg",
                                                                    "https://i.ibb.co/dMN0bLr/train10-Small.jpg"
                                                                    ));

        List<String> trainDescriptions = new ArrayList<>(Arrays.asList("Rediscover the age of decadence and romance. Step inside the Palace on Wheels and discover the elegance and pomp of a bygone era.",
                "Explore the royal land of sand dunes and regal palaces and experience the beauty of incredible India. Palace on Wheels has been voted as the 4th best luxury train in the world, and has an elegant ambience that perfectly matches the amazing charm of the Indian terrain.",
                "As the name suggests, an expedition in this luxury train is actually like living in a palace on wheels. The train takes you to the exotic destinations of Rajasthan, like Jaipur, Udaipur, Ranthambore etc, along with a visit to the magnificent Taj Mahal.",
                "There are 39 Deluxe Cabins and 2 Super Deluxe Cabins on the Palace on Wheels train (with a total capacity is of 82 passengers). Palace on Wheels is a fully air-conditioned luxury train specially facilitated for the global luxury traveler.",
                "The intricate wood carved furnishing, silk and velvet bedspreads and drapes along with wall to wall carpeting create a special opulence that invite relaxation.",
                "The Palace on Wheels has 1 Super Deluxe coach with 2 suites, Emerald and Diamond, with queen-size double beds.",
                "Personal attendants meet you on the platform to show you to your Super Deluxe suite, taking your luggage and detailing facilities available on board. Always on call, the service leaves nothing to be desired.",
                "The use of exquisite silk and velvet furnishings, inspired by fabrics and designs used by kings in their living areas and bed rooms, along with finely carved furniture, are the hallmarks of these exclusive coaches."));

        trainSliderItem = sliderLoadItems(trainDescriptions,trainImageList);
        Log.d("tarin",String.valueOf(trainSliderItem.size()));
        SliderView sliderView = findViewById(R.id.imageSliderTrain);
        SliderAdapter adapter = new SliderAdapter(this);

        adapter.addItem(trainSliderItem.get(0));
        adapter.addItem(trainSliderItem.get(1));
        adapter.addItem(trainSliderItem.get(2));
        adapter.addItem(trainSliderItem.get(3));
        adapter.addItem(trainSliderItem.get(4));
        adapter.addItem(trainSliderItem.get(5));
        adapter.addItem(trainSliderItem.get(6));
        adapter.addItem(trainSliderItem.get(7));

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(12);
        sliderView.startAutoCycle();
    }

    public ArrayList<SliderItem> sliderLoadItems(List<String> cityLocationDescriptions, List<String> cityImages){
        ArrayList<SliderItem> sliderItemList = new ArrayList<>();
        for(int i = 0; i < cityImages.size(); i++){
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription(cityLocationDescriptions.get(i));
            sliderItem.setImageUrl(cityImages.get(i));
            sliderItemList.add(sliderItem);
        }
        return sliderItemList;
    }
}