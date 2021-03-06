package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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

public class MijnZoekertjes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ZoekertjeDB>zoekertjes;
    private ZoekertjesListAdapter adapter;
    Gson gson;
    ListView lv;

    private OnFragmentInteractionListener mListener;

    public MijnZoekertjes() {
        // Required empty public constructor
    }

    public static MijnZoekertjes newInstance(String param1, String param2) {
        MijnZoekertjes fragment = new MijnZoekertjes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//voorkomt dat toetsenbord gepopped wordt bij edittext
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String userID = preferences.getString("userID", "");
        gson = new Gson();
        lv = (ListView) view.findViewById(R.id.mijnListView);
        zoekertjes=new ArrayList<ZoekertjeDB>();
        Map<String, String> gegevens = new HashMap<>();
        gegevens.put("userid", userID);
        final JSONObject jsonObject = new JSONObject(gegevens);
        final JSONArray jArray = new JSONArray();
        jArray.put(jsonObject);

        JsonArrayRequest projectRequest = new JsonArrayRequest(Request.Method.POST,
                getString(R.string.url) + "/getZoekertjesPersoon",
                jArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Projecten", "GELUKT!");
                        Type type = new TypeToken<List<ZoekertjeDB>>(){}.getType();
                        zoekertjes = gson.fromJson(response.toString(), type);
                        adapter = new ZoekertjesListAdapter(getActivity(), R.layout.customlayout, zoekertjes);
                        lv.setAdapter(adapter);
                        lv.setTextFilterEnabled(true);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ZoekertjeDB z = (ZoekertjeDB) (parent.getItemAtPosition(position));
                                Intent intent = new Intent(view.getContext(), ZoekertjeViewOwner.class);
                                intent.putExtra("mijnZoekertje", z);
                                startActivity(intent);
                            }
                        });

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



        EditText et=(EditText)view.findViewById(R.id.zoekveld);

        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(final CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                JsonArrayRequest projectRequest = new JsonArrayRequest(Request.Method.POST,
                        getString(R.string.url) + "/getZoekertjesPersoon",
                        jArray,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("Projecten", "GELUKT!");
                                Type type = new TypeToken<List<ZoekertjeDB>>(){}.getType();
                                zoekertjes = gson.fromJson(response.toString(), type);
                                System.out.println("aap "+zoekertjes.get(0).getTitel());
                                adapter = new ZoekertjesListAdapter(getActivity(), R.layout.customlayout, zoekertjes);
                                ListView lv = (ListView) view.findViewById(R.id.mijnListView);
                                lv.setAdapter(adapter);
                                lv.setTextFilterEnabled(true);
                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        ZoekertjeDB z = (ZoekertjeDB) (parent.getItemAtPosition(position));
                                        Intent intent = new Intent(view.getContext(), ZoekertjeViewOwner.class);
                                        intent.putExtra("mijnZoekertje", z);
                                        startActivity(intent);
                                    }
                                });
                                List<ZoekertjeDB> filteredList=new ArrayList<>();
                                String search=s.toString().toLowerCase();
                                if(s.length()!=0){
                                    for (ZoekertjeDB zoekertje: zoekertjes) {
                                        if(zoekertje.getTitel().toLowerCase().contains(search)){
                                            System.out.println("true "+zoekertje.getTitel());
                                            filteredList.add(zoekertje);
                                        }
                                    }
                                }else{
                                    filteredList.addAll(zoekertjes) ;
                                }

                                adapter.notifyDataSetChanged(filteredList);

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


            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("refreshen!!!!!!!!!!!!!!!!");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String userID = preferences.getString("userID", "");
        gson = new Gson();
        zoekertjes=new ArrayList<ZoekertjeDB>();
        Map<String, String> gegevens = new HashMap<>();
        gegevens.put("userid", userID);
        final JSONObject jsonObject = new JSONObject(gegevens);
        final JSONArray jArray = new JSONArray();
        jArray.put(jsonObject);

        JsonArrayRequest projectRequest = new JsonArrayRequest(Request.Method.POST,
                getString(R.string.url) + "/getZoekertjesPersoon",
                jArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Projecten", "GELUKT!");
                        Type type = new TypeToken<List<ZoekertjeDB>>(){}.getType();
                        zoekertjes = gson.fromJson(response.toString(), type);
                        adapter = new ZoekertjesListAdapter(getActivity(), R.layout.customlayout, zoekertjes);
                        lv.setAdapter(adapter);
                        lv.setTextFilterEnabled(true);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ZoekertjeDB z = (ZoekertjeDB) (parent.getItemAtPosition(position));
                                Intent intent = new Intent(view.getContext(), ZoekertjeViewOwner.class);
                                intent.putExtra("mijnZoekertje", z);
                                startActivity(intent);
                            }
                        });

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


    }
}

