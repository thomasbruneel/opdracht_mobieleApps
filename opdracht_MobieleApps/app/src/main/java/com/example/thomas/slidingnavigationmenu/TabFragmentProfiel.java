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
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("receiver",user.getEmail());
                        editor.putString("adres",user.getGemeente());
                        editor.apply();

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
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                final String receiver = preferences.getString("receiver", "");
                intent.putExtra("receiver", receiver);
                startActivity(intent);

            }
        });

        location.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                final String adres = preferences.getString("adres", "");
                final Intent intent2=new Intent(getActivity(),MapsActivity.class);
                intent2.putExtra("adres",adres);
                startActivity(intent2);


            }
        });
        return view;
    }

}