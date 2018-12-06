package com.example.thomas.slidingnavigationmenu.Room;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import static android.arch.persistence.room.ForeignKey.CASCADE;
/**
 * Created by thomas on 6/12/2018.
 */
@Entity
public class UserLocal {
    @NonNull
    @PrimaryKey
    private String emailId;

    private String email;
    private String adres;

    public UserLocal(){

    }

    @NonNull
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(@NonNull String emailId) {
        this.emailId = emailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
