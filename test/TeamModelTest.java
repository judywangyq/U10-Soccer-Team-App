import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Before;
import org.junit.Test;
import soccerteam.Player;
import soccerteam.PlayerModel;
import soccerteam.Position;
import soccerteam.Team;
import soccerteam.TeamModel;

/**
 * Tests for soccer team.
 */
public class TeamModelTest {

  private Team team1;
  private Team team2;
  private Team team3;
  private Player player1;
  private Player player2;
  private Player player3;
  private Player player4;
  private Player player5;
  private Player player6;
  private Player player7;
  private Player player8;
  private Player player9;
  private Player player10;
  private Player player11;
  private Player player12;
  private Player player13;
  private Player player14;
  private Player player15;
  private Player player16;
  private Player player17;
  private Player player18;
  private Player player19;
  private Player player20;
  private Player player21;


  /**
   * Set up teams.
   */
  @Before
  public void setUp() {
    team1 = new TeamModel("JJ Team");
    team2 = new TeamModel("AA Team");
    team3 = new TeamModel("Red Team");
    player1 = new PlayerModel("Rachel", "Green",
        LocalDate.of(2015, 7, 24), Position.GOALIE, 2);
    player2 = new PlayerModel("Monica", "Galler",
        LocalDate.of(2016, 2, 04), Position.DEFENDER, 1);
    player3 = new PlayerModel("Hewitt", "Hsiao",
        LocalDate.of(2016, 12, 20), Position.FORWARD, 5);
    player4 = new PlayerModel("Sam", "Shen",
        LocalDate.of(2014, 4, 16), Position.DEFENDER, 3);
    player5 = new PlayerModel("Jeevin", "Gill",
        LocalDate.of(2017, 11, 16), Position.MIDFIELDER, 5);
    player6 = new PlayerModel("Eason", "Chen",
        LocalDate.of(2014, 8, 01), Position.FORWARD, 1);
    player7 = new PlayerModel("Marcarious", "Hui",
        LocalDate.of(2013, 9, 15), Position.GOALIE, 4);
    player8 = new PlayerModel("Chandler", "Bing",
        LocalDate.of(2015, 8, 10), Position.DEFENDER, 1);
    player9 = new PlayerModel("John", "Doe",
        LocalDate.of(2016, 3, 17), Position.MIDFIELDER, 5);
    player10 = new PlayerModel("Sarah", "Lee",
        LocalDate.of(2017, 5, 25), Position.FORWARD, 3);
    player11 = new PlayerModel("David", "Kim",
        LocalDate.of(2014, 11, 28), Position.GOALIE, 2);
    player12 = new PlayerModel("Emma", "Watson",
        LocalDate.of(2016, 9, 14), Position.DEFENDER, 3);
    player13 = new PlayerModel("Daniel", "Radcliffe",
        LocalDate.of(2017, 6, 30), Position.FORWARD, 4);
    player14 = new PlayerModel("Rupert", "Grint",
        LocalDate.of(2016, 11, 7), Position.MIDFIELDER, 2);
    player15 = new PlayerModel("Tom", "Felton",
        LocalDate.of(2016, 12, 3), Position.DEFENDER, 3);
    player16 = new PlayerModel("Evanna", "Lynch",
        LocalDate.of(2017, 8, 16), Position.MIDFIELDER, 5);
    player17 = new PlayerModel("Matthew", "Lewis",
        LocalDate.of(2015, 4, 5), Position.FORWARD, 2);
    player18 = new PlayerModel("Bonnie", "Wright",
        LocalDate.of(2015, 2, 17), Position.DEFENDER, 4);
    player19 = new PlayerModel("Katie", "Leung",
        LocalDate.of(2014, 7, 25), Position.MIDFIELDER, 1);
    player20 = new PlayerModel("Robert", "Pattinson",
        LocalDate.of(2016, 1, 13), Position.FORWARD, 5);
    player21 = new PlayerModel("Kristen", "Stewart",
        LocalDate.of(2017, 10, 19), Position.GOALIE, 2);


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

    team2.addPlayer(player1);
    team2.addPlayer(player2);
    team2.addPlayer(player3);
    team2.addPlayer(player4);
    team2.addPlayer(player5);
    team2.addPlayer(player6);
    team2.addPlayer(player7);
    team2.addPlayer(player8);
    team2.addPlayer(player9);
    team2.addPlayer(player10);
    team2.addPlayer(player11);
    team2.addPlayer(player12);
    team2.addPlayer(player13);
    team2.addPlayer(player14);
    team2.addPlayer(player15);
    team2.addPlayer(player16);
    team2.addPlayer(player17);
    team2.addPlayer(player18);
    team2.addPlayer(player19);
    team2.addPlayer(player20);
    team2.addPlayer(player21);

  }

  /**
   * test form a team of less than 10 players.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLessThan10PlayersToTeam() {
    team3.addPlayer(player1);
    team3.addPlayer(player2);
    team3.addPlayer(player3);
    team3.addPlayer(player4);
    team3.addPlayer(player5);
    team3.addPlayer(player6);
    team3.addPlayer(player7);

    List<Player> team3PlayerList = team3.getPlayerList();

    team3.formTeam(team3PlayerList);

  }

  /**
   * test form team of size of 11 players , nad than form 7 players starting line up.
   */
  @Test
  public void testFormTeam() {

    List<Player> team1PlayerList = team1.getPlayerList();

    Map<Player, Integer> team1WithJersey = team1.formTeam(team1PlayerList);

    assertEquals(11, team1WithJersey.size());

    Map<Player, List<Entry<Integer, Position>>> team1StartingLineUp =
        team1.formStartingLineUp(team1PlayerList, team1WithJersey);

    //    System.out.println("----------------------\n See who gets preferred position:\n");
    //    System.out.println("Updated startingPositionInfo map: " + team1StartingLineUp);
    //    for (Map.Entry<Player, List<Map.Entry<Integer, Position>>> entry :
    //    team1StartingLineUp.entrySet()) {
    //      Player player = entry.getKey();
    //      List<Map.Entry<Integer, Position>> positions = entry.getValue();
    //      System.out.println(player.getFirstName() + ":");
    //      for (Map.Entry<Integer, Position> position : positions) {
    //        System.out.println("- " + position.getKey() + " " + position.getValue().name());
    //      }
    //    }
    //    System.out.println("------end-------------------\n");

    assertEquals(7, team1StartingLineUp.size());
  }

  /**
   * test get team name.
   */
  @Test
  public void testGetTeamName() {
    assertEquals("JJ Team", team1.getTeamName());

  }

  /**
   * test form team of from more than 20 players. select the most skilled 20 players.
   */
  @Test
  public void testFormTeamOver20() {

    List<Player> team2PlayerList = team2.getPlayerList();

    Map<Player, Integer> team2WithJersey = team2.formTeam(team2PlayerList);

    assertEquals(20, team2WithJersey.size());

  }

  /**
   * test form team from more than 20 players list.
   */
  @Test
  public void testFormTeamOver20StartingLineUp() {

    List<Player> team2PlayerList = team2.getPlayerList();

    Map<Player, Integer> team2WithJersey = team2.formTeam(team2PlayerList);

    assertEquals(20, team2WithJersey.size());

    Map<Player, List<Entry<Integer, Position>>> team2StartingLineUp =
        team2.formStartingLineUp(team2PlayerList, team2WithJersey);

    assertEquals(7, team2StartingLineUp.size());

  }

  /**
   * test getplayerlist.
   */
  @Test
  public void testGetPlayerList() {
    List<Player> team1PlayerList = team1.getPlayerList();

    // Test that the list size is correct
    assertEquals(11, team1PlayerList.size());

    assertTrue(team1PlayerList.contains(player1));
    assertTrue(team1PlayerList.contains(player3));
    assertTrue(team1PlayerList.contains(player4));
    assertTrue(team1PlayerList.contains(player6));
    assertTrue(team1PlayerList.contains(player10));
  }


  /**
   * test toString.
   */
  @Test
  public void testToStringTeamOfficial() {

    List<Player> team1PlayerList = team1.getPlayerList();
    Map<Player, Integer> team1WithJersey = team1.formTeam(team1PlayerList);
    String team1WithJerseyString = team1.toStringTeamOfficial(team1WithJersey);

    //   System.out.println(team1WithJerseyString);

    assertEquals(11, team1WithJersey.size());


    assertEquals("Chandler Bing, jersey number: 6\n"
        + "Eason Chen, jersey number: 11\n"
        + "John Doe, jersey number: 12\n"
        + "Monica Galler, jersey number: 17\n"
        + "Jeevin Gill, jersey number: 10\n"
        + "Rachel Green, jersey number: 14\n"
        + "Hewitt Hsiao, jersey number: 9\n"
        + "Marcarious Hui, jersey number: 3\n"
        + "David Kim, jersey number: 1\n"
        + "Sarah Lee, jersey number: 4\n"
        + "Sam Shen, jersey number: 2", team1WithJerseyString);
  }

  @Test
  public void testToStringTeamStarting() {

    List<Player> team1PlayerList = team1.getPlayerList();
    Map<Player, Integer> team1WithJersey = team1.formTeam(team1PlayerList);

    Map<Player, List<Entry<Integer, Position>>> team1StartingLineUp =
        team1.formStartingLineUp(team1PlayerList, team1WithJersey);


    String team1StartingLineUpString = team1.toStringTeamStarting(team1StartingLineUp);


    assertEquals("Starting Lineup: \n"
        + "GOALIEs:\n"
        + "- Hui, Marcarious (#9)\n\n"
        + "DEFENDERs:\n"
        + "- Lee, Sarah (#1)\n"
        + "- Shen, Sam (#8)\n\n"
        + "MIDFIELDERs:\n"
        + "- Doe, John (#15)\n"
        + "- Gill, Jeevin (#14)\n"
        + "- Green, Rachel (#11)\n\n"
        + "FORWARDs:\n"
        + "- Hsiao, Hewitt (#2)\n", team1StartingLineUpString);
  }

}