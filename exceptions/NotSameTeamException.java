package chess.exceptions;

public class NotSameTeamException extends RuntimeException{
  public NotSameTeamException(String message){
    super(message);
  }
}
