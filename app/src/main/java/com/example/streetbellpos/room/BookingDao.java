package com.example.streetbellpos.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.streetbellpos.models.gson.BookingDetails;

import java.util.List;

@Dao
public interface BookingDao {

    @Query("SELECT * FROM bookingDetails")
    List<BookingDetails> getAllBooking();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookingDetails directors);

}
