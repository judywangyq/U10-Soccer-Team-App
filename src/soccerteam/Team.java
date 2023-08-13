package soccerteam;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A single team information class. Include add/remove player,
 * assign random jersey number, etc methods.
 * Will be used to return teamlist, and startinglineup.
 */
public interface Team {

  /**
   * Get object team's name.
   * @return string team name
   */
  String getTeamName();

  /**
   * Add player to a team.
   * @param player is a player object from Player class.
   * @return a player list
   */
  List<Player> addPlayer(Player player);

  //  /**
  //   * Remove player from a team.
  //   * @param player is a player object from Player class.
  //   * @throws IllegalStateException if the team is empty
  //   * @throws IllegalArgumentException if the player is not already in the team
  //   */
  //  List<Player> removePlayer(Player player);

  /**
   * Get player list.
   * @return original players of the team.
   */
  List<Player> getPlayerList();

  /**
   * Form team. The team must have a minimum of 10 players and a maximum of 20.
   * @param team original team list
   * @return a team map with jersey numbers.
   * @throws IllegalArgumentException if there are less than 10 players. (the user must be informed
   *                                  that the team cannot be created unless more players are
   *                                  added)
   * @throws IllegalArgumentException if the team has more than 20 players. (the ones with the
   *                                  lowest skill level most be ignored so that we only have 20
   *                                  players)
   */
  Map<Player, Integer> formTeam(List<Player> team);

  /**
   * Form starting line up team(group of seven players). These are usually the most skilled players
   * in the team. If possible, these players will be assigned their preferred positions, but there
   * is no guarantee that this will happen.
   * @param teamOfficial  list of team official
   * @param assignJersyMap jersey number map
   * @return a team list.
   * @throws IllegalArgumentException if there are less or more than 7 players. (the ones with the
   *                                  lowest skill level most be ignored so that we only have 7
   *                                  players)
   */
  Map<Player, List<Entry<Integer, Position>>> formStartingLineUp(List<Player> teamOfficial,
      Map<Player, Integer> assignJersyMap);

  /**
   * tostring method for printing out the entire team.
   * Provided for every player: first name, last name, jersey number.
   * The list is sorted in alphabetical order (last name).
   * @param team1WithJersey the map after assigning jersey number of official players
   * @return string
   */
  String toStringTeamOfficial(Map<Player, Integer> team1WithJersey);

  /**
   * tostring method for printing out the starting line up team.
   * Provided for every player: first name, last name, jersey number, and position.
   * The list is sorted by position (goalie, defender, midfielder, forward).
   * Players with the same position should be ordered alphabetically, and then by jersey number.
   * @param team1StartingLineUp map of official starting line up team with
   *                            assign positions and their jersey number.
   * @return string
   */
  String toStringTeamStarting(Map<Player, List<Entry<Integer, Position>>> team1StartingLineUp);

}
