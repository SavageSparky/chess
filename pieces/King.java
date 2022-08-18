package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class King extends ChessPiece {
  private ChessPieceType chessPieceType = ChessPieceType.KING;
  private boolean safeZones[][] = new boolean[3][3];
  private boolean isNotMoved = true;
  private boolean isCheck = false;

  public King(int rowPosition, int columnPosition, boolean isTeamWhite) {
    super(rowPosition, columnPosition, isTeamWhite);
    for(int i = 0; i<3; i++) {
      for(int j = 0; j<3; j++) {
        safeZones[i][j] = false;
      }
    }
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
      // Board.displayBoard();
    }catch(IllegalMoveException e) {
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

  @Override
  public boolean checkForChessPieceSpecificExceptions(int toRow, int toColumn) {
    int rowDifference = this.getRowPosition() - toRow;
    int columnDifference = this.getColumnPosition() - toColumn;
    if(Math.abs(rowDifference) > 1 || Math.abs(columnDifference) > 1) {
      throw new IllegalMoveException("Illegal Move");
    }
    return true;
  }

  public void updateSafeZones() {
    // update zone matrix
  }

  @Override
  public String toString() {
    return "   " + chessPieceType.toString() + (isTeamWhite? " W": " B") + "   ";
  }
}
