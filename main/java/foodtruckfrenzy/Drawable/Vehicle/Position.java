package foodtruckfrenzy.Drawable.Vehicle;

/**
 * Position stores the coordinates and previously visited cell
 * to be used for backtracking in the cop's depth first search
 */
public class Position {

    /**
     * Creates a new Position object holding the specified row and column of the grid.
     * Also holds a position object prev referencing a previous position
     * @param row the row of the grid.
     * @param col the column of the grid.
     * @param prev the previously visited position by the tracking algorithm.
     */
    public Position(int row, int col, Position prev) {
        this.row = row;
        this.col = col;
        this.prev = prev;
    }

    public int row;
    public int col;
    public Position prev;
}
