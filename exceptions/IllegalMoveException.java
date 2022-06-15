package chess.exceptions;

public class IllegalMoveException extends RuntimeException{
  public IllegalMoveException(String message) {
    super("WARNING: " + message);
  }
}
