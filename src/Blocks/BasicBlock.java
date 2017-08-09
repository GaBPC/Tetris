/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author gabriel
 */
public abstract class BasicBlock {

    private Color color = null;
    private ArrayList<Point> points = null;
    private boolean[][] model = null;
    private int topRow = 0;
    private int leftRow = 0;

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
                    this.points.add(new Point(j + this.leftRow, i + this.topRow));
                }
            }
        }
    }
    
    public void resetBlock(){
        this.leftRow = 0;
        this.topRow = 0;
        this.generatePoints();
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

    public final void moveDown() {
        for (Shape shape : this.points) {
            shape.setCenterY(shape.getCenterY() + 1);
        }
        this.topRow += 1;
    }

    public final void moveLeft() {
        for (Shape shape : this.points) {
            shape.setCenterX(shape.getCenterX() - 1);
        }
        this.leftRow -= 1;
    }

    public final void moveRight() {
        for (Shape shape : this.points) {
            shape.setCenterX(shape.getCenterX() + 1);
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
    
    public final void removePoint(Point point){
        
    }

    /**
     * Gets an iterator with all the points forming the block
     *
     * @return the iterator of Cell
     */
    public final ArrayList<Point> getPoints() {

        return this.points;
    }

    public final float getLowestPoint() {

        float ret = 0;

        for (Point point : this.points) {
            if (point.getCenterY() > ret) {
                ret = point.getCenterY();
            }
        }

        return ret;

    }

    public final float getRightmostPoint() {

        float ret = 0;

        for (Point point : this.points) {
            if (point.getCenterX() > ret) {
                ret = point.getCenterX();
            }
        }

        return ret;

    }

    public final float getLeftmostPoint() {

        float ret = 9999;

        for (Point point : this.points) {
            if (point.getCenterX() < ret) {
                ret = point.getCenterX();
            }
        }

        return ret;

    }
}
