package chess.board;

import chess.pieces.*;
import chess.exceptions.*;

public class Board {
  private static final ChessPiece[][] squares = new ChessPiece[8][8];
  static {
    for(int i=0; i<8; i++) {
      squares[1][i] = (new Pawn(1, i, false));     // generating BLACK PAWNs and assigning them.
      squares[6][i] = (new Pawn(6, i, true));      // generating WHITE PAWNs and assigning them.
    }
    // generating Rooks
    squares[0][0] = (new Rook(0, 0, false));
    squares[0][7] = (new Rook(0, 7, false));
    squares[7][0] = (new Rook(7, 0, true));
    squares[7][7] = (new Rook(7, 7, true));

    // generating Knights
    squares[0][1] = (new Knight(0, 1, false));
    squares[0][6] = (new Knight(0, 6, false));
    squares[7][1] = (new Knight(7, 1, true));
    squares[7][6] = (new Knight(7, 6, true));

    // generating Bishops
    squares[0][2] = (new Bishop(0, 2, false));
    squares[0][5] = (new Bishop(0, 5, false));
    squares[7][2] = (new Bishop(7, 2, true));
    squares[7][5] = (new Bishop(7, 5, true));

    // generating King
    squares[0][3] = (new King(0, 3, false));
    squares[7][3] = (new King(7, 3, true));

    // generating Queen
    squares[0][4] = (new Queen(0, 4, false));
    squares[7][4] = (new Queen(7, 4, true));

    for(int i=2; i<6; i++) {
      for(int j=0; j<8; j++) {
        squares[i][j] = null;
      }
    }
  }

  private Board() { } // construct to prevent creating instance

  public static ChessPiece getChessPiece(int rowPosition, int columnPosition) {
    return squares[rowPosition][columnPosition];
  }

  public static void setChessPiece(int rowPosition, int columnPosition, ChessPiece chessPiece) {
    squares[rowPosition][columnPosition] = chessPiece;
  }


  public static void displayBoard(){
    System.out.print("  ");
    for(int i=0; i<8; i++){
      System.out.print("     " + i + "     ");
    }
    System.out.println();
    System.out.println("  _________________________________________________________________________________________");
    for(int i=0; i<8; i++){
      System.out.println("  |          |          |          |          |          |          |          |          |");
      System.out.print(i + " ");
      for(int j=0; j<8; j++){
        if(squares[i][j] != null){
          System.out.print("|" + squares[i][j]);
        }
        else{
          System.out.print("|          ");
        }
      }
      System.out.println("|");
      System.out.println("  |__________|__________|__________|__________|__________|__________|__________|__________|");
    }
  }

  private static boolean checkForBasicExceptions(ChessPiece chessPieceSource, ChessPiece chessPieceDestination, boolean isWhite) {
    try {
      if(chessPieceSource == null){
        throw new NullPointerException("The selected square has NO CHESSPIECE !");
      }
      else if(chessPieceSource.isTeamWhite() != isWhite) {
        throw new NotSameTeamException("This Chess Piece is NOT YOUR TEAM!");
      }
      else if(chessPieceDestination != null) {
        if(chessPieceDestination.isTeamWhite() == isWhite) {
          throw new SameTeamException();
        }
        else if(chessPieceDestination.getChessPieceType() == ChessPieceType.KING){
          throw new CannotKillKingException();
        }
      }
    }
    catch(NullPointerException | NotSameTeamException | SameTeamException | CannotKillKingException e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  public static boolean movePiece(int fromRow, int fromColumn, int toRow, int toColumn, boolean isWhite){
    ChessPiece chessPieceSource = squares[fromRow][fromColumn];
    ChessPiece chessPieceDestination = squares[toRow][toColumn];
    
    // checks if selected piece is AVAILABLE and then SAME TEAM and 
    // destination doesn't contain SAME TEAM PIECE and then NOT KING
    if(!checkForBasicExceptions(chessPieceSource, chessPieceDestination, isWhite)) {
      return false;
    }
    else {
      return chessPieceSource.movePiece(toRow, toColumn);
    }

  }




}
