package com.jcertif.relooking.model;

import android.content.Context;

/**
 * Created by bashizip on 23/03/2015.
 */
public class Cheveux extends RelookingItem {


    protected Cheveux(Context context) {
        super(context);
    }

    @Override
    public String getFileName() {
        return "cheveux";
    }

    @Override
    public int getItemsCount() {
        return 0;
    }
}
