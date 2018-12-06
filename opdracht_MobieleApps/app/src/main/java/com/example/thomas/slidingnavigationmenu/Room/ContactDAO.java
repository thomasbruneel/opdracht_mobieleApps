package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by thomas on 6/12/2018.
 */
@Dao
public interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(UserLocal user);

    @Update
    public void update(UserLocal user);

    @Delete
    public void delete(UserLocal user);

    @Query("SELECT * FROM UserLocal WHERE emailId = :id")
    public UserLocal getUser(String id);


}
