package com.jcertif.relooking.model;

import android.content.Context;
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
public  abstract  class  RelookingItem implements View.OnDragListener, IResizable {


    private  int resourceId;

    private Context context;
    private  ItemType type;
    private float width;
    private float height;

    protected RelookingItem(Context context) {
        this.context = context;
    }



    public int getResourceId() {
        resourceId = context.getApplicationContext().getResources().getIdentifier(getFileName(), "drawable", context.getApplicationContext().getPackageName());
        return resourceId;
    }

    public abstract  String getFileName();

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
