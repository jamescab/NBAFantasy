package placeholder;

import model.FantasyPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Athlete;

public class AthleteTest {

    private Athlete ath;
    private FantasyPlayer f;

    @BeforeEach
    public void BeforeEach(){
        ath = new Athlete("Patrick", "PG");
        f = new FantasyPlayer("Test", "TestColour");
    }

    @Test
    void testGetAthleteName() {
        assertEquals(ath.getAthleteName(),"Patrick");
    }

    @Test
    void testGetPoints() {
        assertEquals(ath.getPoints(),0);
    }

    @Test
    void testGetWeeklyPoints() {
        assertEquals(ath.getWeeklyPoints(), 0);
    }

    @Test
    void testWeeklyPointSum() {
        ath.weeklyPointSum(5,1);
        assertEquals(ath.getWeeklyPoints(), 5);
    }

    @Test
    void testAddToTotalPoints() {
        ath.weeklyPointSum(6,1);
        ath.addToTotalPoints();
        assertEquals(ath.getPoints(),6);
    }

    @Test
    void testResetWeeklyPoints() {
        ath.weeklyPointSum(7,1);
        ath.addToTotalPoints();
        assertFalse(ath.getWeeklyPoints() == 0);
        ath.resetWeeklyPoints();
        assertEquals(ath.getWeeklyPoints(),0);
    }

    @Test
    void testSetOwner() {
        ath.setOwner(f);
        assertEquals("Test", ath.getOwner().getName());
    }

    @Test
    void testHasTeam() {
        assertFalse(ath.hasTeam());
        f.draftToTeam(ath);
        assertTrue(ath.hasTeam());
        f.dropPlayer(ath);
        assertFalse(ath.hasTeam());
        assertFalse(f.getFantasyTeam().contains(ath));
    }

    @Test
    void testMoveToTeam() {
        FantasyPlayer f1 = new FantasyPlayer();
        ath.moveToTeam(null, f);
        assertEquals(ath.getOwner(), f);
        ath.moveToTeam(f, f1);
        assertFalse(f.getTeam().containsKey(ath.getAthleteName()));
        assertTrue(f1.getTeam().containsKey(ath.getAthleteName()));
        assertEquals(ath.getOwner(), f1);
    }

}
