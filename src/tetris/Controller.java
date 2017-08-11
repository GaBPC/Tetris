/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public final BasicBlock createNewBlock(){
        return BlocksFactory.createRandomBlock();
    }

    
    public BasicBlock getCurrentBlock() {
        return this.currentBlock;
    }

    public void moveCurrentBlockRigth() {

        float x = this.currentBlock.getRightmostPoint();

        if (x < this.gameBoard.getColumnCount() - 1 && this.gameBoard.checkRigthMovement(this.currentBlock)) {
            this.currentBlock.moveRight();
        }

    }

    public void moveCurrentBlockLeft() {

        float x = this.currentBlock.getLeftmostPoint();

        if (x > 0 && this.gameBoard.checkLeftMovement(this.currentBlock)) {
            this.currentBlock.moveLeft();
        }

    }

    public boolean moveCurrentBlockDown() {

        float y = this.currentBlock.getLowestPoint();

        if (y < this.gameBoard.getRowCount() - 1 && this.gameBoard.checkBottomMovement(this.currentBlock)) {
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
        int rigthX = (int) this.currentBlock.getRightmostPoint();
        int leftX = (int) this.currentBlock.getLeftmostPoint();
        int y = (int) this.currentBlock.getLowestPoint();

        if (leftX > 0 && rigthX < this.gameBoard.getColumnCount() - 1 && y < this.gameBoard.getRowCount() - 1) {
            if (this.gameBoard.checkRigthMovement(this.currentBlock) && this.gameBoard.checkLeftMovement(this.currentBlock)) {
                this.currentBlock.rotate();
            }
        }
    }

    public void saveBlock() {
        BasicBlock aux = this.savedBlock;
        this.savedBlock = this.currentBlock;
        if (aux == null) {
            this.currentBlock = this.createNewBlock();
        } else {
            this.currentBlock = aux;
            this.currentBlock.resetBlock();
        }
    }
    
    public int getBoardRowCount(){
        return this.gameBoard.getRowCount();
    }
    
    public int getBoardColumnCount(){
        return this.gameBoard.getColumnCount();
    }
    
    public Iterator<Cell> getCells(){
        return this.gameBoard.getCells();
    }
    
    

}
