package com.jcertif.relooking.graphics;

import android.view.View;

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
public class MainCanvas implements CanvasOperation {

    private View parent;
    private  Avatar avatar;
    private  CanvasDimensions dimensions;


    @Override
    public void addItem(RelookingItem item) {

    }

    @Override
    public void removeItem(RelookingItem item) {

    }

    @Override
    public void changeAvatar(Avatar avatar) {

    }

    @Override
    public void upDataDimensions(CanvasDimensions newDimensions) {

    }
}
