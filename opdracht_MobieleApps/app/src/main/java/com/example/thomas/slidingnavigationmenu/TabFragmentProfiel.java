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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.thomas.slidingnavigationmenu.Models.BiedingDB;
import com.example.thomas.slidingnavigationmenu.Models.UserDB;
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

public class TabFragmentProfiel extends Fragment {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();
    Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_tab_fragment_profiel, container, false);

        final TextView email =(TextView) view.findViewById(R.id.profielemail);
        final TextView adres =(TextView) view.findViewById(R.id.profieladres);

        Intent i = getActivity().getIntent();
        z = (ZoekertjeDB) i.getSerializableExtra("mijnZoekertje");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String idemail = preferences.getString("userID", "");

        gson = new Gson();

        Map<String, String> gegevens = new HashMap<>();
        gegevens.put("userid", idemail);
        final JSONObject jsonObject = new JSONObject(gegevens);
        JSONArray jArray = new JSONArray();
        jArray.put(jsonObject);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                getString(R.string.url) + "/getUserByIdEmail",
                jArray,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("getUserByIdEmail", response.toString());
                        Type type = new TypeToken<UserDB>(){}.getType();
                        UserDB user = gson.fromJson(response.toString().substring(1,response.toString().length()-1), type);
                        email.setText(user.getEmail());
                        adres.setText(user.getGemeente());

                    }
                },

                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("getUserByIdEmail", "Error: " + error.toString() + ", " + error.getMessage());
                    }
                }
        );
        VolleyClass.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request, "getUserByIdEmail");
        Button contact=(Button) view.findViewById(R.id.contact);
        Button location=(Button) view.findViewById(R.id.location);
        contact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),Email.class);
                intent.putExtra("subject", z.getBeschrijving());
                startActivity(intent);

            }
        });

        location.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                final String idemail = preferences.getString("userID", "");

                gson = new Gson();
                Map<String, String> gegevens = new HashMap<>();
                gegevens.put("idemail", idemail);
                final JSONObject jsonObject = new JSONObject(gegevens);
                JSONArray jArray = new JSONArray();
                jArray.put(jsonObject);
                final Intent intent2=new Intent(getActivity(),MapsActivity.class);
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
                VolleyClass.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request, "getGemeente");


            }
        });
        return view;
    }

}