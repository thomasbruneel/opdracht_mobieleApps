package com.example.thomas.slidingnavigationmenu.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class UserDB {

    @NonNull
    private String userid;

    private String email;


    public UserDB(){

    }

    @NonNull
    public String getUserid() {
        return userid;
    }

    public void setUserid(@NonNull String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }



}
