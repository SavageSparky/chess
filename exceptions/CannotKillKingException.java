package chess.exceptions;

public class CannotKillKingException extends RuntimeException{
  public CannotKillKingException(){
    super("You cannot kILL the KING!");
  }
}
