/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
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
    
    //create a tile object that stores a row and column position
    public Tile(int r, int c) {
        this.r = r;
        this.c = c;
        
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
        // Define what happens on left click
        if(flagged) {

        } else if (m) {
            setText("X"); // Example action: mark as mine
            this.setEnabled(false);
        } else {
            setText("O"); // Example action: mark as safe
            this.setEnabled(false);
        }
    }

    private void handleRightClick() {
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
