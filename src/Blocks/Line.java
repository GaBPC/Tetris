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
public class Line extends BasicBlock {

    private static final boolean[][] model = new boolean[][]{{false, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, true, false, false}};

    public Line(int cell_width, int cell_heigth) {
        super(Line.model, Color.cyan, 5 * cell_width, 100, cell_width, cell_heigth);

    }

}
