package ui;

import exceptions.InvalidInputException;
import model.FantasyPlayer;
import ui.collect.Info;
import ui.collect.PromptCollector;
import ui.collect.TeamCollector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RosterStarter {
    private Scanner in;
    private ArrayList<FantasyPlayer> leagueRoster;
    private ArrayList<String> teamNameList;

    //Constructor
    public RosterStarter() {
        leagueRoster = new ArrayList<>();
        teamNameList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: runs a loop to add players to the start of a fantasy league. Records team names and colours.
    public void rosterCreator() {
        in = new Scanner(System.in);
        ArrayList<FantasyPlayer> leagueRoster = new ArrayList();

        boolean keepAddingplayers = true;
        while (keepAddingplayers) {
            addPlayer(leagueRoster);
            keepAddingplayers = addMorePlayersPrompt();
        }

        JOptionPane.showMessageDialog(null, "All players in. Number of players this season is: " + leagueRoster.size());
        this.leagueRoster = leagueRoster;
    }

    //EFFECTS: Asks for user input in GUI on whether or not to keep adding players and returns boolean
    public boolean addMorePlayersPrompt() {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                PromptCollector promptCollect = new PromptCollector();
                String answer = promptCollect.getAnswer();
                if (!answer.equals("Y") && !answer.equals("N")) {
                    throw new InvalidInputException();
                } else if (answer.equals("N")) {
                    isValidInput = true;
                    return false;
                } else {
                    isValidInput = true;
                }
            } catch (InvalidInputException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
            }
        }
        return true;
    }

    //MODIFIES: leagueRoster
    //EFFECTS: prompts user input to enter a team name and colour. Adds player to leagueRoster
    private void addPlayer(ArrayList<FantasyPlayer> roster) {
        TeamCollector t = new TeamCollector();
        Info info = t.getTeamInfo();
        String teamName = info.getName();
        String teamColour = info.getColour();

        FantasyPlayer player = new FantasyPlayer(teamName, teamColour);
        roster.add(player);
        teamNameList.add(teamName);
    }

    public ArrayList<FantasyPlayer> getLeagueRoster() {
        return leagueRoster;
    }

    public ArrayList<String> getTeamNameList() {
        return  teamNameList;
    }
}
