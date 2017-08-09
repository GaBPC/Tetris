/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tetris.Tetris;

/**
 *
 * @author gabriel
 */
public class MenuState extends BasicGameState {

    @Override
    public int getID() {
        return Tetris.MENU_STATE_ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Press [Enter] to Play", 0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_ENTER)){
            sbg.enterState(Tetris.PLAY_STATE_ID);
        }
    }
    
}
