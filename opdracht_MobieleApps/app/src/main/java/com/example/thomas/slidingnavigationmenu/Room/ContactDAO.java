package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public void insert(ZoekertjeDB zoekertje);

    @Update
    public void update(ZoekertjeDB zoekertje);

    @Delete
    public void delete(ZoekertjeDB zoekertje);

    @Query("SELECT * FROM zoekertje")
    public List<ZoekertjeDB> getZoekertjes();

    @Query("SELECT * FROM zoekertje WHERE zoekertjeid = :id")
    public ZoekertjeDB getZoekertjes(int id);


    @Query("SELECT * FROM zoekertje WHERE userid=:userid")
    List<ZoekertjeDB> findRepositoriesForUser(int userid);

    //user table
    @Insert
    public void insert(UserDB user);

    @Update
    public void update(UserDB user);

    @Delete
    public void delete(UserDB user);


}