package com.jcertif.relooking.media;

import android.content.Context;
import android.widget.RelativeLayout;

import com.jcertif.relooking.model.ItemType;
import com.jcertif.relooking.model.RelookingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bashizip on 20/03/2015.
 *
 */
public abstract  class RelookingItemsFactory {


    public static List<RelookingItem> loadAllFromResource(RelookingItem like){

        List<RelookingItem> itemslList=new ArrayList<>();

        switch (like.getType()){

            case HAIR:

                for(int i=0;i<like.getItemsCount();i++){



                }

                    break;
            case BODY:

                break;

            case CLOTHES:
                break;
            case MAKEUP:
                break;
            case SHOES:
                break;
            case AVATAR:
                break;

        }
        return itemslList;
    }




}
