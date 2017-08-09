/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author gabriel
 */
public abstract class BasicBlock extends Polygon {

    private int cellWidth, cellHeigth;

    private Color color = null;
    private ArrayList<Cell> cells = null;
    private boolean[][] model = null;
    private int topRow = 0;
    private int leftRow = 0;

    public BasicBlock(boolean[][] model, Color color, int centerX, int centerY, int cell_width, int cell_heigth) {

        this.model = model;
        this.cellWidth = cell_width;
        this.cellHeigth = cell_heigth;
        this.color = color;

        this.generatePoints();

    }

    private void generatePoints() {
        this.cells = new ArrayList<>();

        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {
                if (model[i][j] == true) {
                    this.cells.add(new Cell((j + this.leftRow) * this.cellWidth, (i + this.topRow) * this.cellHeigth, this.cellWidth, this.cellHeigth));
                }
            }
        }
    }

    /**
     * Function that rotates the block.
     *
     */
    public final void rotate() {

        // Transposes the matrix
        boolean[][] modelTemp = new boolean[this.model[0].length][this.model.length];
        for (int i = 0; i < this.model.length; i++) {
            for (int j = 0; j < this.model[i].length; j++) {
                modelTemp[j][i] = this.model[i][j];
            }
        }

        // Reverses each matrix row
        for (int i = 0; i < this.model.length; i++) {
            for (int j = 0; j < this.model[i].length / 2; j++) {
                boolean aux = modelTemp[i][j];
                modelTemp[i][j] = modelTemp[i][modelTemp[0].length - j - 1];
                modelTemp[i][modelTemp[0].length - j - 1] = aux;
            }
        }


        this.model = modelTemp;


        this.generatePoints();

    }

    /**
     * Function that calculates the position of the pixel that is down the
     * screen.
     *
     * @return the postion of that pixel
     */
    public final float getBottomPosition() {

        /**
         * Since the Y + axis is oriented downwards on the screen, the pixel
         * above will be in (x, 0)
         */
        float ret = 0;

        for (Cell cell : this.cells) {
            if (cell.getBottomPosition() > ret) {
                ret = cell.getBottomPosition();
            }
        }

        return ret;
    }

    /**
     * Function that calculates the position of the pixel to the right of the
     * screen.
     *
     * @return the postion of that pixel
     */
    public final float getRightPosition() {

        float ret = 0;

        for (Cell cell : this.cells) {
            if (cell.getRightPosition() > ret) {
                ret = cell.getRightPosition();
            }
        }

        return ret;
    }

    /**
     * Function that calculates the position of the pixel to the left of the
     * screen.
     *
     * @return the postion of that pixel
     */
    public final float getLeftPosition() {

        float ret = 99999999;

        for (Cell cell : this.cells) {
            if (cell.getLeftPosition() < ret) {
                ret = cell.getLeftPosition();
            }
        }

        return ret;
    }

    public final void moveDown(int amount) {
        for (Shape shape : this.cells) {
            shape.setCenterY(shape.getCenterY() + amount);
        }
        this.topRow += 1;
    }

    public final void moveLeft(int amount) {
        for (Shape shape : this.cells) {
            shape.setCenterX(shape.getCenterX() - amount);
        }
        this.leftRow -= 1;
    }

    public final void moveRight(int amount) {
        for (Shape shape : this.cells) {
            shape.setCenterX(shape.getCenterX() + amount);
        }
        this.leftRow += 1;
    }

    /**
     * Returns the color with which the block has to be drawn
     *
     * @return the color with which the block has to be drawn
     */
    public final Color getColor() {
        return this.color;
    }

    /**
     * Gets an iterator with all the cells forming the block
     *
     * @return the iterator of Cell
     */
    public final ArrayList<Cell> getCells() {

        return this.cells;
    }

}
