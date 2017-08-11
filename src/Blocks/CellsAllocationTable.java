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
 *
 * @author gabriel
 */
public class CellsAllocationTable {

    private final int rowCount, columnCount;
    private Cell[][] map = null;

    public CellsAllocationTable(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.map = new Cell[this.rowCount][this.columnCount];
        this.clear();
    }

    /**
     * Sets all the cells of the table on null.
     *
     */
    public void clear() {

        for (int i = 0; i < this.rowCount; i++) {
            for (int j = 0; j < this.columnCount; j++) {
                this.map[i][j] = null;
            }
        }

    }

    /**
     * Sets the cell in the intersection given by the row and the column.
     *
     * @param row indicates the row where the cell will be placed.
     * @param column indicates the column where the cell will be placed.
     * @param color indicates the color that the cell will have.
     */
    public void setCell(int row, int column, Color color) {
        this.map[row][column] = new Cell(row, column, color);
    }

    /**
     * Gets a specific cell of the table.
     *
     * @param row indicates the row where the cell is placed.
     * @param column indicates the column where the cell is placed.
     * @return the cell in the intersection given by the row and the column.
     */
    public Cell getCell(int row, int column) {
        return this.map[row][column];
    }

    /**
     * Determines if a cell is empty.
     *
     * @param row indicates the row where the cell is placed.
     * @param column indicates the column where the cell is placed.
     * @return true if the cell is empty, false otherwise
     */
    public boolean isEmpty(int row, int column) {
        return this.map[row][column] == null;
    }

    /**
     * Determines if an entire row is empty.
     *
     * @param row indicates the row to check.
     * @return true if all the cells on the row are empty, false otherwise
     */
    public boolean rowIsEmpty(int row) {

        int j = 0;

        while (j < this.columnCount && this.isEmpty(row, j)) {
            j++;
        }

        return j >= this.columnCount;

    }

    /**
     * Returns an Iterator with all the cells set in the table.
     *
     * @return an iterator of Cell.
     */
    public Iterator<Cell> getCells() {
        ArrayList<Cell> ret = new ArrayList<>();

        for (int i = 0; i < this.rowCount; i++) {
            for (int j = 0; j < this.columnCount; j++) {
                if (!this.isEmpty(i, j)) {
                    ret.add(this.getCell(i, j));
                }
            }
        }

        return ret.iterator();
    }

    /**
     * Function that removes all lines on the board. A line is a row with all
     * its cells occupied.
     *
     * @return the number of lines eliminated.
     */
    public int deleteLines() {

        ArrayList<Integer> rows = new ArrayList<>();

        for (int i = 0; i < this.rowCount; i++) {
            boolean line = true;
            for (int j = 0; j < this.columnCount; j++) {
                if (this.isEmpty(i, j)) {
                    line = false;
                }
            }
            if (line) {
                rows.add(i);
            }
        }

        if (rows.size() > 0) {
            Iterator<Integer> it = rows.iterator();

            while (it.hasNext()) {
                Integer index = it.next();
                for (int i = 0; i < this.columnCount; i++) {
                    this.map[index][i] = null;
                }
            }

            Cell[][] newMap = new Cell[this.rowCount][this.columnCount];

            int k = this.rowCount - 1;
            for (int i = this.rowCount - 1; i >= 0; i--) {
                boolean empty = true;
                for (int j = 0; j < this.columnCount; j++) {
                    if (!this.isEmpty(i, j)) {
                        empty = false;
                    }
                }
                if (!empty) {
                    for (int j = 0; j < this.columnCount; j++) {
                        if (this.map[i][j] != null) {
                            newMap[k][j] = new Cell(k, j, this.map[i][j].getColor());
                        } else {
                            newMap[k][j] = this.map[i][j];
                        }
                    }
                    k--;
                }
            }

            this.map = newMap;
        }

        return rows.size();

    }

}
