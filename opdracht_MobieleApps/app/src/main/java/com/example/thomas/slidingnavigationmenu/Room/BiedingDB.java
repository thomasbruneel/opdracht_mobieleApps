package com.example.thomas.slidingnavigationmenu.Room;


import java.io.Serializable;


public class BiedingDB implements Serializable {

    private int idBieding;

    private String biedernaam;

    private double biederprijs;

    private String datum;

    private int zoekertjeid;

    public BiedingDB(){
    }

    public BiedingDB(int idBieding, String biedernaam, double biederprijs, String datum, int zoekertjeid) {
        this.idBieding = idBieding;
        this.biedernaam = biedernaam;
        this.biederprijs = biederprijs;
        this.datum = datum;
        this.zoekertjeid = zoekertjeid;
    }

    public int getIdBieding() {
        return idBieding;
    }

    public void setIdBieding(int idBieding) {
        this.idBieding = idBieding;
    }

    public String getBiedernaam() {
        return biedernaam;
    }

    public void setBiedernaam(String biedernaam) {
        this.biedernaam = biedernaam;
    }

    public double getBiederprijs() {
        return biederprijs;
    }

    public void setBiederprijs(double biederprijs) {
        this.biederprijs = biederprijs;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getZoekertjeid() {
        return zoekertjeid;
    }

    public void setZoekertjeid(int zoekertjeid) {
        this.zoekertjeid = zoekertjeid;
    }

    @Override
    public String toString() {
        return "BiedingDB{" +
                "idBieding=" + idBieding +
                ", biedernaam='" + biedernaam + '\'' +
                ", biederprijs=" + biederprijs +
                ", datum='" + datum + '\'' +
                ", zoekertjeid=" + zoekertjeid +
                '}';
    }
}
