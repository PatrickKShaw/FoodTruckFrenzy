package foodtruckfrenzy.SecondaryUI;

import java.awt.event.ActionListener;

import foodtruckfrenzy.GameFramework.Scoreboard;

/**
 * The GameWonScreen class extends the GameOverScreen class to display a screen for when the game is won.
 * The purpose of this class is to pass the specific file paths, listeners, and Scoreboard to its parent class
 */

public class GameWonScreen extends GameOverScreen {
    /**
     * Constructs a GameWonScreen object with the specified ActionListeners for the start and exit buttons.
     * Constructs the GameLostScreen with the file paths for the game won background, restart button, and exit button images, and the Scoreboard object.
     * The Scoreboard object is passed so that the final game values and scores + final time can be displayed
     * @param startListener The ActionListener to handle start button events.
     * @param exitListener The ActionListener to handle exit button events.
     * @param scoreboard The Scoreboard object to display the final score, values, and time on the screen.
     */
    public GameWonScreen(ActionListener startListener, ActionListener exitListener, Scoreboard scoreboard) {
        super(startListener, exitListener, "youwin.png", "restart.png", "exit.png", scoreboard);
    }
}