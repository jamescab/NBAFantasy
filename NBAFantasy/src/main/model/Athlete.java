package model;

import exceptions.InvalidPointsError;
import ui.collect.PointCollector;

import javax.swing.*;

public class Athlete {
    private String athleteName;
    private String position;
    private int points;
    private int weeklyPoints;
    private FantasyPlayer owner;

    //EFFECTS: Constructor for athlete with set parameter (name)
    public Athlete(String name, String pos) {
        this.athleteName = name;
        this.position = pos;
        this.points = 0;
        this.owner = null;
    }

    //Constructor with set name, position and points
    public Athlete(String name, String pos, String points) {
        this.athleteName = name;
        this.position = pos;
        this.points = Integer.parseInt(points);
        this.owner = null;
    }

    //EFFECTS: returns athlete's name as a String
    public String getAthleteName() {
        return this.athleteName;
    }

    public String getPosition() {
        return this.position;
    }

    //EFFECTS: returns the athlete's total recorded points
    public int getPoints() {
        return this.points;
    }

    //REQUIRES: pointsGained > 0
    //EFFECTS: Records total number of points gained in the past week
    public void weeklyPointSum(int pointsGained, int weekNum) {
        PointCollector pointCollect = new PointCollector();
        boolean isValid = false;

        int ptsGain = pointsGained;
        while (!isValid) {
            try {
                if (ptsGain <= 0) {
                    throw new InvalidPointsError();
                } else {
                    isValid = true;
                    this.weeklyPoints = ptsGain;
                }
            } catch (InvalidPointsError e) {
                JOptionPane.showMessageDialog(null,"Invalid: enter a positive number: ");
                ptsGain = pointCollect.getPoints(this, weekNum);
            } finally {
                JOptionPane.showMessageDialog(null,"Entered");
            }
        }
    }

    public int getWeeklyPoints() {
        return this.weeklyPoints;
    }

    public void resetWeeklyPoints() {
        this.weeklyPoints = 0;
    }

    public void addToTotalPoints() {
        this.points = weeklyPoints + points;
    }

    //MODIFIES: this
    //EFFECTS: sets the owner of the athlete to the given FantasyPlayer
    public void setOwner(FantasyPlayer owner) {
        this.owner = owner;
    }

    //EFFECTS: returns athlete's owner
    public FantasyPlayer getOwner() {
        return owner;
    }

    //EFFECTS: returns whether or not Athlete has an owner already
    public boolean hasTeam() {
        return owner != null;
    }

    //MODIFIES: this, FantasyPlayer
    //EFFECTS: Athlete is dropped from current team and moved to another team
    public void moveToTeam(FantasyPlayer currentOwner, FantasyPlayer newOwner) {
        if (!newOwner.equals(currentOwner)) {
            if (hasTeam()) {
                currentOwner.dropPlayer(this);
            }
            setOwner(newOwner);
            newOwner.getTeam().put(athleteName, this);
        }
    }

    //MODIFIES: this
    //REQUIRES: sets owner of Athlete to a null value
    public void dropFromTeam() {
        setOwner(null);
    }

}
