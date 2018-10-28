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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mijnDrawer;
    private ActionBarDrawerToggle mijnToggle;
    NavigationView nv;
    View headerView;

    private FirebaseAuth fbauht;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbauht=FirebaseAuth.getInstance();

        mijnDrawer=(DrawerLayout) findViewById(R.id.drawer);
        mijnToggle=new ActionBarDrawerToggle(this,mijnDrawer,R.string.open,R.string.close);
        mijnDrawer.addDrawerListener(mijnToggle);
        mijnToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv=(NavigationView) findViewById(R.id.nv1);
        setupDrawerContent(nv);
        headerView=nv.getHeaderView(0);
        TextView tv=(TextView) headerView.findViewById(R.id.uiCurrentUser);
        if(fbauht.getCurrentUser()!=null){
            tv.setText(" email: "+fbauht.getCurrentUser().getEmail());
        }



        FragmentManager fragmentManager = getSupportFragmentManager();
        Class fragmentClass=Home.class;
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
                fragmentClass=Home.class;
                break;

            case R.id.login:
                fragmentClass=Login.class;
                break;

            case R.id.mijnZoekertjes:
                fragmentClass=MijnZoekertjes.class;
                break;

            case R.id.about:
                fragmentClass=About.class;
                break;

            case R.id.settings:
                fragmentClass=Settings.class;
                break;

            case R.id.logout:
                fbauht.signOut();
                TextView tv=(TextView) headerView.findViewById(R.id.uiCurrentUser);
                if(fbauht.getCurrentUser()==null){
                    tv.setText("");
                }
                fragmentClass=Home.class;
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






}

