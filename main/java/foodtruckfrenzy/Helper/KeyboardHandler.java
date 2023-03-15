package foodtruckfrenzy.Helper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyboardHandler class is responsible for listening to the keyboard input
 * This class keeps track of whether the up/W down/S left/A right/D are pressed down by the user
 */
public class KeyboardHandler implements KeyListener {

    // Boolean state of whether the keys are pressed or not
    private boolean _upPressed = false;
    private boolean _downPressed = false;
    private boolean _rightPressed = false;
    private boolean _leftPressed = false;
    private boolean _pause = false;

    /**
     * Returns whether up/W is pressed
     * @return true if up/W is pressed, false if not
     */
    public boolean upPressed() {
        return _upPressed;
    }

    /**
     * Returns whether down/S is pressed
     * @return true if down/S is pressed, false if not
     */
    public boolean downPressed() {
        return _downPressed;
    }

    /**
     * Returns whether right/D is pressed
     * @return true if right/D is pressed, false if not
     */
    public boolean rightPressed() {
        return _rightPressed;
    }

    /**
     * Returns whether left/A is pressed
     * @return true if left/A is pressed, false if not
     */
    public boolean leftPressed() {
        return _leftPressed;
    }

    /**
     * Returns whether p is pressed
     * @return true if p is pressed, false if not
     */
    public boolean pause() {
        return _pause;
    }

    /**
     * Resets all key boolean values to their false/unpressed state
     */
    public void resetKeys() {
        _upPressed = false;
        _downPressed = false;
        _rightPressed = false;
        _leftPressed = false;
        _pause = false;

    }

    /**
     * Method invoked when a key is pressed
     * This updates the corresponding boolean values to true based on what keys are pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                _upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                _downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                _leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                _rightPressed = true;
                break;
            case KeyEvent.VK_P:
                _pause = true;
                break;
        }
    }

    /**
     * Method invoked when a key is released
     * This updates the corresponding boolean values to false based on what keys are released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                _upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                _downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                _leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                _rightPressed = false;
                break;
            case KeyEvent.VK_P:
                _pause = false;
                break;
        }
    }

    /**
     * This method is not needed for this implementation but is required to be overriden
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}