package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;



public class ZoekertjeToevoegen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static final int REQUEST_IMAGE_CAPTURE=1;
    static final int REQUEST_IMAGE_GALLERY=2;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    public ZoekertjeToevoegen() {
        // Required empty public constructor
    }
    public static ZoekertjeToevoegen newInstance(String param1, String param2) {
        ZoekertjeToevoegen fragment = new ZoekertjeToevoegen();
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
        view=inflater.inflate(R.layout.fragment_zoekertje_toevoegen, container, false);

        final String userID = getArguments().getString("userID");
        final String email =getArguments().getString("email");
        System.out.println("mijn email adres is "+email);

        Button button=(Button) view.findViewById(R.id.uiToevoegButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText etTitel=(EditText)view.findViewById(R.id.uiTitel);
                String titel=etTitel.getText().toString();
                EditText etPrijs=(EditText)view.findViewById(R.id.uiPrijs);
                double prijs=Double.parseDouble(etPrijs.getText().toString());
                EditText etBeschrijving=(EditText)view.findViewById(R.id.uiBeschrijving);
                String beschrijving=etBeschrijving.getText().toString();

                ImageView imageView = (ImageView) view.findViewById(R.id.afbeelding);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
                byte[] imageInByte = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageInByte, Base64.DEFAULT);

                Map<String, String> gegevens = new HashMap<>();
                gegevens.put("titel", titel);
                gegevens.put("beschrijving", beschrijving);
                gegevens.put("prijs", String.valueOf(prijs));
                gegevens.put("image",imageString);
                gegevens.put("userid",userID);
                final JSONObject jsonObject = new JSONObject(gegevens);
                JSONArray jArray = new JSONArray();
                jArray.put(jsonObject);

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                        getString(R.string.url) + "/addZoekertje",
                        jArray,
                        new com.android.volley.Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("ToevoegenZoekertje", response.toString());
                            }
                        },

                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("ToevoegenZoekertje", "Error: " + error.toString() + ", " + error.getMessage());
                            }
                        }
                );
                VolleyClass.getInstance(getActivity().getApplicationContext()).addToRequestQueue(request, "ToevoegenZoekertje");

                Toast.makeText(getActivity(),"met succes zoekertje toegevoegd",Toast.LENGTH_SHORT).show();
                etTitel.getText().clear();
                etPrijs.getText().clear();
                etBeschrijving.getText().clear();
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.noimageavailable));

            }
        });

        //foto trekken en foto weergeven in imageview
        ImageButton b = (ImageButton) view.findViewById(R.id.uiTakePictureButton);
        b.setBackgroundDrawable(null);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                    Log.i("camera","device heeft camera");
                    dispatchTakePictureIntent();
                }
                else{
                    Log.i("camera","device heeft geen camera");
                }
            }
        });

        //foto uit gallery halen en foto weergeven in imageview
        ImageButton b2 = (ImageButton) view.findViewById(R.id.uiGalleryButton);
        b2.setBackgroundDrawable(null);
        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dispatchGalleryPictureIntent();
            }
        });



        return view;
    }
    //foto trekken
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
    }

    //foto uit gallerij
    private void dispatchGalleryPictureIntent(){
        Intent galleryIntent=new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        getActivity().startActivityForResult(galleryIntent,REQUEST_IMAGE_GALLERY);
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
