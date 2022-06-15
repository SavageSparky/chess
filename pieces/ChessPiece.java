package chess.pieces;


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

}
