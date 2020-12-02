package com.sphurti.royalrajasthan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DBHelper(@Nullable Context context)
    {
        super(context, DBContract.DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            String createTableTrips = "CREATE TABLE " + DBContract.DBEntry.TABLE_NAME_TRIP +
                    "( " +
                    DBContract.DBEntry.TRIP_LOCATION + " TEXT, " +
                    DBContract.DBEntry.FROM_DATE + " TEXT, " +
                    DBContract.DBEntry.TO_DATE + " TEXT, " +
                    DBContract.DBEntry.TRIP_HOTEL + " TEXT, " +
                    DBContract.DBEntry.TRIP_COST + " TEXT, "
                    + "primary key (" + DBContract.DBEntry.TRIP_LOCATION + ", " + DBContract.DBEntry.FROM_DATE + ", "
                    + DBContract.DBEntry.TO_DATE + ", " + DBContract.DBEntry.TRIP_HOTEL + ")"
                    + ");";
            db.execSQL(createTableTrips);
            Log.d("TRIP","created");

        String createTableHotels = "CREATE TABLE "+ DBContract.DBEntry.TABLE_NAME_HOTEL+
                "( " + DBContract.DBEntry._ID + " INTEGER Primary key AUTOINCREMENT NOT NULL, " +
                DBContract.DBEntry.HOTEL_LOCATION + " TEXT, " +
                DBContract.DBEntry.HOTEL_NAME + " TEXT, " +
                DBContract.DBEntry.HOTEL_RATING + " DOUBLE);";
        db.execSQL(createTableHotels);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME_TRIP);
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE ADD_NAME ADD COLUMN NEW_COLOUM INTEGER DEFAULT 0");
        }
        onCreate(db);
    }

    public boolean addTrip(String tripLocation, String fromDate, String toDate, String tripHotel, String cost, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.TRIP_LOCATION, tripLocation);
        contentValues.put(DBContract.DBEntry.FROM_DATE, fromDate);
        contentValues.put(DBContract.DBEntry.TO_DATE, toDate);
        contentValues.put(DBContract.DBEntry.TRIP_HOTEL, tripHotel );
        contentValues.put(DBContract.DBEntry.TRIP_COST, cost );

        try{
            long result = db.insertOrThrow(DBContract.DBEntry.TABLE_NAME_TRIP, null, contentValues);
            if(result!=-1){
                Toast.makeText(context, "Your trip has been planned! \n Swipe left or right to delete trip!", Toast.LENGTH_SHORT).show();
            }
            else{
            }

            return true;
        }
        catch (Exception ex){
            Toast.makeText(context, "This trip already exists!!", Toast.LENGTH_SHORT).show();
            Log.d("ADDTRIP",ex.getMessage());
            return false;
        }

    }

    public boolean addHotel(String hotelLocation, String hotelName, double rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.HOTEL_LOCATION, hotelLocation);
        contentValues.put(DBContract.DBEntry.HOTEL_NAME, hotelName);
        contentValues.put(DBContract.DBEntry.HOTEL_RATING, rating);

        db.insert(DBContract.DBEntry.TABLE_NAME_HOTEL, null, contentValues);
        return true;
    }

    public void deleteTrip(String location, String fromDate, String toDate, String hotelName){
        SQLiteDatabase db = this.getReadableDatabase();
        try{

            String where=DBContract.DBEntry.TRIP_LOCATION+ "= ? and " + DBContract.DBEntry.FROM_DATE +
                    " = ? and " + DBContract.DBEntry.TO_DATE + " = ? and " + DBContract.DBEntry.TRIP_HOTEL + " = ?";

            db.delete(DBContract.DBEntry.TABLE_NAME_TRIP, where, new String[]{location, fromDate, toDate, hotelName});

            Log.d("delete", "deleted");
        }
        catch(Exception ignored){
            Log.d("delete", ignored.getMessage());
        }
    }

    public List<String[]> browseTrips(){
        List<String[]> trips = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor =  db.rawQuery( "select rowid,* from "+DBContract.DBEntry.TABLE_NAME_TRIP, null );
            if (cursor != null){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    String[] eachColumn = new String[5];
                    //eachColumn[0] = String.valueOf(cursor.getColumnIndexOrThrow("rowID"));
                    eachColumn[0] = cursor.getString(1);
                    eachColumn[1] = cursor.getString(2);
                    eachColumn[2] = cursor.getString(3);
                    eachColumn[3] = cursor.getString(4);
                    eachColumn[4] = cursor.getString(5);
                    trips.add(eachColumn);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {
            Log.e("Browse trips", ex.getMessage());
        }
        Log.d("listSize",String.valueOf(trips.size()));
        return trips;
    }

    public List<String[]> searchHotels(String cityName){
        List<String[]> hotelNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
           Cursor cursor =  db.rawQuery( "select * from "+DBContract.DBEntry.TABLE_NAME_HOTEL+"  WHERE "+DBContract.DBEntry.HOTEL_LOCATION+" =  \"" + cityName + "\"", null );

            if (cursor != null){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    String[] eachColumn = new String[1];
                    eachColumn[0] = cursor.getString(2);
                    hotelNames.add(eachColumn);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {
            Log.e("Search hotels", ex.getMessage());
        }
        return hotelNames;
    }

    public List<String[]> browseHotels(){
        List<String[]> hotels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor =  db.rawQuery( "select * from "+DBContract.DBEntry.TABLE_NAME_HOTEL, null );
            if (cursor != null){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    String[] eachColumn = new String[4];
                    eachColumn[0] = cursor.getString(0);
                    eachColumn[1] = cursor.getString(1);
                    eachColumn[2] = cursor.getString(2);
                    eachColumn[3] = cursor.getString(3);


                    hotels.add(eachColumn);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {
            Log.e("Browse trips", ex.getMessage());
        }
        return hotels;
    }



}
