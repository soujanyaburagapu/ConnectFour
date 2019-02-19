import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ConnectFour: Two-player console, graphics.
 * All variables/methods are declared as static (belong to the class).
 */

@SuppressWarnings("serial")
public class ConnectPanel extends JFrame{

    // Name-constants to represent the seeds and cell contents
    public static final int EMPTY = 0;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    // Name-constants to represent the various states of the game
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int PLAYER1_WON = 2;
    public static final int PLAYER2_WON = 3;

    // The game board and the game status
    public static int currentState = 1;
// the current state of the game, set initially equal to 1 (to start a new game)

    public static int currentPlayer;
    // the current player (PLAYER1 or PLAYER2)
    public static int currentRow, currentCol;
    // current seed's row and column
    public static int Rows, Columns, AmountToWin;
    public static int scorePlayer1, scorePlayer2, oldScorePlayer1, oldScorePlayer2;
    public static int content;
    public static int theSeed;
    public static String playAgain;
    public static int[][] board;
    public static Scanner input = new Scanner(System.in); // the input Scanner

    public static final Color black = Color.BLACK;
    public static final Color red = Color.RED;
    public static final Color blue = Color.BLUE;

    public static Player player1 = new Player();
    public static Player player2 = new Player();
    public static Chip chip1 = new Chip();
    public static Chip chip2 = new Chip();

    public static int playAgain(){
        boolean validInput = false;
        do {
            System.out.println(
                    "Would you like to play a game? Please answer 'yes' or 'no'.");
            String playAgain = input.next();

            if ("yes".equals(playAgain) || "no".equals(playAgain)) {
                if ("no".equals(playAgain)) {
                    return 0;
                } if ("yes".equals(playAgain)) {
                    return 1;
                } validInput = true;
            } else {
                System.out.println(
                        "Sorry, your answer is not understood.");
            }
        } while (!validInput); // repeat until input is valid
        return 1;
    }

    public static void main(String[] args) {
        // Initialize the game-board and current status
        while ( currentState != PLAYING){
// after finishing a game, this makes it possible starting a new one
            if (playAgain() == 1){
                System.out.println("Player 1, please enter your name: ");
                String namePlayer1 = input.next();

                System.out.println("Player 2, please enter your name: ");
                String namePlayer2 = input.next();

                player1.setName(namePlayer1);
                player2.setName(namePlayer2);

                boolean validInput = false;
                do {
                    System.out.println( player1.namePlayer +
                            ", please enter the desired color of your chips in lower case letters: ");
                    String color1 = input.next();
                    chip2.setColor(color1);

                    if ("black".equals(color1) || "grey".equals(color1) ||
                            "white".equals(color1) || "red".equals(color1) ||
                            "orange".equals(color1) || "yellow".equals(color1) ||
                            "green".equals(color1) || "blue".equals(color1) ||
                            "purple".equals(color1) || "brown".equals(color1) || "pink".equals(color1)) {

                        do {
                            System.out.println( player2.namePlayer +
                                    ", please enter the desired color of your chips in lower case letters: ");
                            String color2 = input.next();
                            chip2.setColor(color2);

                            if ("black".equals(color2) || "grey".equals(color2) ||
                                    "white".equals(color2) || "red".equals(color2) ||
                                    "orange".equals(color2) || "yellow".equals(color2) ||
                                    "green".equals(color2) || "blue".equals(color2) ||
                                    "purple".equals(color2) || "brown".equals(color2) ||
                                    "pink".equals(color2)) {
                                validInput = true;

                                if(color1.equals(color2)){

                                    if(color1.equals("red")){
                                        System.out.println(player2.namePlayer +
                                                ", your chosen colors are the same, therefore you will play with the color blue.");
                                    } else {
                                        System.out.println(player2.namePlayer +
                                                ", your chosen colors are the same, therefore you will play with the color red.");
                                    }
                                }
                            } else {
                                System.out.println("Sorry "
                                        + player2.namePlayer + ", your color is not recognized. Please try again.");
                            }
                        } while (!validInput); // repeat until input is valid

                    } else {
                        System.out.println("Sorry "
                                + player1.namePlayer + ", your color is not recognized. Please try again.");
                    }

                } while (!validInput); // repeat until input is valid

                validInput = false;

                do {
                    System.out.println(
                            "Enter the amount of rows of the playing field in integers: ");
                    int Rows1 = input.nextInt() ;

                    if (Rows1 > 0) {

                        do {
                            System.out.println(
                                    "Enter the amount of columns of the playing field in integers: ");
                            int Columns1 = input.nextInt();

                            if (Columns1 > 0) {

                                do {
                                    System.out.println(
                                            "Please enter what amount of chips in a row a player needs to win the game: ");
                                    int AmountToWin1 = input.nextInt();

                                    if (Rows1 >= AmountToWin1 || Columns1 >= AmountToWin1) {
                                        // checks if the conditions are valid for this amount of chips

                                        Rows = Rows1;
                                        Columns = Columns1;
                                        AmountToWin = AmountToWin1;
                                        board = new int[Rows][Columns]; // game board in 2D array
                                        content = 1;

                                        ConnectPanel frame = new ConnectPanel(theSeed, currentRow, currentCol);
                                        frame.setSize(100*Columns, 100*Rows);
                                        frame.setLocationRelativeTo(null);
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.setVisible(true);

                                        validInput = true;
                                        // input okay, exit loop
                                    } else {
                                        System.out.println(
                                                "The chosen amount of chips is not valid regarding the size of the playing field.");
                                    }

                                } while (!validInput); // repeat until input is valid

                            } else {
                                System.out.println(
                                        "The desired amount of columns is not compatible in this game.");
                            }

                        } while (!validInput);  // repeat until input is valid

                    } else {
                        System.out.println(
                                "The desired amount of rows is not compatible in this game.");
                    }

                } while (!validInput); // repeat until input is valid



                initGame(Rows, Columns, board);
                // Play the game once
                do {
                    playerMove(currentPlayer, Rows, Columns, board, AmountToWin);
                    // update currentRow and currentCol
                    updateGame(
                            currentPlayer, currentRow, currentCol, Rows, Columns, board, AmountToWin);
                    // update currentState


                    // Print message if game-over
                    if (currentState == PLAYER1_WON) {
                        System.out.println(player1.namePlayer
                                + " wins the game by " + scorePlayer1 + "-" + scorePlayer2 +
                                "! Thank you for playing!");
                    } if (currentState == PLAYER2_WON) {
                        System.out.println(player2.namePlayer
                                + " wins the game by " + scorePlayer1 + "-" + scorePlayer2 +
                                "! Thank you for playing!");
                    } if (currentState == DRAW) {
                        System.out.println("It is a draw! " + player1.namePlayer +
                                " and " + player2.namePlayer + " played " + scorePlayer1 +
                                "-" + scorePlayer2 + ". Thank you for playing!");
                    }
                    currentPlayer = (currentPlayer == PLAYER1) ? PLAYER2 : PLAYER1; // Switch player
                } while (currentState == PLAYING); // repeat if not game-over
            } else {
                System.out.println("That's a shame, but a good day to you!");
                currentState = 0;
            }
        }
    }

    public static void initGame(int Rows, int Columns, int[][] board) {
        for (int row = 0; row < Rows; ++row) {
            for (int col = 0; col < Columns; ++col) {
                board[row][col] = 0;  // all cells empty
            }
        }
        currentState = PLAYING; // ready to play
        currentPlayer = 1;  // player 1 plays first
    }

    public static void playerMove(
            int theSeed, int Rows, int Columns, int[][] board, int AmountToWin) {
        boolean validInput = false;  // for input validation
        do {
            if (theSeed == 1) {
                System.out.println(player1.namePlayer +
                        ", enter the column in which you would like to place your chip (1-" + Columns + "): ");

            } else {
                System.out.println(player2.namePlayer +
                        ", enter the column in which you would like to place your chip (1-" + Columns + "): ");
            }

            int inputColumn = input.nextInt() - 1;

            if(inputColumn >= 0 && inputColumn < Columns){
                int col = inputColumn;
                int row = availableRow(col, Rows, board); //finds the first empty row by corresponding column
                currentRow = row;
                if(row != -1){ // when availableRow returns -1, the chosen move is invalid

                    if (col >= 0 && col < Columns && board[row][col] == EMPTY) {
                        currentCol = col;
                        board[row][currentCol] = theSeed;  // update game-board content

                        ConnectPanel frame = new ConnectPanel(theSeed, currentRow, currentCol);
                        frame.setSize(100*Columns, 100*Rows);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);

                        validInput = true;  // input okay, exit loop

                    } else {
                        System.out.println(
                                "Sorry, the size of the playing field is insufficient to accept this move. Please try again.");
                    }

                } else {
                    System.out.println(
                            "Sorry, the size of the playing field is insufficient to accept this move. Please try again.");
                }

            } else {
                System.out.println(
                        "Sorry, the size of the playing field is insufficient to accept this move. Please try again.");
            }

        } while (!validInput);  // repeat until input is valid
    }

    /** Update the "currentState" after the player with "theSeed" has placed on
     (currentRow, currentCol). */
    public static void updateGame(
            int theSeed, int currentRow, int currentCol,
            int Rows, int Columns, int[][] board, int AmountToWin) {
        if (gainPoint(theSeed, currentRow, currentCol, board, Rows, Columns, AmountToWin)) { // check for gained point
            currentState = PLAYING;
            if (theSeed == 1) {
                System.out.println("The current standings are " + scorePlayer1 + "-" + oldScorePlayer2 + ".");
            }
            if (theSeed == 2) {
                System.out.println("The current standings are " + oldScorePlayer1 + "-" + scorePlayer2 + ".");
            }

        }
        if (fullBoard(Rows, Columns, board)) {  // check for full board
            if (theSeed == 1) {
                scorePlayer2 = oldScorePlayer2;
            }
            if (theSeed == 2) {
                scorePlayer1 = oldScorePlayer1;
            }
            if (scorePlayer1 > scorePlayer2) {
                currentState = PLAYER1_WON;
            }
            if (scorePlayer1 < scorePlayer2) {
                currentState = PLAYER2_WON;
            }
            if (scorePlayer1 == scorePlayer2) {
                currentState = DRAW;
            }
        } // Otherwise, no change to currentState (still PLAYING).
    }

    /** Return true if it is a draw (no more empty cell) */
    public static boolean fullBoard(int Rows, int Columns, int[][] board) {
        for (int row = 0; row < Rows; ++row) {
            for (int col = 0; col < Columns; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;  //  meaning there's still an empty cell
                }
            }
        } return true;  // no empty cell, the game is finished
    }

    /** Return true if the player with "theSeed" gains a point after placing at
     (currentRow, currentCol) */
    public static boolean gainPoint(
            int theSeed, int currentRow, int currentCol, int[][] board, int Rows, int Columns, int AmountToWin) {
        oldScorePlayer1 = scorePlayer1;
        oldScorePlayer2 = scorePlayer2;
        scorePlayer1 = 0;
        scorePlayer2 = 0;

        //AmountToWin in Rows
        for (int row = 0; row < Rows; row++){
            for(int col = 0; col <= Columns - AmountToWin; col++){
                int i = 0;
                for(int n = 0; n < AmountToWin; n++){
                    if (board[row][col] == theSeed && board[row][col] == board[row][col + n]) {
                        i++;
                    }
                    if (i == AmountToWin) {
                        if (theSeed == 1) {
                            scorePlayer1 = scorePlayer1 - oldScorePlayer1 + 1;
                        }
                        if (theSeed == 2) {
                            scorePlayer2 = scorePlayer2 - oldScorePlayer2 + 1;
                        }
                    }
                }
            }
        }
        //AmountToWin in Columns
        for (int col = 0; col < Columns; col++){
            for(int row = Rows - 1; row >= AmountToWin - 1 ; row--){
                int i = 0;
                for(int n = 0; n < AmountToWin; n++){
                    if( board[row][col] == theSeed && board[row][col] == board[row - n][col])  {
                        i++;
                    }
                    if (i == AmountToWin) {
                        if (theSeed == 1) {
                            scorePlayer1 = scorePlayer1 - oldScorePlayer1 + 1;
                        }
                        if (theSeed == 2) {
                            scorePlayer2 = scorePlayer2 - oldScorePlayer2 + 1;
                        }
                    }
                }
            }
        }
        //AmountToWin Diagonal
        for (int row = Rows - 1; row >= AmountToWin - 1; row--) {
            for (int col = 0; col <= Columns - AmountToWin; col++) {
                int i = 0;
                for (int n = 0; n < AmountToWin; n++) {
                    if ( board[row][col] == theSeed && board[row][col] == board[row - n][col + n]) {
                        i++;
                    }
                    if (i == AmountToWin) {
                        if (theSeed == 1) {
                            scorePlayer1 = scorePlayer1 - oldScorePlayer1 + 1;
                        }
                        if (theSeed == 2) {
                            scorePlayer2 = scorePlayer2 - oldScorePlayer2 + 1;
                        }
                    }
                }
            }
        }
        //AmountToWin other Diagonal
        for (int row = Rows - 1; row >= AmountToWin - 1; row--) {
            for(int col = Columns - 1; col >= AmountToWin - 1 ; col--) {
                int i = 0;
                for (int n = 0; n < AmountToWin; n++) {
                    if (board[row][col] == theSeed && board[row][col] == board[row - n][col - n]) {
                        i++;
                    }
                    if (i == AmountToWin) {
                        if (theSeed == 1) {
                            scorePlayer1 = scorePlayer1 - oldScorePlayer1 + 1;
                        }
                        if (theSeed == 2) {
                            scorePlayer2 = scorePlayer2 - oldScorePlayer2 + 1;
                        }
                    }
                }
            }
        }
        if (scorePlayer1 > 0 || scorePlayer2 > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /** Print the game board */
    public ConnectPanel(int theSeed, int currentCol, int currentRow){
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(Rows, Columns));

        for (int i=0; i<Columns*Rows; i++) {
            p1.add(new ArcsPanel());
        }
        add(p1, BorderLayout.CENTER);
    }

    class ArcsPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for(int i = 0; i<Rows; i++){
                for(int j = 0; j<Columns; j++){
                    int x1 = 100*(Columns - 1);
                    int y1 = 100*(Rows - 1);
                    g.fillOval(x1, y1, 100, 100);

                    int xCenter = getWidth() / 2;
                    int yCenter = getHeight() / 2;
                    int radius = (int)(Math.min(getWidth(), getHeight())*0.4);

                    int x2 = xCenter - radius;
                    int y2 = yCenter - radius;

                    g.fillArc(x2, y2, 2*radius, 2*radius, 0, 360);

                    String color1 = chip1.getColor();
                    String color2 = chip2.getColor();

                    if(color1.equals(color2)){
                        if (color1.equals("red")) {
                            if (theSeed == 0) {
                                int x3 = 100*(currentCol - 1);
                                int y3 = 100*(currentRow - 1);
                                g.fillOval(x3, y3, 100, 100);
                                g.setColor(Color.WHITE);
                            }
                            if (theSeed == 1) {
                                int x3 = 100*(currentCol - 1);
                                int y3 = 100*(currentRow - 1);
                                g.fillOval(x3, y3, 100, 100);
                                g.setColor(Color.RED);
                            }
                            if (theSeed == 2) {
                                int x3 = 100*(currentCol - 1);
                                int y3 = 100*(currentRow - 1);
                                g.fillOval(x3, y3, 100, 100);
                                g.setColor(Color.BLUE);
                            }
                        }
                        else {
                            if (theSeed == 0) {
                                int x3 = 100*(currentCol - 1);
                                int y3 = 100*(currentRow - 1);
                                g.fillOval(x3, y3, 100, 100);
                                g.setColor(Color.WHITE);
                            }
                            if (theSeed == 1) {
                                int x3 = 100*(currentCol - 1);
                                int y3 = 100*(currentRow - 1);
                                g.fillOval(x3, y3, 100, 100);
                                g.setColor(Color.RED);
                            }
                            if (theSeed == 2) {
                                int x3 = 100*(currentCol - 1);
                                int y3 = 100*(currentRow - 1);
                                g.fillOval(x3, y3, 100, 100);
                                g.setColor(Color.BLUE);
                            }
                        }
                    }
                    else {
                        if (theSeed == 0) {
                            int x3 = 100*(currentCol - 1);
                            int y3 = 100*(currentRow - 1);
                            g.fillOval(x3, y3, 100, 100);
                            g.setColor(Color.WHITE);
                        }
                        if (theSeed == 1) {
                            int x3 = 100*(currentCol - 1);
                            int y3 = 100*(currentRow - 1);
                            g.fillOval(x3, y3, 100, 100);
                            g.setColor(Color.RED);
                        }
                        if (theSeed == 2) {
                            int x3 = 100*(currentCol - 1);
                            int y3 = 100*(currentRow - 1);
                            g.fillOval(x3, y3, 100, 100);
                            g.setColor(Color.BLUE);
                        }
                    }
                    setBackground(Color.getHSBColor(0.6f, 0.5f, 0.5f));
                }
            }
        }
    }

    /**finds the first empty space in a column starting at the bottom.*/
    public static int availableRow( int col, int Rows, int[][] board){
        for( int row = Rows -1; row >= 0; row--){
            if(board[row][col] == EMPTY){
                return row;
            }
        } return -1;
    }
}

