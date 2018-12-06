package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Room.BiedingDB;

import java.util.List;

/**
 * Created by thomas on 01/11/2018.
 */

public class BiedingListAdapter extends ArrayAdapter<BiedingDB> {
    private Context mContext;
    private int mResource;
    private List<BiedingDB> mObjects;
    private int lastPosition = -1;



    public BiedingListAdapter(Context context, int resource, List<BiedingDB> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mObjects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        double prijs=getItem(position).getBiederprijs();
        String price=String.valueOf(prijs);
        String time=getItem(position).getDatum();

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvPrice=(TextView)convertView.findViewById(R.id.uiListPrice);
        tvPrice.setText(" bod: "+price+ "€");

        TextView tvTime=(TextView) convertView.findViewById(R.id.uiListTime);
        tvTime.setText(time);

        TextView tvBieder=(TextView) convertView.findViewById(R.id.uiListBieder);
        tvBieder.setText("bieder...");

        return convertView;

    }

    public void notifyDataSetChanged(BiedingDB bieding) {
        this.add(bieding);
    }
}
