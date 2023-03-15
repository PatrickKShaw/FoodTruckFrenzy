package foodtruckfrenzy.Sprite;

import foodtruckfrenzy.Drawable.DrawableEnum;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Random;
import java.util.EnumMap;

/**
 * SpriteLoader preloads all the sprites for the Drawable objects at load time
 * This is used so the images are only loaded once to increase performance
 * The images are stored in an enum map and returned when getImage is called
 */
public class SpriteLoader {

    // house and food are ArrayLists because they have multiple images for the same object, selected at random
    private static ArrayList<ImageIcon> _houseImages = new ArrayList<ImageIcon>();
    private static ArrayList<ImageIcon> _foodImages = new ArrayList<ImageIcon>();
    private static EnumMap<DrawableEnum, ImageIcon> _imagesEnumMap = new EnumMap<>(DrawableEnum.class);

    private static Random random = new Random();

    static {
            
        try {

            _imagesEnumMap.put(DrawableEnum.FOODTRUCK_UP, new ImageIcon(SpriteLoader.class.getResource("foodTruckUp.png")));
            _imagesEnumMap.put(DrawableEnum.FOODTRUCK_DOWN, new ImageIcon(SpriteLoader.class.getResource("foodTruckDown.png")));
            _imagesEnumMap.put(DrawableEnum.FOODTRUCK_RIGHT, new ImageIcon(SpriteLoader.class.getResource("foodTruckRight.png")));
            _imagesEnumMap.put(DrawableEnum.FOODTRUCK_LEFT, new ImageIcon(SpriteLoader.class.getResource("foodTruckLeft.png")));
            
            _imagesEnumMap.put(DrawableEnum.COP_UP, new ImageIcon(SpriteLoader.class.getResource("copCarUp.gif")));
            _imagesEnumMap.put(DrawableEnum.COP_DOWN, new ImageIcon(SpriteLoader.class.getResource("copCarDown.gif")));
            _imagesEnumMap.put(DrawableEnum.COP_RIGHT, new ImageIcon(SpriteLoader.class.getResource("copCarRight.gif")));
            _imagesEnumMap.put(DrawableEnum.COP_LEFT, new ImageIcon(SpriteLoader.class.getResource("copCarLeft.gif")));

            _imagesEnumMap.put(DrawableEnum.SPEED_TRAP, new ImageIcon(SpriteLoader.class.getResource("speedtrap.png")));
            _imagesEnumMap.put(DrawableEnum.POT_HOLE, new ImageIcon(SpriteLoader.class.getResource("pothole.png")));
            _imagesEnumMap.put(DrawableEnum.RECIPE, new ImageIcon(SpriteLoader.class.getResource("recipe.png")));
            _imagesEnumMap.put(DrawableEnum.PICKUP_GLITTER, new ImageIcon(SpriteLoader.class.getResource("pickupGlitter.gif")));

            _houseImages.add(new ImageIcon(SpriteLoader.class.getResource("house1.png")));
            _houseImages.add(new ImageIcon(SpriteLoader.class.getResource("house2.png")));
            _houseImages.add(new ImageIcon(SpriteLoader.class.getResource("house3.png")));
            _houseImages.add(new ImageIcon(SpriteLoader.class.getResource("house4.png")));
            _houseImages.add(new ImageIcon(SpriteLoader.class.getResource("house5.png")));
            _houseImages.add(new ImageIcon(SpriteLoader.class.getResource("house6.png")));
            
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("potato.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("apple.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("steak.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("chicken.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("cherry.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("icecream.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("egg.png")));
            _foodImages.add(new ImageIcon(SpriteLoader.class.getResource("can.png")));
            
            _imagesEnumMap.put(DrawableEnum.HORIZONTAL_ROAD, new ImageIcon(SpriteLoader.class.getResource("road/Horizontal.png")));
            _imagesEnumMap.put(DrawableEnum.VERTICAL_ROAD, new ImageIcon(SpriteLoader.class.getResource("road/Vertical.png")));
            _imagesEnumMap.put(DrawableEnum.FOUR_WAY_INTERSECT, new ImageIcon(SpriteLoader.class.getResource("road/FourWay.png")));

            _imagesEnumMap.put(DrawableEnum.SOUTH_EAST_CORNER, new ImageIcon(SpriteLoader.class.getResource("road/SouthEastCorner.png")));
            _imagesEnumMap.put(DrawableEnum.SOUTH_WEST_CORNER, new ImageIcon(SpriteLoader.class.getResource("road/SouthWestCorner.png")));
            _imagesEnumMap.put(DrawableEnum.NORTH_EAST_CORNER, new ImageIcon(SpriteLoader.class.getResource("road/NorthEastCorner.png")));
            _imagesEnumMap.put(DrawableEnum.NORTH_WEST_CORNER, new ImageIcon(SpriteLoader.class.getResource("road/NorthWestCorner.png")));
            
            _imagesEnumMap.put(DrawableEnum.THREE_WAY_NORTH, new ImageIcon(SpriteLoader.class.getResource("road/ThreeWayNorth.png")));
            _imagesEnumMap.put(DrawableEnum.THREE_WAY_EAST, new ImageIcon(SpriteLoader.class.getResource("road/ThreeWayEast.png")));
            _imagesEnumMap.put(DrawableEnum.THREE_WAY_SOUTH, new ImageIcon(SpriteLoader.class.getResource("road/ThreeWaySouth.png")));
            _imagesEnumMap.put(DrawableEnum.THREE_WAY_WEST, new ImageIcon(SpriteLoader.class.getResource("road/ThreeWayWest.png")));
        } catch (Exception e) {
            System.err.println("Error when loading sprite files with SpriteLoader:");
            e.printStackTrace();
        }  
    }

    /**
     * Returns the appropriate sprite image requested from the enum map
     * or randomly from either the house or food ArrayLists if applicable
     * @param type DrawableEnum type of Drawable sprite that we need the image for
     * @return The appropriate Image as requested
     */
    public static ImageIcon getImage(DrawableEnum type) {

        if (type == DrawableEnum.OBSTRUCTION) {
            int randomNumber = random.nextInt(_houseImages.size());
            return _houseImages.get(randomNumber);
        } else if (type == DrawableEnum.FOOD) {
            int randomNumber = random.nextInt(_foodImages.size());
            return _foodImages.get(randomNumber);
        }
    
        return _imagesEnumMap.get(type);
    }
}
