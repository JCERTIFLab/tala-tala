package com.jcertif.relooking.model;

import android.content.Context;

/**
 * Created by bashizip on 23/03/2015.
 */
public class Corps extends RelookingItem {

    protected Corps(Context context) {
        super(context);
    }

    @Override
    public String getFileName() {
        return "corps";
    }

    @Override
    public int getItemsCount() {
        return 0;
    }
}
