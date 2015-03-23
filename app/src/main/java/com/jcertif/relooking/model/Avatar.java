package com.jcertif.relooking.model;

import android.content.Context;


/**
 * Created by bashizip on 20/03/2015.
 * L'avatar represente l'image de la personne sur laquelle on applique le relooking.
 * Les autres items seront drag-dropees dessus.Mais il peut lui aussi etre drag-dropE.
 *
 */
public class Avatar extends RelookingItem {


    protected Avatar(Context context) {
        super(context);
    }

    @Override
    public String getFileName() {
        return "default_avatar";
    }
}
