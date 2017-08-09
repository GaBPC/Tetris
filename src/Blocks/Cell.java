/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author gabriel
 */
public class Cell extends Rectangle {

    private float width, height;

    public Cell(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
    }


    /**
     * Function that calculates the position of the pixel that is down the
     * screen.
     *
     * @return the postion of that pixel
     */
    public final float getBottomPosition() {
        return this.getCenterY() + this.height / 2;
    }

    /**
     * Function that calculates the position of the pixel to the right of the
     * screen.
     *
     * @return the postion of that pixel
     */
    public final float getRightPosition() {
        return this.getCenterX() + this.width / 2;
    }

    /**
     * Function that calculates the position of the pixel to the left of the
     * screen.
     * 
     * @return the postion of that pixel
     */
    public final float getLeftPosition() {
        return this.getCenterX() - this.width / 2;
    }

}
