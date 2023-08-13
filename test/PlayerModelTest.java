import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import soccerteam.Player;
import soccerteam.PlayerModel;
import soccerteam.Position;

/**
 * Tests for soccer team Players.
 */
public class PlayerModelTest {
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

  /**
   * Set up some players.
   */
  @Before
  public void setUp() {
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
  }

  /**
   * test a player object is older than 10 years old.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerModelThrowsExceptionWhenAgeIsGreaterThan10() {
    new PlayerModel("Phoebe", "Buffay",
        LocalDate.of(2010, 5, 24), Position.DEFENDER, 3);
  }

  /**
   * test if an object skill level is not within 1-5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerModelThrowsExceptionWhenSkillLevelIsOutOfRange() {
    new PlayerModel("Chandler", "Bing",
        LocalDate.of(2015, 7, 24), Position.FORWARD, 6);
  }

  /**
   * test get player first name.
   */
  @Test
  public void testGetFirstName() {
    assertEquals("Rachel", player1.getFirstName());
    assertEquals("Marcarious", player7.getFirstName());
  }

  /**
   * test get last name.
   */
  @Test
  public void testGetLastName() {
    assertEquals("Shen", player4.getLastName());
    assertEquals("Gill", player5.getLastName());
  }

  /**
   * test get date of birth.
   */
  @Test
  public void testGetDoB() {
    assertEquals(LocalDate.of(2017, 5, 25), player10.getDoB());
    assertEquals(LocalDate.of(2014, 8, 01), player6.getDoB());
  }

  /**
   * Test get preferred position.
   */
  @Test
  public void testGetPreferredPositon() {
    assertEquals(Position.DEFENDER, player8.getPreferredPosition());
    assertEquals(Position.DEFENDER, player2.getPreferredPosition());
  }

  /**
   * test get skill level.
   */
  @Test
  public void testGetSkillLevel() {
    assertEquals(5, player9.getSkillLevel());
    assertEquals(1, player6.getSkillLevel());
  }

  /**
   * test whether isselectedstarting is set to false when initiated.
   * @throws NoSuchFieldException if such field does not found
   * @throws IllegalAccessException if such field is not accessable
   */
  @Test
  public void testIsSelectedStartingInitiatedAsFalse()
      throws NoSuchFieldException, IllegalAccessException {
    PlayerModel player12 = new PlayerModel("Jennifer", "Lee",
        LocalDate.of(2018, 1, 1), Position.FORWARD, 3);

    Field field = player12.getClass().getDeclaredField("isSelectedStarting");
    ((Field) field).setAccessible(true);

    boolean isSelectedStarting = (boolean) field.get(player12);
    assertFalse(isSelectedStarting);
  }

  /**
   * test if isselctedstarting field becomes true after calling.
   */
  @Test
  public void testisSelectedStartingTrue() {
    assertTrue(player5.isSelectedStarting());
    assertTrue(player11.isSelectedStarting());
  }

}