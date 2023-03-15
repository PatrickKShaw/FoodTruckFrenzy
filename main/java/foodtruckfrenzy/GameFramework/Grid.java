package foodtruckfrenzy.GameFramework;

import java.awt.Graphics2D;

import foodtruckfrenzy.Drawable.BoardElement.BoardElement;
import foodtruckfrenzy.Drawable.Item.ScoreValue;

/**
 * Grid class is an object which acts as a game grid
 * This object will store a 2d array of BoardElements in a predetermined size which can be set and updated accordingly
 * The class provides methods to retrieve, set, and interact with BoardElements on the grid, and to draw cells on the graphics context.
 */
public class Grid {

    public final static int ROWS = 20; // Number of grid rows
    public final static int COLS = 41; // Number of grid columns
    public final static int CELL_SIZE = 32; // Size of each grid cell
    
    private BoardElement[][] grid;

    /**
     * Construtor for the Grid class
     * Initliazes a new BoardElement 2d array at ROWS x COLS
     */
    public Grid() {
        grid = new BoardElement[ROWS][COLS];
    }

    /**
     * Returns the BoardElement requested
     * @param row row of requested BoardElement
     * @param col column of requested BoardElement
     * @return requested BoardElement object
     */
    public BoardElement getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Sets a specified BoardElement on the grid to a new one
     * @param row row of specified BoardElement
     * @param col col of specified BoardElement
     * @param cell new BoardElement to be set
     */
    public void setCell(int row, int col, BoardElement cell) {
        grid[row][col] = cell;
    }

    /**
     * Calls the draw method on the specified BoardElement
     * @param row row of specified BoardElement
     * @param col column of specified BoardElement
     * @param g2d Graphics2D object which corresponds to the grid to be passed to the draw method
     */
    public void drawCell(int row, int col, Graphics2D g2d) {
        grid[row][col].draw(g2d);
    }

    /**
     * Calls the isObstruction() method of the specified BoardElement
     * @param row row of specified BoardElement
     * @param col column of specified BoardElement
     * @return value returned by the BoardElement's isObstruction() method
     */
    public boolean isObstruction(int row, int col) {
        return grid[row][col].isObstruction();
    }

    /**
     * Calls the interact() ethod of the specified BoardElement
     * @param row row of specified BoardElement
     * @param col column of specified BoardElement
     * @return value returned by the BoardElement's interact() method
     */
    public ScoreValue interact(int row, int col) {

        return getCell(row, col).interact();
    }

}
