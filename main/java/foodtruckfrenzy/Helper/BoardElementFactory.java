package foodtruckfrenzy.Helper;

import foodtruckfrenzy.Drawable.DrawableEnum;
import foodtruckfrenzy.Drawable.BoardElement.Obstruction;
import foodtruckfrenzy.Drawable.BoardElement.Road;
import foodtruckfrenzy.Drawable.BoardElement.BoardElement;
import foodtruckfrenzy.Drawable.Item.Food;
import foodtruckfrenzy.Drawable.Item.PotHole;
import foodtruckfrenzy.Drawable.Item.Recipe;
import foodtruckfrenzy.Drawable.Item.SpeedTrap;

/**
 * Factory method class which allows for the creation of various board elements
 * These BoardElements can contain different graphics or items depending on the type
 */
public class BoardElementFactory {
    /**
     * Creates BoardElement object based on specified parameters
     * @param type BoardElementEnum to be converted into a BoardElement
     * @param row row that the new BoardElement will be on a grid
     * @param col column that the new BoardElement will be on a grid
     * @return new Boardelement object based off specified parameters
     */
    public static BoardElement create(LayoutEnum type, int row, int col) {
        switch (type) {
            case H:
                return new Road(row, col, null, DrawableEnum.HORIZONTAL_ROAD);
            case V:
                return new Road(row, col, null, DrawableEnum.VERTICAL_ROAD);
            case X:
                return new Road(row, col, null, DrawableEnum.FOUR_WAY_INTERSECT);

            case U:
                return new Road(row, col, null, DrawableEnum.NORTH_WEST_CORNER);
            case I:
                return new Road(row, col, null, DrawableEnum.NORTH_EAST_CORNER);
            case J:
                return new Road(row, col, null, DrawableEnum.SOUTH_EAST_CORNER);
            case K:
                return new Road(row, col, null, DrawableEnum.SOUTH_WEST_CORNER);
                
            case N:
                return new Road(row, col, null, DrawableEnum.THREE_WAY_NORTH);
            case B:
                return new Road(row, col, null, DrawableEnum.THREE_WAY_EAST);
            case M:
                return new Road(row, col, null, DrawableEnum.THREE_WAY_SOUTH);
            case C:
                return new Road(row, col, null, DrawableEnum.THREE_WAY_WEST);


            case O:
                return new Obstruction(row, col);
            case S:
                return new Road(row, col, new SpeedTrap(row, col), DrawableEnum.HORIZONTAL_ROAD);
            case P:
                return new Road(row, col, new PotHole(row, col), DrawableEnum.HORIZONTAL_ROAD);
            case L:
                return new Road(row, col, new PotHole(row, col), DrawableEnum.VERTICAL_ROAD);
            case F:
                return new Road(row, col, new Food(row, col), DrawableEnum.HORIZONTAL_ROAD);
            case R:
                return new Road(row, col, new Food(row, col), DrawableEnum.VERTICAL_ROAD);
            case Q:
                return new Road(row, col, new Recipe(row, col), DrawableEnum.HORIZONTAL_ROAD);
            
            default:
                throw new IllegalArgumentException();
        }
    }
}
