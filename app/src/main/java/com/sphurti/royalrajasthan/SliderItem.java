package com.sphurti.royalrajasthan;

import android.os.Parcel;
import android.os.Parcelable;

public class SliderItem implements Parcelable {
    private String description;
    private String imageUrl;
    private String locationName;

    public SliderItem() {
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private String cityName;

    public String getDescription() {
        return description;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    protected SliderItem(Parcel in) {
        description = in.readString();
        imageUrl = in.readString();
        locationName = in.readString();
        cityName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(locationName);
        dest.writeString(cityName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SliderItem> CREATOR = new Parcelable.Creator<SliderItem>() {
        @Override
        public SliderItem createFromParcel(Parcel in) {
            return new SliderItem(in);
        }

        @Override
        public SliderItem[] newArray(int size) {
            return new SliderItem[size];
        }
    };
}