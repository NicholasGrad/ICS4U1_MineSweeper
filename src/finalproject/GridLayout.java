/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author nicho
 */
public class GridLayout extends javax.swing.JFrame{
    JFrame gridFrame = new JFrame(); //creates frame
    JButton[][] grid; //names the grid of button
    
    public GridLayout(int width, int length){ //constructor
        gridFrame.setExtendedState(MAXIMIZED_BOTH);
        gridFrame.setLayout(new java.awt.GridLayout(width,length)); //set layout
        grid=new JButton[width][length]; //allocate the size of grid
        for(int y=0; y<length; y++){
            for(int x=0; x<width; x++){
                    grid[x][y]=new JButton(""); //creates new button	  
                    gridFrame.add(grid[x][y]); //adds button to grid
            }
        }
        gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridFrame.pack(); //sets appropriate size for frame
        gridFrame.setVisible(true); //makes frame visible
    }
    
}
