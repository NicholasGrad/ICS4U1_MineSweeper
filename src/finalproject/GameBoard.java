package finalproject;

// import JFrame libraries
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

/**
 * This class creates a GameBoard object which creates a gui for the Minesweeper
 * game as well as a grid of button tile objects
 */
public class GameBoard {

    // declare properties of the game
    int tileSize;   // size of each tile
    int numRows;    // number of rows
    int numCols;    // number of columns 
    int numMines;   // number of mines
    int fontSize;   // size of tile text

    // tracks number of tiles that do not contain mines
    int emptySpaces;

    // declare and initialize variables used before first tile click
    boolean minesSet = false;   // mines start as not set
    boolean flagBeforeGen;  // variable for if there is a flag placed on the first clicked tile

    // declare and initialize the number of correctly done tiles
    int score;

    // declare a 2D array for locations of tiles on the grid
    Tile[][] board;

    // declare and initialize JFrame elements
    JFrame frmGame = new JFrame("MineSweeper"); // frame
    JLabel lblTitle = new JLabel(); // title
    JLabel lblTime = new JLabel();  // time
    JPanel pnlTop = new JPanel();   // panel for top labels
    JPanel pnlGrid = new JPanel();  // main grid

    // Timer variables
    private Timer timer;
    private int elapsedTime = 0;

    /**
     * Create a new JFrame
     * @param rows
     * @param columns
     * @param mines
     * @param size
     * @param font 
     */
    public GameBoard(int rows, int columns, int mines, int size, int font) {
        // initialize properties of the board (based on arguments received from frmTitleScreen)   
        tileSize = size;
        numRows = rows;
        numCols = columns;
        numMines = mines;
        fontSize = font;
        
        // calculate the width and height of the board
        int boardWidth = numCols * tileSize;
        int boardHeight = numRows * tileSize;
        
        // calculate the number of spaces that do not contain mines
        emptySpaces = (numCols * numRows) - numMines;
        
        // create a new 2D array of Tile objects
        board = new Tile[numRows][numCols];
        
        // initialize properties of the JFrame elements
        frmGame.setSize(boardWidth, boardHeight);   // frame
        frmGame.setLocationRelativeTo(null);
        frmGame.setResizable(false);
        frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGame.setLayout(new BorderLayout());
        frmGame.setVisible(true);

        lblTitle.setFont(new Font("Monospaced", Font.BOLD, 25));    // title label
        lblTitle.setBackground(Color.decode("#0000FF"));
        lblTitle.setForeground(Color.white);
        lblTitle.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblTitle.setHorizontalAlignment(JLabel.LEFT);
        lblTitle.setText("MINESWEEPER");
        lblTitle.setOpaque(true);

        lblTime.setFont(new Font("Monospaced", Font.BOLD, 25)); //  time label
        lblTime.setBackground(Color.decode("#0000FF"));
        lblTime.setForeground(Color.white);
        lblTime.setBorder(new EmptyBorder(0, 0, 0, 10));
        lblTime.setHorizontalAlignment(JLabel.RIGHT);
        lblTime.setText("TIME: 0");
        lblTime.setOpaque(true);

        pnlTop.setLayout(new BorderLayout());   //panel for top labels
        pnlTop.setBackground(Color.decode("#0000FF"));
        pnlTop.add(lblTitle, BorderLayout.WEST);
        pnlTop.add(lblTime, BorderLayout.EAST);
        frmGame.add(pnlTop, BorderLayout.NORTH);

        pnlGrid.setLayout(new GridLayout(numRows, numCols));    // main grid
        pnlGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlGrid.setBackground(Color.decode("#0000FF"));
        frmGame.add(pnlGrid);

        // make the game board screen visible
        frmGame.setVisible(true);

        // create the grid of button objects
        createGrid(board);

        // Initialize and start the timer
        initTimer();
    }

    /**
     * This method loops through the entire game grid,
     * creating a button tile at each index
     * @param board 
     */
    private void createGrid(Tile[][] board) {
        // loop through each row
        for (int r = 0; r < numRows; r++) {
            // loop through each column
            for (int c = 0; c < numCols; c++) {
                // create a new tile object at this location
                Tile tile = new Tile(r, c, this);
                
                // add this object to the 2D "board" array
                board[r][c] = tile;
                
                // set the visual properties of the tile
                tile.setFont(new Font("Monospaced", Font.BOLD, fontSize));
                tile.setBackground(Color.white);
                tile.setFocusPainted(false);

                // add this button object to the grid
                pnlGrid.add(tile);
            }
        }
    }

    /**
     * This method is run when mines are not yet
     * set (before first left click). It sets
     * the mines.
     * @param clickedTile 
     */
    public void handleFirstClick(Tile clickedTile) {
        // if mines are not set and there is no flag on the clicked tile
        if (!minesSet && !flagBeforeGen) {
            // generate and set the mines
            placeMines(board, clickedTile);
            
            // set mines as placed
            minesSet = true;
            
            // run the revealing tile method
            revealTile(clickedTile.r, clickedTile.c);
        }
    }
    
    /**
     * This method randomly generates mines
     * and places them on the grid
     * @param board
     * @param firstClickedTile 
     */
    private void placeMines(Tile[][] board, Tile firstClickedTile) {
        // declare and initialize number of placed mines as zero
        int minesPlaced = 0;
        
        // while the number of set mines is less than the number of total mines 
        while (minesPlaced < numMines) {
            // generate a random mine location on the grid
            int mineRow = (int) (Math.random() * numRows);
            int mineCol = (int) (Math.random() * numCols);

            // if the generated location is not the originally clicked tile nor a tile with a mine, place a mine
            if ((mineRow != firstClickedTile.r || mineCol != firstClickedTile.c) && !board[mineRow][mineCol].m) {
                // set a mine in the tile object at the generated location
                board[mineRow][mineCol].setMine(true);
                
                //increase the number of placed mines by one
                minesPlaced++;
            }
        }
    }

    /**
     * This recursive method checks the surrounding tiles of the given row and column
     * coordinates to reveal them if they are not already revealed and do not contain a mine.
     * If the tile has no surrounding mines, it recursively reveals its surrounding tiles.
     * @param row
     * @param col 
     */
    public void revealTile(int row, int col) {
        // base case for if the clicked tile is outside the grid or already revealed
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || board[row][col].revealed) {
            return;
        }
        // set as revealed and disable the button tile
        board[row][col].revealed = true;
        board[row][col].setEnabled(false);
        
        // count the number of mines around this tile
        int mineCount = countMinesAround(row, col);

        // if there are surrounding mines
        if (mineCount > 0) {
            // set the text of the tile to the number of surrounding mines
            board[row][col].setText(String.valueOf(mineCount));
        }
        // if there are no surrounding mines
        else {
            // set text of the tile as empty
            board[row][col].setText("");
            
            // Iterate through the surrounding tiles
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = col - 1; c <= col + 1; c++) {
                    // skip the current tile
                    if (r != row || c != col) {
                        // recursively call this method for each surrounding tile
                        revealTile(r, c);
                    }
                }
            }
        }

        // increase the number of correct tiles
        score++;

        // Check if the game is won
        if (score == emptySpaces) {
            gameWon();
        }
    }

    /**
     * This method counts the number of mines
     * surrounding a tile
     * @param row
     * @param col
     * @return 
     */
    private int countMinesAround(int row, int col) {
        // declare and initialize number of mines as zero
        int mineCount = 0;
        
        // iterate through the surrounding tiles 
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                // if a surrounding tile has a mine
                if (r >= 0 && r < numRows && c >= 0 && c < numCols && board[r][c].m) {
                    //increase number of mines counted
                    mineCount++;
                }
            }
        }
        
        // return the number of surrounding mines
        return mineCount;
    }

    /**
     * This method is run when the number of correctly revealed
     * tiles is equal to the number of empty tiles
     */
    public void gameWon() {
        // Stop the timer
        timer.stop();

        // create a new end screen, passing the current game's parameters
        new frmGameEndScreen(tileSize, numRows, numCols, numMines, fontSize, this, elapsedTime).setVisible(true);
        
        // display a popup box that the user won
        JOptionPane.showMessageDialog(frmGame, "All Mines Cleared!");
    }

    /**
     * This method is run when a tile containing a mine is
     * clicked. It reveals all mine locations.
     */
    public void gameLost() {
        // Stop the timer
        timer.stop();

        // disable all tiles and show all mines
        // iterate through each row of the grid
        for (int r = 0; r < numRows; r++) {
            // iterate through each column of the grid
            for (int c = 0; c < numCols; c++) {
                // disable the tiles
                board[r][c].setEnabled(false);
                
                // if there is a mine, set bomb image
                if (board[r][c].m) {
                
                // load bomb image from file
                ImageIcon bombIcon = new ImageIcon("src\\finalproject\\bomb.png");
                  
                // set bombs
                board[r][c].setIcon(bombIcon);
                }
            }
        }
        // create a new end screen, passing the current game's parameters
        new frmGameEndScreen(tileSize, numRows, numCols, numMines, fontSize, this, elapsedTime).setVisible(true);
        
        // display a popup box that the user lost
        JOptionPane.showMessageDialog(frmGame, "Mine Exploded!");
    }

    /**
     * Initialize and start the timer
     */
    private void initTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                lblTime.setText("TIME: " + elapsedTime);
            }
        });
        // start timer
        timer.start();
    }
}
