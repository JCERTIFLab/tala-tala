package com.jcertif.relooking.graphics;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import  java.util.List;

import com.jcertif.relooking.R;
import com.jcertif.relooking.model.RelookingItem;


/**
 *
 * Created by bashizip on 23/03/2015.
 *
 */
public  class PaletteAdapter extends BaseAdapter {

    private List<Drawable> itemList;
    private LayoutInflater mLayoutInflater;
    private Context ctx;

    public PaletteAdapter(Context ctx, List<Drawable> itemList) {
        this.ctx=ctx;
        this.itemList=itemList;
    }

    public PaletteAdapter(List<Drawable> itemList) {
        this.itemList=itemList;
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
        return position;

    }


    public List<Drawable> getItemList() {
        return itemList;
    }

    public void setItemList(List<Drawable> itemList) {
        this.itemList = itemList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Drawable item = itemList.get(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.palette_item, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById((R.id.palette_image));
        imageView.setImageDrawable(item);

        return convertView;
    }

    private static class ViewHolder {
        public ImageView mImageView;

    }
}
