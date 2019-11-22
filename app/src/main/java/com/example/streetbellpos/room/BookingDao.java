package com.example.streetbellpos.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.streetbellpos.models.gson.BookingDetails;

import java.util.List;

@Dao
public interface BookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookingDetails word);

    @Query("DELETE FROM bookingTable")
    void deleteAll();

    @Query("SELECT * FROM bookingTable")
    List<BookingDetails> getAllBooking();


}
