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
    
    public static enum statesID{
        MENU, PLAY, GAME_OVER
    }
            
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
        AppGameContainer app = new AppGameContainer(new Tetris("Tetris"));
        // Sets the game's screen size and if it's full size
        app.setDisplayMode(400, 800, false);
        // Shows (or not) the FPS
        app.setShowFPS(false);
        // Syncs the FPS
        app.setVSync(true);
        // Starts the game
        app.start();
    }
}
