package foodtruckfrenzy.GameFramework;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.*;

import foodtruckfrenzy.Main;
import foodtruckfrenzy.Drawable.Item.Food;
import foodtruckfrenzy.Drawable.Item.Recipe;
import foodtruckfrenzy.Drawable.Vehicle.Cop;
import foodtruckfrenzy.Drawable.Vehicle.FoodTruck;
import foodtruckfrenzy.Helper.KeyboardHandler;
import foodtruckfrenzy.SecondaryUI.Frame;
import foodtruckfrenzy.SecondaryUI.PauseScreen;
import foodtruckfrenzy.SecondaryUI.ScreenType;

/*
 * This class is the logic behind the game
 * Creating a new instance of this class creates a new game with everything
 * Includes the Frame, all panels, the charactes and everything
 * Potentially should be refactored to a singleton object as only one of these should exist at a time
 */
public class Game {
    
    private final int SCOREBOARD_HEIGHT = 100;
    private final int FRAME_WIDTH = Grid.COLS * Grid.CELL_SIZE;
    private final int FRAME_HEIGHT = Grid.ROWS * Grid.CELL_SIZE;
    private final int TIMER_DELAY = 75; // in milliseconds

    private final JFrame _frame;
    private final KeyboardHandler _keyboardHandler;
    private final Timer _timer;
    private final JPanel _mainPanel;
    private final Scoreboard _scoreboardPanel;
    private final GamePanel _gamePanel;
    private final CardLayout _layout;
    private boolean _paused = false;

    private int timerIndex;

    /*
     * Game constructor
     * Creates everything needed to run the game instance
     */
    public Game() {
        timerIndex = 0;
        Food.resetCount();
        Recipe.resetCount();

        /**
         * This frame is setup as follows:
         * CardLayout mainPanel which is the content pane
         * CardLayout mainPanel contains the gameAndScorePanel
         * CardLayout mainPanel contains the pausePanel
         * gameAndScorePanel contains the gamePanel and scoreboardPanel
         * KeyBoard listner is attached to the gamePanel which is given focus
         * On start, flip CardLaout mainPanel to gameAndScorePanel
         * On pause, flip CardLayout mainPanel to pausePanel
         * On resume, flip CardLaout mainPanel back to gameAndScorePanel
         */

        _mainPanel = new JPanel(new CardLayout());

        _frame = new JFrame("Food Truck Frenzy");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setResizable(true);

        JPanel gameAndScorePane = new JPanel(new BorderLayout());
        gameAndScorePane.setBackground(Color.BLACK);
        gameAndScorePane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        _gamePanel = new GamePanel();
        FoodTruck mainCharacter = _gamePanel.getMainCharacter();
        ArrayList<Cop> cops = _gamePanel.getCops();
        cops.get(0).getDirections();

        _gamePanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        _gamePanel.setFocusable(true);
        _gamePanel.setBackground(new Color(54, 65, 79));
        _gamePanel.requestFocusInWindow();

        _scoreboardPanel = new Scoreboard(mainCharacter); 
        _scoreboardPanel.setPreferredSize(new Dimension(FRAME_WIDTH, SCOREBOARD_HEIGHT));

        _keyboardHandler = new KeyboardHandler();
        _gamePanel.addKeyListener(_keyboardHandler);

        gameAndScorePane.add(_gamePanel, BorderLayout.CENTER);
        gameAndScorePane.add(_scoreboardPanel, BorderLayout.NORTH);

        _mainPanel.add(gameAndScorePane, "game");

        /**
         * Action listener for the pause menu which resumes the game on interaction
         */
        ActionListener resumeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resume();
            }
        };

        /**
         * Action listener for the pause menu which goes to the title screen on interaction
         */
        ActionListener restartListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main(null);
                _frame.dispose();
            }
        };
        JPanel pausePanel = new PauseScreen(resumeListener, restartListener);

        _mainPanel.add(pausePanel, "pause");
        _frame.setContentPane(_mainPanel);
        _layout = (CardLayout) _mainPanel.getLayout();
        _layout.show(_mainPanel, "game");

        _frame.pack();
        _frame.setVisible(true);

        /*
         * Game tick timer which controls all game running logic
         * Controls player movement and cop movement
         * Checks for win and loss condidions
         */
        _timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!_paused) {
                    if (_keyboardHandler.pause())
                        pause();

                    boolean moved = false;
                    if (_keyboardHandler.upPressed() && !_keyboardHandler.downPressed() && !moved)
                        moved = mainCharacter.moveUp();

                    if (_keyboardHandler.downPressed() && !_keyboardHandler.upPressed() && !moved)
                        moved = mainCharacter.moveDown();

                    if (_keyboardHandler.leftPressed() && !_keyboardHandler.rightPressed() && !moved)
                        moved = mainCharacter.moveLeft();
                        
                    if (_keyboardHandler.rightPressed() && !_keyboardHandler.leftPressed() && !moved)
                        moved = mainCharacter.moveRight();

                    timerIndex ++;
                    if (timerIndex > Integer.MAX_VALUE - 1)
                        timerIndex = 0;
                    
                    // Check if there is a collision after player movement
                    if (!_paused && checkCopCharacterCollision(cops, mainCharacter)) {
                        loss();
                    }

                    for (Cop cop : cops) {
                        cop.trackTruck();
                    }

                    if (timerIndex % 2 == 0) {
                        cops.get(0).chaseTruck();
                        cops.get(1).chaseTruck();
                    }

                    if (timerIndex % 3 == 0) {
                        cops.get(2).chaseTruck();
                    }

                    // Check if there is a collision after cop movement
                    if (!_paused && checkCopCharacterCollision(cops, mainCharacter)) {
                        loss();
                    }

                    _gamePanel.repaint();
                    _scoreboardPanel.update(); 

                    if (!_paused && mainCharacter.getScoreInt() < 0) {
                        loss();
                    }

                    if (!_paused && checkWinCondition(mainCharacter)) {
                        win();
                    }

                }
            }
        });

    }

    /**
     * Starts the game timer, effectively starting the game
     */
    public void startTimer() {
        _timer.start();
    }

    /**
     * Pauses the game and shows the pause menu 
     * While suspending the game loop through a boolean value update
     */
    private void pause() {
        _keyboardHandler.resetKeys();
        _paused = true;
        _layout.show(_mainPanel, "pause");

        _gamePanel.setFocusable(true);
        _gamePanel.requestFocusInWindow();
    }

    /**
     * Resumes the game and shows the game panel 
     * While resuming the game loop through a boolean value update
     */
    private void resume() {
        _paused = false;
        _layout.show(_mainPanel, "game");
        _gamePanel.setFocusable(true);
        _gamePanel.requestFocusInWindow();
    }

    /**
     * Function to be called when the game ends in a loss
     * Kills the frame and timer
     * Shows a new GAME_LOST screen Frame
     * Sets paused to true to prevent further loop progression
     */
    private void loss() {
        _paused = true;
        _timer.stop();
        _frame.dispose();
        new Frame(ScreenType.GAME_LOST, _scoreboardPanel);
    }

    /**
     * Function to be called when the game ends in a win
     * Kills the frame and timer
     * Shows a new GAME_WON screen Frame
     * Sets paused to true to prevent further loop progression
     */
    private void win() {
        _paused = true;
        _timer.stop();
        _frame.dispose();
        new Frame(ScreenType.GAME_WON, _scoreboardPanel);
    }

    /**
     * checks if the specified FoodTruck object is in the same grid location as any of the Cop objects in the specified array
     * @param cops Cop object ArrayList
     * @param foodTruck FoodTruck object to check collisons
     * @return true if there is a collison between the foodTruck or any Cop in the array, false if not or if an argument is null
     */
    private boolean checkCopCharacterCollision(ArrayList<Cop> cops, FoodTruck foodTruck) {
        
        if (cops == null || foodTruck == null)
            return false;

        for (Cop cop : cops)
            if (cop.getCol() == foodTruck.getCol() && cop.getRow() == foodTruck.getRow())
                return true;
        
        return false;
    }

    /**
     * Check win condition for the game, if foodTruck collected all Food items in the system and is on tile row 16, column 40
     * @param foodTruck foodTruck corresponding to player being checked for a win
     * @return true if there is a successful win, otherwise false
     */
    private boolean checkWinCondition(FoodTruck foodTruck) {
        return foodTruck.getCol() == 40 && foodTruck.getRow() == 16 && foodTruck.getIngredientsFound() >= Food.getCount();
    }

}
