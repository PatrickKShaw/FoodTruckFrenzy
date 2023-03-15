package foodtruckfrenzy.Drawable.Item;

import foodtruckfrenzy.Drawable.DrawableEnum;

/**
 * Food class extends Item class, representing a standard item that can be collected
 * Foods are created with a specified row and column, and their drawable type is set to FOOD
 * They have a FOOD positive score
 * This class also keeps track of the amount of created instances of FOOD objects
 */
public class Food extends Item {

    private static int _count = 0;

    /**
     * Constructs a new Food object with a specified row and column, drawable type, and bonus score value.
     * Increases the instance counter.
     * @param row the row of the Recipe on the game grid
     * @param col the column of the Recipe on the game grid
     */
    public Food(int row, int col) {
        super(row, col, DrawableEnum.FOOD, 200, ScoreType.FOOD);
        Food._count++;
    }

    /**
     * Returns the number of Food objects created
     * @return the count of Food objects
     */
    public static int getCount() {
        return _count;
    }

    /**
     * Resets the count of Food objects to 0
     */
    public static void resetCount() {
        _count = 0;
    }
    
}
