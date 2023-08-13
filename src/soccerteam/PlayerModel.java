package soccerteam;

import java.time.LocalDate;
import java.time.Period;

/**
 * PlayModel class implements Player interface to create player objects.
 * Player object should include first , last name
 * DoB (throw exception is player older than 10 years old)
 * preferred position (goalie, defender, midfielder, forward)
 * Skill level(should between 1-5)
 * isSelctedStarting all set to false when object created. will turn into true if gets selected.
 */
public class PlayerModel implements Player {

  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private Position preferredPosition;
  private int skillLevel;
  private boolean isSelectedStarting = false;

  /**
   * Player Constructor.
   * @param firstName first name in string
   * @param lastName last name in string
   * @param dateOfBirth date of birth
   * @param preferredPosition preferred position enum
   * @param skillLevel integer
   */
  public PlayerModel(String firstName, String lastName, LocalDate dateOfBirth,
      Position preferredPosition, int skillLevel) {

    this.firstName = firstName;
    this.lastName = lastName;

    LocalDate todayDate = LocalDate.now();
    int age = Period.between(dateOfBirth, todayDate).getYears();

    if (age >= 10) {
      throw new IllegalArgumentException("Child more than 10 years "
          + "old can't be in children's soccer team!");
    }
    this.dateOfBirth = dateOfBirth;

    this.preferredPosition = preferredPosition;

    if (skillLevel < 1 || skillLevel > 5) {
      throw new IllegalArgumentException("Skill level should be within range 1-5!");
    }
    this.skillLevel = skillLevel;
  }

  @Override
  public String getFirstName() {
    return this.firstName;
  }

  @Override
  public String getLastName() {
    return this.lastName;
  }

  @Override
  public LocalDate getDoB() {
    return this.dateOfBirth;
  }

  @Override
  public Position getPreferredPosition() {
    return this.preferredPosition;
  }

  @Override
  public boolean isSelectedStarting() {
    this.isSelectedStarting = true;
    return true;
  }

  @Override
  public int getSkillLevel() {
    return this.skillLevel;
  }
}