package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class Bishop extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.BISHOP;

  public Bishop(int rowPosition, int columnPosition, boolean isTeamWhite){
    super(rowPosition, columnPosition, isTeamWhite);
  }

  public ChessPieceType getChessPieceType(){
    return chessPieceType;
  }

  // Bishop specific functionalities
  @Override
  public boolean movePiece(int toRow, int toColumn) {
    try {
      checkForChessPieceSpecificExceptions(toRow, toColumn);
    }catch(IllegalMoveException | PieceOnPathException e) {
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
    int rowDifference = this.getRowPosition() - toRow;
    int columnDifference = this.getColumnPosition() - toColumn;
    ChessPiece destinationChessPiece = Board.getChessPiece(toRow, toColumn);
    if(Math.abs(rowDifference) != Math.abs(columnDifference)) {
      throw new IllegalMoveException("Illegal move");
    }
    int incrementRowFactor = rowDifference < 0 ? 1: -1;
    int incrementColumnFactor = columnDifference < 0 ? 1: -1;
    for(int i = this.rowPosition + incrementRowFactor, j = this.columnPosition + incrementColumnFactor;
        i != toRow ;
        i += incrementRowFactor, j += incrementColumnFactor) {
      if(Board.getChessPiece(i, j) != null) {
        throw new PieceOnPathException();
      }
    }
    return true;
  }


  @Override
  public String toString(){
    return "  " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "  ";
  }

}
