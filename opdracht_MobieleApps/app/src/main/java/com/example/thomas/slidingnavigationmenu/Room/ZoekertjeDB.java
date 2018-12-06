package com.example.thomas.slidingnavigationmenu.Room;
import android.support.annotation.NonNull;


import java.io.Serializable;

public class ZoekertjeDB implements Serializable {

    private int idZoekertje;

    private String titel;

    private String beschrijving;

    private double prijs;

    private String image;

    private String userid;

    public ZoekertjeDB() {
    }

    @NonNull
    public int getIdZoekertje() {
        return idZoekertje;
    }

    public void setIdZoekertje(@NonNull int idZoekertje) {
        this.idZoekertje = idZoekertje;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ZoekertjeDB{" +
                "idZoekertje=" + idZoekertje +
                ", titel='" + titel + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", userid=" + userid +
                '}';
    }
}




