package com.example.thomas.slidingnavigationmenu;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.slidingnavigationmenu.Room.AppDatabase;
import com.example.thomas.slidingnavigationmenu.Room.BiedingDB;
import com.example.thomas.slidingnavigationmenu.Room.ContactDAO;
import com.example.thomas.slidingnavigationmenu.Room.ZoekertjeDB;

import java.util.ArrayList;
import java.util.List;

public class ZoekertjeViewPublic extends AppCompatActivity {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoekertje_view_public);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//vermijd layoutmove bij keyboard pop up

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

        //biedingen
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "appdatabase.db")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        ContactDAO contactDAO = database.getContactDAO();
        biedingen=contactDAO.findRepositoriesForBieding(z.getZoekertjeid());
        adapter=new BiedingListAdapter(this,R.layout.customlayout2,biedingen);
        mijnListView=(ListView) findViewById(R.id.biedingListView);
        mijnListView.setAdapter(adapter);

        Button button=(Button) findViewById(R.id.voegBiedingToeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "appdatabase.db")
                        .allowMainThreadQueries()   //Allows room to do operation on main thread
                        .build();
                ContactDAO contactDAO = database.getContactDAO();
                EditText et=(EditText) findViewById(R.id.bieding);
                BiedingDB bieding=new BiedingDB();
                bieding.setBiedingprijs(Double.parseDouble(et.getText().toString()));
                bieding.setZoekertjeid(z.getZoekertjeid());
                contactDAO.insert(bieding);
                adapter.notifyDataSetChanged(bieding);
            }
        });
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
