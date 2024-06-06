/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class boardLayout {
    
    int tileSize = 100;
    int numRows;
    int numCols;
    
    int numMines;
    
    private class MineTile extends JButton {
        int r;
        int c;

        public MineTile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    JFrame frame = new JFrame("Board"); //create a JFrame for the main boardgame
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    //create JFrame elements and add to the frame
    public boardLayout(int rows, int columns, int mines){
        
        numRows = rows;
        numCols = columns;
        numMines = mines;
        
        //
        int boardWidth = numCols * tileSize;
        int boardHeight = numRows * tileSize;
        
        //
        MineTile[][] board = new MineTile[numRows][numCols];
        
        //initialize frame settings
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //create a title label
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Minesweeper");
        textLabel.setOpaque(true);
        
        //create a text panel for the title label
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        //create a grid panel
        boardPanel.setLayout(new GridLayout(numRows, numCols));
        frame.add(boardPanel);
        
        //for loop to create a grid of button tiles based on number of rows and coloumns
        for (int r = 0; r < numRows; r++) { //iterate through each row
            for (int c = 0; c < numCols; c++) { //iterate through each column
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));

                boardPanel.add(tile);
                
            }
        }
        frame.setVisible(true);
    }

}
