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

        return new JShape();
    }

}
