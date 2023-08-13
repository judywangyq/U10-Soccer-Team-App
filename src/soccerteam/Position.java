package soccerteam;

/**
 * a ENUM for position.
 * include the maximun number for a position in starting line up.
 */
public enum Position {
  //GOALIE, DEFENDER, MIDFIELDER, FORWARD

  GOALIE(1),
  DEFENDER(2),
  MIDFIELDER(3),
  FORWARD(1);

  private int maxNumPlayers;

  private Position(int maxNumPlayers) {
    this.maxNumPlayers = maxNumPlayers;
  }

  /**
   * get maximun numbers.
   * @return integer
   */
  public int getMaxNumPlayers() {
    return maxNumPlayers;
  }
}

