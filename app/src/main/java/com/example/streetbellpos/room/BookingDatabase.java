package com.example.streetbellpos.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.streetbellpos.models.gson.BookingDetails;


@Database(entities = {BookingDetails.class}, version = 1, exportSchema = false)
@TypeConverters({BookingDetails.Converters.class})
public abstract class BookingDatabase extends RoomDatabase {


    public abstract BookingDao bookingDao();


}
