package foodtruckfrenzy.Drawable.BoardElement;

import java.awt.Graphics2D;

import foodtruckfrenzy.Drawable.DrawableEnum;
import foodtruckfrenzy.Drawable.Item.Glitter;
import foodtruckfrenzy.Drawable.Item.Item;
import foodtruckfrenzy.Drawable.Item.ScoreType;
import foodtruckfrenzy.Drawable.Item.ScoreValue;

/**
 * Road class represents a road on the game grid.
 * This extends BoardElement and can contain an item.
 * The class contains a special override method to draw the item ontop of the road
 * This class allows the character to interact with the item on the road
 */
public class Road extends BoardElement {

    private Item _item;

    /**
     * Constructs a new Road object with the given row, column, item, and drawable.
     * @param row The row of the road on the game grid.
     * @param col The column of the road on the game grid.
     * @param item The item contained within the road, can be null.
     * @param drawable The Drawable associated with the road.
     */
    public Road(int row, int col, Item item, DrawableEnum drawable) {
        super(row, col, drawable);
        _item = item;
    }

    /**
     * Draws the road and its contained item on the screen using the given Graphics2D object.
     * @param g2d The Graphics2D object used to draw the road and item.
     */
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        if (!(_item == null))
            _item.draw(g2d);
    }

    /**
     * Interacts with the Item contained within the road and returns the score value associated with the interaction.
     * Items with a positive value are removed from the Road after interaction
     * @return A ScoreValue object representing the score value obtained from the interaction. If there is no item, returns null.
     */
    @Override
    public ScoreValue interact() {
        
        if (_item == null)
            return null;

        int value = _item.getValue();
        ScoreType scoreType = _item.getScoreType();

        if (value>0) {
            _item = new Glitter(this.getRow(), this.getCol());
        }

        return new ScoreValue(scoreType, value);
    }
}
