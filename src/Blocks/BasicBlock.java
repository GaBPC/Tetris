/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import java.util.ArrayList;
import org.newdawn.slick.Color;

/**
 *
 * @author gabriel
 */
public abstract class BasicBlock {

    /**
     * It's the color with which the block has to been painted.
     */
    private Color color = null;
    /**
     * It's a collection of Point.
     */
    private ArrayList<Cell> points = null;
    /**
     * It's the block's model. Represents how the different cells that compose
     * it are distributed.
     */
    private boolean[][] model = null;
    /**
     * 
     */
    private int topRow = 0;
    /**
     * 
     */
    private int leftRow = 0;

    /**
     * 
     * 
     * @param model
     * @param color 
     */
    public BasicBlock(boolean[][] model, Color color) {

        this.model = model;
        this.color = color;

        this.generatePoints();

    }

    private void generatePoints() {
        this.points = new ArrayList<>();

        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {
                if (model[i][j] == true) {
                    this.points.add(new Cell(j + this.leftRow, i + this.topRow, this.getColor()));
                }
            }
        }
    }

    public void resetBlock() {
        this.leftRow = 0;
        this.topRow = 0;
        this.generatePoints();
    }

    /**
     * Function that calculates the position of the block once it has rotated.
     * Uses a block model and rotates it 90º.Returns a rotated model. It is
     * useful to know if the rotation can be performed or will cause a collision
     * with other blocks.
     *
     * @param model the model to rotate. It won't be modified.
     * @return a new model rotated.
     */
    public boolean[][] calculateRotation(boolean[][] model) {
        boolean[][] modelTemp = new boolean[this.model[0].length][this.model.length];

        // Transposes the model
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

        return modelTemp;

    }

    /**
     * Function that rotates the block.
     *
     */
    public final void rotate() {

        this.model = this.calculateRotation(this.model);

        this.generatePoints();
    }

    public final void moveDown() {
        this.points.stream().forEach((shape) -> {
            shape.setY(shape.getY() + 1);
        });
        this.topRow += 1;
    }

    public final void moveLeft() {
        this.points.stream().forEach((shape) -> {
            shape.setX(shape.getX() - 1);
        });
        this.leftRow -= 1;
    }

    public final void moveRight() {
        this.points.stream().forEach((shape) -> {
            shape.setX(shape.getX() + 1);
        });
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
     * Gets an iterator with all the points forming the block
     *
     * @return the iterator of Cell
     */
    public final ArrayList<Cell> getPoints() {

        return this.points;
    }

    public final float getLowestPoint() {

        float ret = 0;

        for (Cell point : this.points) {
            if (point.getY() > ret) {
                ret = point.getY();
            }
        }

        return ret;

    }

    public final float getRightmostPoint() {

        float ret = 0;

        for (Cell point : this.points) {
            if (point.getX() > ret) {
                ret = point.getX();
            }
        }

        return ret;

    }

    public final float getLeftmostPoint() {

        float ret = 9999;

        for (Cell point : this.points) {
            if (point.getX() < ret) {
                ret = point.getX();
            }
        }

        return ret;

    }
}
