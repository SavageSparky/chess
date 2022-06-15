package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class Pawn extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.PAWN;
  private boolean isMoved = false;

  public Pawn(int rowPosition, int columnPosition, boolean isTeamWhite) {
    super(rowPosition, columnPosition, isTeamWhite);
  }

  // getters
  
  @Override
  public ChessPieceType getChessPieceType() {
    return chessPieceType;
  }

  @Override
  public boolean movePiece(int toRow, int toColumn) {
    if(checkForPawnExceptions(toRow, toColumn)) {
      return false;
    }
    Board.setChessPiece(toRow, toColumn, this);
    Board.setChessPiece(this.rowPosition, this.columnPosition, null);
    this.rowPosition = toRow;
    this.columnPosition = toColumn;
    if((isTeamWhite && this.rowPosition == 0) && (!isTeamWhite && this.rowPosition == 7)) {
      promotePawn();
    }
    return true;
  }

  private boolean checkForPawnExceptions(int toRow, int toColumn) {
    int rowDifference = this.rowPosition - toRow;
    int columnDifference = this.columnPosition - toColumn;
    ChessPiece destinationChessPiece = Board.getChessPiece(toRow, toColumn);
    try {
      if(columnDifference < -1 || columnDifference > 1) {
        throw new IllegalMoveException("Pawn cannot move like that");
      }
      else if((columnDifference == -1 || columnDifference == 1) && (isTeamWhite == destinationChessPiece.isTeamWhite() || destinationChessPiece == null)) {
        throw new IllegalMoveException("Illegal Move: Destination either your Piece or no Piece at all");
      }else if(columnDifference == 0 && destinationChessPiece != null ){
        throw new IllegalMoveException("Destination has other chess piece");
      }

      if(isTeamWhite) {
        if(rowDifference != 1 || rowDifference != 2) {
          throw new IllegalMoveException("Illegal Move");
        }
        else if((rowDifference == 2 ) && (columnDifference != 0 )) {
          throw new IllegalMoveException("Illegal Move");
        }
        else if(rowDifference == 2 && isMoved) {
          throw new IllegalMoveException("This Pawn can move only ONE STEP FORWARD");
        }
        else if(rowDifference == 2 && Board.getChessPiece(this.rowPosition-1, this.columnPosition) != null) {
          throw new PieceOnPathException();
        }
      }
      else {
        if(rowDifference != -1 || rowDifference != -2) {
          throw new IllegalMoveException("Illegal Move");
        }
        else if((rowDifference == -2 ) && (columnDifference != 0 )) {
          throw new IllegalMoveException("Illegal Move");
        }
        else if(rowDifference == -2 && isMoved) {
          throw new IllegalMoveException("This Pawn can move only ONE STEP FORWARD");
        }
        else if(rowDifference == -2 && Board.getChessPiece(this.rowPosition+1, this.columnPosition) != null) {
          throw new PieceOnPathException();
        }
      }  
    }
    catch(IllegalMoveException | PieceOnPathException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  public void promotePawn() {
    // System.out.println("Enter a number to ");
    
  }

  @Override
  public String toString(){
    return "  " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "  ";
  }
}
