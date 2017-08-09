/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boards;

import Blocks.BasicBlock;
import Blocks.BlocksFactory;
import Blocks.Cell;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author gabriel
 */
public class BasicBoard {

    public static final int ROW_COUNT = 16;
    public static final int COLUM_COUNT = 10;

    private int board_width, board_heigth;

    private int cell_width, cell_heigth;

    private BasicBlock currentBlock = null;
    private ArrayList<BasicBlock> blocks = null;

    private boolean[][] board = null;

    public BasicBoard(int board_width, int board_heigth) {
        this.board_width = board_width;
        this.board_heigth = board_heigth;
        this.calculateCellSize();
        this.board = new boolean[BasicBoard.COLUM_COUNT][BasicBoard.ROW_COUNT];
        
        for(int i = 0 ; i < this.board.length ; i++){
            for(int j = 0 ; j < this.board[i].length ; j++){
                this.board[i][j] = false;
            }
        }
        
        this.blocks = new ArrayList<>();
    }

    /**
     * Function that calculates the size (in pixels) of each cell of the board,
     * based on the width and height (of the board itself)
     *
     */
    private void calculateCellSize() {
        this.cell_width = this.board_width / BasicBoard.COLUM_COUNT;
        this.cell_heigth = this.board_heigth / BasicBoard.ROW_COUNT;
    }

    /**
     *
     * Function that adds a new block to the game. This block will be the one
     * that will become the current block that the player can control.
     *
     */
    public void insertNewBlock() {

        this.currentBlock = BlocksFactory.createRandomBlock(this.cell_width, this.cell_heigth);
        this.blocks.add(this.currentBlock);
    }

    /**
     * Function that returns all the currents blocks on the game in a iterator.
     *
     * @return an Iterator of BasicBlock
     */
    public Iterator<BasicBlock> getBlocks() {
        return this.blocks.iterator();
    }

    public void moveCurrentBlockRigth() {

        float x = this.currentBlock.getRightPosition();

        if (x < this.board_width) {
            this.currentBlock.moveRight(this.cell_width);
        }

    }

    public void moveCurrentBlockLeft() {

        float x = this.currentBlock.getLeftPosition();

        if (x > 0) {
            this.currentBlock.moveLeft(this.cell_width);
        }

    }

    public void moveCurrentBlockDown() {

        float y = this.currentBlock.getBottomPosition();

        if (y < this.board_heigth) {
            
            ArrayList<Cell> cells = this.currentBlock.getCells();
            

            this.currentBlock.moveDown(this.cell_heigth);
        } else {
            this.insertNewBlock();
        }
    }

    public void dropCurrentBlock() {

        while (this.currentBlock.getBottomPosition() < this.board_heigth) {

            this.currentBlock.moveDown(this.cell_heigth);
        }

        this.insertNewBlock();

    }
    
    public void rotateCurrentBlock(){
        this.currentBlock.rotate();
    }

}
