package com.jcertif.relooking.model;

import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;

import com.jcertif.relooking.graphics.IResizable;

/**
 * Classe mere de tous les items impliquEs dans le relooking
 *
 *
 * Created by bashizip on 20/03/2015.
 *
 */
public abstract class RelookingItem implements View.OnDragListener, IResizable {

    private String name;
    private  ItemType type;
    private float width;
    private float height;

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







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract Drawable getDrawableResource();
}
