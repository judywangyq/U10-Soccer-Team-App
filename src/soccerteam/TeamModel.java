package soccerteam;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * TeamModel class implements Team interface to create teams.
 * Each player will also have a jersey number once the team is created.
 * Select the most skilled 7 players to form starting lineup.
 */
public class TeamModel implements Team {

  private String teamName;
  private List<Player> playerList;
  private Map<Player, Integer> jerseyNumbers;
  private Map<Player, List<Entry<Integer, Position>>> startingPositionInfo;

  /**
   * Team constructor.
   * @param name team name
   */
  public TeamModel(String name) {
    this.teamName = name;
    this.playerList = new ArrayList<>();
    this.jerseyNumbers = new HashMap<>();
    this.startingPositionInfo = new HashMap<>();
  }

  @Override
  public String getTeamName() {
    return this.teamName;
  }

  @Override
  public List<Player> addPlayer(Player player) {
    playerList.add(player);
    return playerList;
  }

  //  @Override
  //  public List<Player> removePlayer(Player player) {
  //    if (playerList.size() < 1) {
  //      throw new IllegalStateException("Team is empty!");
  //    }
  //
  //    if (playerList.contains(player)) {
  //      playerList.remove(player);
  //    } else {
  //      throw new IllegalStateException("There is no such player in the team!");
  //    }
  //    return playerList;
  //  }

  @Override
  public List<Player> getPlayerList() {
    return playerList;
  }

  /*
   * Randomly assign Jersey number to a player. the number will be assigned to them once the team is
   * created
   * @throws IllegalArgumentException if the jersey number is less than 1 or greater than 20
   */
  private Map<Player, Integer> assignJerseyNum(List<Player> teamOfficial) {

    Random rand = new Random();
    Set<Integer> assignedNums = new HashSet<>();
    //Map<Player, Integer> jerseyNumbers = new HashMap<>();

    for (Player player : teamOfficial) {
      int num;
      do {
        num = rand.nextInt(20) + 1;
      } while (assignedNums.contains(num));
      assignedNums.add(num);
      jerseyNumbers.put(player, num);
    }
    return jerseyNumbers;
  }

  /*
   * sort players by skill level in descending order. So that can be used in selecting starting line
   * up or remove player from the team.
   *
   * @param playerList original unsorted playerList.
   */
  private List<Player> sortSkillLevel(List<Player> playerList) {
    List<Player> sortedList = new ArrayList<>(playerList);
    //Collections.sort(sortedList, Comparator.comparingInt(Player::getSkillLevel).reversed());
    Comparator<Player> bySkillLevel = Comparator.comparingInt(Player::getSkillLevel).reversed();
    Comparator<Player> byLastName = Comparator.comparing(Player::getLastName);
    sortedList.sort(bySkillLevel.thenComparing(byLastName));
    return sortedList;
  }

  /*
   * sort players alphabetically by last name. So that can be used in sorting starting line up.
   * @param the map with jersey num after form team.
   */
  private Map<Player, Integer> sortLastName(Map<Player, Integer> jerseyNumbers) {
    Map<Player, Integer> sortedJerseyNumbers = new TreeMap<>(
        Comparator.comparing(Player::getLastName));
    sortedJerseyNumbers.putAll(jerseyNumbers);
    return sortedJerseyNumbers;
  }

  /*
   * sort players alphabetically by last name. So that can be used in sorting starting line up.
   * @param the map with jersey num after form team.
   */
  //  private Map<Player, List<Map.Entry<Integer, Position>>> sortByPosition(
  //      Map<Player, List<Map.Entry<Integer, Position>>> startingLineupMap) {
  //    Map<Player, List<Map.Entry<Integer, Position>>> sortedStartingLineup =
  //        new TreeMap<>(Comparator
  //            .<Player, Position>comparing(p -> startingLineupMap.get(p).get(0).getValue())
  //            .thenComparing(Player::getLastName)
  //            .thenComparing(p -> startingLineupMap.get(p).get(0).getKey()));
  //    sortedStartingLineup.putAll(startingLineupMap);
  //    return sortedStartingLineup;
  //  }

  /*
   * sort players by positions (goalie, defender, midfielder, forward).
   * then by last name.
   * and then by jersey number.
   */
  private Map<Player, List<Map.Entry<Integer, Position>>> sortByPosition(
      Map<Player, List<Map.Entry<Integer, Position>>> startingLineupMap) {
    Map<Player, List<Map.Entry<Integer, Position>>> sortedStartingLineup =
        new TreeMap<>(Comparator.<Player, Position>comparing(p -> {
          Position pos = startingLineupMap.get(p).get(0).getValue();
          switch (pos) {
            case GOALIE:
              return Position.GOALIE;
            case DEFENDER:
              return Position.DEFENDER;
            case MIDFIELDER:
              return Position.MIDFIELDER;
            case FORWARD:
              return Position.FORWARD;
            default:
              throw new IllegalArgumentException("No Such position");
          }
        })
            .thenComparing(Player::getLastName)
            .thenComparing(p -> startingLineupMap.get(p).get(0).getKey()));
    sortedStartingLineup.putAll(startingLineupMap);
    return sortedStartingLineup;
  }

  @Override
  public Map<Player, Integer> formTeam(List<Player> playerList) {
    if (playerList.size() < 10) {
      throw new IllegalArgumentException("Teams must have at least 10 players!");
    } else {

      List<Player> sortedOriginalList = sortSkillLevel(playerList);
      List<Player> teamOfficial = new ArrayList<>();

      if (playerList.size() > 20) {
        for (int i = 0; i < 20; i++) {
          teamOfficial.add(sortedOriginalList.get(i));
        }
      } else {
        for (int i = 0; i < playerList.size(); i++) {
          teamOfficial.add(sortedOriginalList.get(i));
          //System.out.println("TeamClass sortedOriginalList size: " + sortedOriginalList.size());
          //teamOfficial.add(playerList.get(i));
          //System.out.println("TeamClass teamOfficial size: " + teamOfficial.size());
          //teamOfficial.addAll(playerList);
        }
      }
      Map<Player, Integer> teamJerseyNumbers = assignJerseyNum(teamOfficial);
      //System.out.println("TeamClass jerseyNumbers map size: " + teamJerseyNumbers.size());

      return sortLastName(teamJerseyNumbers);
    }
  }

  /*
   * from the original player list select 7 most skilled players to play starting line up.
   * @param teamOfficial original team list of all players
   * @return a list of 7 most skilled players.
   */
  private List<Player> select7Players(List<Player> teamOfficial) {

    List<Player> sortedTeamOfficial = sortSkillLevel(teamOfficial);
    List<Player> teamStarting = new ArrayList<>();

    for (int i = 0; i < 7; i++) {
      Player player = sortedTeamOfficial.get(i);
      player.isSelectedStarting();
      teamStarting.add(player);
    }
    return teamStarting;
  }

  /*
   * assigned starting line up players' preferred positions.
   * for those who don;t get their preferred positions, the position assigned will be random.
   */
  private Map<Player, List<Map.Entry<Integer, Position>>> assignStartingPosition(
      List<Player> teamStarting, Map<Player, Integer> jerseyNumbers) {

    // Sort the players based on their skill level
    Collections.sort(teamStarting, Comparator.comparingInt(Player::getSkillLevel).reversed());

    // Initialize the counters for each position
    Map<Position, Integer> positionCounters = new HashMap<>();
    positionCounters.put(Position.GOALIE, 0);
    positionCounters.put(Position.FORWARD, 0);
    positionCounters.put(Position.MIDFIELDER, 0);
    positionCounters.put(Position.DEFENDER, 0);

    // Initialize the map of starting position info
    //Map<Player, List<Map.Entry<Integer, Position>>> startingPositionInfo = new HashMap<>();

    // Assign the players to the positions based on their preferred positions
    // and the availability of positions
    for (Player player : teamStarting) {
      if (!player.isSelectedStarting()) {
        throw new IllegalArgumentException("This player is not selected to start line up!");
      }

      Position preferredPosition = player.getPreferredPosition();
      if (positionCounters.get(preferredPosition) < preferredPosition.getMaxNumPlayers()) {
        // Assign the player to their preferred position if it is available
        startingPositionInfo.computeIfAbsent(player, k -> new ArrayList<>())
            .add(Map.entry(jerseyNumbers.get(player), preferredPosition));
        positionCounters.put(preferredPosition, positionCounters.get(preferredPosition) + 1);
      } else {
        // Assign the player to the first available position
        for (Position position : Position.values()) {
          if (positionCounters.get(position) < position.getMaxNumPlayers()) {
            startingPositionInfo.computeIfAbsent(player, k -> new ArrayList<>())
                .add(Map.entry(jerseyNumbers.get(player), position));
            positionCounters.put(position, positionCounters.get(position) + 1);
            break;
          }
        }
      }
    }

    return startingPositionInfo;
  }


  @Override
  public Map<Player, List<Entry<Integer, Position>>> formStartingLineUp(List<Player> teamOfficial,
      Map<Player, Integer> assignJersyMap) {

    List<Player> startingPlayers = select7Players(teamOfficial);
    Map<Player, List<Entry<Integer, Position>>> teamStarting = new HashMap<>();
    //teamStarting.clear();
    teamStarting.putAll(assignStartingPosition(startingPlayers, assignJersyMap));

    //Map<Player, List<Entry<Integer, Position>>> teamStarting =
    //  assignStartingPosition(select7Players(teamOfficial), assignJersyMap);

    for (Map.Entry<Player, List<Entry<Integer, Position>>> entry :
        sortByPosition(teamStarting).entrySet()) {
      Player player = entry.getKey();
      List<Entry<Integer, Position>> positions = entry.getValue();
      String playerName = player.getFirstName() + " " + player.getLastName();
      System.out.println("TeamModel:" + playerName + ": " + positions);
    }

    return sortByPosition(teamStarting);
  }

  @Override
  public String toStringTeamOfficial(Map<Player, Integer> jerseyNumbers) {

    List<Player> players = new ArrayList<>(jerseyNumbers.keySet());

    StringBuilder toStringTeamOfficial = new StringBuilder();
    for (Player player : players) {
      toStringTeamOfficial.append(player.getFirstName())
          .append(" ")
          .append(player.getLastName())
          .append(", jersey number: ")
          .append(jerseyNumbers.get(player))
          .append("\n");
    }
    toStringTeamOfficial.append("\n");
    return toStringTeamOfficial.toString();
  }

  @Override
  public String toStringTeamStarting(Map<Player, List<Map.Entry<Integer, Position>>> teamStarting) {
    StringBuilder toStringTeamStarting = new StringBuilder();
    toStringTeamStarting.append("Starting Lineup:\n");

    // Loop through each position in order
    for (Position position : Position.values()) {
      toStringTeamStarting.append(position.toString() + "s:\n");

      // Loop through each player at this position
      for (Map.Entry<Player, List<Map.Entry<Integer, Position>>> entry : teamStarting.entrySet()) {
        Player player = entry.getKey();
        List<Map.Entry<Integer, Position>> playerData = entry.getValue();

        // Check if this player is at the current position
        for (Map.Entry<Integer, Position> data : playerData) {
          if (data.getValue() == position) {
            int jerseyNumber = data.getKey();
            toStringTeamStarting.append("- " + player.getLastName() + ", "
                + player.getFirstName() + " (#" + jerseyNumber + ")\n");
            break;
          }
        }
      }
      toStringTeamStarting.append("\n");
    }

    return toStringTeamStarting.toString();
  }
}
