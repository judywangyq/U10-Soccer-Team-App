package soccerteam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Controller. Implementation of the controller interface.
 */
public class ControllerImpl implements Controller {

  private TeamModel teamModel;
  // private Map<Player, Integer> officialTeam;
  // private ViewImpl view;

  /**
   * Constructor for the controller.
   * @param teamModel connects to teamModel.
   */
  public ControllerImpl(TeamModel teamModel) {
    this.teamModel = teamModel;
    //this.officialTeam = new HashMap<>();
    //this.view = view;
  }

  @Override
  public String getTeamName() {
    String teamName = teamModel.getTeamName();
    return teamName;
  }

  @Override
  public List<Player> addPlayerToList(Player player) {
    List<Player> playerList = teamModel.addPlayer(player);
    return playerList;
  }

  @Override
  public Map<Player, Integer> formOfficialTeam(List<Player> teamOriginalList) {
    Map<Player, Integer> officialTeam;
    officialTeam = teamModel.formTeam(teamOriginalList);
    return officialTeam;
  }

  @Override
  public Map<Player, List<Entry<Integer, Position>>> formStartingLineUp(
      List<Player> teamOriginalList, Map<Player, Integer> assignJersyMap) {

    Map<Player, Integer> formalTeamList = formOfficialTeam(teamOriginalList);

    Map<Player, List<Entry<Integer, Position>>> startingLineup;
    //startingLineup = teamModel.formStartingLineUp(teamOriginalList,assignJersyMap);
    startingLineup = teamModel.formStartingLineUp(teamOriginalList, formalTeamList);

    for (Map.Entry<Player, List<Entry<Integer, Position>>> entry : startingLineup.entrySet()) {
      Player player = entry.getKey();
      List<Entry<Integer, Position>> positions = entry.getValue();
      String playerName = player.getFirstName() + " " + player.getLastName();
      System.out.println("Controller:" + playerName + ": " + positions);
    }

    return startingLineup;
  }

  @Override
  public String printFormalTeam(Map<Player, Integer> formalTeam) {
    return teamModel.toStringTeamOfficial(formalTeam);
  }

  @Override
  public String printStartingLineUp(Map<Player, List<Map.Entry<Integer, Position>>> teamStarting) {
    return teamModel.toStringTeamStarting(teamStarting);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
