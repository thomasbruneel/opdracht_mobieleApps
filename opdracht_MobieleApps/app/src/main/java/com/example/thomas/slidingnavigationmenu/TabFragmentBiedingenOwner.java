package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.thomas.slidingnavigationmenu.Models.BiedingDB;

import com.example.thomas.slidingnavigationmenu.Models.ZoekertjeDB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFragmentBiedingenOwner extends Fragment {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();
    Gson gson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_tab_fragment_biedingen_owner, container, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String email = preferences.getString("email", "");
        Intent i = getActivity().getIntent();
        z = (ZoekertjeDB) i.getSerializableExtra("mijnZoekertje");
        biedingen=new ArrayList<BiedingDB>();
        gson = new Gson(); // deze was ik vergeten !!!!!!!!!!!!!!!!!!!!!!!!!

        Map<String, String> gegevens = new HashMap<>();
        gegevens.put("zoekertjeid", String.valueOf(z.getIdZoekertje()));
        final JSONObject jsonObject = new JSONObject(gegevens);
        final JSONArray jArray = new JSONArray();
        jArray.put(jsonObject);

        JsonArrayRequest projectRequest = new JsonArrayRequest(Request.Method.POST,
                getString(R.string.url) + "/getBiedingenVanZoekertje",
                jArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Projecten", "GELUKT!");
                        Type type = new TypeToken<List<BiedingDB>>(){}.getType();
                        System.out.println("azerty "+response.toString());
                        biedingen = gson.fromJson(response.toString(), type);
                        adapter = new BiedingListAdapter(getActivity(), R.layout.customlayout2, biedingen);
                        ListView lv = (ListView) view.findViewById(R.id.biedingListView);
                        lv.setAdapter(adapter);
                        lv.setTextFilterEnabled(true);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Projecten", "Error: " + error.toString() + ", " + error.getMessage());
                    }
                }
        );
        VolleyClass.getInstance(getActivity().getApplicationContext()).addToRequestQueue(projectRequest, "Inloggen");

        return view;
    }


}
