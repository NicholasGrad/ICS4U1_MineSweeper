package finalproject;

// import action and JFrame libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 * This class creates tile objects to hold information of each button object in
 * the GameBoard grid
 */
public class Tile extends JButton {

    // declare variables of this tile
    int r, c;   // row and column indexes
    boolean flagged;    // whether tile has a flag
    boolean m;  // whether tile has a mine
    boolean revealed;   // whether tile has been revealed

    // declare an instance of the GameBoard class
    GameBoard GameBoardInstance;

    /**
     * Object constructor for a button tile at a specified location on the game
     * grid
     *
     * @param r
     * @param c
     * @param GameBoardInstance
     */
    public Tile(int r, int c, GameBoard GameBoardInstance) {
        // initialize tile properties
        this.r = r;
        this.c = c;
        this.GameBoardInstance = GameBoardInstance;
        this.revealed = false;

        // Add mouse listener to handle left and right clicks
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // if left-clicked
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // run left-click method
                    handleLeftClick();
                } // if right-clicked
                else if (SwingUtilities.isRightMouseButton(e)) {
                    // run right-click method
                    handleRightClick();
                }
            }
        });
    }

    /**
     * This method sets a mine at this tile
     *
     * @param m
     */
    public void setMine(boolean m) {
        // set mine
        this.m = m;
    }

    /**
     * This method runs when tile is left-clicked
     */
    private void handleLeftClick() {
        // if this tile has not been clicked
        if (this.isEnabled() && !revealed) {
            // if this is the first tile to be clicked
            if (!GameBoardInstance.minesSet) {
                // run first click method in GameBoard
                GameBoardInstance.handleFirstClick(this);
            }
            // if this tile is flagged
            if (flagged) {
                // do nothing
                return;
            } // if this tile has a mine
            else if (m) {
                // Load bomb image from file
                ImageIcon bombIcon = new ImageIcon("src\\finalproject\\bomb.png");

                // set the bomb image
                setIcon(bombIcon);

                // run game lost method in GameBoard
                GameBoardInstance.gameLost();
            } // if this tile has no mine or flag
            else {
                // run reveal tile method in GameBoard
                GameBoardInstance.revealTile(r, c);
            }
        }
    }

    /**
     * This method runs when tile is right-clicked
     */
    private void handleRightClick() {
        // if this tile has not been clicked
        if (this.isEnabled() && !revealed) {
            // if this tile is already flagged
            if (flagged) {
                // remove flag
                setText("");
                flagged = false;

                // no flag on first clicked tile
                GameBoardInstance.flagBeforeGen = false;
            } // if tile is not flagged
            else {
                // Load flag image from file (adjust the path as needed)
                ImageIcon flagIcon = new ImageIcon("src\\finalproject\\flag.png");

                // Set the bomb image as the icon
                setIcon(flagIcon);

                // add flag
                flagged = true;

                // flag on first clicked tile
                GameBoardInstance.flagBeforeGen = true;
            }
        }
    }
}
