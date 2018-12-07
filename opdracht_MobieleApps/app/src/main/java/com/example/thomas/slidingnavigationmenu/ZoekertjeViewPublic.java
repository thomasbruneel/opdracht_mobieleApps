package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.thomas.slidingnavigationmenu.Models.BiedingDB;
import com.example.thomas.slidingnavigationmenu.Models.ZoekertjeDB;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZoekertjeViewPublic extends AppCompatActivity {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();
    Gson gson;

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

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("info zoekertje"));
        tabLayout.addTab(tabLayout.newTab().setText("biedingen"));
        tabLayout.addTab(tabLayout.newTab().setText("info eigenaar"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterPublic adapter = new PagerAdapterPublic
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                final String idemail = preferences.getString("userID", "");

                gson = new Gson();
                Map<String, String> gegevens = new HashMap<>();
                gegevens.put("idemail", idemail);
                final JSONObject jsonObject = new JSONObject(gegevens);
                JSONArray jArray = new JSONArray();
                jArray.put(jsonObject);
                final Intent intent2=new Intent(this,MapsActivity.class);
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                        getString(R.string.url) + "/getGemeente",
                        jArray,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("getGemeente", response.toString());
                                String myJSONString=response.toString().substring(1,response.toString().length()-1);// [] wegdoen
                                JsonObject jobj = new Gson().fromJson(myJSONString, JsonObject.class);
                                String adres = jobj.get("gemeente").toString().substring(1,jobj.get("gemeente").toString().length()-1);
                                intent2.putExtra("adres",adres);
                                startActivity(intent2);
                            }
                        },

                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("getGemeente", "Error: " + error.toString() + ", " + error.getMessage());
                            }
                        }
                );
                VolleyClass.getInstance(this.getApplicationContext()).addToRequestQueue(request, "getGemeente");


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
