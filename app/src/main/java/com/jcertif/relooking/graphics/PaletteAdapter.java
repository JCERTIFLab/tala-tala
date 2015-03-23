package com.jcertif.relooking.graphics;


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

    List<RelookingItem> itemList;
    private LayoutInflater mLayoutInflater;

    public PaletteAdapter(  List<RelookingItem> itemList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        RelookingItem item = itemList.get(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_relooking, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById((R.id.palette_image));
        imageView.setImageResource(item.getResourceId());
        return convertView;
    }

    private static class ViewHolder {
        public ImageView mImageView;

    }
}
