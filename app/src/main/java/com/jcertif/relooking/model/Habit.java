package com.jcertif.relooking.model;

import android.content.Context;

/**
 * Created by bashizip on 23/03/2015.
 */
public class Habit extends RelookingItem {


    protected Habit(Context context) {
        super(context);
    }

    @Override
    public String getFileName() {
        return "habit";
    }

    @Override
    public int getItemsCount() {
        return 0;
    }
}
