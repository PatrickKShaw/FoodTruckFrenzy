package foodtruckfrenzy.Drawable.Vehicle;

/**
 * Represents a Cop object that moves around on the grid autonomously following the foodtruck
 * Inherits from the Vehicle class for its movement
 */

// import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import foodtruckfrenzy.Drawable.DrawableEnum;
import foodtruckfrenzy.GameFramework.Grid;

public class Cop extends Vehicle{

    private FoodTruck foodtruck;

    private List<Position> queue = new LinkedList<>();
    private PositionList visited = new PositionList();
    private List<Direction> directions = new LinkedList<>();

    private Position target;
    private Position searchOrigin;

    /**
     * Creates a new Cop object at the specified row and column of the grid.
     * @param row the row of the cop's starting position.
     * @param col the column of the cop's starting position.
     * @param grid the grid object that the cop will be moving on.
     */
    public Cop(int row, int col, Grid grid, FoodTruck player) {
        super(row, col, grid, DrawableEnum.COP_RIGHT);
        this.foodtruck = player;
        target = new Position(player.getRow(), player.getCol(), null);
        queue.add(new Position(row, col, null));
        // System.out.println("\n ONLY SHOULD PRINT ONCE \n");
        searchOrigin = new Position(row, col, null);
        getDirections();
    }

    /**
     * Moves the cop car up one cell on the grid and updates its sprite accordingly.
     * @return true if the cop car was able to move up, false otherwise.
     */
    @Override
    public boolean moveUp() {
        boolean moved = super.moveUp();
        this.setType(DrawableEnum.COP_UP);
        return moved;
    }

    /**
     * Moves the cop car down one cell on the grid and updates its sprite accordingly.
     * @return true if the cop car was able to move down, false otherwise.
     */
    @Override
    public boolean moveDown() {
        boolean moved = super.moveDown();
        this.setType(DrawableEnum.COP_DOWN);
        return moved;
    }

    /**
     * Moves the cop car left one cell on the grid and updates its sprite accordingly.
     * @return true if the cop car was able to move left, false otherwise.
     */
    @Override
    public boolean moveLeft() {
        boolean moved = super.moveLeft();
        this.setType(DrawableEnum.COP_LEFT);
        return moved;
    }

    /**
     * Moves the cop car right one cell on the grid and updates its sprite accordingly.
     * @return true if the cop car was able to move right, false otherwise.
     */
    @Override
    public boolean moveRight() {
        boolean moved = super.moveRight();
        this.setType(DrawableEnum.COP_RIGHT);
        return moved;
    }

    /**
     * Tracks the movements of the food truck and appends to the list of directions
     * for the cop to follow the food truck
     */
    public void trackTruck() {
        // System.out.println(directions.size());
        target.row = foodtruck.getRow();
        target.col = foodtruck.getCol();

        // System.out.println("--- Queue -----");
        // for (Position q : queue) {
        //     if (q != null )System.out.println(q.col + " " + q.row);
        //     else System.out.println("null");
        // }

        if (!queue.isEmpty())
            target.prev = queue.get(0);
        
        // System.out.println("target pos \t" + target.col + " " + target.row);
        // System.out.println("previous pos \t" + target.prev.col + " " + target.prev.row);
        // System.out.println("\n");
        // System.out.println("--- Queue -----");
        // for (Position q : queue) {
        //     if (q != null )System.out.println(q.col + " " + q.row);
        // }
        // System.out.println("#################################");

        if (!(target.row == target.prev.row && target.col == target.prev.col)) {
            // System.out.println("getting directinons");
            queue.add(new Position(target.row, target.col, target.prev));
            getDirections();
        }
    }

    /**
     * Calls move the corresponding move functions to move the cop car according to the
     * next value in the directions list.
     */
    public void chaseTruck() {
        if (!directions.isEmpty()) {
            if (directions.get(0) == Direction.UP)
                moveUp();
            if (directions.get(0) == Direction.DOWN)
                moveDown();
            if (directions.get(0) == Direction.LEFT)
                moveLeft();
            if (directions.get(0) == Direction.RIGHT)
                moveRight();
            directions.remove(0);
        }
    }

    /**
     * Gets the food truck position, gathers cooredinates towards the food truck through getPath()
     * and converts coordinate values to up/down/left/right directions to be appended to the 
     * directions arrayList.
     * Alrogirhtm is implemented with a breadth-first search.
     */
    public void getDirections() {
        target = new Position(foodtruck.getRow(), foodtruck.getCol(), null);
        // ArrayList<Direction> directions = new ArrayList<>();
        List<Position> path = new LinkedList<>();
        
        Position currentPos = queue.get(0);
        // System.out.println("current Position: " + currentPos.col + " " + currentPos.row);

        while (!(currentPos.row == target.row && currentPos.col == target.col)) {
            currentPos = queue.get(0);
            checkAdjacentCells(currentPos);
            visited.add(currentPos);

            queue.remove(0);
        }
        // Position lastPos = queue.get(queue.size()-1);
        queue.clear();
        queue.add(currentPos);
        // System.out.println("Queue size: " + queue.size());
        // System.out.println("Last element: " + queue.get(queue.size() - 1).col + " " + queue.get(queue.size() - 1).row);
        // System.out.println("current Position: " + currentPos.col + " " + currentPos.row);
        
        // System.out.println("--- Queue -----");
        // for (Position q : queue) {
        //     if (q != null )System.out.println(q.col + " " + q.row);
        // }
        // System.out.println("#################################");
        
        path = getPath(currentPos);

        // System.out.println("--- Path -----");
        // for (Position p : path) {
        //     if (p != null)System.out.println(p.col + " " + p.row);
        //     else System.out.println("null");
        // }
        // System.out.println("#################################");
        
        if (path.size() > 0) {
            // Converts set of coordinates to set of directions
            for (int i = 0; i < path.size() - 1; i++) {
                // get direction UP
                if (path.get(i).row > path.get(i + 1).row && path.get(i).col == path.get(i + 1).col)
                    directions.add(Direction.UP);

                // get direction DOWN
                if (path.get(i).row < path.get(i + 1).row && path.get(i).col == path.get(i + 1).col)
                    directions.add(Direction.DOWN);

                // get direction LEFT
                if (path.get(i).row == path.get(i + 1).row && path.get(i).col > path.get(i + 1).col)
                    directions.add(Direction.LEFT);

                // get direction RIGHT
                if (path.get(i).row == path.get(i + 1).row && path.get(i).col < path.get(i + 1).col)
                    directions.add(Direction.RIGHT);
            }
        }

        // System.out.println("--- Directions -----");
        // for (Direction d : directions) {
        //     System.out.println(d);
        // }
        // System.out.println("#################################");
    }

    /**
     * Traces back from the node at the target position (food truck) and records
     * coordinates leading to the food truck.
     * @return ArrayList of Position objects containing ordered coordinates the path
     * from the origin (cop) to the food truck.
     */
    public List<Position> getPath(Position pos) {
        List<Position> path = new LinkedList<>();

        path.add(pos);
        while (!(pos.row == searchOrigin.row && pos.col == searchOrigin.col)) {
            path.add(pos.prev);
            pos = pos.prev;
            if (pos == null)
                break;
        }
        Collections.reverse(path);

        searchOrigin = path.get(path.size() - 1);
        return path;
    }

    /**
     * Checks whether adjeacent cells of a given position are roads and adds them
     * to the queue for the depth-first search if they are.
     */
    public void checkAdjacentCells(Position pos) {

        // check upper adjacent cell
        if (pos.row - 1 >= 0 && !visited.containsPos(new Position(pos.row - 1, pos.col, pos)) 
        && !this.getGrid().getCell(pos.row - 1, pos.col).isObstruction())
            queue.add(new Position(pos.row - 1, pos.col, pos));

        // check lower adjacent cell
        if (pos.row + 1 < Grid.ROWS && !visited.containsPos(new Position(pos.row + 1, pos.col, pos)) 
        && !this.getGrid().getCell(pos.row + 1, pos.col).isObstruction())
            queue.add(new Position(pos.row + 1, pos.col, pos));

        // check left adjacent cell
        if (pos.col - 1 >= 0 && !visited.containsPos(new Position(pos.row, pos.col - 1, pos)) 
        && !this.getGrid().getCell(pos.row, pos.col - 1).isObstruction())
            queue.add(new Position(pos.row, pos.col - 1, pos));

        // check right adjacent cell
        if (pos.col + 1 < Grid.COLS && !visited.containsPos(new Position(pos.row, pos.col + 1, pos)) 
        && !this.getGrid().getCell(pos.row, pos.col + 1).isObstruction())
            queue.add(new Position(pos.row, pos.col + 1, pos));

    }
}
