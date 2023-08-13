package soccerteam;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * This is the view for soccer team.
 */
public class ViewImpl extends JFrame implements View {

  public final JLabel teamNameDisplay;
  private final JTable addPlayerTable;
  private final JButton addMorePlayerButton;
  private final JButton createTeamButton;
  private final JButton selectStartingLineUpButton;
  private final JButton exitButton;
  private final JTextArea teamListText;
  private final JTextArea startingLineUpText;
  private final Controller controller;
  private boolean isTeamCreated = false;

  /**
   * This is the view of the soccer team MVC model.
   * @param caption the caption of the team
   * @param controller controller
   */
  public ViewImpl(String caption, ControllerImpl controller) {

    super(caption);

    //this.playerList = new ArrayList<>();

    this.controller = controller;

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    teamNameDisplay = new JLabel(controller.getTeamName());
    this.add(teamNameDisplay);


    String[] columnNames = {"First Name", "Last Name", "Date Of Birth yyyy-MM-dd",
        "Preferred Position", "Skill Level"};
    Object[][] data = new Object[11][5]; // 11 rows, 5 columns

    DefaultTableModel table = new DefaultTableModel(data, columnNames) {
      @Override
      public Class<?> getColumnClass(int column) {
        switch (column) {
          case 3: // Preferred Position column
            return Position.class;
          case 4: // Skill Level column
            return Integer.class;
          default:
            return String.class;
        }
      }
    };

    addPlayerTable = new JTable(table);

    // Set the preferred height of the row to accommodate the wrapped text
    addPlayerTable.setRowHeight(addPlayerTable.getRowHeight() * 2);

    JComboBox<Position> positionComboBox = new JComboBox<>(Position.values());
    DefaultCellEditor positionEditor = new DefaultCellEditor(positionComboBox);
    addPlayerTable.getColumnModel().getColumn(3).setCellEditor(positionEditor);

    JComboBox<Integer> skillLevelComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
    DefaultCellEditor skillLevelEditor = new DefaultCellEditor(skillLevelComboBox);
    addPlayerTable.getColumnModel().getColumn(4).setCellEditor(skillLevelEditor);

    addPlayerTable.getTableHeader().setVisible(true);

    JTableHeader header = addPlayerTable.getTableHeader();
    header.setFont(new Font("SansSerif", Font.BOLD, 12));

    JScrollPane scrollPane = new JScrollPane(addPlayerTable); // Add JTable to a JScrollPane
    scrollPane.setPreferredSize(new Dimension(600, 500)); // Set preferred size of JScrollPane

    this.add(scrollPane); // Add JScrollPane to main panel
    this.add(header, BorderLayout.NORTH); // Add JTable

    teamListText = new JTextArea();
    teamListText.setEditable(false);
    teamListText.setPreferredSize(new Dimension(200, 500));
    //    teamListText.setBounds(500, 500, 2000, 3000);
    //    teamListText.setBorder(BorderFactory.createTitledBorder("Formal Team List"));
    //    this.add(new JScrollPane(teamListText));
    //    this.add(teamListText);

    JScrollPane scrollPaneFormTeam = new JScrollPane(teamListText);
    scrollPaneFormTeam.setPreferredSize(new Dimension(200, 500));
    scrollPaneFormTeam.setBorder(BorderFactory.createTitledBorder("Formal Team List"));
    this.add(scrollPaneFormTeam);

    //teamListText.setText("Formal Team:");

    startingLineUpText = new JTextArea();
    startingLineUpText.setEditable(false);
    startingLineUpText.setPreferredSize(new Dimension(200, 300));
    startingLineUpText.setBounds(500, 500, 2000, 3000);
    startingLineUpText.setBorder(BorderFactory.createTitledBorder("Starting Line Up Team List"));
    this.add(new JScrollPane(startingLineUpText));
    this.add(startingLineUpText);
    //startingLineUpText.setText("Starting Line Up Team:");

    addMorePlayerButton = new JButton("Add More Player");
    addMorePlayerButton.setActionCommand("Add addition one more row to the table");
    addMorePlayerButton.setBounds(500, 500, 150, 30);
    this.add(addMorePlayerButton);
    addAddMorePlayerListener();

    createTeamButton = new JButton("Create Team");
    createTeamButton.setActionCommand("click to create team");
    this.add(createTeamButton);
    addCreateTeamButtonListener(controller);

    selectStartingLineUpButton = new JButton("Form Starting Line Up");
    selectStartingLineUpButton.setActionCommand("click to form selecting line up");
    this.add(selectStartingLineUpButton);
    addStartingLineUpButtonListener(controller);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);
    addExitListener();

    pack();
    setVisible(true);
  }

  @Override
  public void addExitListener() {
    //addMorePlayerButton.addActionListener(evt -> features.createPlayer());
    //createTeamButton.addActionListener(evt -> features.printFormalTeam());
    //selectStartingLineUpButton.addActionListener(evt -> features.printStartingLineUp());
    exitButton.addActionListener(evt -> {
      System.exit(0);
    });
  }

  @Override
  public void addAddMorePlayerListener() {
    System.out.println("clicked add player button");
    addMorePlayerButton.addActionListener(e -> setAddMorePlayers());
  }

  @Override
  public void setAddMorePlayers() {
    DefaultTableModel originalTable = (DefaultTableModel) addPlayerTable.getModel();
    originalTable.addRow(new Object[]{"", "", "", "", ""});
    //System.out.println("clicked add player button");
  }

  private List<Player> parseTableInfoToList() {
    List<Player> playerList = new ArrayList<>();

    DefaultTableModel tableInfo = (DefaultTableModel) addPlayerTable.getModel();
    int rowCount = tableInfo.getRowCount();
    for (int i = 0; i < rowCount; i++) {
      String firstName = (String) tableInfo.getValueAt(i, 0);
      if (firstName == null || firstName.isEmpty()) {
        // empty row encountered, break out of the loop
        break;
      }

      String lastName = (String) tableInfo.getValueAt(i, 1);
      //LocalDate dateOfBirth = (LocalDate) tableInfo.getValueAt(i, 2);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate dateOfBirth = LocalDate.parse((CharSequence) tableInfo.getValueAt(i, 2), formatter);

      Position preferredPosition = (Position) tableInfo.getValueAt(i, 3);
      int skillLevel = (int) tableInfo.getValueAt(i, 4);

      try {
        Player player = new PlayerModel(firstName, lastName, dateOfBirth, preferredPosition,
            skillLevel);
        playerList.add(player);
      } catch (IllegalArgumentException ex) {
        String errorMessage = "Invalid input for player " + firstName
            + " " + lastName + ":\n" + ex.getMessage();
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
      }
      //tableInfo.setRowCount(0);
      // return playerList;
    }
    for (Player player : playerList) {
      System.out.println(player.getFirstName() + " " + player.getLastName());
    }
    return playerList;
  }

  private Map<Player, Integer> createTeam(ControllerImpl controller) {
    //List<Player> originalPlayerList = parseTableInfoToList();


    Map<Player, Integer> officialPlayerList = null;
    try {
      //officialPlayerList = controller.formOfficialTeam(originalPlayerList);
      officialPlayerList = controller.formOfficialTeam(parseTableInfoToList());
    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      //return;
    }
    return officialPlayerList;
  }

  private Map<Player, List<Entry<Integer, Position>>> createStartingLineUp(
      ControllerImpl controller) {
    Map<Player, Integer> formerList = createTeam(controller);

    Map<Player, List<Entry<Integer, Position>>> startingLineUp;

    startingLineUp = controller.formStartingLineUp(parseTableInfoToList(), formerList);

    // Map<Player, List<Entry<Integer, Position>>> startingLineUp =
    // controller.formStartingLineUp(parseTableInfoToList(), formerList);

    return startingLineUp;
  }

  @Override
  public void addCreateTeamButtonListener(ControllerImpl controller) {
    createTeamButton.addActionListener(e -> {
      teamListText.setText("");

      //String teamList = controller.printFormalTeam(officialPlayerList);
      //String teamList = controller.printFormalTeam(createTeam(controller));
      String teamList = controller.printFormalTeam(createTeam(controller)).trim();
      System.out.println(teamList.toString());
      teamListText.setText(teamList);
      isTeamCreated = true;

      selectStartingLineUpButton.setEnabled(true);
    });
  }

  @Override
  public void addStartingLineUpButtonListener(ControllerImpl controller) {

    selectStartingLineUpButton.setEnabled(false);

    selectStartingLineUpButton.addActionListener(e -> {
      startingLineUpText.setText("");
      if (!isTeamCreated) {
        // show an error message or do nothing if the team is not created yet
        return;
      }

      //List<Player> originalPlayerList = parseTableInfoToList();
      //Map<Player, Integer> formalTeam = controller.formOfficialTeam(originalPlayerList);
      //Map<Player, Integer> formalTeam = controller.formOfficialTeam(parseTableInfoToList());

      Map<Player, List<Entry<Integer, Position>>> startingLineUp =
          createStartingLineUp(controller);

      //      for (Map.Entry<Player, List<Entry<Integer, Position>>> entry :
      //      startingLineUp.entrySet()) {
      //        Player player = entry.getKey();
      //        List<Entry<Integer, Position>> positions = entry.getValue();
      //        String playerName = player.getFirstName() + " " + player.getLastName();
      //        System.out.println(playerName + ": " + positions);
      //      }

      String startingLineUpList = controller.printStartingLineUp(startingLineUp);
      System.out.println(startingLineUpList.toString());
      startingLineUpText.setText(startingLineUpList);

      selectStartingLineUpButton.setEnabled(false);
    });
  }

  /*
    In order to make this frame respond to keyboard events, it must be within strong focus.
    Since there could be multiple components on the screen that listen to keyboard events,
    we must set one as the "currently focussed" one so that all keyboard events are
    passed to that component. This component is said to have "strong focus".

    We do this by first making the component focusable and then requesting focus to it.
    Requesting focus makes the component have focus AND removes focus from whoever had it
    before.
  */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


}
