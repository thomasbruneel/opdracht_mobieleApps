package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public void insert(ZoekertjeDB zoekertje);

    @Update
    public void update(ZoekertjeDB zoekertje);

    @Delete
    public void delete(ZoekertjeDB zoekertje);

    @Query("SELECT * FROM ZoekertjeDB")
    public List<ZoekertjeDB> getZoekertjes();

    @Query("SELECT * FROM ZoekertjeDB WHERE zoekertjeid = :id")
    public ZoekertjeDB getZoekertjes(int id);
}

