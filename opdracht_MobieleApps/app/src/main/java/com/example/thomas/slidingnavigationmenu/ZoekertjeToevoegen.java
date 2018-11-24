package com.example.thomas.slidingnavigationmenu;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;
import com.example.thomas.slidingnavigationmenu.Room.AppDatabase;
import com.example.thomas.slidingnavigationmenu.Room.ContactDAO;
import com.example.thomas.slidingnavigationmenu.Room.UserDB;
import com.example.thomas.slidingnavigationmenu.Room.ZoekertjeDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ZoekertjeToevoegen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ZoekertjeToevoegen#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    private FirebaseAuth fbauth;

    private FirebaseFirestore db;


    private OnFragmentInteractionListener mListener;

    public ZoekertjeToevoegen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZoekertjeToevoegen.
     */
    // TODO: Rename and change types and number of parameters
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
        fbauth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

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
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageInByte = baos.toByteArray();

                //eventuele errors
                AppDatabase database = Room.databaseBuilder(getActivity(), AppDatabase.class, "appdatabase.db")
                        .allowMainThreadQueries()   //Allows room to do operation on main thread
                        .build();
                String currentDBPath=getContext().getDatabasePath("appdatabase").getAbsolutePath();
                System.out.println("pad database "+currentDBPath);
                ContactDAO contactDAO = database.getContactDAO();
                ZoekertjeDB zoekertje=new ZoekertjeDB();
                zoekertje.setTitel(titel);
                zoekertje.setBeschrijving(beschrijving);
                zoekertje.setPrijs(prijs);
                zoekertje.setUserid(1);
                zoekertje.setFoto(imageInByte);
                //UserDB user=new UserDB();
                //user.setName("thomas");
                //contactDAO.insert(user);
                contactDAO.insert(zoekertje);

                Toast.makeText(getActivity(),"met succes zoekertje toegevoegd",Toast.LENGTH_SHORT).show();

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
