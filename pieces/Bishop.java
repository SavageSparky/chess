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
