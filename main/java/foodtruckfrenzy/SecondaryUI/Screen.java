package foodtruckfrenzy.SecondaryUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * The Screen class represents a JPanel that displays a background image and two buttons: a "resume" button and an "exit" button.
 * The buttons have custom images and behavior when clicked or hovered over. 
 * The background image and button images can be set through the constructor with a path to the image files.
 * The buttons have ActionListeners that can be passed in through the constructor, which will be triggered when the buttons are clicked.
 * The Screen class is the Parent class of all other UI screens such as TitleScreen, PauseScreen, GameOverDisplay and it's children: GameLostScreen and GameWonScreen
 */

public class Screen extends JPanel {
    protected BufferedImage backgroundImage;
    protected BufferedImage startImage;
    protected BufferedImage exitImage;
    private JButton resumeButton;
    private JButton exitButton;
    protected int width;
    protected int height;

    /**
     * Constructs a new Screen object with the specified ActionListener objects and image paths.
     * @param resumeListener the listener to be notified when the "resume" button is pressed
     * @param exitListener the listener to be notified when the "exit" button is pressed
     * @param bgImagePath the path to the background image file
     * @param startImagePath the path to the "start" button image file
     * @param exitImagePath the path to the "exit" button image file
     * @param width The Desired width of the display
     * @param height the Desired height of the display
     */

    public Screen(ActionListener resumeListener, ActionListener exitListener, String bgImagePath, String startImagePath, String exitImagePath,int width,int height) {
        /**
         * Loads the images from the given file paths and resizes them to the correct dimensions for background and the two buttons
         * @throws IOException if there is a read or resize error 
         */
        try {
            InputStream backgroundInputStream = Screen.class.getResourceAsStream(bgImagePath);
            InputStream startInputStream = Screen.class.getResourceAsStream(startImagePath);
            InputStream exitInputStream = Screen.class.getResourceAsStream(exitImagePath);
            backgroundImage = ImageIO.read(backgroundInputStream);
            backgroundImage = resize(backgroundImage, width, height);
            startImage = ImageIO.read(startInputStream);
            startImage = resize(startImage, 100, 50);
            exitImage = ImageIO.read(exitInputStream);
            exitImage = resize(exitImage, 100, 50);

        } catch (IOException e) {
            System.out.println("Error loading background image:");
            e.printStackTrace();
        }

        /**
         * Sets the layout of the Screen to a GridBagLayout with specified constraints to be implemented.
         */
        setLayout(new GridBagLayout());

        /**
         * Initializes a GridBagConstraints object and implements specified constraints.
         */

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        /**
         * Creates a JLabel with an ImageIcon with the backgroud image and adds it to the screen
         * Creates a JPanel with a GridBagLayout, with a transparent background
         * Sets the GridBagConstraints and spaces the buttons apart
         * Creates a JButton with an ImageIcon containing the start image and adds an ActionListener
         * Sets the size , removes the button border, removes the focus paint, and makes the content area painted on top of the button image
         * Adds a MouseAdapter to the button to change the border to blue when the mouse goes over the button and remove the border when it leaves the button
         * resumeListener ActionListener to be added to resume button
         * startImage Image to be added to the resumeButton
         * backgroundLabel the background image displayed on the screen
         * gbc GridBagConstrainsts used to set the layout of the buttonpanel and resumeButton
         * buttonPanel JPanel containing the resume button
         * resumeButton JButton used to resume the game
         * exitListener ActionListener to be added to exit button
         * exitImage Image to be added to the exitButton
         * gbc GridBagConstrainsts used to set the layout of the  exitButton
         * buttonPanel JPanel containing the exit button
         * exitButton JButton used to exit the game
         * exitButton has the same properties as resumeButtons except for the image and the listener and it is positioned underneath the resumeButton
         */

        // creates a new JLabel and add an ImageIcon with the background image
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        add(backgroundLabel, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // makes the panel transparent
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // makes the panel background transparent

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // adds some spacing between the buttons
        gbc.anchor = GridBagConstraints.CENTER;

        resumeButton = new JButton(new ImageIcon(startImage));
        resumeButton.addActionListener(resumeListener);
        resumeButton.setPreferredSize(new Dimension(100, 50));
        resumeButton.setBorder(null); // removes the button border
        resumeButton.setFocusPainted(false); // removes the focus paint
        resumeButton.setContentAreaFilled(false); // makes the content area painted directly on top of the button background
        resumeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                resumeButton.setBorder(new LineBorder(Color.BLUE, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeButton.setBorder(null);
            }
        });
        buttonPanel.add(resumeButton, gbc);

        gbc.gridy++;
        exitButton = new JButton(new ImageIcon(exitImage));
        exitButton.addActionListener(exitListener);
        exitButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setBorder(null); // remove the button border
        exitButton.setFocusPainted(false); // remove the focus paint
        exitButton.setContentAreaFilled(false); // make the content area painted directly on top of the button background
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBorder(new LineBorder(Color.BLUE, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBorder(null);
            }
        });
        buttonPanel.add(exitButton, gbc);

        /**
         * Sets the backgroundLabel constraints and adds the button panel to it
         * Sets the layout of the backgroundLabel
         * Resizes the resume and exit buttons to correct dimensions
         * Displays the button panel and refreshes it
         */

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.CENTER;

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(buttonPanel, gbc);

        
        resumeButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setPreferredSize(new Dimension(100, 50));

        buttonPanel.setVisible(true); // force display the panel

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    /**
     * resizes the BufferedImage to the specified width and height
     * This is 
     * @param img image to be resized
     * @param width the specific width
     * @param height the specific height
     * @return the resized image
     */
    private static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    /**
     * gets the preferred size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
