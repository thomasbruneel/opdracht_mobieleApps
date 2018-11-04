package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;

public class ZoekertjeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoekertje_view);

        Intent i = getIntent();
        Zoekertje z = (Zoekertje) i.getSerializableExtra("mijnZoekertje");

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle(z.getName()+"  (€ "+z.getPrice()+")");

    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }
}
