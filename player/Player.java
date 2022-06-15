package chess.player;

public class Player {
  private boolean isTeamWhite;

  private Player(boolean isTeamWhite) {
    this.isTeamWhite = isTeamWhite;
  }

  public boolean isTeamWhite() {
    return isTeamWhite;
  }

  public static Player[] generatePlayers() {
    Player[] players = new Player[2];
    players[0] = new Player(true);
    players[1] = new Player(false);
    return players;
  }
}
