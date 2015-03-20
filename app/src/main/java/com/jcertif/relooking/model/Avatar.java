package com.jcertif.relooking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.jcertif.relooking.R;

/**
 * Created by bashizip on 20/03/2015.
 * L'avatar represente l'image de la personne sur laquelle on applique le relooking.
 * Les autres items seront drag-dropees dessus.Mai il peut lui aussi etre drag-dropE.
 *
 */
public class Avatar extends RelookingItem {

    Context ctx;
    Drawable drawable;

    public Avatar(Context ctx) {
        this.ctx = ctx;
        setType(ItemType.AVATAR);
    }

    public Avatar(Context ctx,View view) {
        this.ctx = ctx;
    }

    @Override
    public Drawable getDrawableResource() {
        if(drawable ==null){
            return  ctx.getResources().getDrawable(R.drawable.default_avatar);
        }
        return drawable;
    }

    /**
     * Update dwawable at runtime
     * Depuis plusieurs sources : camera, galerie, etc..
     * @param drawable
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
