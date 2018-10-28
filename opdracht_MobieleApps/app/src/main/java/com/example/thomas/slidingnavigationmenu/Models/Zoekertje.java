package com.example.thomas.slidingnavigationmenu.Models;

import java.io.Serializable;

/**
 * Created by thomas on 22/10/2018.
 */
@SuppressWarnings("serial")
public class Zoekertje implements Serializable {
    private int id;
    private String name;
    private double price;

    public Zoekertje(){
    }

    public Zoekertje(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: "+id+" ; naam: "+name+" ; price : "+price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
