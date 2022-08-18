package chess.pieces;

import chess.board.Board;

public abstract class ChessPiece {
  protected int rowPosition;
  protected int columnPosition;
  protected boolean isTeamWhite;

  protected ChessPiece(int rowPosition, int columnPosition, boolean isTeamWhite) {
    this.rowPosition = rowPosition;
    this.columnPosition = columnPosition;
    this.isTeamWhite = isTeamWhite;
  }

  public int getRowPosition(){
    return rowPosition;
  }

  public int getColumnPosition(){
    return columnPosition;
  }

  public boolean isTeamWhite(){
    return isTeamWhite;
  }
  
  
  // public int getRowPosition();
  // public int getColumnPosition();
  // public boolean isTeamWhite();
  
  public abstract ChessPieceType getChessPieceType();
  public abstract boolean movePiece(int toRow, int toColumn);
  public abstract boolean checkForChessPieceSpecificExceptions(int toRow, int toColumn);

  protected boolean inspectForCheckSelf(int toRow, int toColumn) {
    // Board.displayBoard();
    int copyOfRowPosition = this.rowPosition;
    int copyOfColumnPosition = this.columnPosition;
    ChessPiece copyOfDestinationChessPiece = Board.getChessPiece(toRow, toColumn);
    Board.setChessPiece(toRow, toColumn, this);
    Board.setChessPiece(this.rowPosition, this.columnPosition, null);
    this.rowPosition = toRow;
    this.columnPosition = toColumn;
    // Board.displayBoard();
    if(Board.inspectAllOpponentPiecesForCheck(isTeamWhite)){
      return true;
    }
    this.rowPosition = copyOfRowPosition;
    this.columnPosition = copyOfColumnPosition;
    Board.setChessPiece(rowPosition, columnPosition, Board.getChessPiece(toRow, toColumn));
    Board.setChessPiece(toRow, toColumn, copyOfDestinationChessPiece);
    return false;
  }

  protected boolean inspectForCheckAgainst() {
    int[] kingPosition = this.isTeamWhite ? Board.getBlackKingPosition(): Board.getWhiteKingPosition();
    try {
      checkForChessPieceSpecificExceptions(kingPosition[0], kingPosition[1]);
    }
    catch(RuntimeException e) {
      return false;
    } 
    return true;
  }

}
