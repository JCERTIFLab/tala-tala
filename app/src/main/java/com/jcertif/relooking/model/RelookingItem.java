package com.jcertif.relooking.model;

import android.view.DragEvent;
import android.view.View;

import com.jcertif.relooking.graphics.IReseizable;

/**
 * Created by bashizip on 20/03/2015.
 *
 */
public  class RelookingItem implements View.OnDragListener, IReseizable{

    private  int drawableResource;

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
}
