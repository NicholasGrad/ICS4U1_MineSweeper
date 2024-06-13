package finalproject;

import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class GameBoard {

    int tileSize;
    int numRows;
    int numCols;
    int numMines;
    int fontSize;
    int numClicks;
    boolean minesSet = false;
    Tile[][] board;
    JFrame frmGame = new JFrame("MineSweeper");
    JLabel lblTitle = new JLabel();
    JLabel lblScore = new JLabel();
    JPanel pnlTop = new JPanel();
    JPanel pnlGrid = new JPanel();

    public GameBoard(int rows, int columns, int mines, int size, int font) {
        tileSize = size;
        numRows = rows;
        numCols = columns;
        numMines = mines;
        fontSize = font;
        board = new Tile[numRows][numCols];
        int boardWidth = numCols * tileSize;
        int boardHeight = numRows * tileSize;

        frmGame.setSize(boardWidth, boardHeight);
        frmGame.setLocationRelativeTo(null);
        frmGame.setResizable(false);
        frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGame.setLayout(new BorderLayout());

        lblTitle.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblTitle.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblTitle.setHorizontalAlignment(JLabel.LEFT);
        lblTitle.setText("MINESWEEPER");
        lblTitle.setOpaque(true);

        lblScore.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblScore.setBorder(new EmptyBorder(0, 0, 0, 10));
        lblScore.setHorizontalAlignment(JLabel.RIGHT);
        lblScore.setText("SCORE: 0");
        lblScore.setOpaque(true);

        pnlTop.setLayout(new BorderLayout());
        pnlTop.add(lblTitle, BorderLayout.WEST);
        pnlTop.add(lblScore, BorderLayout.EAST);
        frmGame.add(pnlTop, BorderLayout.NORTH);

        pnlGrid.setLayout(new GridLayout(numRows, numCols));
        pnlGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlGrid.setBackground(Color.decode("#415E84"));
        frmGame.add(pnlGrid);

        frmGame.setVisible(true);

        createGrid(board);
    }

    private void createGrid(Tile[][] board) {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                Tile tile = new Tile(r, c, this);
                board[r][c] = tile;
                pnlGrid.add(tile);
            }
        }
    }

    public void handleFirstClick(Tile clickedTile) {
        if (!minesSet) {
            placeMines(board, clickedTile);
            minesSet = true;
        }
        revealTile(clickedTile.r, clickedTile.c);
    }

    private void placeMines(Tile[][] board, Tile firstClickedTile) {
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int mineRow = (int) (Math.random() * numRows);
            int mineCol = (int) (Math.random() * numCols);

            if ((mineRow != firstClickedTile.r || mineCol != firstClickedTile.c) && !board[mineRow][mineCol].m) {
                board[mineRow][mineCol].setMine(true);
                minesPlaced++;
            }
        }
    }

    public void revealTile(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || board[row][col].revealed) {
            return;
        }
        board[row][col].revealed = true;
        board[row][col].setEnabled(false);
        int mineCount = countMinesAround(row, col);

        if (mineCount > 0) {
            board[row][col].setText(String.valueOf(mineCount));
        } else {
            board[row][col].setText("");
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = col - 1; c <= col + 1; c++) {
                    if (r != row || c != col) {
                        revealTile(r, c);
                    }
                }
            }
        }

        numClicks++;
        lblScore.setText("SCORE: " + numClicks);
    }

    private int countMinesAround(int row, int col) {
        int mineCount = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < numRows && c >= 0 && c < numCols && board[r][c].m) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    public void gameOver() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                board[r][c].setEnabled(false);
                if (board[r][c].m) {
                    board[r][c].setText("X");
                }
            }
        }
        JOptionPane.showMessageDialog(frmGame, "Game Over! Your score is: " + numClicks);
    }
}
