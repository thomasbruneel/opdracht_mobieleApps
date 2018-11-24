package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ImageView afbeelding=(ImageView)convertView.findViewById(R.id.uiListImage);

        tvName.setText("titel: "+titel);
        tvPrice.setText("prijs: €"+price);

        byte[] image=getItem(position).getFoto();
        if(image!=null){
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            afbeelding.setImageBitmap(bmp);
        }

        //afbeelding.setImageBitmap(Bitmap.createScaledBitmap(bmp, afbeelding.getWidth(),
               // afbeelding.getHeight(), false));

        return convertView;

    }
}
