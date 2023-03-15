package foodtruckfrenzy.GameFramework;

import javax.swing.JPanel;

import foodtruckfrenzy.Drawable.Vehicle.Cop;
import foodtruckfrenzy.Drawable.Vehicle.FoodTruck;
import foodtruckfrenzy.Helper.BoardElementFactory;
import foodtruckfrenzy.Helper.MapLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * GamePanel class is the visual aspect of the game
 * This class creates all the FoodTruck, Cop, BoardElements and places them on the grid
 * This class reads from the MapLayout
 */
class GamePanel extends JPanel {
    
    private Grid grid = new Grid();
    private final FoodTruck _mainCharacter;
    private final ArrayList<Cop> _cops = new ArrayList<>();

    /**
     * GamePanel constructor which does all the creation of the FoodTruck, Cop, BoardElements
     * Initializes and places them on the grid
     */
    public GamePanel() {

        for (int i = 0; i < Grid.ROWS; i++) {
            for (int j = 0; j < Grid.COLS; j++) {
                grid.setCell(i, j, BoardElementFactory.create(MapLayout.getElementAt(i, j), i, j));
            }
        }

        _mainCharacter = new FoodTruck(3, 0, grid);
        _cops.add(new Cop(8, 13, grid, _mainCharacter));
        _cops.add(new Cop(17, 40, grid, _mainCharacter));
        _cops.add(new Cop(19, 13, grid, _mainCharacter));
    }

    /**
     * Override the JPanel paint component to be called when the panel repaints
     * @param g Graphics object corresponding to the grid
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        for (int i = 0; i < Grid.ROWS; i++) {
            for (int j = 0; j < Grid.COLS; j++) {
                grid.drawCell(i,j,g2d);
            }
        }
        _mainCharacter.draw(g2d);
        
        for (Cop cop : _cops) {
            cop.draw(g2d);
        }
    }

    /**
     * Gets the main character object
     * @return FoodTruck object corresponding to the main character
     */
    public FoodTruck getMainCharacter() {
        return _mainCharacter;
    }

    /**
     * Gets all the cop objects
     * @return ArrayList<Cop> object corresponding to the lsit of cops
     */
    public ArrayList<Cop> getCops() {
        return _cops;
    }
}
