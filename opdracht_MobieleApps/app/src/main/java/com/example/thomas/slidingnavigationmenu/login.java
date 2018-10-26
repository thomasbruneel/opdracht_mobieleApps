package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mijnDrawer;
    private ActionBarDrawerToggle mijnToggle;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mijnDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mijnToggle = new ActionBarDrawerToggle(this, mijnDrawer, R.string.open, R.string.close);
        mijnDrawer.addDrawerListener(mijnToggle);
        mijnToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv1);
        nv.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mijnToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        if (id == R.id.login) {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        }
        return false;
    }

}