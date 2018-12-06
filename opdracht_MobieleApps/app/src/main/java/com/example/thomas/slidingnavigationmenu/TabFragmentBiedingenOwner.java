package com.example.thomas.slidingnavigationmenu;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.thomas.slidingnavigationmenu.BiedingListAdapter;
import com.example.thomas.slidingnavigationmenu.R;
import com.example.thomas.slidingnavigationmenu.Room.AppDatabase;
import com.example.thomas.slidingnavigationmenu.Room.BiedingDB;
import com.example.thomas.slidingnavigationmenu.Room.ContactDAO;
import com.example.thomas.slidingnavigationmenu.Room.ZoekertjeDB;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentBiedingenOwner extends Fragment {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_tab_fragment_biedingen_owner, container, false);
        Intent i = getActivity().getIntent();
        z = (ZoekertjeDB) i.getSerializableExtra("mijnZoekertje");
        //biedingen
        AppDatabase database = Room.databaseBuilder(getActivity(), AppDatabase.class, "appdatabase.db")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        ContactDAO contactDAO = database.getContactDAO();
        //biedingen=contactDAO.findRepositoriesForBieding(z.getZoekertjeid());
        adapter=new BiedingListAdapter(getActivity(), R.layout.customlayout2,biedingen);
        mijnListView=(ListView) view.findViewById(R.id.biedingListView);
        mijnListView.setAdapter(adapter);

        return view;
    }


}
