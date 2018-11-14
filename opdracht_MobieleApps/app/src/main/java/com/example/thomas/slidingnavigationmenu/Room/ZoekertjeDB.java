package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ZoekertjeDB {
    @NonNull
    @PrimaryKey
    private int zoekertjeid;

    private String titel;
    private String beschrijving;
    private double prijs;


    public ZoekertjeDB() {
    }

}




