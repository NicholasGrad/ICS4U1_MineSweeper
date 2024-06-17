package finalproject;

// import action and JFrame libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class Tile extends JButton {

    int r, c;
    boolean flagged;
    boolean m;
    boolean revealed;
    GameBoard GameBoardInstance;

    public Tile(int r, int c, GameBoard GameBoardInstance) {
        this.r = r;
        this.c = c;
        this.GameBoardInstance = GameBoardInstance;
        this.revealed = false;

        

        // Add mouse listener to handle left and right clicks
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    handleLeftClick();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    handleRightClick();
                }
            }
        });
    }

    public void setMine(boolean m) {
        // set mine
        this.m = m;
    }

    private void handleLeftClick() {
        
        if (this.isEnabled() && !revealed) {
            if (!GameBoardInstance.minesSet) {
                GameBoardInstance.handleFirstClick(this);
            }
            if (flagged) {
                return;
            } else if (m) {
                // Load bomb image from file
                ImageIcon bombIcon = new ImageIcon("src\\finalproject\\bomb.png");

                // set the bomb image
                setIcon(bombIcon);
                
                setForeground(Color.red);
                GameBoardInstance.gameLost();
            } else {
                GameBoardInstance.revealTile(r, c);
            }
        }
    }

    private void handleRightClick() {
        if (this.isEnabled() && !revealed) {
            if (flagged) {
                setText("");
                flagged = false;
                GameBoardInstance.flagBeforeGen = false;
            } else {
                // Load flag image from file (adjust the path as needed)
                ImageIcon flagIcon = new ImageIcon("src\\finalproject\\flag.png");

                // Set the bomb image as the icon
                setIcon(flagIcon);
                
                flagged = true;
                GameBoardInstance.flagBeforeGen = true;
            }
        }
    }
}