package com.jcertif.relooking.com.jcertif.relooking.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by bashizip on 23/03/2015.
 *
 */
public class ItemsUtils {



    public enum ItemType {
        HAIR, EYES, CLOTHES, SHOES, MAKEUP, AVATAR, BODY;
    }

    public static int getItemCount(ItemType type) {

        int count = 0;

        switch (type) {

            case HAIR:

                count = 13;
                break;
            case EYES:
                count = 5;
                break;

            case CLOTHES:
                count = 15;
                break;
            case MAKEUP:
                count = 0;
                break;
            case SHOES:
                count = 1;
                break;
            case AVATAR:
                count = 1;
                break;

        }

        return count;

    }

    public static String getBaseName(ItemType type) {

        String name = "";

        switch (type) {

            case HAIR:

                name = "cheveux";
                break;
            case EYES:
                name = "oeil";
                break;

            case CLOTHES:
                name = "blanc";
                break;
            case MAKEUP:
                name = "maquillage";
                break;
            case SHOES:
                name = "soulier";
                break;
            case AVATAR:
                name = "background";
                break;

        }

        return name;
    }


}

