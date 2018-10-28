package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MijnZoekertjes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MijnZoekertjes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MijnZoekertjes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MijnZoekertjes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mijZoekertjes.
     */
    // TODO: Rename and change types and number of parameters
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
        final View view=inflater.inflate(R.layout.fragment_mijn_zoekertjes, container, false);
        // code hieronder is tijdelijk gewoon uittesten van listview
        ArrayList<Zoekertje>producten=new ArrayList<>();
        producten.add(new Zoekertje(1,"fiets",14.5));
        producten.add(new Zoekertje(2,"stoel",12));
        producten.add(new Zoekertje(3,"tafel",9));


     ListView lv=(ListView)view.findViewById(R.id.mijnListView);
     ArrayAdapter<Zoekertje> adapter=new ArrayAdapter<Zoekertje>(getActivity(),android.R.layout.simple_list_item_1,producten);
     lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Zoekertje p = (Zoekertje) (parent.getItemAtPosition(position));
                Intent intent = new Intent(view.getContext(),ZoekertjeView.class);
                intent.putExtra("mijnProduct",p);
                startActivity(intent);
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
