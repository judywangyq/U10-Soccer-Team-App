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
public class Main {

  /**
   * Driver class. Create MVC objects.
   * @param args not used.
   */
  public static void main(String[] args) {
    TeamModel model = new TeamModel("JJ Team");
    ControllerImpl controller = new ControllerImpl(model);
    ViewImpl view = new ViewImpl("Soccer Team", controller);
    //controller.setView(view);
  }

}

