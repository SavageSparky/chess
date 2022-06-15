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
  public void movePiece(int toRow, int toColumn){

  }

  @Override
  public String toString(){
    return " " + chessPieceType.toString() + (isTeamWhite? " W": " B") + " ";
  }
}
