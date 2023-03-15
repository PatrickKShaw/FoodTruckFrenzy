package foodtruckfrenzy.GameFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import foodtruckfrenzy.Drawable.Item.Food;
import foodtruckfrenzy.Drawable.Item.Recipe;
import foodtruckfrenzy.Drawable.Vehicle.FoodTruck;

/**
 * The Scoreboard class is responsible for displaying the current score and game progress.
 * It contains components to display the ingredients and recipes collected, damage inflicted by obstacles, speed fines, time elapsed and the current score.
 */
public class Scoreboard extends JPanel {
    /** 
     * Fonts and Colors for the Score Board
    */
    private final Font _sbFont1 = new Font("Comic Sans MS", Font.PLAIN, 16);
    private final Font _sbFont2 = new Font("Comic Sans MS", Font.PLAIN, 22);
    private final Font _sbFont3 = new Font("Comic Sans MS", Font.BOLD, 44);
    
    private final Color _backGroundColor = new Color(54, 65, 79); 
    private final Color _red = new Color(227, 0, 39);
    private final Color _yellow = new Color(255, 240, 75);
    private final Color _green = new Color(80, 255, 90);
    private final Color _white = Color.WHITE;
    
    /**
     * Labels for the ScoreBoard Data
     */
    private JLabel _scoreLabel = new JLabel("Score: ");
    private JLabel _scoreTotal = new JLabel();
    private JLabel _ingredientsLabel = new JLabel("Ingredients");
    private JLabel _recipesLabel = new JLabel("Recipes: ");
    private JLabel _damageLabel = new JLabel("Damage: "); 
    private JLabel _fineLabel = new JLabel("Fines: ");
    private JLabel _timeLabel = new JLabel("Time: 00:00");
    private JLabel _pauseInstructions = new JLabel("Press 'P' to pause");

    /**
     * Scoreboard Data, Game State, and Player(data source)
     */
    private FoodTruck player;
    private int score;
    private int ingredientsFound;
    private int ingredientsDiscoverable;
    private int recipesFound;
    private int recipesDiscoverable;
    private int damage;
    private int fines;
    private String timeElapsed;
    private boolean _paused = false;   

    /**
     * Timer object that updates the Scoreboards Timer
     */
    private Timer _timer;
    private int _minute;
    private int _second; 

    /**
     * Constructs a new scoreboard panel for the given player object.
     * @param player the player object to associate with this scoreboard
     */
    public Scoreboard(FoodTruck player) {
        
        // Set the layout and background color of the scoreboard panel
        setLayout(new BorderLayout(10, 10));
        setBackground(_backGroundColor);
        
        // Left Panel - displays various statistics about the player
        JPanel leftPanel = new JPanel(new GridLayout(4, 1));
        leftPanel.setBackground(_backGroundColor);

        // Set the font and color attributes left panel stats
        _ingredientsLabel.setFont(_sbFont1);
        _ingredientsLabel.setForeground(_white);
        _recipesLabel.setFont(_sbFont1);
        _recipesLabel.setForeground(_white);
        _fineLabel.setFont(_sbFont1);
        _fineLabel.setForeground(_white);
        _timeLabel.setFont(_sbFont1);
        _timeLabel.setForeground(_white); 
        _damageLabel.setFont(_sbFont1);
        _damageLabel.setForeground(_white);

        // Add stat labels to the left panel
        leftPanel.add(_ingredientsLabel); 
        leftPanel.add(_recipesLabel);
        leftPanel.add(_fineLabel);
        leftPanel.add(_damageLabel);

        // Centre Panel - displays the players Score
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(_backGroundColor);

        // Set the font and color of the Score Label
        _scoreLabel.setFont(_sbFont3);       
        _scoreLabel.setForeground(_white);
        _scoreTotal.setFont(_sbFont3);
        _scoreTotal.setForeground(_white);

        // Add the score label and total score to center panel
        centerPanel.add(_scoreLabel);
        centerPanel.add(_scoreTotal);


        // Right Panel - displays the time elapsed and instructions to pause
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        rightPanel.setBackground(_backGroundColor);

        // Set the font and color of the labels
        _timeLabel.setFont(_sbFont2);
        _timeLabel.setForeground(_white);      
        _pauseInstructions.setFont(_sbFont1);
        _pauseInstructions.setForeground(_white);
    
        // Add time and pause instructions to the right panel
        rightPanel.add(_timeLabel);
        rightPanel.add(_pauseInstructions);
        
        // Add left, centre and right panels to the Scoreboard
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
      
        // Assign player 
        this.player = player;  
        
        // Get totals for discoverable items
        ingredientsDiscoverable = Food.getCount(); 
        recipesDiscoverable = Recipe.getCount();
        
        // Create and start the scoreboard timer to keep track of game time
        _second = 00;
        _minute = 00;
        simpleTimer();
        _timer.start();
    }

    /**
     * <p>
    Updates the scoreboard data with the player's latest progress and score.
    This function retrieves data from the player instance and updates the relevant
    score board labels accordingly. It also sets the foreground color of the score and
    ingredients labels depending on the player's progress. If the player has found all the
    ingredients, the ingredients label foreground is set to green. If the player has found
    all the recipes, the recipes label foreground is set to green. If the player's score is
    positive, the score total foreground is set to yellow. If the player's score is negative,
    the score total foreground is set to red.
    </p>
    */
    public void update() {
        
        // fetch data from player
        score = player.getScoreInt();
        ingredientsFound = player.getIngredientsFound();
        recipesFound = player.getRecipesFound();
        damage = player.getDamage();
        fines = player.getFines();

        // adjust font color accordingly
        if(score < 0) {
            _scoreTotal.setForeground(_red);
        }
        if (score > 0) {
            _scoreTotal.setForeground(_yellow);
        }
        if (ingredientsFound == ingredientsDiscoverable) {
            _ingredientsLabel.setForeground(_green);
        }
        if (recipesFound == recipesDiscoverable) {
            _recipesLabel.setForeground(_green);
        }

        // update scoreboard labels
        _ingredientsLabel.setText("Ingredients: " + ingredientsFound + "/" + ingredientsDiscoverable);
        _recipesLabel.setText("Recipes: " + recipesFound + "/" + recipesDiscoverable);
        _damageLabel.setText("Damages: " + damage);
        _fineLabel.setText("Speed Fines: " + fines);
        _scoreTotal.setText(player.getScore());
      
    }

    /**
    * Sets up a simple timer to keep track of the time elapsed in the game.
    * The timer increments every second and updates the time elapsed on the scoreboard.
    */
    public void simpleTimer() {
        _timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!_paused) {
                _second++;
                    if (_second == 60) {
                        _minute++;
                        _second = 0;
                    }
                    // save and display the current time elapsed as (min : sec)
                    timeElapsed = String.format("%02d",_minute) + ":" + String.format("%02d", _second);
                    _timeLabel.setText("Time: " + timeElapsed);
                }
            }
        });
    }

    
    /**
    * Starts the timer for the scoreboard.
    */
    public void startTimer() {
        _timer.start();
    }

    /**
     * Pauses the timer for the scoreboard. 
     */
    public void pauseTimer() {
        _paused = true;
    }

    /**
     * Resumes the timer for the scoreboard.
     */
    public void resumeTimer() {
        _paused = false;
    }

    /**
     * Restarts the timer for the scoreboard.
     */
    public void restartTimer() {
        _timer.restart();
    }

    /**
     * Returns the number of ingredients found by the player.
     * @return an integer representing the number of ingredients found by the player.
     */
    public int getIngredientsFound() {
        return ingredientsFound;
    }

    /**
     * Returns the number of recipes found by the player.
     * @return an integer representing the number of recipes found by the player.
     */
    public int getRecipesFound() {
        return recipesFound;
    }

    /**
     * Returns the damage accumulated on the players foodtruck during gameplay.
     * @return an integer representing the negative score from accumulating damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the fines accumulated by the player during gameplay.
     * @return an integer representing the negative score from accumulating damage.
     */
    public int getFines() {
        return fines;
    }

    /**
     * Returns the total score accumulated by the player during gameplay.
     * @return an integer representing the total score accumulated by the player during gameplay.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the time elapsed during gameplay.
     * @return a string representing the elapsed time during gameplay. (min : sec) Example for 1 minute and 30 seconds elapsed:  "01 : 30".
     */
    public String getTime() {
        return timeElapsed;
    }

}

