package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class Knight extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.KNIGHT;

  public Knight(int rowPosition, int columnPosition, boolean isTeamWhite) {
    super(rowPosition, columnPosition, isTeamWhite);
  }

  // getters

  @Override
  public ChessPieceType getChessPieceType(){
    return chessPieceType;
  }


  @Override
  public boolean movePiece(int toRow, int toColumn){
    try {
      checkForChessPieceSpecificExceptions(toRow, toColumn);
    }
    catch(IllegalMoveException e) {
      System.out.println(e.getMessage());
      return false;
    }
    if(!inspectForCheckSelf(toRow, toColumn)) {
      return false;
    }
    if(inspectForCheckAgainst()){
      System.out.println("    ! CHECK !");
    }
    return true;
  }

  @Override
  public boolean checkForChessPieceSpecificExceptions(int toRow, int toColumn) {
    int rowDifference = Math.abs(this.getRowPosition() - toRow);
    int columnDifference = Math.abs(this.getColumnPosition() - toColumn);
    if(rowDifference > 2 || rowDifference < 1 || columnDifference > 2 || columnDifference < 1) {
      throw new IllegalMoveException("Illegal Move");
    }
    if((rowDifference == 2 && columnDifference != 1) || (rowDifference == 1 && columnDifference !=2)) {
      throw new IllegalMoveException("Illegal Move");
    }
    return false;
  }

  @Override
  public String toString(){
    return "  " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "  ";
  }
}
