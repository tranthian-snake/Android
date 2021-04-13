package com.example.tranthian.database;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase appDatabase;
    public  abstract ProductDao productDao();

    public  static  AppDatabase getAppDatabase(Context context){
        if (appDatabase==null){
            appDatabase= Room.databaseBuilder(context, AppDatabase.class, "News").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
