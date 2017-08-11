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
    /**
     *
     */
    private int linesCount;

    public BasicBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.CAT = this.initializeBAT();
        this.linesCount = 0;
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

    public int getLinesCount() {
        return this.linesCount;
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
    public final boolean checkRigthMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Cell> points = block.getCells().iterator();
        while (points.hasNext() && check) {
            Cell point = points.next();
            int pointX = (int) point.getRow();
            int pointY = (int) point.getColumn();

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
    public final boolean checkLeftMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Cell> points = block.getCells().iterator();
        while (points.hasNext() && check) {
            Cell point = points.next();
            int pointX = (int) point.getRow();
            int pointY = (int) point.getColumn();

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
    public final boolean checkBottomMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Cell> points = block.getCells().iterator();
        while (points.hasNext() && check) {
            Cell point = points.next();
            int pointX = (int) point.getRow();
            int pointY = (int) point.getColumn();

            Cell cell = this.CAT.getCell(pointY + 1, pointX);

            if (cell != null) {
                check = false;
            }
        }
        return check;
    }

    /**
     * A function that validates if the block can rotate on the board.
     *
     * @param block
     * @return true if the block can be rotated, false otherwise.
     */
    public final boolean checkRotation(BasicBlock block) {
        Iterator<Cell> cells = block.cellsNeededToRotate();

        boolean check = true;

        while (cells.hasNext() && check) {
            Cell cell = cells.next();

            int row = cell.getRow();
            int col = cell.getColumn();

            if (row < 0 || row >= this.rowCount || col < 0 || col >= this.columnCount || !this.CAT.isEmpty(row, col)) {
                check = false;
            }

        }

        // TODO
        return check;
    }

    /**
     * A function that registers the cells of a block on the game board. A block
     * must be registered when it is not wanted that the user can continue to
     * control it.
     *
     * @param block
     */
    public final void registerBlock(BasicBlock block) {
        Iterator<Cell> points = block.getCells().iterator();

        while (points.hasNext()) {
            Cell point = points.next();

            int x = (int) point.getRow();
            int y = (int) point.getColumn();

            this.CAT.setCell(y, x, block.getColor());
        }

        this.linesCount += this.CAT.deleteLines();

        if (this.isGameOver()) {
            //TODO
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
