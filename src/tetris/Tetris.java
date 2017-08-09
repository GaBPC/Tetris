/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import States.GameOverState;
import States.MenuState;
import States.PlayState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author gabriel
 */
public class Tetris extends StateBasedGame {

    public static final int MENU_STATE_ID = 0;
    public static final int PLAY_STATE_ID = 1;
    public static final int GAME_OVER_STATE_ID = 2;

    public Tetris(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new MenuState());
        this.addState(new PlayState());
        this.addState(new GameOverState());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Tetris("Testing"));
        // Sets the game's screen size and if it's full size
        app.setDisplayMode(440, 704, false);
        // Shows (or not) the FPS
        app.setShowFPS(false);
        // Syncs the FPS
        app.setVSync(true);
        // Starts the game
        app.start();
    }
}
