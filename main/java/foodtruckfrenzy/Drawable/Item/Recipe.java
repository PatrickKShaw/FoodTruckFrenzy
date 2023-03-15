package foodtruckfrenzy.Drawable.Item;

import foodtruckfrenzy.Drawable.DrawableEnum;

/**
 * Recipie class extends Item class, representing a bonus item that can be collected
 * Recipies are created with a specified row and column, and their drawable type is set to RECIPIE
 * They have a BONUS positive score
 * This class also keeps track of the amount of created instances of Recipie objects
 */
public class Recipe extends Item {
    
    public static int _count = 0;

    /**
     * Constructs a new Recipe object with a specified row and column, drawable type, and bonus score value.
     * Increases the instance counter.
     * @param row the row of the Recipe on the game grid
     * @param col the column of the Recipe on the game grid
     */
    public Recipe(int row, int col) {
        super(row, col, DrawableEnum.RECIPE, 400, ScoreType.BONUS);
        Recipe._count++;
    }

    /**
     * Returns the number of Recipe objects created
     * @return the count of Recipe objects
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
