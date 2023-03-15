package foodtruckfrenzy.Drawable.Vehicle;

import foodtruckfrenzy.Drawable.DrawableEnum;
import foodtruckfrenzy.Drawable.Item.ScoreType;
import foodtruckfrenzy.Drawable.Item.ScoreValue;
import foodtruckfrenzy.GameFramework.Grid;

/**
 * Represents a Food Truck object that can move around a grid and interact with different score values on the grid.
 * Inherits from the Vehicle class and adds scoreboard attributes for damage, fines, and score, as well as attributes
 * for the number of ingredients and recipes found.
 */
public class FoodTruck extends Vehicle {
    
    private int damage; // damage Food Truck has taken from colliding with obstructions.
    private int fines;  // fines Food Truck has accumulated from speed traps
    private int score;  // total score incurred by Food Truck player
    private int ingredientsFound; 
    private int recipesFound;


    /**
     * Creates a new Food Truck object at the specified row and column of the grid.
     * @param row the row of the food truck's starting position.
     * @param col the column of the food truck's starting position.
     * @param grid the grid object that the food truck will be moving on.
     */
    public FoodTruck(int row, int col, Grid grid) {
        super(row, col, grid, DrawableEnum.FOODTRUCK_RIGHT);
    }

    /**
     * Moves the Food Truck up one cell on the grid and updates its sprite accordingly.
     * @return true if the food truck was able to move up, false otherwise.
     */
    @Override
    public boolean moveUp() {
        boolean moved = super.moveUp();
        this.setType(DrawableEnum.FOODTRUCK_UP);

        if (moved)
            interact();

        return moved;
    }

    /**
     * Moves the Food Truck down one cell on the grid and updates its sprite accordingly.
     * @return true if the food truck was able to move down, false otherwise.
     */
    @Override
    public boolean moveDown() {
        boolean moved = super.moveDown();
        this.setType(DrawableEnum.FOODTRUCK_DOWN);

        if (moved)
            interact();

        return moved;
    }

    /**
     * Moves the Food Truck right one cell on the grid and updates its sprite accordingly.
     * @return true if the food truck was able to move right, false otherwise.
     */
    @Override
    public boolean moveRight() {
        boolean moved = super.moveRight();
        this.setType(DrawableEnum.FOODTRUCK_RIGHT);
        
        if (moved)
            interact();

        return moved;
    }

    /**
     * Moves the Food Truck left one cell on the grid and updates its sprite accordingly.
     * @return true if the food truck was able to move left, false otherwise.
     */
    @Override
    public boolean moveLeft() {
        boolean moved = super.moveLeft();
        this.setType(DrawableEnum.FOODTRUCK_LEFT);

        if (moved)
            interact();

        return moved;
    }

    /**
     * Interacts with the current cell on the grid that the Food Truck is in, adding score and updating 
     * attributes as necessary.
     */
    private void interact() {
        ScoreValue scoreValue = this.getGrid().interact(this.getRow(), this.getCol());

        if (scoreValue == null) 
            return;

        int value = scoreValue.getValue();
        ScoreType scoreType = scoreValue.getScoreType();

        this.score += value;

        switch(scoreType) {
            case FOOD:
                ingredientsFound++;
                break;
            case DAMAGE:
                addDamage(value);
                break;
            case SPEED:
                addFines(value);
                break;
            case BONUS:
                recipesFound++;
                break;
            default:
                break;
        }

    }

    /**
     * Returns the number of ingredients found by the Food Truck during gameplay.
     * @return an int representing the number of ingredients found by the food truck. 
     */
    public int getIngredientsFound() {
       
        return this.ingredientsFound;
    
    }

    /**
     * Returns the number of recipes found by the Food Truck during gameplay.
     * @return an int representing the number of recipes found by the food truck.
     */
    public int getRecipesFound() {

        return this.recipesFound;
    }

    /**
     * Adds damage to the Food Truck after collision occurs between obstruction and Food Truck. 
     * @param damage is an int representing the value of damage caused by collision with the obstruction.
     */
    public void addDamage(int damage) {

        this.damage -= damage;

    } 

    /**
     * Returns the amount damage accumulated by the Food Truck during gameplay.
     * @return an integer that represents the damage incurred by the truck during gameplay. 
     */
    public int getDamage() {

        return this.damage;
    
    }

    /**
     * Adds fines to the Food Truck after passing through speed traps.
     * @param fine is an integer that represents fines incurred by the Food Truck after interacting with speed traps.
     */
    public void addFines(int fine) {
        
        this.fines -= fine;
    
    }

    /**
     * Returns the fines total amount accumulated by the Food Truck.
     * @return an integer representing the fines accumulated by the Food Truck during gameplay.
     */
    public int getFines() {

        return this.fines;

    }
    /**
     * Returns the total score achieved by the Food Truck during gameplay as a string.
     * @return a string representing the score achieved by the player during gameplay.
     */
    public String getScore() {

        return Integer.toString(this.score);

    }

    /**
    * Returns the total score achieved by the Food Truck during gameplay as an integer.
     * @return an integer representing the score achieved by the player during gameplay.
     */
    public int getScoreInt() {
        return this.score;
    }

    /**
     * Calculates and stores the total score accumulated by the Food Truck. The score is calculated by subtracting
     * the value of fines and damage from the score attribute. 
     */
    public void calculateScore() {

        this.score = this.score - fines - damage;

    } 
}
