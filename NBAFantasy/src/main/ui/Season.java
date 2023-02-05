package ui;

import model.*;
import ui.collect.PointCollector;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Season implements Loadable, Saveable {

    private ArrayList<Athlete> athleteRoster;
    private ArrayList<Athlete> addToRoster;
    private Map<String, FantasyPlayer> leaderboard = new HashMap<>();
    private Map<String, Athlete> leagueAthletes = new HashMap<>();
    private RosterStarter rosterStart;

    //EFFECTS: Constructor for a Season object
    public Season() {
        this.addToRoster = new ArrayList();
        this.rosterStart = new RosterStarter();
    }

    //EFFECTS: method in charge of running a whole season in its entirety
    public void runWholeSeason() throws IOException {

        load();
        JOptionPane.showMessageDialog(null, "Welcome to the new NBA Fantasy season!");
        rosterStart.rosterCreator();
        DraftStarter d = new DraftStarter(this, rosterStart);
        d.draft();
        updateLeaderboard();

        for (int i = 1; i < 3; i++) {
            athletePointCollection(i, rosterStart);
            fantasyPlayerPointCollection(rosterStart);
            displayStandings();
        }
        JOptionPane.showMessageDialog(null, pickWinner(rosterStart.getLeagueRoster()));
    }

    //EFFECTS: getter method for season's athleteRoster
    public ArrayList<Athlete> returnRoster() {
        return this.athleteRoster;
    }

    public Map<String, Athlete> returnLeagueMap() {
        return this.leagueAthletes;
    }

    //REQUIRES: valid text file that is not empty
    //MODIFIES: athleteRoster
    //EFFECTS: obtains all athletes from a text file and displays their info. Saves to Season object's athleteRoster.
    public void load() throws IOException {
        ArrayList<Athlete> roster = new ArrayList();
        List<String> lines = Files.readAllLines(Paths.get("./data/roster.txt"));
        Iterator rosterIterator = lines.iterator();

        while (rosterIterator.hasNext()) {
            String line = (String)rosterIterator.next();
            ArrayList<String> partsOfLine = splitOnSpace(line);

            String name = partsOfLine.get(0);
            String position = partsOfLine.get(1);
            String points = partsOfLine.get(2);

            Athlete athlete = new Athlete(name, position, points);
            roster.add(athlete);
            leagueAthletes.put(athlete.getAthleteName(), athlete);
        }
        this.athleteRoster = roster;
    }

    //REQUIRES: valid text file destination
    //MODIFIES: athleteRoster
    //EFFECTS: saves data of all existing players into a text file
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("./data/roster.txt", "UTF-8");

        if (addToRoster.isEmpty() == false) {
            for (Athlete athlete: addToRoster) {
                athleteRoster.add(athlete);
            }
        }

        for (Athlete athlete: athleteRoster) {
            String line = athlete.getAthleteName() + "/" + athlete.getPosition() + "/" + (athlete.getPoints());
            writer.println(line);
        }
        writer.close();
        addToRoster.clear();
    }

    //EFFECTS: helper method to separate String by spaces in a line
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("/");
        return new ArrayList(Arrays.asList(splits));
    }

    //MODIFIES: this
    //EFFECTS: Places all fantasy players in leaderboard hashmap
    public void updateLeaderboard() {
        for (FantasyPlayer fantasyPlayer: rosterStart.getLeagueRoster()) {
            leaderboard.put(fantasyPlayer.getName(), fantasyPlayer);
        }
    }

    //EFFECTS: Displays current points standings between players in GUI form
    public void displayStandings() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current standings: \n");
        for (String teamName: rosterStart.getTeamNameList()) {
            String name = leaderboard.get(teamName).getName();
            int points = leaderboard.get(teamName).getPoints();
            sb.append(name + " ").append(points).append('\n');
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    //EFFECTS: calculates player with most points and returns their name
    public String pickWinner(ArrayList<FantasyPlayer> participants) {
        int mostPoints = 0;
        String winner = "No winners.";
        for (FantasyPlayer f: participants) {
            if (f.getPoints() > mostPoints) {
                mostPoints = f.getPoints();
                winner = f.getName();
            } else if (f.getPoints() == mostPoints) {
                winner = winner + " and " + f.getName();
            }
        }
        return winner + " have won the season!";
    }

    //MODIFIES: Athlete.points
    //EFFECTS: Collects points gained by athletes over the week. Only does this for athletes in a fantasy team
    public void athletePointCollection(int weekNum, RosterStarter rosterStart) {
        JOptionPane.showMessageDialog(null, "Points collection for week " + weekNum);
        for (FantasyPlayer f: rosterStart.getLeagueRoster()) {
            for (Athlete athlete : f.getFantasyTeam()) {
                PointCollector pointCollect = new PointCollector();
                int pts = pointCollect.getPoints(athlete, weekNum);
                athlete.weeklyPointSum(pts, weekNum);
                athlete.addToTotalPoints();
            }
        }
    }

    //MODIFIES: FantasyPlayer.points
    //EFFECTS: Displays each FantasyPlayer's current fantasy scores
    public void fantasyPlayerPointCollection(RosterStarter rosterStart) {
        for (FantasyPlayer player: rosterStart.getLeagueRoster()) {
            player.weeklyPointSum();
        }
    }
}