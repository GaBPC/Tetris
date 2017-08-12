/*
 * Copyright (C) 2017 gabriel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Blocks;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Color;

/**
 * A basic block is an abstraction to represent each Tetromino of the game. It
 * is composed of 4 instances of Cell, where each cell, at least, must be
 * contiguous to another cell of the block.
 *
 * Every block can move down or sideways, but can not move up. These positions
 * are based on a basic game board.
 *
 * In addition, every block can rotate by 90 degrees, allowing 4 different
 * positions for the same block. Finally, every block must have a color.
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

    protected int topRow = 0;

    protected int leftColumn = 0;

    /**
     * An enum with all the positions that the block can take.
     */
    protected static enum rotation {
        NORMAL, RIGTH, DOWN, LEFT;

        /**
         * Function that calculates the next position based in the actual
         * postion.
         *
         * @return the nex position
         */
        rotation next() {
            switch (this) {
                case NORMAL:
                    return RIGTH;
                case RIGTH:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return NORMAL;
                default:
                    return NORMAL;
            }
        }
    }

    /**
     * The actual position of the block
     */
    protected rotation actualRotation;

    /**
     * The pivot cell to rotations.
     */
    protected Cell pivotCell = null;

    /**
     *
     *
     * @param model
     * @param color
     */
    public BasicBlock(boolean[][] model, Color color) {

        this.actualRotation = rotation.NORMAL;

        this.model = model;
        this.color = color;

        this.points = this.generateCells();

    }

    /**
     * Function that generates the set of cells that form the block.
     *
     * @return An ArrayList of Cell wich contains the block's points.
     */
    private ArrayList<Cell> generateCells() {
        ArrayList<Cell> ret = new ArrayList<>();

        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {
                if (model[i][j] == true) {
                    Cell cell = new Cell(i + this.topRow, j + this.leftColumn, this.getColor());
                    ret.add(cell);
                    if (i == 1 && j == 1) {
                        this.pivotCell = cell;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Function that puts the block back at the spawn cell.
     */
    public void resetToSpawn() {
        this.leftColumn = 0;
        this.topRow = 0;
        this.points = this.generateCells();
    }

    /**
     * Function that returns the cells that are need to be free so that the
     * block can rotate. These points are relative to the pivot cell of the
     * block, which is always P (1,1) in the block model.
     *
     * The points are calculated based on the actual rotation of the block.
     *
     * @return an iterator of Point
     */
    public abstract Iterator<Cell> cellsNeededToRotate();

    /**
     * Function that rotates the block.
     */
    public final void rotate() {
        boolean[][] modelTemp = new boolean[this.model[0].length][this.model.length];

        // Transposes the model.
        for (int i = 0; i < this.model.length; i++) {
            for (int j = 0; j < this.model[i].length; j++) {
                modelTemp[j][i] = this.model[i][j];
            }
        }

        // Reverses each matrix row.
        for (int i = 0; i < this.model.length; i++) {
            for (int j = 0; j < this.model[i].length / 2; j++) {
                boolean aux = modelTemp[i][j];
                modelTemp[i][j] = modelTemp[i][modelTemp[0].length - j - 1];
                modelTemp[i][modelTemp[0].length - j - 1] = aux;
            }
        }

        this.model = modelTemp;

        this.actualRotation = this.actualRotation.next();

        this.points = this.generateCells();
    }

    /**
     * Moves the block one position down. This will cause each Cell forming the
     * block to move down one position.
     */
    public final void moveDown() {
        this.points.forEach((cell) -> {
            cell.setRow(cell.getRow() + 1);
        });

        this.topRow += 1;
    }

    /**
     * Moves the block one position to the left. This will cause each Cell
     * forming the block to move down one position.
     */
    public final void moveLeft() {
        this.points.stream().forEach((shape) -> {
            shape.setColumn(shape.getColumn() - 1);
        });
        this.leftColumn -= 1;
    }

    /**
     * Moves the block one position to the rigth. This will cause each Cell
     * forming the block to move down one position.
     */
    public final void moveRight() {
        this.points.stream().forEach((shape) -> {
            shape.setColumn(shape.getColumn() + 1);
        });
        this.leftColumn += 1;
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
        return this.points;
    }

    /**
     *
     *
     * @return
     */
    public Cell getPivotCell() {
        return pivotCell;
    }

    /**
     * Function that returns the cell whose position is the lowest. This is done
     * by comparing in which row each cell is located.
     *
     * @return the indicated Cell.
     */
    public final Cell getLowestCell() {

        Cell ret = new Cell(0, 0);

        for (Cell cell : this.points) {
            if (cell.getRow() > ret.getRow()) {
                ret = cell;
            }
        }

        return ret;

    }

    /**
     * Function that returns the cell whose position is to the right. This is
     * done by comparing which column each cell is in.
     *
     * @return the indicated Cell.
     */
    public final Cell getRightmostCell() {

        Cell ret = new Cell(0, 0);

        for (Cell cell : this.points) {
            if (cell.getColumn() > ret.getColumn()) {
                ret = cell;
            }
        }

        return ret;

    }

    /**
     * Function that returns the cell whose position is to the left. This is
     * done by comparing which column each cell is in.
     *
     * @return the indicated Cell.
     */
    public final Cell getLeftmostCell() {

        Cell ret = new Cell(9999, 9999);

        for (Cell cell : this.points) {
            if (cell.getColumn() < ret.getColumn()) {
                ret = cell;
            }
        }

        return ret;

    }
}
