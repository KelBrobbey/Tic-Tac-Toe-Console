/*
This file is being used to solve Bathi's offical bootcamp projects. Solving project
#1 - Recreating Tic Tac Toe using scanner class. Starting on 05/13/2022
*/
package com.markone;
import java.util.*;
import java.lang.*;

public class TicTacToe{
    public static void main(String[]args){            
        //Using a char array to hold the state of the board
        char[][] board = new char[3][3];

        //Intializing the board with empty spaces
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = ' ';
            }
        }
        
        /*
        Creating boolean variable to determine when game is over. For gameMode = 0: when 
        gameOver is false, we should be cycling through the selection process from player 1 
        and player 2. For gameMode = 1: when gameOver is false, we should be cycling through 
        the selection process from the user and then the bot's selection
        */
        boolean gameOver = false;
        
        /*
        Creating a boolean variable to determine whose turn it is within the game.For gameMode = 0:
        Initializing as true, which would mean it's player 1's turn; if it is false,
        it is player 2's turn. For gameMode = 1/2: Initializing it as true which would 
        mean that it is the player's turn to go, instead of the bot
        */
        boolean player = true;
        
        //Need to capture the move input from the user
        String move = "";
        
        //This will keep track of the bot's moves for both easy and hard bots
        int[] botMove = new int[2];
        
        //Introducing Game
        System.out.print("Running Kelvin's Tic Tac Toe Game\nAvailable game modes:\n  0: player vs player\n  1: player vs easy bot\n  2: player vs hard bot\nSelect: ");
        
        //Capturing game mode selection from user using scanner, and will validate to ensure user picks one of the options
        String gameMode;
        while(true){
            Scanner myObj = new Scanner(System.in);
            gameMode = myObj.next();
            if(!gameMode.equals("0") && !gameMode.equals("1") && !gameMode.equals("2")){
                System.out.print("Incorrect input, please enter one of the available options\nAvailable game modes:\n  0: player vs player\n  1: player vs easy bot\n  2: player vs hard bot\nSelect: ");
            }
            else{
                break;
            }
        }
        
        //User has selected 'player vs player' game mode:
        if(gameMode.equals("0")){
            //Introducing game mode and gathering player's names
            String player1;
            String player2;
            System.out.print("Starting player vs player mode\nWe will need to determine who is playing, player 1 starts as X.\nPlayer 1, what is your name: ");
            Scanner myObj2 = new Scanner(System.in);
            player1 = myObj2.nextLine();
            System.out.print("Player 2, what is your name: ");
            player2 = myObj2.nextLine();
            System.out.println(player1 + " will be X and " + player2 + " will be O.");
            printBoard(board);
            
            //Running the game now:
            
            //Need to keep track of what element is being placed and what player is moving
            char ch;
            String currPlayer;
            while(!gameOver){
                //Need to figure out what element will be placed and which player is moving
                if(player){
                    ch = 'X';
                    currPlayer = player1;
                }
                else{
                    ch = 'O';
                    currPlayer = player2;
                }
                
                //Getting and setting the user's move
                implementMove(board, move, currPlayer, ch);
                printBoard(board);
                if(player && haveVictor(board)){
                    System.out.print(player1 + " has won the game.\nWould you two like to play again? y or no: ");
                    Scanner myObj3 = new Scanner(System.in);
                    String rematch = myObj3.nextLine();
                    if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                        clearBoard(board);
                        System.out.println("\n\nRestarting player vs player game mode\nYou are going first, " + player1 + ", and playing as X");
                        printBoard(board);
                        player = true;
                    }
                    else{
                        System.out.println("Game has ended");
                        gameOver = true;
                    }
                }
                else if(!player && haveVictor(board)){
                    System.out.print(player2 + " has won the game.\nWould you two like to play again? y or no: ");
                    Scanner myObj3 = new Scanner(System.in);
                    String rematch = myObj3.nextLine();
                    if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                        clearBoard(board);
                        System.out.println("\n\nRestarting player vs player game mode\nYou are going first, " + player1 + ", and playing as X");
                        printBoard(board);
                        player = true;
                    }
                    else{
                        System.out.println("Game has ended");
                        gameOver = true;
                    }
                }
                else if(fullBoard(board)){
                    System.out.print("This is a tied game.\nWould you two like to play again? y or no: ");
                    Scanner myObj3 = new Scanner(System.in);
                    String rematch = myObj3.nextLine();
                    if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                        clearBoard(board);
                        System.out.println("\n\nRestarting player vs player game mode\nYou are going first, " + player1 + ", and playing as X");
                        printBoard(board);
                        player = true;
                    }
                    else{
                        System.out.println("Game has ended");
                        gameOver = true;
                    }
                }
                else{
                    player = !player;
                }
            }
        }
        //User has selected 'player vs easy bot' game mode:
        else if(gameMode.equals("1")){
            System.out.println("Starting player vs easy bot\nYou are going first and playing as X");
            printBoard(board);
            
            //Running the game now
            while(!gameOver){
                if(player){
                    implementMove(board, move, "", 'X');
                    printBoard(board);
                    
                    //We need to determine if the game is over or if the board is full, if so, we want to ask user if they want to play again
                    if(haveVictor(board)){                        
                        System.out.print("Player has won versus the easy bot. Congrats, you have no fucking balls\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs easy bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                        
                    }
                    else if(fullBoard(board)){
                        System.out.print("You really tied be an easy bot? LMAOOOOOOOOOOOOO\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs easy bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                    }
                    player = !player;
                    System.out.println("");
                }
                else{
                    System.out.println("Bot is making its move...");
                    botMove = easyBotMove(board);
                    implementBotMove(board, botMove);
                    printBoard(board);
                    if(haveVictor(board)){
                        System.out.print("You have lost to the easy bot. What are you doing on this Earth honestly?\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs easy bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                    }
                    else if(fullBoard(board)){
                        System.out.print("You really tied be an easy bot? LMAOOOOOOOOOOOOO\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs easy bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                    }
                    System.out.println("");
                    player = !player;
                }  
            }            
        }
        else{
            System.out.println("Starting player vs hard bot. LOL GOOD LUCK, YOU ARE GOING TO NEED IT\nYou are going first and playing as X");
            printBoard(board);
            
            //Running the game now
            while(!gameOver){
                if(player){
                    implementMove(board, move, "", 'X');
                    printBoard(board);
                    
                    //We need to determine if the game is over or if the board is full, if so, we want to ask user if they want to play again
                    if(haveVictor(board)){                        
                        System.out.print("Player has won versus the hard bot. Congrats, I didn't think that was possible\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs hard bot (Yikes)\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                        
                    }
                    else if(fullBoard(board)){
                        System.out.print("You tied the hard bot. No shame, no shame\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs hard bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                    }
                    player = !player;
                    System.out.println("");
                }
                else{
                    System.out.println("Bot is making its move...");
                    botMove = hardBotMove(board);
                    implementBotMove(board, botMove);
                    printBoard(board);
                    if(haveVictor(board)){
                        System.out.print("You have lost to the hard bot. To be expected\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs hard bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                    }
                    else if(fullBoard(board)){
                        System.out.print("You tied the hard bot. No shame, no shame\nWould you like to play again? y or n: ");
                        Scanner myObj3 = new Scanner(System.in);
                        String rematch = myObj3.nextLine();
                        if(rematch.equals("y") || rematch.equals("Y") || rematch.equals("Yes") || rematch.equals("yes")){
                            clearBoard(board);
                            System.out.println("\n\nRestarting player vs hard bot\nYou are going first and playing as X");
                            printBoard(board);
                            player = false;
                        }
                        else{
                            System.out.println("Game has ended");
                            gameOver = true;
                        }
                    }
                    System.out.println("");
                    player = !player;
                }  
            }
        }
    }
    
    //Method to display the board with the indicies and lines
    public static void printBoard(char[][] board){
        String output = "   1 2 3\nA ";        
        for(int i = 0; i < board.length; i++){
            if(i == 1){
                output += "B ";
            }
            if(i == 2){
                output += "C ";
            }
            for(int j = 0; j < board[i].length; j++){
                output += "|" + board[i][j];
            }
            if(i != 2){
                output += "|\n  |-|-|-|\n";
            }
            else{
                output += "|";
            }
            
        }
        System.out.println(output);
    }
    
    //Method to determine if a player has won
    public static boolean haveVictor(char[][] board){
        //Checking rows
        for(int i = 0; i < board.length; i++){
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) return true;
        }
        
        //Checking columns
        for(int i = 0; i < board.length; i++){
            if(board[0][i] != ' ' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) return true;
        }
        
        //Checking diagonals
        if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) return true;
        if(board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) return true;
        
        return false;
    }
    
    //Method to determine if the board is full
    public static boolean fullBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    
    //Method to determine the easy bot's board move
    public static int[] easyBotMove(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                //Checking to see if the enemy has a spot where if they put an X, they will win
                if(board[i][j] == ' '){
                    board[i][j] = 'X';
                    if(haveVictor(board)){
                      board[i][j] = ' ';
                      return new int[] {i, j};
                    }
                    board[i][j] = ' ';
                }
                
            }
        }
        
        //There wasn't a winning move for the enemy so now we put the element in a random place
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 3);
        //Making sure we aren't puting the next element where we already have one
        while(board[row][col] == 'X' || board[row][col] == 'O'){
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        }
        return new int[] {row, col};
    }
    
    //Method to determine if the move by user is valid
    public static boolean validMove(char[][] board, String move){
        int row;
        int col = Character.getNumericValue(move.charAt(2));
        col--;
        if(move.charAt(0) == 'A'){
            row = 0;
        }
        else if(move.charAt(0) == 'B'){
            row = 1;
        }
        else{
            row = 2;
        }
        if(board[row][col] == 'X' || board[row][col] == 'O'){
            return false;
        }
        else{
            return true;
        }
    }
    
    //Method to clear the board in order to restart the game
    public static void clearBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = ' ';
            }
        }
    }
    
    //Method to handle placing the move onto the board after validation
    public static void implementMove(char[][] board, String move, String currPlayer, char ch){
        if(currPlayer.equals("")){
            System.out.print("Your move: ");
        }
        else{
          System.out.print(currPlayer + "'s move: ");  
        }
        while(true){
            Scanner myObj3 = new Scanner(System.in);
            move = myObj3.nextLine();
            if(move.length() != 3 || (move.charAt(0) != 'A' && move.charAt(0) != 'B' && move.charAt(0) != 'C') || (move.charAt(2) != '1' && move.charAt(2) != '2' && move.charAt(2) != '3')){
                System.out.print("Incorrect input, as input isn't one of the specific row or column, please enter a new, valid space\nYour move: ");
            }
            else if(!validMove(board, move)){
                System.out.print("Incorrect input, as there is already an element at that given space, please enter a new, valid space\nYour move: ");
            }
            else{
                break;
            }
        }
        //Figuring out what row and colum we will be placing the next letter in
        int row;
        char a = 'A';
        char b = 'B';
        char c = 'C';
        if(a == move.charAt(0)){
            row = 0;
        }
        else if(b == move.charAt(0)){
            row = 1;
        }
        else{
            row = 2;
        }
        int col = Character.getNumericValue(move.charAt(2));
        col--;
        board[row][col] = ch;


        System.out.println("Placing " + ch + " on (" + move.charAt(0) + ", " + move.charAt(2) + ")");
    }
    
    //Method to handle placing the bot's move onto the board
    public static void implementBotMove(char[][] board, int[] botMove){
        int row = botMove[0];
        int col = botMove[1];
        board[row][col] = 'O';
        col++;
        char ch;
        if(row == 0){
            ch = 'A';
        }
        else if(row == 1){
            ch = 'B';
        }
        else{
            ch = 'C';
        }
        System.out.println("Bot is playing O on (" + ch + ", " + col + ")");
    }
    
    //Similar to the haveVictor methods, instead returns positive or negative values for the bot to determine best possible moves
    public static int evaluate(char[][] board){
        //Checking rows
        for(int i = 0; i < board.length; i++){            
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                if(board[i][0] == 'O'){
                    return 10;
                }
                else if(board[i][0] == 'X'){
                    return -10;
                }
            }
        }
        
        //Checking columns
        for(int i = 0; i < board.length; i++){
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                if(board[0][i] == 'O'){
                    return 10;
                }
                else if(board[0][i] == 'X'){
                    return -10;
                }
            }
        }
        
        //Checking diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            if(board[0][0] == 'O'){
                return 10;
            }
            else if(board[0][0] == 'X'){
                return -10;
            }
        }
        
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            if(board[0][2] == 'O'){
                return 10;
            }
            else if(board[0][2] == 'X'){
                return -10;
            }
        }
        
        return 0;
    }
    
    //Method to determine if there are moves left -> the opposite of fullBoard method
    public static boolean isMovesLeft(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }
    
    //Method to determine the optimal move with minimax
    public static int minimax(char[][] board, int depth, Boolean isMax){
        int score = evaluate(board);
        if(score == 10 || score == -10){
            return score;
        }
        
        if(!isMovesLeft(board)){
            return 0;
        }
        
        if(isMax){
            int best = -1000;
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    if(board[i][j] == ' '){
                        board[i][j] = 'O';
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
        else{
            int best = 1000;
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    if(board[i][j] == ' '){
                        board[i][j] = 'X';
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }
    
    //Combining minimax and evaluate to finally get the best possible move
    public static int[] hardBotMove(char[][] board){
        int bestValue = -1000;
        int[] answer = new int[2];
        answer[0] = -1;
        answer[0] = -1;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == ' '){
                    board[i][j] = 'O';
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = ' ';
                    
                    if(moveVal > bestValue){
                        answer[0] = i;
                        answer[1] = j;
                        bestValue = moveVal;
                    }
                }
            }
        }
        return answer;
    }
}
