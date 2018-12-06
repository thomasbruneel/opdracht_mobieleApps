package com.example.thomas.slidingnavigationmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Models.BiedingDB;
import com.example.thomas.slidingnavigationmenu.Models.ZoekertjeDB;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentProfiel extends Fragment {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_tab_fragment_profiel, container, false);
        TextView tekst=(TextView) view.findViewById(R.id.tekst);
        tekst.setText("hier moet info komen over de eigenaar van zoekertje....");

        return view;
    }
}