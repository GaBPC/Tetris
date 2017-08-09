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
public class LShape extends BasicBlock {

    private static final boolean[][] model = new boolean[][]{{true, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, false, false, false}};

    public LShape(int cell_width, int cell_heigth) {
        super(LShape.model, Color.orange);

    }

}
