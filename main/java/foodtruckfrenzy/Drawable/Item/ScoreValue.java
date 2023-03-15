package foodtruckfrenzy.Drawable.Item;

/**
 * Class which stores information about a score
 * Contains ScoreType _scoreType which is the type of score
 * Contains int _value which is the value of the score
 */
public class ScoreValue {
    private ScoreType _scoreType;
    private int _value;

    /**
     * ScoreValue constructor to create a new pair of type and value
     * @param scoreType ScoreType type of score 
     * @param value int vlaue of score
     */
    public ScoreValue(ScoreType scoreType, int value){
        _scoreType = scoreType;
        _value = value;
    }

    /**
     * Gets type of score
     * @return ScoreType returns enum of type of score
     */
    public ScoreType getScoreType() {
        return _scoreType;
    }

    /**
     * Gets value of score
     * @return returns int of value of score
     */
    public int getValue() {
        return _value;
    }
}
