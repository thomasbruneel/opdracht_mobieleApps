package com.example.thomas.slidingnavigationmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thomas.slidingnavigationmenu.Models.ZoekertjeDB;

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
        String image=getItem(position).getImage();
        byte[] byteImage= Base64.decode(image,Base64.DEFAULT);
        if(image!=null){
            Bitmap bmp = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
            afbeelding.setImageBitmap(bmp);
        }


        return convertView;

    }

    public void notifyDataSetChanged(List<ZoekertjeDB> zoekertjes) {
        this.clear();
        this.addAll(zoekertjes);
    }
}
