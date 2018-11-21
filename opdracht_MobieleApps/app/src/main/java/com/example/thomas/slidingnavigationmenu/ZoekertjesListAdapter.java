package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Models.Zoekertje;
import com.example.thomas.slidingnavigationmenu.Room.ZoekertjeDB;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 01/11/2018.
 */

public class ZoekertjesListAdapter extends ArrayAdapter<ZoekertjeDB> {
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;



    public ZoekertjesListAdapter(Context context, int resource, List<ZoekertjeDB> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String titel=getItem(position).getTitel();
        String price=String.valueOf(getItem(position).getPrijs());

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvName=(TextView)convertView.findViewById(R.id.uiListTitle);
        TextView tvPrice=(TextView)convertView.findViewById(R.id.uiListPrice);

        tvName.setText("titel: "+titel);
        tvPrice.setText("prijs: €"+price);

        return convertView;

    }
}
