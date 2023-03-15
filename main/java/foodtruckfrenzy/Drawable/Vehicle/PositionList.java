package foodtruckfrenzy.Drawable.Vehicle;

import java.util.LinkedList;

/**
 * PositionList stores position objects to be used for the player
 * tracking algorithm
 */
public class PositionList extends LinkedList<Position> {
    /**
     * Checks if PositionList contains a Position with matching row and col
     * values as the input positoin
     * @return true if the input Position row and col match a Position in PositionList
    */
    public boolean containsPos(Position pos) {
        if (this.isEmpty()) return false;

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).col == pos.col && this.get(i).row == pos.row)
                return true;
        }
        
        return false;
    }
    
}
