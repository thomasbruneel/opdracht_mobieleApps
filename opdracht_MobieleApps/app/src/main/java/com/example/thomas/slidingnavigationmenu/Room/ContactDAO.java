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
    public void insert(Zoekertje zoekertje);

    @Update
    public void update(Zoekertje zoekertje);

    @Delete
    public void delete(Zoekertje zoekertje);

    @Query("SELECT * FROM Zoekertje")
    public List<Zoekertje> getZoekertjes();

    @Query("SELECT * FROM Zoekertje WHERE zoekertjeid = :id")
    public Zoekertje getZoekertjes(int id);
}

