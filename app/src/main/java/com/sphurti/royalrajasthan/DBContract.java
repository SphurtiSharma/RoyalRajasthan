package com.sphurti.royalrajasthan;

import android.provider.BaseColumns;

public class DBContract {
    public static final String DB_NAME = "TripDB";
    public static final int DB_VERSION = 1;
    public static class DBEntry implements BaseColumns{
        public static final String TABLE_NAME_TRIP = "trip";
        public static final String TABLE_NAME_HOTEL = "hotel";
        public static final String TRIP_LOCATION = "tripLocation";
        public static final String FROM_DATE = "fromDate";
        public static final String TO_DATE = "toDate";
        public static final String TRIP_HOTEL = "hotel";
        public static final String HOTEL_LOCATION = "hotelLocation";
        public static final String HOTEL_NAME = "hotelName";
        public static final String HOTEL_RATING = "hotelRating";
        public static final String TRIP_COST = "tripCost";
    }
}
