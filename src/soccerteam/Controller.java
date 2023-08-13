package soccerteam;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Interface for soccer team creating controller.
 */
public interface Controller {

  /*
   * Create a Team object.
   */
  //Team createTeam();

  // void setView(ViewImpl v);

  String getTeamName();

  /**
   * coach adding players to original list.
   * @param player player object.
   * @return an original list of all players.
   */
  List<Player> addPlayerToList(Player player);

  /**
   * selecting team line up.
   * @param teamOfficial the original team list;
   * @param assignJersyMap official team with jersey number;
   * @return map.
   */
  Map<Player, List<Entry<Integer, Position>>> formStartingLineUp(List<Player> teamOfficial,
      Map<Player, Integer> assignJersyMap);

  Map<Player, Integer> formOfficialTeam(List<Player> teamOriginalList);

  /**
   * Print the list of formal team.
   * @param formalTeam pass the formal team map to print
   * @return string
   */
  String printFormalTeam(Map<Player, Integer> formalTeam);

  /**
   * Print the list of starting lineup.
   * @param teamStarting the map of formal team starting line up.
   * @return string.
   */
  String printStartingLineUp(Map<Player, List<Map.Entry<Integer, Position>>> teamStarting);

  /**
   * Exit the program.
   */
  void exitProgram();
}
