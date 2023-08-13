package soccerteam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is a driver file.
 * the steps are creates some player objects, form team, assign jersey numbers
 * and select starting line up players and assign positions.
 */
public class MainModel {

  /**
   * Set up some objects.
   * @param args a string argument if run on terminal.
   */
  public static void main(String[] args) {
    // Create a team
    Team team1 = new TeamModel("JJ Team");
    System.out.println("A Team is Created, and the team's name is :" + team1.getTeamName() + "\n");

    Player player1 = new PlayerModel("Rachel", "Green",
        LocalDate.of(2015, 7, 24), Position.MIDFIELDER, 5);
    Player player2 = new PlayerModel("Monica", "Galler",
        LocalDate.of(2016, 2, 04), Position.DEFENDER, 5);
    Player player3 = new PlayerModel("Hewitt", "Hsiao",
        LocalDate.of(2016, 12, 20), Position.FORWARD, 5);
    Player player4 = new PlayerModel("Sam", "Shen",
        LocalDate.of(2014, 4, 16), Position.DEFENDER, 3);
    Player player5 = new PlayerModel("Jeevin", "Gill",
        LocalDate.of(2017, 11, 16), Position.MIDFIELDER, 1);
    Player player6 = new PlayerModel("Eason", "Chen",
        LocalDate.of(2014, 8, 01), Position.FORWARD, 1);
    Player player7 = new PlayerModel("Marcarious", "Hui",
        LocalDate.of(2013, 9, 15), Position.GOALIE, 4);
    Player player8 = new PlayerModel("John", "Doe",
        LocalDate.of(2015, 8, 10), Position.DEFENDER, 1);
    Player player9 = new PlayerModel("Chandler", "Bing",
        LocalDate.of(2016, 3, 17), Position.MIDFIELDER, 5);
    Player player10 = new PlayerModel("Sarah", "Lee",
        LocalDate.of(2017, 5, 25), Position.FORWARD, 3);
    Player player11 = new PlayerModel("David", "Kim",
        LocalDate.of(2014, 11, 28), Position.GOALIE, 2);
    Player player12 = new PlayerModel("Emma", "Wang",
        LocalDate.of(2016, 9, 14), Position.DEFENDER, 5);
    Player player13 = new PlayerModel("Yufei", "Wang",
        LocalDate.of(2017, 6, 30), Position.FORWARD, 4);
    Player player14 = new PlayerModel("Rupert", "Grint",
        LocalDate.of(2016, 11, 7), Position.MIDFIELDER, 2);

    // Adding players to list
    team1.addPlayer(player1);
    team1.addPlayer(player2);
    team1.addPlayer(player3);
    team1.addPlayer(player4);
    team1.addPlayer(player5);
    team1.addPlayer(player6);
    team1.addPlayer(player7);
    team1.addPlayer(player8);
    team1.addPlayer(player9);
    team1.addPlayer(player10);
    team1.addPlayer(player11);
    team1.addPlayer(player12);
    team1.addPlayer(player13);
    team1.addPlayer(player14);

    System.out.println("Add Players to original list, and form a team no "
        + "less than 10 players and no more than 20 players."
        + "\n"
        + "Jersey numbers are unique and are randomly assigned between 1-20 once team is formed."
        + "\n\n"
        + "Following is the formal team list, sorted by last name in alphabetical order:");

    // form Team ,and assign jersey number
    List<Player> team1PlayerList = team1.getPlayerList();
    Map<Player, Integer> team1WithJersey = team1.formTeam(team1PlayerList);
    String team1WithJerseyString = team1.toStringTeamOfficial(team1WithJersey);

    System.out.println(team1WithJerseyString);

    //Selecting starting Line up team, and assign positions
    Map<Player, List<Entry<Integer, Position>>> team1StartingLineUp =
        team1.formStartingLineUp(team1PlayerList, team1WithJersey);


    System.out.println("Select 7 most skilled players to play starting lineup."
        + "\n"
        + "These players will be assigned their preferred positions, "
        + "those who don't get their preferred positions "
        + "will be randomly assigned to empty positions."
        + "\n\n"
        + "Following is the selected team starting Line up, "
        + "sorted by sorted by position (goalie, defender, midfielder, forward). "
        + "Players with the same position are ordered alphabetically."
        + "\n");

    String team1StartingLineUpString = team1.toStringTeamStarting(team1StartingLineUp);
    System.out.println(team1StartingLineUpString);

  }
}

