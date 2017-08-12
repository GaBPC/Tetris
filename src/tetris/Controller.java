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
package tetris;

import Blocks.BasicBlock;
import Blocks.BlocksFactory;
import Blocks.Cell;
import Boards.BasicBoard;
import java.util.Iterator;

/**
 *
 * @author gabriel
 */
public class Controller {

    private BasicBlock currentBlock = null;
    private BasicBlock savedBlock = null;
    private BasicBoard gameBoard = null;

    public Controller(BasicBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.currentBlock = this.createNewBlock();
    }

    public final BasicBlock createNewBlock() {
        return BlocksFactory.getRandomBlock();
    }

    public BasicBlock getCurrentBlock() {
        return this.currentBlock;
    }

    public void moveCurrentBlockRigth() {

        float col = this.currentBlock.getRightmostCell().getColumn();

        if (col < this.gameBoard.getColumnCount() - 1 && this.gameBoard.checkRigthMovement(this.currentBlock)) {
            this.currentBlock.moveRight();
        }

    }

    public void moveCurrentBlockLeft() {

        float col = this.currentBlock.getLeftmostCell().getColumn();

        if (col > 0 && this.gameBoard.checkLeftMovement(this.currentBlock)) {
            this.currentBlock.moveLeft();
        }

    }

    public boolean moveCurrentBlockDown() {

        float row = this.currentBlock.getLowestCell().getRow();

        if (row < this.gameBoard.getRowCount() - 1 && this.gameBoard.checkBottomMovement(this.currentBlock)) {
            this.currentBlock.moveDown();
            return true;
        } else {
            this.gameBoard.registerBlock(this.currentBlock);
            this.currentBlock = this.createNewBlock();
            return false;
        }
    }

    public void dropCurrentBlock() {

        boolean check = this.moveCurrentBlockDown();
        while (check) {
            check = this.moveCurrentBlockDown();
        }

    }

    public void rotateCurrentBlock() {
        if (this.gameBoard.checkRotation(this.currentBlock)) {
            this.currentBlock.rotate();
        }
    }

    public void saveBlock() {
        BasicBlock aux = this.savedBlock;
        this.savedBlock = this.currentBlock;
        if (aux == null) {
            this.currentBlock = this.createNewBlock();
        } else {
            this.currentBlock = aux;
            this.currentBlock.resetToSpawn();
        }
    }

    public int getBoardRowCount() {
        return this.gameBoard.getRowCount();
    }

    public int getBoardColumnCount() {
        return this.gameBoard.getColumnCount();
    }

    public Iterator<Cell> getBoardCells() {
        return this.gameBoard.getCells();
    }

    public int getLinesCount() {
        return this.gameBoard.getLinesCount();
    }

}
