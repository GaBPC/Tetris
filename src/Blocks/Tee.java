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

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.Color;

/**
 *
 * @author gabriel
 */
public class Tee extends BasicBlock {

    private static final boolean[][] MODEL = new boolean[][]{{false, true, false}, {true, true, true}, {false, false, false}};

    public Tee() {
        super(Tee.MODEL, Color.magenta);

    }

    @Override
    public Iterator<Cell> cellsNeededToRotate() {

        ArrayList<Cell> cells = new ArrayList<>();

        Cell pivot = super.getPivotCell();
        Cell cell1 = null;
        Cell cell2 = null;
        Cell cell3 = null;

        int pivotCol = pivot.getColumn();
        int pivotRow = pivot.getRow();

        switch (this.actualRotation) {
            case NORMAL:
                cell1 = new Cell(pivotRow, pivotCol + 1);
                cell2 = new Cell(pivotRow - 1, pivotCol);
                cell3 = new Cell(pivotRow + 1, pivotCol);
                break;
            case RIGTH:
                cell1 = new Cell(pivotRow, pivotCol - 1);
                cell2 = new Cell(pivotRow + 1, pivotCol);
                cell3 = new Cell(pivotRow, pivotCol + 1);
                break;
            case DOWN:
                cell1 = new Cell(pivotRow - 1, pivotCol);
                cell2 = new Cell(pivotRow, pivotCol + 1);
                cell3 = new Cell(pivotRow + 1, pivotCol);

                break;
            case LEFT:
                cell1 = new Cell(pivotRow - 1, pivotCol);
                cell2 = new Cell(pivotRow, pivotCol + 1);
                cell3 = new Cell(pivotRow, pivotCol - 1);
                break;
        }

        cells.add(pivot);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        return cells.iterator();
    }
}
