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
public class Tee extends BasicBlock {

    private static final boolean[][] model = new boolean[][]{{false, true, false, false}, {true, true, true, false}, {false, false, false, false}, {false, false, false, false}};

    public Tee() {
        super(Tee.model, Color.magenta);

    }

}
