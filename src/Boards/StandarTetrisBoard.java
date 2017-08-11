/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boards;

import Blocks.CellsAllocationTable;

/**
 *
 * @author gabriel
 */
public class StandarTetrisBoard extends BasicBoard {

    public StandarTetrisBoard() {
        super(16, 10);
    }

    /**
     * On a basic tetris game the board will start empty.
     *
     * @return an empty CellsAllocationTable (all the cells on null).
     */
    @Override
    protected CellsAllocationTable initializeBAT() {

        CellsAllocationTable cat = new CellsAllocationTable(super.getRowCount(), super.getRowCount());

        cat.clear();

        return cat;
    }

    /**
     * In a basic game of tetris the game ends when a cell in the first row of
     * the board is occupied.
     *
     */
    @Override
    public boolean isGameOver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
