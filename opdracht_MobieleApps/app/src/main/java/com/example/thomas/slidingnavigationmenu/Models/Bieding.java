package com.example.thomas.slidingnavigationmenu.Models;

public class Bieding {
    private String userId;
    private Zoekertje zoekertje;
    private double biedprijs;

    public Bieding(String userId, Zoekertje zoekertje, double biedprijs) {
        this.userId = userId;
        this.zoekertje = zoekertje;
        this.biedprijs = biedprijs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Zoekertje getZoekertje() {
        return zoekertje;
    }

    public void setZoekertje(Zoekertje zoekertje) {
        this.zoekertje = zoekertje;
    }

    public double getBiedprijs() {
        return biedprijs;
    }

    public void setBiedprijs(double biedprijs) {
        this.biedprijs = biedprijs;
    }
}
