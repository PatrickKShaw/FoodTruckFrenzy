package foodtruckfrenzy.SecondaryUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

import foodtruckfrenzy.Main;
import foodtruckfrenzy.GameFramework.Game;
import foodtruckfrenzy.GameFramework.Scoreboard;

/**
 * The Frame class sets up the JFrame for the TitleScreen, GameLostScreen, and GameWonScreen to be displayed
 * It creates the specific Screen object to be displayed depending on what is needed
 */

public class Frame {
    private Screen _screen;
    private JFrame _frame;

    /**
     * Constructs a Frame object with specified screenType and Scoreboard object
     * @param screenType specifies the type of screen to be displayed on the JFrame
     * @param scoreboard the Scoreboard object used to display the final score, time, and values on the GameOverScreen objects
     */

    public Frame(ScreenType screenType, Scoreboard scoreboard) {
        
        ActionListener startGameListener = new ActionListener() {
            /**
             * Is invoked when the start button is clicked (starts the game)
             * @param e is the action event that occurred
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game();
                game.startTimer();
                _frame.dispose(); // close the TitleScreen window
            }
        };

        ActionListener closeAppListener = new ActionListener() {
            /**
             * Is invoked when the exit button is clicked (exits the game)
             * @param e is the action event that occurred
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        ActionListener restartListener = new ActionListener() {
            /**
             * Is invoked when the restart button is clicked (restarts the game)
             * @param e is the action event that occurred
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main(null);
                _frame.dispose();
            }
        };

        /**
         * Sets up the JFrame and Screen object based on desired screenType
         * If the screenType is GAME_WON it will create a GameWonScreen object and set the JFrame title to "Game Won!"
         * If the screenType is GAME_LOST it will create a GameLostScreen object and set the JFrame title to "Game Lost!"
         * If the screenType is TITLE, it creates a TitleScreen object and set the JFrame title to "Food Truck Frenzy"
         * The Action listener objects handle events when buttons are clicked (depending on screenType: restart, start, and closeApp)
         * Sets the Frame's DefaultCloseOperation
         * Screen object is added to the JFrame content pane
         * The JFrame is packed using pack()
         * The JFrame is centred on the screen
         * The JFrame is set to visible and shown using the setVisible() method
         */
        
        switch(screenType) {
            case GAME_WON:
                _screen = new GameWonScreen(restartListener, closeAppListener, scoreboard);
                _frame = new JFrame("Game Won!");
                break;
            
            case GAME_LOST:
                _screen = new GameLostScreen(restartListener, closeAppListener, scoreboard);
                _frame = new JFrame("Game Lost!");
                break;

            case TITLE:
                _screen = new TitleScreen(startGameListener, closeAppListener);
                _frame = new JFrame("Food Truck Frenzy");
                break;
        }

        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.getContentPane().add(_screen);
        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }
}

