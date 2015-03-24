package com.jcertif.relooking.com.jcertif.relooking.utils;

/**
 * Created by bashizip on 23/03/2015.
 */
public class ItemsUtils {


    public enum ItemType {
        HAIR, EYES, CLOTHES, SHOES, MAKEUP, AVATAR, LIPS,BODY;
    }

    public static int getItemCount(ItemType type) {

        int count = 0;

        switch (type) {

            case HAIR:
                count = 10;
                break;
            case EYES:
                count = 7;
                break;

            case LIPS:
                count = 3;
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
                name = "chaussure";
                break;
            case AVATAR:
                name = "background";
                break;

            case LIPS:
                name = "levre";
                break;
        }

        return name;
    }


}

