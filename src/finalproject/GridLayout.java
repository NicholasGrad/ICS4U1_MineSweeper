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
public class GridLayout {
    JFrame frame = new JFrame(); //creates frame
    JButton[][] grid; //names the grid of button
    
    public GridLayout(int width, int length){ //constructor
        frame.setLayout(new java.awt.GridLayout(width,length)); //set layout
        grid=new JButton[width][length]; //allocate the size of grid
        for(int y=0; y<length; y++){
            for(int x=0; x<width; x++){
                    grid[x][y]=new JButton(""); //creates new button	  
                    frame.add(grid[x][y]); //adds button to grid
            }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setVisible(true); //makes frame visible
    }
}
