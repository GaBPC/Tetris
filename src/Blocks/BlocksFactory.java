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

    public static BasicBlock createRandomBlock() {
        int type = new Random().nextInt(7);
        
        BasicBlock ret = null;

        switch (type) {
            case 0:
                ret = new Line();
                break;
            case 1:
                ret = new Square();
                break;
            case 2:
                ret = new Tee();
                break;
            case 3:
                ret = new LShape();
                break;
            case 4:
                ret = new JShape();
                break;
            case 5:
                ret = new ZShape();
                break;
            case 6:
                ret = new SShape();
                break;

        }

        return ret;
    }

}
