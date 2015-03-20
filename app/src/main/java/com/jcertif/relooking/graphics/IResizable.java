package com.jcertif.relooking.graphics;

/**
 * Created by bashizip on 20/03/2015.
 */
public interface IResizable {


    /**
     * Resize item
     * @param ratio (in percentage)
     */
    public abstract void resize(float ratio);

    /**
     * Rotation
     * @param angle (radians)
     */
    public abstract  void rotate(float angle);

    /**
     * Translation from coordinates(x0,y0) to (x1,y1)
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     */
    public abstract void translate(float x0, float y0, float x1, float y1);
}
