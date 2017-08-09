/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import java.util.Random;

/**
 *
 * @author gabriel
 */
public abstract class BlocksFactory {

    public static BasicBlock createRandomBlock(int cellWidth, int cellHeigth) {
        int type = new Random().nextInt(7);

        BasicBlock ret = null;

        switch (type) {
            case 0:
                ret = new Line(cellWidth, cellHeigth);
                break;
            case 1:
                ret = new Square(cellWidth, cellHeigth);
                break;
            case 2:
                ret = new Tee(cellWidth, cellHeigth);
                break;
            case 3:
                ret = new LShape(cellWidth, cellHeigth);
                break;
            case 4:
                ret = new JShape(cellWidth, cellHeigth);
                break;
            case 5:
                ret = new ZShape(cellWidth, cellHeigth);
                break;
            case 6:
                ret = new SShape(cellWidth, cellHeigth);
                break;

        }

        return ret;
    }

}
