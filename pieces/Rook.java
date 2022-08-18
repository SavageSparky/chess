package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class Rook extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.ROOK;
  private boolean isNotMoved = true;

  public Rook(int rowPosition, int columnPosition, boolean isTeamWhite) {
    super(rowPosition, columnPosition, isTeamWhite);
  }

  // getters
  @Override
  public ChessPieceType getChessPieceType() {
    return chessPieceType;
  }

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
    if(inspectForCheckAgainst()) {
      System.out.println("    ! CHECK !");
    }
    return true;
  }

  public boolean isCastlingSupported() {
    return isNotMoved;
  }

  @Override
  public boolean checkForChessPieceSpecificExceptions(int toRow, int toColumn) {
    int rowDifference = this.rowPosition - toRow;
    int columnDifference = this.columnPosition - toColumn;

    // check for only row and column
    if(rowDifference !=0 && columnDifference !=0) {
      throw new IllegalMoveException("Rook cannot move along both Rows and Columns simultaneously");
    }

    // check for pieces in the path
    if(rowDifference != 0) { // condition to check horizontal or vertical movement
      int incrementFactor = rowDifference < 0 ? 1: -1;
      for( int i = this.rowPosition + incrementFactor; i != toRow; i += incrementFactor ) {
        if(Board.getChessPiece(i, this.columnPosition) != null) {
          throw new PieceOnPathException();
        }
      }
    }
    else {
      int incrementFactor = columnDifference < 0 ? 1: -1;
      for( int i = this.columnPosition + incrementFactor; i != toColumn; i += incrementFactor ) {
        if(Board.getChessPiece(this.rowPosition, i) != null) {
          throw new PieceOnPathException();
        }
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return "   " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "   ";
  }
}
