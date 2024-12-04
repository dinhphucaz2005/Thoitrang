package com.example.thoitrang.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.thoitrang.dao.AppDao;
import com.example.thoitrang.model.HoaDon;

@Database(entities = {HoaDon.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract AppDao appDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "hoa_don_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}
