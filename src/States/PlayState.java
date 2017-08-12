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
package States;

import Blocks.BasicBlock;
import Blocks.Cell;
import Boards.StandarTetrisBoard;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tetris.Controller;
import tetris.Tetris;

/**
 *
 * @author gabriel
 */
public class PlayState extends BasicGameState {

    private Controller gameController = null;

    private float cellWidth, cellHeigth;

    private int inputTick, downTick;
    private int tick;
    private int timer;

    private boolean moveDown, moveLeft, moveRigth, rotate;

    @Override
    public int getID() {
        return Tetris.statesID.PLAY.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        this.gameController = new Controller(new StandarTetrisBoard());

        this.cellWidth = gc.getWidth() / this.gameController.getBoardColumnCount();
        this.cellHeigth = gc.getHeight() / this.gameController.getBoardRowCount();

        this.tick = 150;
        this.inputTick = this.tick;
        this.downTick = 2 * this.tick;
        this.timer = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // Gets the current Block and sets the color to the block's color.
        BasicBlock current = this.gameController.getCurrentBlock();
        g.setColor(current.getColor());
        // Gets all the blocks that conforms the current block.
        Iterator<Cell> points = current.getCells().iterator();
        // Draws the current block.
        while (points.hasNext()) {
            Cell point = points.next();
            int x = (int) (point.getColumn() * this.cellWidth);
            int y = (int) (point.getRow() * this.cellHeigth);
            g.setColor(point.getColor());
            Shape shape = new Rectangle(x, y, this.cellWidth, this.cellHeigth);
            g.fill(shape);
            g.setColor(Color.white);
            g.draw(shape);
            
            Shape helpLines = new Rectangle(x, y + this.cellHeigth, this.cellWidth, this.gameController.getBoardRowCount() * this.cellHeigth - y);
            g.draw(helpLines);

        }

        // Gets the board's placed block.
        Iterator<Cell> cells = this.gameController.getBoardCells();
        // Draws the board cells.
        while (cells.hasNext()) {
            Cell point = cells.next();
            int x = (int) (point.getColumn() * this.cellWidth);
            int y = (int) (point.getRow() * this.cellHeigth);
            g.setColor(point.getColor());
            Shape shape = new Rectangle(x, y, this.cellWidth, this.cellHeigth);
            g.fill(shape);
            g.setColor(Color.white);
            g.draw(shape);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        this.timer += i;
        this.inputTick -= i;
        this.downTick -= i;

        if (this.inputTick <= 0) {

            if (this.moveDown) {
                this.gameController.moveCurrentBlockDown();
            }

            if (this.moveLeft) {
                this.gameController.moveCurrentBlockLeft();
            }

            if (this.moveRigth) {
                this.gameController.moveCurrentBlockRigth();
            }

            if (this.rotate) {
                this.gameController.rotateCurrentBlock();
            }

            this.inputTick = this.tick;
        }

        if (this.downTick <= 0) {
            this.gameController.moveCurrentBlockDown();
            this.downTick = 5 * this.tick;
        }

    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_A:
            case Input.KEY_LEFT:
                this.moveLeft = true;
                break;
            case Input.KEY_D:
            case Input.KEY_RIGHT:
                this.moveRigth = true;
                break;
            case Input.KEY_S:
            case Input.KEY_DOWN:
                this.moveDown = true;
                break;
            case Input.KEY_SPACE:
                this.gameController.dropCurrentBlock();
                break;
            case Input.KEY_W:
            case Input.KEY_UP:
                this.rotate = true;
                break;
            case Input.KEY_C:
                this.gameController.saveBlock();
                break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_A:
            case Input.KEY_LEFT:
                this.moveLeft = false;
                break;
            case Input.KEY_D:
            case Input.KEY_RIGHT:
                this.moveRigth = false;
                break;
            case Input.KEY_S:
            case Input.KEY_DOWN:
                this.moveDown = false;
                break;
            case Input.KEY_W:
            case Input.KEY_UP:
                this.rotate = false;
                break;
        }
    }

}
