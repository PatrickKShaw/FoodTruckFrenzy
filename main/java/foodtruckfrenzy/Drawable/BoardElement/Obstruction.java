package foodtruckfrenzy.Drawable.BoardElement;

import foodtruckfrenzy.Drawable.DrawableEnum;
import foodtruckfrenzy.Drawable.Item.ScoreValue;

/**
 * The Obstruction class extends the BoardElement class and represents an obstruction on the game grid. 
 * Obstructions are stationary and cannot be interacted with, so calling the interact method on an obstruction will throw an UnsupportedOperationException.
 * Obstructions are created with a specified row and column, and their drawable type is set to OBSTRUCTION.
 */
public class Obstruction extends BoardElement {

    /**
     * Constructs a new Obstruction object with a specified row and column.
     * @param row the row of the Obstruction on a game grid
     * @param col the column of the Obstruction on a game grid
     */
    public Obstruction(int row, int col) {
        super(row, col, DrawableEnum.OBSTRUCTION);
    }

    /**
     * Throws an UnsupportedOperationException since obstructions cannot be interacted with
     * @return nothing, since the method always throws an exception
     * @throws UnsupportedOperationException always thrown when this method is called
     */
    @Override
    public ScoreValue interact() {
        throw new UnsupportedOperationException(" 'interact' method called on an obstruction");
    }   
}
