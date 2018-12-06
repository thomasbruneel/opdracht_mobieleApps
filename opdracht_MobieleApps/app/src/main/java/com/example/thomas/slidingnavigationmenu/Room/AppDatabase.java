package com.example.thomas.slidingnavigationmenu.Room;

/**
 * Created by thomas on 6/12/2018.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {UserLocal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}