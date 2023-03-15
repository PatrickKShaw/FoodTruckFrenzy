package foodtruckfrenzy.Drawable.Item;

import foodtruckfrenzy.Drawable.Drawable;
import foodtruckfrenzy.Drawable.DrawableEnum;

/**
 * Items are an abstract class which are to have a score type and score value associated to them
 */
public abstract class Item extends Drawable {

    private int _value;
    private ScoreType _scoreType;

    /**
     * Item constructor
     * @param row row where item will be on a grid
     * @param col column where item will be on a grid
     * @param type DrawableEnum corresponding to the type of item to be displayed
     * @param value int value of item
     * @param scoreType ScoreType type of score to be counted as
     */
    public Item(int row, int col, DrawableEnum type, int value, ScoreType scoreType) {
        super(row, col, type);
        _value = value;
        _scoreType = scoreType;
    }
    
    /**
     * Get the value of item
     * @return int value of item is returned
     */
    public int getValue() {
        return _value;
    }
    
    /**
     * Get what type of score the item has
     * @return ScoreType of item is returned
     */
    public ScoreType getScoreType() {
        return _scoreType;
    }
}
