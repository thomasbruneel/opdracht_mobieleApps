package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;





//@Entity(tableName = "Zoekertje")
@Entity(tableName = "Zoekertje",
        foreignKeys = @ForeignKey(entity = UserDB.class,
        parentColumns = "userid",
        childColumns = "userid",
        onDelete = CASCADE))
public class ZoekertjeDB implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int zoekertjeid;

    private String titel;
    private String beschrijving;
    private double prijs;

    private int userid;

  //  @Relation(parentColumn = "zoekertjeid", entityColumn = "biedingid")
  //  public List<BiedingDB> biedingDBList;



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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ZoekertjeDB{" +
                "zoekertjeid=" + zoekertjeid +
                ", titel='" + titel + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", userid=" + userid +
                '}';
    }
}




