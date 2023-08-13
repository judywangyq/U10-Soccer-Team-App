package soccerteam;

/**
 * The interface for our view class.
 */
public interface View {

  /**
   * button to exit.
   */
  void addExitListener();

  /**
   * Button to add more player.
   */
  void addAddMorePlayerListener();

  /**
   * button to Add more Players.
   * one row in table is added.
   */
  void setAddMorePlayers();

  /**
   * Display team list.
   * @param controller pass controller as parameter.
   */
  void addCreateTeamButtonListener(ControllerImpl controller);

  /**
   * Display starting line up.
   * @param controller pass controller as parameter.
   */
  void addStartingLineUpButtonListener(ControllerImpl controller);

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached
   * to it, so that keyboard events will still flow through.
   */
  void resetFocus();

}
