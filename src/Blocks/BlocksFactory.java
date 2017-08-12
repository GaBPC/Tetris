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
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author gabriel
 */
public abstract class BlocksFactory {

    static enum BlockType {
        LINE, SQUARE, TEE, LSHAPE, JSHAPE, ZSHAPE, SSHAPE;

        private static BasicBlock newBlock(BlockType type) {
            BasicBlock ret = null;

            switch (type) {
                case LINE:
                    ret = new Line();
                    break;
                case SQUARE:
                    ret = new Square();

                    break;
                case TEE:
                    ret = new Tee();
                    break;
                case LSHAPE:
                    ret = new LShape();
                    break;
                case JSHAPE:
                    ret = new JShape();
                    break;
                case ZSHAPE:
                    ret = new ZShape();
                    break;
                case SSHAPE:
                    ret = new SShape();
                    break;
            }

            return ret;
        }
    }

    private static ArrayList<BlockType> blocksSecuence = new ArrayList<>();

    static {
        blocksSecuence.addAll(Arrays.asList(BlockType.values()));
    }

    private static LinkedList<BasicBlock> bag = new LinkedList<>();

    private static void generateNewBag() {
        Collections.shuffle(BlocksFactory.blocksSecuence);
        BlocksFactory.blocksSecuence.forEach((type) -> {
            BlocksFactory.bag.add(BlocksFactory.BlockType.newBlock(type));
        });
    }

    public static BasicBlock getRandomBlock() {

        if (BlocksFactory.bag.isEmpty()) {
            BlocksFactory.generateNewBag();
        }

        return BlocksFactory.bag.poll();
    }

}
