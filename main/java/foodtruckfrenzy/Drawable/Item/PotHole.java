package foodtruckfrenzy.Drawable.Item;

import foodtruckfrenzy.Drawable.DrawableEnum;

/**
 * PotHole class class extends the Item clas and represents the pothole trap on a game grid.
 * Pot holes have a negative DAMAGE score
 * POtHoles are created with a specified row and column, and their drawable type is set to POT_HOLE
 */
public class PotHole extends Item {

    /**
     * Constructs a new PotHole object with a specified row and column, drawable type, and damage score value.
     * @param row the row of the PotHole on the game grid
     * @param col the column of the PotHole on the game grid
     */
    public PotHole(int row, int col) {
        super(row, col, DrawableEnum.POT_HOLE, -86, ScoreType.DAMAGE);
    }
    
}
