package chess.exceptions;

public class SameTeamException extends RuntimeException{
  public SameTeamException() {
    super("WARNING: The destination has your Team Piece");
  }
}
