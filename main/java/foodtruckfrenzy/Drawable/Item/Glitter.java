package foodtruckfrenzy.Drawable.Item;

import java.awt.Graphics2D;

import foodtruckfrenzy.Drawable.DrawableEnum;

/**
 * Special glitter item that displays an animation to be used when emphasis is needed
 * Has a NULL score of 0 and it should have no bearing on the gameplay
 * The class is only drawn for a limited number of frames before the draw function is no longer able to be used
 */
public class Glitter extends Item {

    private int drawn = 0;

    /**
     * Glitter constructor to be displayed at specified row and column on a grid
     * @param row specified row
     * @param col specified column
     */
    public Glitter(int row, int col) {
        super(row, col, DrawableEnum.PICKUP_GLITTER, 0, ScoreType.NULL);
    } 

    /**
     * Override typical draw method to only allow for drawing of limited frames
     * Calls the super method for the actual drawing
     * @param g2d Graphics2D panel to be drawn on
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (drawn < 20) {
            super.draw(g2d);
            drawn++;
        }
    }
}
