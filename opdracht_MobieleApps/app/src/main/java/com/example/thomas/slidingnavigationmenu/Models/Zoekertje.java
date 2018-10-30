package com.example.thomas.slidingnavigationmenu.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 22/10/2018.
 */
@SuppressWarnings("serial")
public class Zoekertje implements Serializable {

    private String userId;
    private String name;
    private double price;
    private List<Bieding> biedinglijst;

    public Zoekertje(){
    }

    public Zoekertje(String userId,String name, double price) {

        this.userId=userId;
        this.name = name;
        this.price = price;
        this.biedinglijst = new ArrayList<Bieding>();
    }

    @Override
    public String toString() {
        return " naam: "+name+" ; price : "+price;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getUserID() {
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userID;
    }

    public List<Bieding> getBiedinglijst() {
        return biedinglijst;
    }

    public void setBiedinglijst(List<Bieding> biedinglijst) {
        this.biedinglijst = biedinglijst;
    }
}
