package com.sphurti.royalrajasthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorCitiesActivity extends AppCompatActivity {


    ArrayList<SliderItem> sliderItem = new ArrayList<SliderItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_cities);

        List<String> majorCities = new ArrayList<>(Arrays.asList("Jaipur","Udaipur","Chittorgarh","Bikaner","Jodhpur"));
        List<String> cityDescription = new ArrayList<>(Arrays.asList("The Pink City","The City of Lakes","The City of Courage","The Desert City", "The Blue City"));

        List<String> imageList = new ArrayList<>(Arrays.asList("https://i.ibb.co/rb9BbYL/Pink-city-Small.jpg","https://i.ibb.co/qRDrcS4/Lake-City-Small.jpg","https://i.ibb.co/DKtPZBZ/Fort1-Small.jpg","https://i.ibb.co/drCCgrR/junagarh-fort4-Small.jpg","https://i.ibb.co/3v1cy7F/Blue-City1-Small.jpg"));

        List<String> cityName1 = new ArrayList<>(Arrays.asList("Jaipur"));
        List<String> jaipurLocations = new ArrayList<>(Arrays.asList("Hawa Mahal","Albert Hall Museum","Amer Fort","Patrika Gate","Birla Temple"));
        List<String> jaipurLocationDescription = new ArrayList<>(Arrays.asList("An extraordinary pink-painted, delicately honeycombed hive that rises a dizzying five storeys. It was constructed in 1799 by Maharaja Sawai Pratap Singh to enable ladies of the royal household to watch the life and processions of the city.",
                "Built-in the year 1876, it was initially planned to be a concert hall. It resembles the architecture of the Victoria and Albert Hall Museum in London, so, the name. The galleries of the museum have a combination of antiques and artefacts from the past which will blow away your mind.",
                "Set among rugged hills, it is a classic romantic Rajasthani palace with beautiful gardens, magnificent gateways, courtyards, a glittering chamber of inlaid mirrors, lots of carved marble and inlay work. It is built in sandstone and white marble and its complex has very interesting apartments",
                "It is a gorgeous rainbow walkway that looks like something out of a Maharajas palace! As you walk under the gate, you will find a row of fabulous archways. Each one has been decorated with Rajasthani culture in mind. It showcases major attractions in Jaipur.",
                "This is a popular place of pilgrimage among Hindu devotees. The structure is made of sparkling white marble and looks incredibly wonderful. Lush green gardens surround the temple on the sides and give a dreamy touch to the structure."));
        List<String> jaipurImages = new ArrayList<>(Arrays.asList("https://i.ibb.co/w49cXLr/Hawa-Mahal-1-Small.jpg","https://i.ibb.co/vBYQMXm/albert-museum-jaipur-Small.jpg","https://i.ibb.co/MPzfvPS/Amber-Fort-Small.jpg","https://i.ibb.co/sW5YQ8T/Patrika-Gate-Jaipur-Small.jpg","https://i.ibb.co/JCfLmjd/Birla-Temple-1-Small.jpg"));

        List<String> cityName2 = new ArrayList<>(Arrays.asList("Udaipur"));
        List<String> udaipurLocations = new ArrayList<>(Arrays.asList("City Palace","Lake Palace","Monsoon Palace","Fateh Sagar","Jagdish Temple"));
        List<String> udaipurLocationDescription = new ArrayList<>(Arrays.asList(
                "The largest palace complex of Rajasthan located on the banks of Lake Pichola, is the epitome of a colossal citadel that is a concoction of Rajasthani, Mughal, Medieval, European and Oriental architecture. Built completely with marble and granite, it has a beautiful appeal of marblework, mirror work, silver work, inlay-work, wall paintings and murals.",
                "It is one of the most romantic places on this earth situated amidst the scenic Pichola Lake. Raised in white marble, the magnificent Palace stretches across a four-acre island creating a dream-like impression.",
                "It was built as an astronomical center for tracking weather and the monsoon, hence the nickname. It was also intended to be a hunting lodge for the royal family.",
                "Surrounded by the Aravalli Hills, it is the second largest artificial lake in the city and is known for its scenic beauty. Apart from enjoying a laid-back afternoon in the serene beauty of this destination, you can also try your hand at boating and several other water sports that are available here for the visitors.",
                "It is the most popular temple structure in the city that attracts pilgrims from around the world. The temple is the perfect specimen of Indo-Aryan style of architecture with amazingly carved vast pillars and painted ceilings. The temple is 79 feet tall and dominates the city skyline while adding amazing architectural beauty."));
        List<String> udaipurImages = new ArrayList<>(Arrays.asList("https://i.ibb.co/HVKK942/City-Palace-21.jpg","https://i.ibb.co/yBbVNSm/Lake-Palace1-Small.jpg","https://i.ibb.co/ZGDtWWX/Monsoon-Palace2-Small.jpg","https://i.ibb.co/2yChww7/Boats-floating-on-the-beautiful-blue-waters-of-a-lake-with-mountains-in-hte-distance-and-a-beautiful.jpg","https://i.ibb.co/tJTHZYD/City-Palace-1-Small.jpg"));

        List<String> cityName3 = new ArrayList<>(Arrays.asList("Chittorgarh"));
        List<String> chittorLocations = new ArrayList<>(Arrays.asList("Fort","Padmini Palace","Fateh Prakash Palace","Abhaypura","Sanwaliya Seth Temple"));
        List<String> chittorLocationDescription = new ArrayList<>(Arrays.asList(
                "A rock island that rises steeply from the plains. The half-mile ascent through seven gates takes you almost 500 feet up to a plateau that is nearly four miles long. The fort covers the entire plateau, encompassing a village, a reservoir, several active temples, and countless deserted, sublimely beautiful ruins.",
                "This is the palace where the beautiful Queen Padmini lived. The majestic palace is a historical monument related to the self-sacrifice of the queen after Chittorgarh was invaded. The fort is surrounded by a lotus pool which adds to the charm of the monument.",
                "This palace is studded with numerous corridors and pillars built in Rajasthani style. It is beautifully decorated with wall paintings showcasing legends from Rajasthan belonging to the 17th and 19th centuries.",
                "It is a small hill station 12 kms away from Chittorgarh. It is known for waterfalls, lakes, and greenery. The cool and soothing climate here makes it a popular destination during summers.",
                "Located 40 kms from Chittorgarh, Mandaphiya is known as Shri Sanwaliya Dham (The residence of Hindu God, Lord Krishna). This a huge temple with intricate architecture and people believe that all their desires are fulfilled when they visit here."));
        List<String> chittorImages = new ArrayList<>(Arrays.asList("https://i.ibb.co/jbHZDMw/Fort2-Small.jpg","https://i.ibb.co/Xs2L5Vn/Padmini-Palace2-Small.jpg","https://i.ibb.co/xmLRcs6/Fateh-Prakash-Museum-Small.png","https://i.ibb.co/RSJQvnw/Abhaypura-Small.jpg","https://i.ibb.co/JHYf4D4/sanwaliya-Seth-temple-picture-1-Small.jpg"));

        List<String> cityName4 = new ArrayList<>(Arrays.asList("Bikaner"));
        List<String> bikanerLocations = new ArrayList<>(Arrays.asList("Junagarh Palace","Rampuria Haveli","Laxmi Niwas Palace","Camel Festival","Desert Safari"));
        List<String> bikanerLocationDescription = new ArrayList<>(Arrays.asList(
                "Junagarh Fort is one of the most decorated forts in India and houses opulent palaces that give you a peek into the lives and times of the kings of Bikaner. Tt was built more for luxury than defense.",
                "The reminiscence of the bygone era, Rampuria Haveli is the pride of Bikaner. It is a beautiful specimen constructed of Dulmera red sandstone as the crucial base material. The mansion demonstrates gorgeous arty abilities prevalent during that era. The inner halls and rooms are decorated and beautified with aesthetic art.",
                "The magnificent palace was named after Laxmi, the Hindu Goddess of wealth, prosperity, and beauty. Used exclusively as the private residence for the Royal family and their guests, Laxmi Niwas Palace commanded over Bikaner in all its opulence.",
                "The Bikaner camel festival is conducted annually in the month of January with great enthusiasm and participation in honour of the camels. These animals are not only known for surviving these harsh weather conditions but also known for their act of bravery.",
                "The safari gives you a chance to enjoy the very basic cultural vibe of the city. There are a lot of activities to spend a day in the desert and it a beyond amazing to witness the jaw dropping sunset in front of you at the desert safari."));
        List<String> bikanerImages = new ArrayList<>(Arrays.asList("https://i.ibb.co/gMT1gPS/junagarh-fort6-Small.jpg","https://i.ibb.co/3SQ5kv1/Rampuria-Haveli2-Small.jpg","https://i.ibb.co/ssbCg2B/Laxmi-Niwas-Palace2-Small.jpg","https://i.ibb.co/P9LCsSn/camel-festival-Small.jpg","https://i.ibb.co/kxnqgXJ/Camel-Safari2-Small.jpg"));

        List<String> cityName5 = new ArrayList<>(Arrays.asList("Jodhpur"));
        List<String> jodhpurLocations = new ArrayList<>(Arrays.asList("Mehrangarh Fort","Umaid Bhawan Palace","Mandore Garden","Jaswant Thada","Clock Tower"));
        List<String> jodhpurLocationDescription = new ArrayList<>(Arrays.asList(
                "Mehrangarh Fort stands a hundred feet in splendor on a perpendicular cliff, four hundred feet above the sky line of Jodhpur. Burnished red sand stone, imposing, invincible and yet with a strange haunting beauty that beckons.",
                "Home of the erstwhile Jodhpur royal family and currently the world’s sixth-largest private residence, this palace is constructed with the palm court marble. Come, experience luxury like the royals did.",
                "The Mandore Gardens houses ancient temples, memorials and high-rock terraces that are quite magnificent. Visitors can spend the whole day gazing and clicking pictures!",
                "This milky-white marble memorial to the king of Jodhpur, sitting above a small lake , is an array of whimsical domes. It’s a peaceful spot after the hubbub of the city, and the views across to the fort and over the city are superb.",
                "The clock tower is a popular landmark in the old city. The vibrant Sardar Market is close to the tower, and narrow alleys lead from here to a bazaar selling vegetables, spices, Indian sweets, textiles, silver and handicrafts. It is a great place to ramble around at leisure."));
        List<String> jodhpurImages = new ArrayList<>(Arrays.asList("https://i.ibb.co/WcHsBcQ/Mehrangarh-Fort2-Small.jpg","https://i.ibb.co/mJmYgPW/Umaid-Bhawan4-Small.jpg","https://i.ibb.co/stWps0d/Mandore-Garden4-Small.jpg","https://i.ibb.co/ypvjDTT/Jaswant-Thada1-Small.jpg","https://i.ibb.co/cY3yQGT/Clock-Tower-Small.png"));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeActivity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);

        recyclerView.setLayoutManager(gridLayoutManager);
        GridAdapterRecyclerView gridAdapterRecyclerView = new GridAdapterRecyclerView(this,majorCities, cityDescription, imageList, (View v, int position) -> {
            switch (position){
                case 0:  sliderItem = sliderLoadItems(jaipurLocations,jaipurLocationDescription,jaipurImages, cityName1);
                break;
                case 1:  sliderItem = sliderLoadItems(udaipurLocations,udaipurLocationDescription,udaipurImages, cityName2);
                break;
                case 2:  sliderItem = sliderLoadItems(chittorLocations,chittorLocationDescription,chittorImages, cityName3);
                break;
                case 3:  sliderItem = sliderLoadItems(bikanerLocations,bikanerLocationDescription,bikanerImages,cityName4);
                break;
                case 4:  sliderItem = sliderLoadItems(jodhpurLocations,jodhpurLocationDescription,jodhpurImages, cityName5);
                break;
            }
            startActivity(new Intent(MajorCitiesActivity.this, CityActivity.class).putParcelableArrayListExtra("sliderItemList", sliderItem));
        });
        recyclerView.setAdapter(gridAdapterRecyclerView);
    }

    public ArrayList<SliderItem> sliderLoadItems(List<String> cityLocations, List<String> cityLocationDescriptions, List<String> cityImages, List<String> cityName){
        ArrayList<SliderItem> sliderItemList = new ArrayList<>();
        for(int i = 0; i < cityLocations.size(); i++){
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription(cityLocationDescriptions.get(i));
            sliderItem.setImageUrl(cityImages.get(i));
            sliderItem.setLocationName(cityLocations.get(i));
            sliderItem.setCityName(cityName.get(0));
            sliderItemList.add(sliderItem);
        }
        return sliderItemList;
    }
}