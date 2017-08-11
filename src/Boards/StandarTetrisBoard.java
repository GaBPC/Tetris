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

import Blocks.CellsAllocationTable;

/**
 *
 * @author gabriel
 */
public class StandarTetrisBoard extends BasicBoard {

    public StandarTetrisBoard() {
        super(20, 10);
    }

    /**
     * On a basic tetris game the board will start empty.
     *
     * @return an empty CellsAllocationTable (all the cells on null).
     */
    @Override
    protected CellsAllocationTable initializeBAT() {
        return new CellsAllocationTable(super.getRowCount(), super.getColumnCount());
    }

    /**
     * In a basic game of tetris the game ends when a cell in the first row of
     * the board is occupied.
     *
     */
    @Override
    public boolean isGameOver() {
        return !this.CAT.rowIsEmpty(0);
    }

}
