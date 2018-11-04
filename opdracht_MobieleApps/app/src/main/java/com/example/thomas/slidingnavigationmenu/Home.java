package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseFirestore db;
    private ArrayList<Zoekertje>zoekertjes;
    private ZoekertjesListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
      //  Zoekertjes fragment = new MijnZoekertjes();
        Home fragment = new Home();
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

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//voorkomt dat toetsenbord gepopped wordt bij edittext
        final View view=inflater.inflate(R.layout.fragment_home, container, false);

        db= FirebaseFirestore.getInstance();
        db.collection("zoekertjes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        zoekertjes=new ArrayList<Zoekertje>();
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document:task.getResult()){
                                Zoekertje zoekertje=document.toObject(Zoekertje.class);
                                zoekertjes.add(zoekertje);
                            }
                            ListView lv=(ListView)view.findViewById(R.id.mijnListView);

                            adapter=new ZoekertjesListAdapter(getActivity(),R.layout.customlayout,zoekertjes);
                            lv.setAdapter(adapter);
                            lv.setTextFilterEnabled(true);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Zoekertje z = (Zoekertje) (parent.getItemAtPosition(position));
                                    Intent intent = new Intent(view.getContext(),ZoekertjeView.class);
                                    intent.putExtra("mijnZoekertje",z);
                                    startActivity(intent);
                                }
                            });

                            EditText et=(EditText)view.findViewById(R.id.zoekveld);
                            et.addTextChangedListener(new TextWatcher() {

                                public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                                          int arg3) {

                                }

                                public void beforeTextChanged(CharSequence arg0, int arg1,
                                                              int arg2, int arg3) {

                                }

                                public void afterTextChanged(Editable arg0) {
                                    adapter.getFilter().filter(arg0);

                                }
                            });

                        }
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
