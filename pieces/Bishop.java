package chess.pieces;

import chess.exceptions.*;
import chess.board.Board;

public class Bishop extends ChessPiece {
  // private int rowPosition;
  // private int columnPosition;
  // private boolean isTeamWhite;

  private ChessPieceType chessPieceType = ChessPieceType.BISHOP;
  // private boolean isBishopWhite;

  public Bishop(int rowPosition, int columnPosition, boolean isTeamWhite){
    super(rowPosition, columnPosition, isTeamWhite);
    // this.isBishopWhite = isBishopWhite;
  }

  // public Bishop(int rowPosition, int columnPosition, boolean isTeamWhite){
  //   this.rowPosition = rowPosition;
  //   this.columnPosition = columnPosition;
  //   this.isTeamWhite = isTeamWhite;
  // }

  // // getters
  // @Override
  // public int getRowPosition() {
  //   return rowPosition;
  // }
  // @Override
  // public int getColumnPosition() {
  //   return columnPosition;
  // }
  // @Override
  // public boolean isTeamWhite() {
  //   return isTeamWhite;
  // }
  public ChessPieceType getChessPieceType(){
    return chessPieceType;
  }

  // // Bishop specific getter
  // public boolean isBishopWhite(){
  //   return isBishopWhite;
  // }

  // Bishop specific functionalities
  @Override
  public boolean movePiece(int toRow, int toColumn) {
    int rowDifference = this.getRowPosition() - rowPosition;
    int columnDiffernce = this.getColumnPosition() - columnPosition;
    if(Math.abs(rowDifference) == Math.abs(columnDiffernce)){
      for(int i=0; i<rowDifference; i++){
        
      }
    } 
  }


  @Override
  public String toString(){
    return " " + chessPieceType.toString() + (isTeamWhite? " W": " B") + " ";
  }

}
