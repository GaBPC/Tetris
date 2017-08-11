/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Blocks.BasicBlock;
import Blocks.Cell;
import Boards.BasicBoard;
import Boards.StandarTetrisBoard;
import java.util.ArrayList;
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
        return Tetris.PLAY_STATE_ID;
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

        g.setColor(Color.white);
        g.drawString("Playing " + Integer.toString(this.timer), 0, 0);

        BasicBlock current = this.gameController.getCurrentBlock();

        g.setColor(current.getColor());

        Iterator<Cell> points = current.getPoints().iterator();

        while (points.hasNext()) {
            Cell point = points.next();

            g.setColor(point.getColor());
            Shape shape = new Rectangle(point.getX() * this.cellWidth, point.getY() * this.cellHeigth, this.cellWidth, this.cellHeigth);
            g.fill(shape);

            g.setColor(Color.white);
            g.draw(shape);
        }

        Iterator<Cell> cells = this.gameController.getCells();

        while (cells.hasNext()) {
            Cell cell = cells.next();
            
            g.setColor(cell.getColor());
            Shape shape = new Rectangle(cell.getY() * this.cellWidth, cell.getX() * this.cellHeigth, this.cellWidth, this.cellHeigth);
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
