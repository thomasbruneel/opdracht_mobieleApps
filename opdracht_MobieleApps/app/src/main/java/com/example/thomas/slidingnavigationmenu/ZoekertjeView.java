package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;

public class ZoekertjeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoekertje_view);

        Intent i = getIntent();
        Zoekertje p = (Zoekertje) i.getSerializableExtra("mijnZoekertje");

        TextView id=(TextView) findViewById(R.id.uiId);
        TextView naam=(TextView) findViewById(R.id.uiNaam);
        TextView prijs=(TextView) findViewById(R.id.uiPrijs);
        //id.setText(String.valueOf(p.getId()));
        naam.setText(p.getName());
        prijs.setText(String.valueOf(p.getPrice()));

    }
}
