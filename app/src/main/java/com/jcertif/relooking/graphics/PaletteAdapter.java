package com.jcertif.relooking.graphics;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jcertif.relooking.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bashizip on 23/03/2015.
 *
 */
public class PaletteAdapter extends BaseAdapter {

    private List<Drawable> itemList;
    private Context ctx;



    public PaletteAdapter(Context ctx, List<Drawable> itemList) {
        this.ctx = ctx;
        this.itemList = itemList;
    }

    public PaletteAdapter(List<Drawable> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return itemList.indexOf(getItem(position));

    }


    public void updateModel(List<Drawable> newdrawableArrayList) {

        itemList=null;
        System.gc();

        itemList = newdrawableArrayList;
        //Triggers the list update
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(ctx).inflate(
                    R.layout.palette_item, parent, false);

            holder.mImageView = (ImageView) convertView.findViewById((R.id.palette_image));

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }


        Drawable item = itemList.get(position);

        holder.mImageView.setImageDrawable(item);

        return convertView;
    }


     static class ViewHolder {
         ImageView mImageView;

    }
}
