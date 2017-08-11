/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public boolean isEmpty(int row, int column){
        return this.map[row][column] != null;
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
                if (this.isEmpty(i, j)) {
                    ret.add(this.getCell(i, j));
                }
            }
        }

        return ret.iterator();
    }

}
