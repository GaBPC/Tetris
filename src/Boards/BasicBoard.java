/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boards;

import Blocks.BasicBlock;
import Blocks.Cell;
import Blocks.CellsAllocationTable;
import java.util.Iterator;

/**
 * Class that represents the board where the different blocks move. Can be
 * thinked as a matrix of NxM. Each cell in the array can contain 0 or 1
 * instances of Cell.
 *
 *
 *
 * @author gabriel
 */
public abstract class BasicBoard {

    /**
     * Number of CAT's row.
     */
    private final int rowCount;
    /**
     * Number of CAT's column.
     */
    private final int columnCount;

    /**
     * Cells Allocation Table.
     */
    CellsAllocationTable CAT = null;

    public BasicBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.CAT = this.initializeBAT();
    }

    /**
     * @return the number of rows of the board.
     */
    public int getRowCount() {
        return this.rowCount;
    }

        /**
     * @return the number of columns of the board.
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * Function that initializes the CAT. It should be implemented on every
     * board, based on how you want that board to begin.
     *
     * @return a CAT initialized.
     */
    protected abstract CellsAllocationTable initializeBAT();

    /**
     * Function that returns an ArrayList with all the OCCUPIED cells on the
     * board. Each cell has its position and its color.
     *
     * @return an ArrayList of Cell
     */
    public Iterator<Cell> getCells() {
        return this.CAT.getCells();
    }

    /**
     * Function that checks if a given block can move one position to the right
     * on the board. It does this based on the current position of the block and
     * the current state of the board cells.
     *
     * @param block is the block you are trying to move.
     * @return true if the block can be moved, false otherwise.
     */
    public boolean checkRigthMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Cell> points = block.getPoints().iterator();
        while (points.hasNext() && check) {
            Cell point = points.next();
            int pointX = (int) point.getX();
            int pointY = (int) point.getY();

            Cell cell = this.CAT.getCell(pointY, pointX + 1);

            if (cell != null) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Function that checks if a given block can move one position to the left
     * on the board. It does this based on the current position of the block and
     * the current state of the board cells.
     *
     * @param block is the block you are trying to move.
     * @return true if the block can be moved, false otherwise.
     */
    public boolean checkLeftMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Cell> points = block.getPoints().iterator();
        while (points.hasNext() && check) {
            Cell point = points.next();
            int pointX = (int) point.getX();
            int pointY = (int) point.getY();

            Cell cell = this.CAT.getCell(pointY, pointX - 1);

            if (cell != null) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Function that checks if a given block can move one position to the bottom
     * on the board. It does this based on the current position of the block and
     * the current state of the board cells.
     *
     * @param block is the block you are trying to move.
     * @return true if the block can be moved, false otherwise.
     */
    public boolean checkBottomMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Cell> points = block.getPoints().iterator();
        while (points.hasNext() && check) {
            Cell point = points.next();
            int pointX = (int) point.getX();
            int pointY = (int) point.getY();

            Cell cell = this.CAT.getCell(pointY + 1, pointX);

            if (cell != null) {
                check = false;
            }
        }
        return check;
    }

    /**
     * A function that registers the cells of a block on the game board. A block
     * must be registered when it is not wanted that the user can continue to
     * control it.
     *
     * @param block
     */
    public void registerBlock(BasicBlock block) {
        Iterator<Cell> points = block.getPoints().iterator();

        while (points.hasNext()) {
            Cell point = points.next();

            int x = (int) point.getX();
            int y = (int) point.getY();

            this.CAT.setCell(y, x, block.getColor());
        }

    }

    /**
     * Verify, based on the status of the board, if the game is finished. This
     * function must be defined in each game mode (boards), based on the mode
     * rules.
     *
     * @return true if the game is over, false otherwise.
     */
    public abstract boolean isGameOver();

}
