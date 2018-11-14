package com.example.thomas.slidingnavigationmenu.Room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Zoekertje.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}