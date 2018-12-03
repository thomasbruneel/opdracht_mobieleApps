package com.example.thomas.slidingnavigationmenu;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.slidingnavigationmenu.Room.AppDatabase;
import com.example.thomas.slidingnavigationmenu.Room.ContactDAO;
import com.example.thomas.slidingnavigationmenu.Room.ZoekertjeDB;

public class ZoekertjeViewPublic extends AppCompatActivity {
    ZoekertjeDB z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoekertje_view_owner);

        Intent i = getIntent();
        z = (ZoekertjeDB) i.getSerializableExtra("mijnZoekertje");

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle(z.getTitel()+"  ( € "+z.getPrijs()+")");

        TextView tv=(TextView) findViewById(R.id.uiBeschrijving);
        tv.setText(z.getBeschrijving());

        ImageView iv=(ImageView) findViewById(R.id.afbeelding);
        Bitmap bitmap = BitmapFactory.decodeByteArray(z.getFoto(), 0, z.getFoto().length);
        iv.setImageBitmap(bitmap);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.action_contact:
                Intent intent=new Intent(this,Email.class);
                intent.putExtra("subject", z.getBeschrijving());
                startActivity(intent);
                break;

            case R.id.action_location:
                Intent intent2=new Intent(this,MapsActivity.class);
                startActivity(intent2);
                break;
            default:
                finish();
                return super.onOptionsItemSelected(item);
        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.zoekertjeviewmenupublic, menu);
        return true;
    }


}
