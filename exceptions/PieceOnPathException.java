package chess.exceptions;

public class PieceOnPathException extends RuntimeException{
  public PieceOnPathException(){
    super("WARNING: Path is blocked by other piece");
  }
}
