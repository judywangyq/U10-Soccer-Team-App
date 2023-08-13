package soccerteam;

import java.time.LocalDate;

/**
 * A single player information class. Include First , last name, date of birth.
 * preferred positions and skill level.
 */
public interface Player {

  /**
   * Enter player's first name in string.
   * @return a string format of a player's first name
   */
  String getFirstName();

  /**
   * Enter player's last name in string.
   * @return a string format of a player's last name
   */
  String getLastName();

  /**
   * Enter player's Date of Birth.
   * @return a LocalDate of a player's date of birth
   */
  LocalDate getDoB();

  /**
   * Enter player's preferred position.
   * @return an ENUM position
   */
  Position getPreferredPosition();

  /**
   * Check whether a player is selected to start line up.
   * @return boolean
   */
  boolean isSelectedStarting();

  /**
   * Enter player's skill level.
   * @return a skill level integer
   * @throws IllegalArgumentException if the skill level is less or greater than 5.
   */
  int getSkillLevel();

}
