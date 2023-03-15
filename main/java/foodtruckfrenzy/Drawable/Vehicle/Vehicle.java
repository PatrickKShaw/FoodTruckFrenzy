package foodtruckfrenzy.Drawable.Vehicle;

import foodtruckfrenzy.Drawable.Drawable;
import foodtruckfrenzy.Drawable.DrawableEnum;
import foodtruckfrenzy.GameFramework.Grid;

/**
 * Represents a Vehicle object that capable of around on the grid
 * Inherits from the Drawable to be displayed
 */

class Vehicle extends Drawable {

    private int SPEED = 1;
    private Grid _grid;

    /**
     * Creates a new vehicle object at the specified row and column of the grid.
     * @param row the row of the vehicle's starting position.
     * @param col the column of the vehicle's starting position.
     * @param grid the grid object that the vehicle will be moving on.
     * @param type the grid object that the vehicle will be moving on.
     */
    public Vehicle(int row, int col, Grid grid, DrawableEnum type) {
        super(row, col, type);
        _grid = grid;
    }

    /**
     * Moves the vehicle up one cell on the grid.
     * @return true if the vehicle was able to move up, false otherwise.
     */
    public boolean moveUp() {
        int newRow = this.getRow() - SPEED;

        if (newRow < 0) { return false; }

        if (_grid.isObstruction(newRow, this.getCol())) { return false; }

        this.setRow(newRow);
        this.setType(DrawableEnum.FOODTRUCK_UP);

        return true;
    }

    /**
     * Moves the vehicle up one cell on the grid.
     * @return true if the vehicle was able to move up, false otherwise.
     */
    public boolean moveDown() {
        int newRow = this.getRow() + SPEED;

        if (newRow >= Grid.ROWS) { return false; }

        if (_grid.isObstruction(newRow, this.getCol())) { return false; }

        this.setRow(newRow);
        this.setType(DrawableEnum.FOODTRUCK_DOWN);

        return true;
    }

    /**
     * Moves the vehicle up one cell on the grid.
     * @return true if the vehicle was able to move up, false otherwise.
     */
    public boolean moveRight() {
        int newCol = this.getCol() + SPEED;

        if (newCol >= Grid.COLS) { return false; }

        if (_grid.isObstruction(this.getRow(), newCol)) { return false; }

        this.setCol(newCol);
        this.setType(DrawableEnum.FOODTRUCK_RIGHT);

        return true;
    }

    /**
     * Moves the vehicle up one cell on the grid.
     * @return true if the vehicle was able to move up, false otherwise.
     */
    public boolean moveLeft() {
        int newCol = this.getCol() - SPEED;

        if (newCol < 0 ) { return false; }

        if (_grid.isObstruction(this.getRow(), newCol)) { return false; }

        this.setCol(newCol);
        this.setType(DrawableEnum.FOODTRUCK_LEFT);

        return true;
    }

    /**
     * Gets the game grid
     * @return game grid
     */
    public Grid getGrid() {
        return _grid;
    }


}
