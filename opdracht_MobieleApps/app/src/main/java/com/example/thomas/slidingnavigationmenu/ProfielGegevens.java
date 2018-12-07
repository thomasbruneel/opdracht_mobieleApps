package com.example.thomas.slidingnavigationmenu;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.thomas.slidingnavigationmenu.Room.AppDatabase;
import com.example.thomas.slidingnavigationmenu.Room.ContactDAO;
import com.example.thomas.slidingnavigationmenu.Room.UserLocal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfielGegevens extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfielGegevens() {
        // Required empty public constructor
    }

    public static ProfielGegevens newInstance(String param1, String param2) {
        ProfielGegevens fragment = new ProfielGegevens();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_profiel_gegevens, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String idemail = preferences.getString("userID", "");
        final String email = preferences.getString("email", "");

        final EditText et=(EditText)  view.findViewById(R.id.gemeente);
        AppDatabase database = Room.databaseBuilder(getActivity(), AppDatabase.class, "appdatabase.db")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();

        final ContactDAO contactDAO = database.getContactDAO();
        UserLocal user=contactDAO.getUser(idemail);
        if(user!=null){
            et.setText(user.getAdres());
        }
        Button button=(Button) view.findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String gemeente=et.getText().toString();

                //sturen naar lokale db
                UserLocal userlocal=new UserLocal();
                userlocal.setAdres(gemeente);
                userlocal.setEmail(email);
                userlocal.setEmailId(idemail);
                contactDAO.insert(userlocal);


                //sturen naar centrale db
                Map<String, String> gegevens = new HashMap<>();
                gegevens.put("idemail", idemail);
                gegevens.put("gemeente", gemeente);
                final JSONObject jsonObject = new JSONObject(gegevens);
                JSONArray jArray = new JSONArray();
                jArray.put(jsonObject);

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                        getString(R.string.url) + "/updateGemeente",
                        jArray,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("updateGemeente", response.toString());
                            }
                        },

                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("updateGemeente", "Error: " + error.toString() + ", " + error.getMessage());
                            }
                        }
                );
                VolleyClass.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request, "updateGemeente");



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
}
