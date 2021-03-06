package com.example.thomas.slidingnavigationmenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Models.BiedingDB;
import com.example.thomas.slidingnavigationmenu.Models.ZoekertjeDB;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentInfo extends Fragment {
    ZoekertjeDB z;
    private BiedingListAdapter adapter;
    ListView mijnListView;
    List<BiedingDB> biedingen=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_tab_fragment_info, container, false);

        Intent i = getActivity().getIntent();
        z = (ZoekertjeDB) i.getSerializableExtra("mijnZoekertje");
        System.out.println("HELP : " +z.getBeschrijving());


        TextView tv=(TextView) view.findViewById(R.id.Beschrijving);
        tv.setText(z.getBeschrijving());

        ImageView iv=(ImageView) view.findViewById(R.id.afbeelding);
        String image=z.getImage();
        byte[] byteImage= Base64.decode(image,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        iv.setImageBitmap(bitmap);

        return view;
    }
}