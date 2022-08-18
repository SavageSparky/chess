package chess.board;

import java.util.List;
import java.util.ArrayList;
import chess.pieces.*;
import chess.exceptions.*;

public class Board {
  private static final ChessPiece[][] squares = new ChessPiece[8][8];
  private static List<ChessPiece> aliveWhitePieces = new ArrayList<>();
  private static List<ChessPiece> aliveBlackPieces = new ArrayList<>();
  private static final King whiteKing;
  private static final King blackKing;
  private static boolean checkMate = false;

  static {
    for(int i=0; i<8; i++) {
      squares[1][i] = (new Pawn(1, i, false));     // generating BLACK PAWNs and assigning them.
      squares[6][i] = (new Pawn(6, i, true));      // generating WHITE PAWNs and assigning them.
      aliveBlackPieces.add(squares[1][i]);
      aliveWhitePieces.add(squares[6][i]);
    }
    // generating Rooks
    squares[0][0] = (new Rook(0, 0, false));
    squares[0][7] = (new Rook(0, 7, false));
    squares[7][0] = (new Rook(7, 0, true));
    squares[7][7] = (new Rook(7, 7, true));
    aliveBlackPieces.add(squares[0][0]);
    aliveBlackPieces.add(squares[0][7]);
    aliveWhitePieces.add(squares[7][0]);
    aliveWhitePieces.add(squares[7][7]);

    // generating Knights
    squares[0][1] = (new Knight(0, 1, false));
    squares[0][6] = (new Knight(0, 6, false));
    squares[7][1] = (new Knight(7, 1, true));
    squares[7][6] = (new Knight(7, 6, true));
    aliveBlackPieces.add(squares[0][1]);
    aliveBlackPieces.add(squares[0][6]);
    aliveWhitePieces.add(squares[7][1]);
    aliveWhitePieces.add(squares[7][6]);

    // generating Bishops
    squares[0][2] = (new Bishop(0, 2, false));
    squares[0][5] = (new Bishop(0, 5, false));
    squares[7][2] = (new Bishop(7, 2, true));
    squares[7][5] = (new Bishop(7, 5, true));
    aliveBlackPieces.add(squares[0][2]);
    aliveBlackPieces.add(squares[0][5]);
    aliveWhitePieces.add(squares[7][2]);
    aliveWhitePieces.add(squares[7][5]);

    // generating Queen
    squares[0][3] = (new Queen(0, 3, false));
    squares[7][3] = (new Queen(7, 3, true));
    aliveBlackPieces.add(squares[0][3]);
    aliveWhitePieces.add(squares[7][3]);

    // generating King
    squares[0][4] = (new King(0, 4, false));
    squares[7][4] = (new King(7, 4, true));
    aliveBlackPieces.add(squares[0][4]);
    aliveWhitePieces.add(squares[7][4]);
    blackKing = (King)squares[0][4];
    whiteKing = (King)squares[7][4];

    for(int i=2; i<6; i++) {
      for(int j=0; j<8; j++) {
        squares[i][j] = null;
      }
    }

  }

  private Board() { } // constructor to prevent creating instances

  public static ChessPiece getChessPiece(int rowPosition, int columnPosition) {
    return squares[rowPosition][columnPosition];
  }

  public static void setChessPiece(int rowPosition, int columnPosition, ChessPiece chessPiece) {
    squares[rowPosition][columnPosition] = chessPiece;
  }

  public static int[] getBlackKingPosition() {
    return new int[]{blackKing.getRowPosition(), blackKing.getColumnPosition()};
  }
  public static int[] getWhiteKingPosition() {
    return new int[]{whiteKing.getRowPosition(), whiteKing.getColumnPosition()};
  }

  public static boolean getCheckMate() {
    return checkMate;
  }
  

  public static void displayBoard(){
    System.out.print("  ");
    for(int i=0; i<8; i++){
      System.out.print("      " + i + "      ");
    }
    System.out.println();
    System.out.println("  ________________________________________________________________________________________________________");
    for(int i=0; i<8; i++){
      for(int j=0; j<8; j++) {
        System.out.print("  |" + i + j + "        ");
      }
      System.out.println("  |");
      System.out.print(i + " ");
      for(int j=0; j<8; j++){
        if(squares[i][j] != null){
          System.out.print("|" + squares[i][j]);
        }
        else{
          System.out.print("|            ");
        }
      }
      System.out.println("|");
      System.out.println("  |____________|____________|____________|____________|____________|____________|____________|____________|");
    }
  }

  private static boolean checkForBasicExceptions(ChessPiece chessPieceSource, ChessPiece chessPieceDestination, boolean isWhite) {
    try {
      if(chessPieceSource == null){
        throw new NullPointerException("The selected square has NO CHESSPIECE !");
      }
      else if(chessPieceSource.isTeamWhite() != isWhite) {
        throw new NotSameTeamException();
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

  public static boolean inspectAllOpponentPiecesForCheck(boolean isTeamWhite) {
    int[] kingPosition =isTeamWhite ?  Board.getWhiteKingPosition():Board.getBlackKingPosition();
    
      if(isTeamWhite) {
        for(int i=0; i < aliveBlackPieces.size(); i++ ) {
          try {
            if(aliveBlackPieces.get(i).checkForChessPieceSpecificExceptions(kingPosition[0], kingPosition[1])) {
              throw new MoveCausesSelfCheckException();
            }
          }
          catch(IllegalMoveException | PieceOnPathException e) {
          }
          catch(MoveCausesSelfCheckException exception) {
            System.out.println(exception.getMessage());
            return false; 
          }
        }
      }
      else {
        for(int i=0; i < aliveWhitePieces.size(); i++ ) {
          try {
            if(aliveWhitePieces.get(i).checkForChessPieceSpecificExceptions(kingPosition[0], kingPosition[1])) {
              throw new MoveCausesSelfCheckException();
            }
          }catch(IllegalMoveException | PieceOnPathException e) {
          }
          catch(MoveCausesSelfCheckException exception) {
            System.out.println(exception.getMessage());
            return false; 
          }
        }
      }
      // Board.displayBoard();
    return true;
  }

  public static boolean isCheckMate(boolean isTeamWhite) {
    ChessPiece king = isTeamWhite? whiteKing: blackKing;
    
    return checkMate;
  }

}
