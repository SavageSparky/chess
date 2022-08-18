package chess.exceptions;

public class MoveCausesSelfCheckException extends RuntimeException{
  public MoveCausesSelfCheckException() {
    super("WARNING: This move causes CHECK to your King");
  }
}
