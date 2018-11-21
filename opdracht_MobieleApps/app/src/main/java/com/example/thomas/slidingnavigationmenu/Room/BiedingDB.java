package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;

    @Entity(tableName = "Bieding")
    public class BiedingDB {
        @NonNull
        @PrimaryKey(autoGenerate = true)
        private int biedingid;


        private String naambieder;
        private double biedingprijs;

        public BiedingDB(){
        }

        @NonNull
        public int getBiedingid() {
            return biedingid;
        }

        public void setBiedingid(@NonNull int biedingid) {
            this.biedingid = biedingid;
        }

        public String getNaambieder() {
            return naambieder;
        }

        public void setNaambieder(String naambieder) {
            this.naambieder = naambieder;
        }

        public double getBiedingprijs() {
            return biedingprijs;
        }

        public void setBiedingprijs(double biedingprijs) {
            this.biedingprijs = biedingprijs;
        }
    }
