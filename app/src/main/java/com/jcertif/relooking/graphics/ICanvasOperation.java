package com.jcertif.relooking.graphics;

import com.jcertif.relooking.model.RelookingItem;

/**
 * Created by bashizip on 20/03/2015.
 */
public interface ICanvasOperation {


    void addItem(RelookingItem item);

    void removeItem(RelookingItem item);

    void changeAvatar(RelookingItem avatar);

    void upDateDimensions(CanvasDimensions newDimensions);


}
