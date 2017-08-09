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
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author gabriel
 */
public class BasicBoard {

    public static final int ROW_COUNT = 16;
    public static final int COLUM_COUNT = 10;

    private int board_width, board_heigth;

    private int cellWidth, cellHeigth;

    private BasicBlock currentBlock = null;
    private BasicBlock savedBlock = null;
    private ArrayList<BasicBlock> blocks = null;

    private ArrayList<Cell[]> board = null;

    public BasicBoard(int board_width, int board_heigth) {
        this.board_width = board_width;
        this.board_heigth = board_heigth;
        this.calculateCellSize();
        this.board = new ArrayList<Cell[]>();

        for (int i = 0; i < BasicBoard.ROW_COUNT; i++) {
            Cell[] aux = new Cell[BasicBoard.COLUM_COUNT];
            for (int j = 0; j < BasicBoard.COLUM_COUNT; j++) {
                aux[j] = null;
            }
            this.board.add(aux);
        }

        this.blocks = new ArrayList<>();
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeigth() {
        return cellHeigth;
    }

    /**
     * Function that calculates the size (in pixels) of each cell of the board,
     * based on the width and height (of the board itself)
     *
     */
    private void calculateCellSize() {
        this.cellWidth = this.board_width / BasicBoard.COLUM_COUNT;
        this.cellHeigth = this.board_heigth / BasicBoard.ROW_COUNT;
    }

    /**
     *
     * Function that adds a new block to the game. This block will be the one
     * that will become the current block that the player can control.
     *
     */
    public void insertNewBlock() {

        this.currentBlock = BlocksFactory.createRandomBlock(this.cellWidth, this.cellHeigth);
        this.blocks.add(this.currentBlock);
    }

    /**
     * Function that returns all the currents blocks on the game in a iterator.
     *
     * @return an Iterator of BasicBlock
     */
    public BasicBlock getCurrentBlock() {
        return this.currentBlock;
    }

    public ArrayList<Cell[]> getCells() {
        return this.board;
    }

    private boolean checkRigthMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Point> points = block.getPoints().iterator();
        while (points.hasNext() && check) {
            Point point = points.next();
            int pointX = (int) point.getCenterX();
            int pointY = (int) point.getCenterY();

            Cell[] row = this.board.get(pointY);

            Boolean cellVal = row[pointX + 1] != null ? true : false;

            if (cellVal == true) {
                check = false;
            }
        }
        return check;
    }

    private boolean checkLeftMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Point> points = block.getPoints().iterator();
        while (points.hasNext() && check) {
            Point point = points.next();
            int pointX = (int) point.getCenterX();
            int pointY = (int) point.getCenterY();

            Cell[] row = this.board.get(pointY);

            Boolean cellVal = row[pointX - 1] != null ? true : false;

            if (cellVal == true) {
                check = false;
            }
        }
        return check;
    }

    private boolean checkBottomMovement(BasicBlock block) {
        boolean check = true;
        Iterator<Point> points = block.getPoints().iterator();
        while (points.hasNext() && check) {
            Point point = points.next();
            int pointX = (int) point.getCenterX();
            int pointY = (int) point.getCenterY();

            Cell[] row = this.board.get(pointY + 1);

            Boolean cellVal = row[pointX] != null ? true : false;

            if (cellVal == true) {
                check = false;
            }
        }
        return check;
    }

    public void moveCurrentBlockRigth() {

        float x = this.currentBlock.getRightmostPoint();

        if (x < BasicBoard.COLUM_COUNT - 1 && this.checkRigthMovement(this.currentBlock)) {
            this.currentBlock.moveRight();
        }

    }

    public void moveCurrentBlockLeft() {

        float x = this.currentBlock.getLeftmostPoint();

        if (x > 0 && this.checkLeftMovement(this.currentBlock)) {
            this.currentBlock.moveLeft();
        }

    }

    public boolean moveCurrentBlockDown() {

        float y = this.currentBlock.getLowestPoint();

        if (y < BasicBoard.ROW_COUNT - 1 && this.checkBottomMovement(this.currentBlock)) {
            this.currentBlock.moveDown();
            return true;
        } else {
            this.registerBlock(this.currentBlock);
            this.insertNewBlock();
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

        if (leftX > 0 && rigthX < BasicBoard.COLUM_COUNT - 1 && y < BasicBoard.ROW_COUNT - 1) {
            if (this.checkRigthMovement(this.currentBlock) && this.checkLeftMovement(this.currentBlock)) {
                this.currentBlock.rotate();
            }
        }

    }
    
    public void saveBlock(){
        BasicBlock aux = this.savedBlock;
        this.savedBlock = this.currentBlock;
        if(aux == null){
            this.insertNewBlock();
        }
        else{
            this.currentBlock = aux;
            this.currentBlock.resetBlock();
        }
    }

    public void registerBlock(BasicBlock block) {
        Iterator<Point> points = block.getPoints().iterator();

        while (points.hasNext()) {
            Point point = points.next();

            int x = (int) point.getCenterX();
            int y = (int) point.getCenterY();

            Cell[] row = this.board.get(y);

            row[x] = new Cell(y, x, this.currentBlock.getColor());
        }

    }

}
