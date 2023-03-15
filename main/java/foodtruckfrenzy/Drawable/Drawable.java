package foodtruckfrenzy.Drawable;

import java.awt.*;

import javax.swing.ImageIcon;

import foodtruckfrenzy.GameFramework.Grid;
import foodtruckfrenzy.Sprite.SpriteLoader;

/**
 * This abstract Drawable class represents everything that can have a sprite to be drawn
 */
public abstract class Drawable {

    private int _row;
    private int _col;
    private ImageIcon _image;
    private DrawableEnum _type;

    /**
     * Constructor for a new Drawable element
     * @param row row which this element is on a grid
     * @param col column which this element is on a grid
     * @param type DrawableEnum which is to be displayed when this image is drawn
     */
    public Drawable(int row, int col, DrawableEnum type) {
        _type = type;
        _row = row;
        _col = col;
        _image = SpriteLoader.getImage(this._type);
    }

    /**
     * Gets the row that the drawable is on a grid
     * @return row that the drawable is on a grid
     */
    public int getRow() {
        return _row;
    }

    /**
     * Sets a new row for the drawable on a grid
     * @param row new row to set the drawable to on a grid
     */
    public void setRow(int row) {
        this._row = row;
    }

    /**
     * Gets the column that the drawable is on a grid
     * @return column that the drawable is on a grid
     */
    public int getCol() {
        return _col;
    }

    /**
     * Sets a new column for the drawable on a grid
     * @param col new column to set the drawable to on a grid
     */
    public void setCol(int col) {
        _col = col;
    }

    /**
     * Replaces the DrawableEnum in the object
     * Updates the image after setting to get the new image based on this new type
     * @param type new DrawableEnum for the Drawable object
     */
    public void setType(DrawableEnum type) {
        _type = type;
        _image = SpriteLoader.getImage(this._type);
    }

    /**
     * Draws the Drawable object's sprite onto the specified grid
     * @param g2d Graphics2D object corresponding to the specified grid
     */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(_image.getImage(), _col * Grid.CELL_SIZE, _row * Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
    }

}
