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
package Blocks;

import java.util.Iterator;
import org.newdawn.slick.Color;

/**
 *
 * @author gabriel
 */
public class Square extends BasicBlock {

    private static final boolean[][] MODEL = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}};

    public Square() {
        super(Square.MODEL, Color.yellow);

    }

    /**
     * A SquareBlock, being square, rotates on the same blocks that it is occupying.
     *
     * @return
     */
    @Override
    public Iterator<Cell> cellsNeededToRotate() {
        return this.getCells().iterator();
    }
}
