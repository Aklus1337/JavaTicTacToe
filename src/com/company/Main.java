package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var results = new int[2];
        int turn = 0;
        String[] board;

        System.out.println("How many turns do you want to play??");
        int numberOfGames = scanner.nextInt();

        while (numberOfGames > turn) {
            board = populateBoard();
            drawBoard(board);
            turn++;
            results = gameLoop(results, board);
        }
        System.out.println("The game has ended! \nX has " + results[0] + " points, O has " + results[1] + " points.");
    }
    public static int[] gameLoop(int[] results, String[] board){
        String victorious = "";
        boolean xTurn = true;

        while(victorious.equals("")) {
            victorious = checkVictory(board);
            playerInput(board, xTurn);
            xTurn = !xTurn;

            drawBoard(board);

            if (victorious.equals("X")) results[0] += 1;
            else if (victorious.equals("O")) results[1] +=1;
        }
        return results;
    }
    public static void drawBoard(String[] board){
        for(int i = 7; i >= 1; i++){
            System.out.print(board[i]);
            if(i % 3 == 0){
                i -= 6;
                System.out.println();
            }
        }
    }
    public static String[] populateBoard(){
        var board = new String[10];
        for (int i = 0; i<=9; i++) {
            board[i] = "?";
        }
        return board;
    }

    public static void playerInput(String[] board, boolean xTurn){
        String playerChar = xTurn ? "X" : "O";
        var scanner = new Scanner(System.in);
        String input;
        int range; // has to be between 1-9
        System.out.println("What's your input, " + playerChar + "?");
        do{
            System.out.println("Remember the input has to be 1-9!");
            input = scanner.next();
            try{
                range = Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Incorrect input!");
                range = -1; // go through the loop again
            }
        } while((range > 9 || range < 1));
        if (board[range].equals("?")) // check if no player is there
            board[range] = playerChar;
        else{
            playerInput(board, xTurn);
        }
    }

    public static String checkVictory(String[] board){
        if(board[1].equals(board[2]) && board[1].equals(board[3]) && !board[1].equals("?"))
            return (board[1]);
        else if(board[4].equals(board[5]) && board[4].equals(board[6]) && !board[4].equals("?"))
            return (board[4]);
        else if(board[7].equals(board[8]) && board[7].equals(board[9]) && !board[7].equals("?"))
            return (board[7]);
        else if(board[7].equals(board[4]) && board[4].equals(board[1]) && !board[7].equals("?"))
            return (board[7]);
        else if(board[8].equals(board[5]) && board[8].equals(board[2]) && !board[8].equals("?"))
            return (board[8]);
        else if(board[9].equals(board[6]) && board[9].equals(board[3]) && !board[9].equals("?"))
            return (board[9]);
        else if(board[1].equals(board[5]) && board[1].equals(board[9]) && !board[1].equals("?"))
            return (board[1]);
        else if(board[7].equals(board[5]) && board[7].equals(board[3]) && !board[7].equals("?"))
            return (board[7]);
        else {
            // check for a draw
            if (Arrays.stream(board).noneMatch("?"::equals)){
                System.out.println("A draw!");
                return ("");
            }
        }
        return ("");
    }
}
