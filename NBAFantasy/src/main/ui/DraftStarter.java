package ui;

import model.Athlete;
import model.FantasyPlayer;
import ui.collect.AthleteCollector;

import javax.swing.*;
import java.util.ArrayList;

public class DraftStarter {
    private RosterStarter rosterStart;
    private Season season;

    //Constructor
    public DraftStarter(Season season, RosterStarter rosterStart) {
        this.rosterStart = rosterStart;
        this.season = season;
    }

    //MODIFIES: this
    //EFFECTS: goes through each player in the fantasy roster and lets them pick one player per turn.
    public void draft() {
        ArrayList<FantasyPlayer> leagueRoster = rosterStart.getLeagueRoster();
        boolean draftLoop = true;
        int rounds = 0;

        while (draftLoop) {
            draftProcess(leagueRoster, season.returnRoster());
            rounds++;
            if (rounds == 2) {
                draftLoop = false;
            }
        }
        JOptionPane.showMessageDialog(null, "Draft Complete!");
    }

    //MODIFIES: fantasyTeam for each player
    //EFFECTS: allows a team to pick a player and add them to their team
    private void draftProcess(ArrayList<FantasyPlayer> playerRoster, ArrayList<Athlete> athleteRoster) {
        for (FantasyPlayer player: playerRoster) {
            JOptionPane.showMessageDialog(null, player.getName() + ": pick a player!");
            AthleteCollector athleteCollect = new AthleteCollector();
            String choice = athleteCollect.getAthleteName();
            player.draftToTeam(season.returnLeagueMap().get(choice));
        }
    }

}
