package com.jcertif.relooking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;

import com.jcertif.relooking.com.jcertif.relooking.utils.ItemsUtils.ItemType;
import com.jcertif.relooking.graphics.IResizable;

/**
 * Classe mere de tous les items impliquEs dans le relooking
 * <p/>
 * <p/>
 * Created by bashizip on 20/03/2015.
 */
public abstract class RelookingItem implements View.OnDragListener, IResizable {


    private  ItemType type;
    private Drawable drawable;


    protected RelookingItem(ItemType type, Drawable drawable) {
        this.type = type;
        this.drawable = drawable;
    }

    public ItemType getType() {
        return type;
    }

    public Drawable getDrawable() {
        return drawable;
    }


    public void setType(ItemType type) {
        this.type = type;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }


    @Override
    public void resize(float ratio) {

    }

    @Override
    public void rotate(float angle) {

    }

    @Override
    public void translate(float x0, float y0, float x1, float y1) {

    }
}
