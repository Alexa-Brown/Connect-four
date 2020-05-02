/**********************************************************************
 * @file ConnectFour.java
 * @brief This program implements the use of methods and arrays to make a ConnectFour game.
 * @author Alexa Brown
 * @date March 27, 2019
 **********************************************************************/

import java.awt.*;
import java.util.Arrays;

public class ConnectFour {
    public static void main(String[] args) {

        int x;
        int y;
        int plays = 0;
        final int BLUE = 1;
        final int RED = 2;
        int turn = BLUE;
        int[] columnCounter = new int[7]; // Keep track of number of discs in each column in the board
        // Also, columnCounter[x] is the ROW where the next disc should be placed
        int[][] board = new int[6][7]; //don't mess with this

        drawBoard();

        printMessage("Blue's turn", StdDraw.BLUE);

        while (plays < 42) {
            // Was the mouse pressed?
            if (StdDraw.isMousePressed()) {
                x = (int) StdDraw.mouseX();
                y = (int) StdDraw.mouseY();

                // Was one of slots on top clicked?
                if (y >= 6 && y <= 6.5) {
                    // The value of x tells us which of the slots was clicked
                    // So, use x as index for each column in the board

                    // If there is space in column x then draw a blue or red circle
                    if (columnCounter[x] < 6) {
                        // Is it blue's turn?
                        if (turn == BLUE) {
                            board[columnCounter[x]][x] = BLUE;  // Update the board
                            StdDraw.setPenColor(StdDraw.BLUE);  // Select the blue pen
                            // Draw the disc and wait 300 milliseconds
                            StdDraw.filledCircle(x + 0.5, columnCounter[x] + 0.5, 0.5);
                            StdDraw.pause(300);
                            printMessage("Red's turn", StdDraw.RED);

                        } else {  // Turn must be red's
                            board[columnCounter[x]][x] = RED;
                            StdDraw.setPenColor(StdDraw.RED);   // Select the red pen
                            // Draw the disc and wait 300 milliseconds
                            StdDraw.filledCircle(x + 0.5, columnCounter[x] + 0.5, 0.5);
                            StdDraw.pause(300);
                            printMessage("Blue's turn", StdDraw.BLUE);
                        }

                        columnCounter[x]++;     // Update the column counter

                        // Print the board to the screen for debugging purposes
                        print2Darray(board);

                        // If turn is the winner then break the game loop
                        if (isWinner(board, turn)) {
                            if (turn == 1){
                                printMessage("BLUE wins!", StdDraw.BLACK);
                                break;
                            }
                            else if (turn == 2){
                                printMessage("RED wins!", StdDraw.BLACK);
                                break;
                            }

                        }

                        // No winner yet, so change turns and increment plays
                        if (turn == RED) {
                            turn = BLUE;
                        } else {
                            turn = RED;
                        }

                        plays++;
                    }
                }

            }
        }// end while

        if (plays == 42){
            printMessage("Tie!", StdDraw.BLACK);
        }

    }

    public static void printMessage(String message, Color color) {
        StdDraw.setPenColor(Color.white);
        StdDraw.filledRectangle(3.5, 6.75, 1.48, 0.25);

        StdDraw.setPenColor(color);
        StdDraw.text(3.5, 6.70, message);
    }

    public static void print2Darray(int[][] table) {
        int i, j;
        for (i = 0; i < 6; i++) {
            for (j = 0; j < 7; j++) {
                System.out.print(table[i][j] + "  ");
            }
            System.out.println();

        }


    }

    public static boolean isWinner(int[][] table, int turn) {
        for (int j = 0; j < table[0].length; j++){
            for (int i = 0; i < table.length - 3; i++){
                if ((turn == table [i][j]) && (table[i][j] == table[i+1][j]) &&
                        (table[i][j] == table[i+2][j]) && (table[i][j] == table[i+3][j])){
                    return true;
                }
            }
        } //vertical win

        for (int j = 0; j < table[0].length - 3; j++){
            for (int i = 0; i < table.length ; i++){
                if ((turn == table [i][j]) && (table[i][j] == table[i][j+1]) &&
                        (table[i][j] == table[i][j+2]) && (table[i][j] == table[i][j+3])){
                    return true;
                }
            }
        } //horizontal win

        for (int i = 0; i < table.length - 3; i++){
            for (int j = 0; j < table[i].length - 3; j++){
                if ((turn == table[i][j]) && (table[i][j] == table[i+1][j
                        +1]) && (table[i][j] == table[i+2][j+2]) && (table[i][j] ==
                        table[i+3][j+3])){
                    return true;
                }
            }
        } // vertical win one way

        for (int i = 0; i < table.length - 3; i++){
            for (int j = 3; j < table[i].length; j++){
                if ((turn == table[i][j]) && (table[i][j] == table[i+1]
                        [j-1]) && (table[i][j] == table[i+2][j-2]) && (table[i]
                        [j] == table[i+3][j-3])){
                    return true;
                }
            }
        } //vertical win the other way

        return false;
}

    public static void drawBoard () {
        double y = 0;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setScale(0, 7);

        for (int k = 1; k < 7; k++) {
            double x = 0;
            for (int i = 0; i < 7; i++) {
                StdDraw.circle(x + 0.5, y + 0.5, 0.5);
                x++;
            }
            y++;
        }
        // Draw the slots
        StdDraw.setPenColor(Color.gray);
        for (int i = 0; i < 7; i++) {
            StdDraw.filledRectangle(i + 0.5, 6.25, 0.48, 0.25);
        }
    }
}