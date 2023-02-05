package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FantasyPlayer {

    private String name;
    private String teamColour;
    private ArrayList<Athlete> draftedFantasyTeam;
    private Map<String, Athlete> team = new HashMap<>();
    private int points;
    private int weeklyPoints;

    //EFFECTS: Default constructor for player with no set parameters
    public FantasyPlayer() {
        this.name = "default";
        this.teamColour = "White";
        this.draftedFantasyTeam = new ArrayList();
    }

    //EFFECTS: Constructor for player with set parameters (name and colour)
    public FantasyPlayer(String nameEntry, String teamColourEntry) {
        this.name = nameEntry;
        this.teamColour = teamColourEntry;
        this.draftedFantasyTeam = new ArrayList();
    }

    //EFFECTS: returns object 'name' as a String
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns list of athletes on player object's fantasy team
    public ArrayList<Athlete> getFantasyTeam() {
        return this.draftedFantasyTeam;
    }

    //MODIFIES: fantasyTeam
    public void draftToTeam(Athlete draftedAthlete) {
        draftedFantasyTeam.add(draftedAthlete);
        draftedAthlete.moveToTeam(null, this);
    }

    //MODIFIES: this, Athlete
    //REQUIRES: drops athlete from player's team and removes player as athlete's current owner
    public void dropPlayer(Athlete athlete) {
        if (team.containsKey(athlete.getAthleteName())) {
            athlete.dropFromTeam();
            team.remove(athlete.getAthleteName());
            draftedFantasyTeam.remove(athlete);
        }
    }

    public Map<String, Athlete> getTeam() {
        return team;
    }

    public int getPoints() {
        return this.points;
    }

    //EFFECTS: Overridden equals method for hashMap
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FantasyPlayer that = (FantasyPlayer) o;
        return name.equals(that.name)
                && teamColour.equals(that.teamColour);
    }

    //MODIFIES: points
    public void weeklyPointSum() {
        for (Athlete athlete: draftedFantasyTeam) {
            this.weeklyPoints += athlete.getWeeklyPoints();
            athlete.resetWeeklyPoints();
        }
        this.points = points + this.weeklyPoints;
        this.weeklyPoints = 0;
    }
}