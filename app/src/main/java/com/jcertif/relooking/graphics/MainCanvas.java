package com.jcertif.relooking.graphics;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jcertif.relooking.model.Avatar;
import com.jcertif.relooking.model.RelookingItem;

/**
 *
 * Represente la scene principale des operations
 * Contient l'avatar et les items qui y sont ajoutEs
 *
 * Created by bashizip on 20/03/2015.
 *
 */
public class MainCanvas extends FrameLayout implements ICanvasOperation {


    private  Avatar avatar;
    private  CanvasDimensions dimensions;


    public MainCanvas(Context context) {
        super(context);
    }


    @Override
    public void addItem(RelookingItem item) {

        ImageView imgView = new ImageView(getContext());
        imgView.setId(item.getResourceId());
        imgView.setImageDrawable(getResources().getDrawable(item.getResourceId()));

        this.addView(imgView);


    }

    @Override
    public void removeItem(RelookingItem item) {

        removeViewAt(item.getResourceId());

    }

    @Override
    public void changeAvatar(Avatar avatar) {

    }

    @Override
    public void upDateDimensions(CanvasDimensions newDimensions) {

    }
}
