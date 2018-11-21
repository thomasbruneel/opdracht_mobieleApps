package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Bieding",
        foreignKeys = @ForeignKey(entity = ZoekertjeDB.class,
                parentColumns = "zoekertjeid",
                childColumns = "zoekertjeid",
                onDelete = CASCADE))
    public class BiedingDB {
        @NonNull
        @PrimaryKey(autoGenerate = true)
        private int biedingid;

        private String naambieder;
        private double biedingprijs;
        private int zoekertjeid;

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

        public int getZoekertjeid() {
            return zoekertjeid;
        }

        public void setZoekertjeid(int zoekertjeid) {
            this.zoekertjeid = zoekertjeid;
        }
}
