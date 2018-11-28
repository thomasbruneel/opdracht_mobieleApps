package com.example.thomas.slidingnavigationmenu;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout mijnDrawer;
    private ActionBarDrawerToggle mijnToggle;
    NavigationView nv;
    View headerView;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALLERY = 2;

    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mijnDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mijnToggle = new ActionBarDrawerToggle(this, mijnDrawer, R.string.open, R.string.close);
        mijnDrawer.addDrawerListener(mijnToggle);
        mijnToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv1);
        setupDrawerContent(nv);
        headerView = nv.getHeaderView(0);
        TextView tv = (TextView) headerView.findViewById(R.id.uiCurrentUser);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Class fragmentClass = Home.class;
        Fragment myFragment = null;
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        broadcastReceiver=new ConnectionReceiver();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(broadcastReceiver,intentFilter);

        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mijnToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuitem) {
        Fragment myFragment = null;
        Class fragmentClass = null;
        switch (menuitem.getItemId()) {
            case R.id.home:
                fragmentClass = Home.class;
                break;

            case R.id.login:
                fragmentClass = Login.class;
                break;

            case R.id.mijnZoekertjes:
                fragmentClass = MijnZoekertjes.class;
                break;

            case R.id.about:
                fragmentClass = About.class;
                break;

            case R.id.settings:
                fragmentClass = Settings.class;
                break;

            case R.id.zoekertjeToevoegen:
                fragmentClass = ZoekertjeToevoegen.class;
                break;

            case R.id.logout:
                fragmentClass = Home.class;
                break;


        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
        menuitem.setChecked(true);
        setTitle(menuitem.getTitle());
        mijnDrawer.closeDrawers();


    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });

    }

    // code voor foto trekken --> word onmiddelijk uitgevoerd na startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView afbeelding = (ImageView) findViewById(R.id.afbeelding);

        //foto trekken
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            afbeelding.setImageBitmap(imageBitmap);

        }
        //foto uit gallerij
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                afbeelding.setImageBitmap(imageBitmap);
            } catch (FileNotFoundException e) {


            }
        }

        if (requestCode == 12500 && resultCode == RESULT_OK) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }


    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(broadcastReceiver);
    }


}

