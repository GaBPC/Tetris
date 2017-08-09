/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blocks;

import org.newdawn.slick.Color;

/**
 *
 * @author gabriel
 */
public class Square extends BasicBlock {

    private static final boolean[][] model = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}};

    public Square(int cell_width, int cell_heigth) {
        super(Square.model, Color.yellow);

    }

}
