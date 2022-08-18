package chess.exceptions;

public class NotSameTeamException extends RuntimeException{
  public NotSameTeamException(){
    super("WARNING: This Chess Piece is NOT YOUR TEAM!");
  }
}
