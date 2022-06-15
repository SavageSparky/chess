package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class Rook extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.ROOK;

  // private int rowPosition;
  // private int columnPosition;
  // private boolean isTeamWhite;

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
    if(checkForRookExceptions(toRow, toColumn)) {
      return false;
    }
  }

  private boolean checkForRookExceptions(int toRow, int toColumn) {
    int rowDifference = this.rowPosition - toRow;
    int columnDifference = this.columnPosition - toColumn;
    ChessPiece destinationChessPiece = Board.getChessPiece(toRow, toColumn);

    try {
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
        for( int i = this.columnPosition + incrementFactor; i != toRow; i += incrementFactor ) {
          if(Board.getChessPiece(this.rowPosition, i) != null) {
            throw new PieceOnPathException();
          }
        }
      }

      // check for piece in the destination
      if(destinationChessPiece.isTeamWhite == this.isTeamWhite) {
        throw new SameTeamException();
      }

    }
    catch(IllegalMoveException | PieceOnPathException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "  " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "  ";
  }
}
