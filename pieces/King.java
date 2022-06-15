package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class King extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.KING;

  public King(int rowPosition, int columnPosition, boolean isTeamWhite) {
    super(rowPosition, columnPosition, isTeamWhite);
  }

  // getters

  @Override
  public ChessPieceType getChessPieceType() {
    return chessPieceType;
  }

  @Override
  public void movePiece(int toRow, int toColumn) {
    
  }

  @Override
  public String toString() {
    return "  " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "  ";
  }
}
