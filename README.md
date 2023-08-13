## Overview

This program is a software solution to build soccer teams for children under ten years old (U10). 

The U10 team must have a minimum of 10 players and a maximum of 20. 
- If there are less than 10 players, the user will be informed that the team cannot be created unless more players are added. 
- If the team has more than 20 players, we only choose the first 20 most skilled players to form official team.
- Each player gets a random unique number(from 1-20) as jersey number. Jersey numbers are unique, randomly assigned, and cannot be changed once created.

U10 soccer teams have seven players on the field. The best players are usually selected as the starting lineup and the remaining players are on the bench.
- If possible, these players will be assigned their preferred positions, if their preferred position is not available, they wil be assigned to the first empty position.
- The remaining players are considered to be on the bench. They are not assigned positions.

To form the starting lineup, click the "Form Starting Line Up" button. The program will automatically select the best players for each position and display them in a list. The 7 starting lineup players will be consist of:
- 1 Goalie
- 2 Defenders
- 3 Midfielders
- 1 Forward

The program displays 2 lists:
- A list of all the players in the team. The list displays players': first name, last name, jersey number. The list is sorted in alphabetical order (last name).
- A list of the starting lineup: The list displays players': first name, last name, jersey number, and position. The list is sorted by position (goalie, defender, midfielder, forward). Players with the same position are ordered alphabetically, and then by jersey number.


## List of features

- Input team name, which will be displayed on the GUI
- Add players' information to the table and allow adding players
- Create the team and display the list
- Form the starting lineup and display the list


## Instructions to run the program

### How to run the jar file
- Locate the "SoccerTeam.jar" file in the res/ folder
- Open the file with "IntelliJ"
- Right-click the file and select "Run 'SoccerTeam.jar'"
- The GUI for the Soccer Team will appear

### How to Use the Program
- Fill in the table with players' information, including their first name, last name, date of birth (YYYY-MM-DD), preferred position (dropdown menu), and skill level (1-5, dropdown menu).
- If you need to add more players, click the "Add More Player" button to add one row at a time.
- Click the "Create Team" button once the table has enough players (at least 10) to form a team.
- After the team is created, review the team list and reassign jersey numbers if needed. The "Create Team" button can be clicked multiple times.
- Once you are satisfied with the team list, click the "Form Starting Line Up" button to form the official starting lineup. This button can only be clicked once to prevent changes to the starting lineup.
- Click the "Exit" button to close the program.

## Assumptions
- The program assumes that the date of birth column is in the format of YYYY-MM-DD.

## Limitations
- The program restricts the formation of the starting lineup to a single attempt to ensure consistency with the latest information generated from the "Create Team" button.

## Citations
- No citations needed.