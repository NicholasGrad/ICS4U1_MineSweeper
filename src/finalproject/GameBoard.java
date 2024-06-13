package finalproject;

//import 
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class GameBoard {

    // declare game variables
    int tileSize;   // size of each tile
    int numRows;    // number of rows
    int numCols;    // number of columns
    int numMines;   // number of mines

    //track number of successful clicks
    int numClicks;

    
    // declare and initialize a variable that tracks if mines have been generated
    boolean minesSet = false;

    // declare a two-dimensional array of "Tile" objects
    Tile board[][];

    // create JFrame and JFrame elements for the game grid
    JFrame frmGame = new JFrame("MineSweeper"); // create JFrame
    JLabel lblTitle = new JLabel(); // create title label
    JLabel lblScore = new JLabel(); // create score label
    JPanel pnlTop = new JPanel(); // create panel for elements at the top of the window
    JPanel pnlGrid = new JPanel();  // create panel for the grid board

    // create JFrame elements and add to the frame
    public GameBoard(int rows, int columns, int mines, int size) {
        // initialize game variables based on choosen mode
        tileSize = size;
        numRows = rows;
        numCols = columns;
        numMines = mines;

        // 
        board = new Tile[numRows][numCols];

        // declare and initialize the overall width and height of the board
        int boardWidth = numCols * tileSize;
        int boardHeight = numRows * tileSize;

        //initialize frame settings
        frmGame.setSize(boardWidth, boardHeight);
        frmGame.setLocationRelativeTo(null);
        frmGame.setResizable(false);
        frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGame.setLayout(new BorderLayout());

        //create a title label
        lblTitle.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblTitle.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblTitle.setHorizontalAlignment(JLabel.LEFT);
        lblTitle.setText("MINESWEEPER");
        lblTitle.setOpaque(true);

        //create a score label
        lblScore.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblScore.setBorder(new EmptyBorder(0, 0, 0, 10));
        lblScore.setHorizontalAlignment(JLabel.RIGHT);
        lblScore.setText("SCORE: 0");
        lblScore.setOpaque(true);
        
        //create a text panel for the top labels
        pnlTop.setLayout(new BorderLayout());
        pnlTop.add(lblTitle, BorderLayout.WEST);
        pnlTop.add(lblScore, BorderLayout.EAST);
        frmGame.add(pnlTop, BorderLayout.NORTH);

        //create a grid panel
        pnlGrid.setLayout(new GridLayout(numRows, numCols));
        pnlGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlGrid.setBackground(Color.decode("#415E84"));
        frmGame.add(pnlGrid);
        

        frmGame.setVisible(true);

        createGrid(board);

        //placeMines(board);
        //place mines after first click
    }

    public void createGrid(Tile[][] board) {
        //for loop to create a grid of button tiles based on the number of rows and columns
        for (int r = 0; r < numRows; r++) { //iterate through each row
            for (int c = 0; c < numCols; c++) { //iterate through each column
                //create a tile object based on the current index
                Tile tile = new Tile(r, c, this);
                //add the tile object to the 2d board array
                board[r][c] = tile;

                //
                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));

                pnlGrid.add(tile);

                // Add action listener to handle first click to place mines
                tile.addActionListener(e -> handleFirstClick(tile));
            }
        }
    }

    private void handleFirstClick(Tile clickedTile) {

        if (!minesSet) {
            placeMines(board, clickedTile);
            minesSet = true;
        }
        clickedTile.handleLeftClick();
    }

    public void placeMines(Tile[][] board, Tile firstClickedTile) {
        int minesPlaced = 0;
        //place mines
        while (minesPlaced < numMines) {
            int mineRow;
            int mineCol;

            mineRow = (int) (Math.round(Math.random() * (numRows - 1)));
            mineCol = (int) (Math.round(Math.random() * (numCols - 1)));

            System.out.println(mineRow + " ");
            System.out.println(mineCol + "\n");

            //check if there is already a mine in the tile at the generated mine location
            if ((mineRow != firstClickedTile.r || mineCol != firstClickedTile.c) && !board[mineRow][mineCol].m) {
                board[mineRow][mineCol].setMine(true);
                minesPlaced++;
            }
        }
    }
}
