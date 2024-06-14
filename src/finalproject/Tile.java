package finalproject;

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

        this.setFont(new Font("Arial Unicode MS", Font.PLAIN, GameBoardInstance.fontSize));
        this.setBackground(Color.decode("#163757"));
        this.setFocusPainted(false);

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
                setText("X");
                setBackground(Color.red);
                GameBoardInstance.gameOver();
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
                setText("F");
                flagged = true;
                GameBoardInstance.flagBeforeGen = true;
            }
        }
    }
}
