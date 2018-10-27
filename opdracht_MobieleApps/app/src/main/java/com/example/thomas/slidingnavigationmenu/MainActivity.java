package com.example.thomas.slidingnavigationmenu;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mijnDrawer;
    private ActionBarDrawerToggle mijnToggle;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mijnDrawer=(DrawerLayout) findViewById(R.id.drawer);
        mijnToggle=new ActionBarDrawerToggle(this,mijnDrawer,R.string.open,R.string.close);
        mijnDrawer.addDrawerListener(mijnToggle);
        mijnToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv=(NavigationView) findViewById(R.id.nv1);
        setupDrawerContent(nv);


        //testen
        FragmentManager fragmentManager = getSupportFragmentManager();
        Class fragmentClass=home.class;
        Fragment myFragment=null;
        try{
            myFragment=(Fragment)fragmentClass.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        fragmentManager.beginTransaction().replace(R.id.flcontent,myFragment).commit();



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mijnToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuitem){
        Fragment myFragment=null;
        Class fragmentClass = null;
        switch(menuitem.getItemId()){
            case R.id.home:
                fragmentClass=home.class;
                break;

            case R.id.login:
                fragmentClass=login.class;
                break;

            case R.id.settings:
                fragmentClass=settings.class;
                break;

            case R.id.logout:
                fragmentClass=logout.class;
                break;
        }
        try{
            myFragment=(Fragment)fragmentClass.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent,myFragment).commit();
        menuitem.setChecked(true);
        setTitle(menuitem.getTitle());
        mijnDrawer.closeDrawers();


    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                selectItemDrawer(item);
                return true;
            }
        });

    }
    //fragmentlogin

    public void inloggen(View view){
        EditText etGebruikersNaam=(EditText)findViewById(R.id.uiGebruikersnaam);
        String gebruikersNaam=etGebruikersNaam.getText().toString();
        System.out.println(gebruikersNaam);
        EditText etWachtwoord=(EditText)findViewById(R.id.uiWachtwoord);
        String wachtwoord=etWachtwoord.getText().toString();
        System.out.println(wachtwoord);

    }
    public void naarRegistratie(View view){
        Intent intent=new Intent(this,registratie.class);
        startActivity(intent);

    }






}

