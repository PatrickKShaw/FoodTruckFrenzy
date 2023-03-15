package foodtruckfrenzy;

import foodtruckfrenzy.SecondaryUI.Frame;
import foodtruckfrenzy.SecondaryUI.ScreenType;

/**
 * The Main Class sets up and displays the initial window frame and event listeners for the Title Screen of the Food Truck Frenzy game.
 * It has the option to Start or Exit the Game
 * @param args arguments passed to the program
 */

public class Main {

    public static void main(String[] args) {
        /**
         * Creates and Displays the Title Screen on program startup
         */
        new Frame(ScreenType.TITLE, null);
    }
}
