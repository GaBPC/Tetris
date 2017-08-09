/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Blocks.BasicBlock;
import Blocks.Cell;
import Boards.BasicBoard;
import java.util.Iterator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tetris.Tetris;

/**
 *
 * @author gabriel
 */
public class PlayState extends BasicGameState {

    private BasicBoard board = null;

    private int next_block_movement;
    private int tick;
    private int timer;

    @Override
    public int getID() {
        return Tetris.PLAY_STATE_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.board = new BasicBoard(gc.getWidth(), gc.getHeight());

        this.board.insertNewBlock();

        this.tick = 150;
        this.next_block_movement = this.tick;
        this.timer = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.setColor(Color.white);
        g.drawString("Playing " + Integer.toString(this.timer), 0, 0);

        Iterator<BasicBlock> it = this.board.getBlocks();
        while (it.hasNext()) {
            BasicBlock aux = it.next();

            g.setColor(aux.getColor());

            Iterator<Cell> cells = aux.getCells().iterator();
            while (cells.hasNext()) {
                g.setColor(aux.getColor());
                Cell cell = cells.next();
                g.fill(cell);
                g.setColor(Color.white);
                g.draw(cell);
            }

        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        this.timer += i;
        this.next_block_movement -= i;

        if (this.next_block_movement <= 0) {
            Input input = gc.getInput();

            if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
                this.board.moveCurrentBlockLeft();
            }

            if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
                this.board.moveCurrentBlockRigth();
            }

            if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
                this.board.moveCurrentBlockDown();
            }

            if (input.isKeyDown(Input.KEY_SPACE)) {
                this.board.dropCurrentBlock();
            }

            if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_UP)) {
                this.board.rotateCurrentBlock();
            }

            // this.board.moveCurrentBlockDown();
            this.next_block_movement = this.tick;

        }

    }

}
