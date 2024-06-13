/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author nicho
 */
public class Tile extends JButton {

    //declare variables for row and column indexes
    int r;
    int c;
    
    boolean flagged;
    //declare variable for if mine is at tile
    boolean m;
    
    JLabel score;
    GameBoard GameBoardInstance; // Reference to GameBoard class instance
    
    //create a tile object that stores a row and column position
    public Tile(int r, int c,GameBoard GameBoardInstance) {
        this.r = r;
        this.c = c;

        setBackground(Color.black);
        setForeground(Color.white);
        
        this.GameBoardInstance = GameBoardInstance; // Assign GameBoard class instance
        this.score = new JLabel();
        this.add(score);

        this.setLayout(new BorderLayout());
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setVerticalAlignment(JLabel.CENTER);
        
        
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
        //create a mine in this tile
        this.m = m;
    }

    public void handleLeftClick() {

        if (this.isEnabled()) {
            // Define what happens on left click
            if (flagged) {

            } else if (m) {
                setText("X"); // Example action: mark as mine
                this.setEnabled(false);
            } else {
                setText("O"); // Example action: mark as safe
                GameBoardInstance.numClicks++;
                GameBoardInstance.lblScore.setText("SCORE: " + GameBoardInstance.numClicks);
                this.setEnabled(false);
            }
        }
    }

    public void handleRightClick() {
        if (this.isEnabled()) {
            // Define what happens on right click
            if (flagged) {
                setText("");
                flagged = false;
            } else {
                setText("F"); // Example action: mark as flagged
                flagged = true;
            }
        }
    }
}
