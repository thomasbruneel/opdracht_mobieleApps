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

    @NonNull
    public int getZoekertjeid() {
        return zoekertjeid;
    }

    public void setZoekertjeid(@NonNull int zoekertjeid) {
        this.zoekertjeid = zoekertjeid;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "ZoekertjeDB{" +
                "zoekertjeid=" + zoekertjeid +
                ", titel='" + titel + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}




