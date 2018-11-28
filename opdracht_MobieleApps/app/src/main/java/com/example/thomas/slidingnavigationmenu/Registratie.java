package com.example.thomas.slidingnavigationmenu;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Registratie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registratie);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Registratie");




    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }

    public void registreren(View view){
        EditText etGebruikersNaam=(EditText)findViewById(R.id.uiGebruikersnaam);
        String gebruikersNaam=etGebruikersNaam.getText().toString();
        System.out.println(gebruikersNaam);
        EditText etWachtwoord=(EditText)findViewById(R.id.uiWachtwoord);
        String wachtwoord=etWachtwoord.getText().toString();
        System.out.println(wachtwoord);
        EditText etHerhaalWachtwoord=(EditText)findViewById(R.id.uiHerhaalWachtwoord);
        String herhaalWachtwoord=etHerhaalWachtwoord.getText().toString();
        System.out.println(herhaalWachtwoord);




    }
    public void naarLogin(View view){
        finish();

    }
}
