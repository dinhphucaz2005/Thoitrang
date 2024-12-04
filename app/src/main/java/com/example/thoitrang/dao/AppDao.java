package com.example.thoitrang.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thoitrang.model.HoaDon;

import java.util.List;

@Dao
public interface AppDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HoaDon hoaDon);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(HoaDon hoaDon);

    @Delete
    void delete(HoaDon hoaDon);

    @Query("SELECT * FROM hoa_don")
    List<HoaDon> getAllHoaDon();

    @Query("SELECT * FROM hoa_don WHERE maHD = :maHD")
    HoaDon getHoaDonById(int maHD);
}
