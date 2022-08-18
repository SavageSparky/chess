package chess;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.board.Board;
import chess.exceptions.IllegalMoveException;
import chess.player.Player;

public class App {
  private static Scanner input = new Scanner(System.in);
  // private static ChessBoard chessBoard = new ChessBoard();
  private static Player[] players = Player.generatePlayers();


  public static boolean getInput(boolean isWhite){
    int[] values = new int[]{-1, -1, -1, -1};
    boolean exceptionFlag = true;
    // loop till numbers are entered
    while(exceptionFlag) {
      System.out.print("Input Format <fromRow fromColumn toRow toColumn>: ");
      int numberOfUnexecutedSteps = 4;

      try{
        values[0] = input.nextInt();
        numberOfUnexecutedSteps--;
        values[1] = input.nextInt();
        numberOfUnexecutedSteps--;
        values[2] = input.nextInt();
        numberOfUnexecutedSteps--;
        values[3] = input.nextInt();
        numberOfUnexecutedSteps--;

        for(int i: values){
          if(i<0 || i>7){
            throw new ArrayIndexOutOfBoundsException("!!! Please enter numbers between 0 and 7 !!!");
          }
        }

        if(values[0] == values[2] && values[1] == values[3]) {
          throw new IllegalMoveException("WARNING: Source and Destination are SAME.");
        }
        exceptionFlag = false;
      }
      catch(InputMismatchException e) {
        System.out.println("!!! Please enter whole numbers only !!!");
        while(numberOfUnexecutedSteps-- > 0){
          input.next();
        }
      }
      catch(ArrayIndexOutOfBoundsException | IllegalMoveException e) {
        System.out.println(e.getMessage());
      }
    }
    return Board.movePiece(values[0], values[1], values[2], values[3], isWhite);
  }
  
  public static void main(String[] args){
    boolean isWhite = true; // player turn
    while(!Board.getCheckMate()) {
      Board.displayBoard();
      if(isWhite) {
        System.out.print("\n                WHITE's TURN\n");
        while(!getInput(isWhite));     // loop till valid values are entered
        isWhite = !isWhite;
      }
      else {
        System.out.print("\n                BLACK's turn\n");
        while(!getInput(isWhite));   
        isWhite = !isWhite;
      }
    }
  }
}
