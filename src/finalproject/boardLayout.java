package finalproject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class boardLayout {

    //declare variables for the size of each tile, and the number of rows and columns in the grid
    int tileSize;
    int numRows;
    int numCols;

    int numMines;
    
    //
    private class Tile extends JButton {

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
        public void setMine(boolean m){
            this.m = m;
        }
        
        private void handleLeftClick() {
            // Define what happens on left click
            if (flagged){
                
            }
            else if (m) {
                setText("X"); // Example action: mark as mine
            } else {
                setText("O"); // Example action: mark as safe
            }
        }

        private void handleRightClick() {
            // Define what happens on right click
            if(flagged){
                setText("");
                flagged = false;
            }
            else{    
                setText("F"); // Example action: mark as flagged
                flagged = true;
            }
        }
    }

    JFrame frame = new JFrame("Board"); //create a JFrame for the main boardgame
    JLabel textLabel = new JLabel();    //
    JPanel textPanel = new JPanel();    //
    JPanel boardPanel = new JPanel();   //

    //create JFrame elements and add to the frame
    public boardLayout(int rows, int columns, int mines, int size) {
        //set tile size and number of rows nd columns based on arguments
        tileSize = size;
        numRows = rows;
        numCols = columns;
        numMines = mines;

        //
        int boardWidth = numCols * tileSize;
        int boardHeight = numRows * tileSize;

        //
        Tile[][] board = new Tile[numRows][numCols];

        //initialize frame settings
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //create a title label
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("MINESWEEPER");
        textLabel.setOpaque(true);

        //create a text panel for the title label
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        //create a grid panel
        boardPanel.setLayout(new GridLayout(numRows, numCols));
        frame.add(boardPanel);

        //for loop to create a grid of button tiles based on the number of rows and columns
        for (int r = 0; r < numRows; r++) { //iterate through each row
            for (int c = 0; c < numCols; c++) { //iterate through each column
                //create a tile object based on the current index
                Tile tile = new Tile(r, c);
                //add the tile object to the 2d board array
                board[r][c] = tile;

                //
                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));

                boardPanel.add(tile);

            }
        }
        frame.setVisible(true);
 
        
        
        //place mines
        for (int i = 0; i < numMines; i++){
            //
            int mineRow = (int)(Math.round(Math.random()*(numRows-1)));
            int mineCol = (int)(Math.round(Math.random()*(numCols-1)));
            
            
            
            
            System.out.println(mineRow + " ");
            System.out.println(mineCol + "\n");
        }
    }

}
