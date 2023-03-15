package foodtruckfrenzy.Drawable.Item;

import foodtruckfrenzy.Drawable.DrawableEnum;

/**
 * A SpeedTrap class is a type of Item which contains a negative SPEED score value
 */
public class SpeedTrap extends Item {

    /**
     * Constructor for the SpeedTrap class using a specified row and column
     * @param row the row at which the SpeedTrap object is placed on the game grid
     * @param col the column at which the SpeedTrap object is placed on the game grid
     */
    public SpeedTrap(int row, int col) {
        super(row, col, DrawableEnum.SPEED_TRAP, -196, ScoreType.SPEED);
    }
    
}
