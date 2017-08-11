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

    private int next_block_movement;
    private int tick;
    private int timer;

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
        this.next_block_movement = this.tick;
        this.timer = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        for (int i = 0; i < this.gameController.getBoardRowCount(); i++) {
            for (int j = 0; j < this.gameController.getBoardColumnCount(); j++) {
                Shape shape = new Rectangle(j * this.cellWidth, i * this.cellHeigth, this.cellWidth, this.cellHeigth);
                g.setColor(Color.lightGray);
                g.draw(shape);
                g.setColor(Color.white);
                g.drawString(i + " " + j, j * this.cellWidth, i * this.cellHeigth);
            }
        }

        g.setColor(Color.white);
        // g.drawString("Lines: " + this.gameController.getLinesCount(), 0, 0);

        BasicBlock current = this.gameController.getCurrentBlock();

        g.setColor(current.getColor());

        Iterator<Cell> points = current.getCells().iterator();

        while (points.hasNext()) {
            Cell point = points.next();

            g.setColor(point.getColor());
            Shape shape = new Rectangle(point.getRow() * this.cellWidth, point.getColumn() * this.cellHeigth, this.cellWidth, this.cellHeigth);
            g.fill(shape);

            g.setColor(Color.white);
            g.draw(shape);
        }

        Iterator<Cell> cells = this.gameController.getCells();

        while (cells.hasNext()) {
            Cell cell = cells.next();

            g.setColor(cell.getColor());
            Shape shape = new Rectangle(cell.getColumn() * this.cellWidth, cell.getRow() * this.cellHeigth, this.cellWidth, this.cellHeigth);
            g.fill(shape);
            g.setColor(Color.white);
            g.draw(shape);
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        this.timer += i;
        this.next_block_movement -= i;

        if (this.next_block_movement <= 0) {
            Input input = gc.getInput();

            if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
                this.gameController.moveCurrentBlockLeft();
            }

            if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
                this.gameController.moveCurrentBlockRigth();
            }

            if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
                this.gameController.moveCurrentBlockDown();
            }

            if (input.isKeyDown(Input.KEY_SPACE)) {
                this.gameController.dropCurrentBlock();
            }

            if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_UP)) {
                this.gameController.rotateCurrentBlock();
            }

            if (input.isKeyDown(Input.KEY_C)) {
                this.gameController.saveBlock();
            }

            // this.board.moveCurrentBlockDown();
            this.next_block_movement = this.tick;

        }

    }

}
