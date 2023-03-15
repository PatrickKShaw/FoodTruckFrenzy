package foodtruckfrenzy.SecondaryUI;

import java.awt.event.ActionListener;
import javax.swing.JLabel;

import foodtruckfrenzy.GameFramework.Scoreboard;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *The GameOverScreen class extends the Screen class to display a screen with the game over message and the final score, values, and time
 *It displays as soon as the game ends
 */

public class GameOverScreen extends Screen {
    /**
     * Constructs a GameOverScreen object with the specified ActionListeners for the start and exit buttons 
     * Constructs the GameOverScreen with the file paths for the background, restart button, and exit button images, and the Scoreboard object.
     * It displays the values stored in the Scoreboard (Score, Values, and Time)
     * 
     * @param startListener The ActionListener to handle start button events.
     * @param exitListener The ActionListener to handle exit button events.
     * @param bgImagePath The file path for the background image.
     * @param restartImagePath The file path for the restart button image.
     * @param exitImagePath The file path for the exit button image.
     * @param scoreboard The Scoreboard object to display the final score, values, and time on the screen.
     * @param 800 The Desired width of the display
     * @param 600 the Desired height of the display
     */
    public GameOverScreen(ActionListener startListener, ActionListener exitListener, String bgImagePath, String restartImagePath, String exitImagePath, Scoreboard scoreboard) {
        super(startListener, exitListener, bgImagePath, restartImagePath, exitImagePath, 800, 600);

        /**
         * Gets the final scores from the Scoreboard object
         */
  
        int ingredientsFound = scoreboard.getIngredientsFound();
        int recipesFound = scoreboard.getRecipesFound();
        int damage = scoreboard.getDamage();
        int fines = scoreboard.getFines();
        int score = scoreboard.getScore();
        String time = scoreboard.getTime();

        /**
         * adds the score, values, and time to the display
         * creates a JLabel to display the integer values
         */
        
        JLabel scoresLabel1 = new JLabel("FOUND: " + Integer.toString(ingredientsFound) + " Ingredients & " + Integer.toString(recipesFound) + " Recipes " + "   TIME: " + time + "  " + "  DAMAGE: " + Integer.toString(damage) + "   FINES: " + Integer.toString(fines)  + "   SCORE: " + Integer.toString(score));

        // sets the font for the label
        Font font = new Font("Arial", Font.BOLD, 16);
        scoresLabel1.setFont(font);

        // sets the opaque property to false
        scoresLabel1.setOpaque(false);

        // add the label to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0); // add some spacing between the buttons and labels
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy++;
        add(scoresLabel1, gbc); 
    }
}
