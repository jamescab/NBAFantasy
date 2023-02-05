package placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Athlete;
import model.FantasyPlayer;

public class FantasyPlayerTest {
    private FantasyPlayer fd;
    private FantasyPlayer fc;
    private Athlete m;

    @BeforeEach
    public void Before() {
        fd = new FantasyPlayer();
        fc = new FantasyPlayer("Warriors", "Blue");
        m = new Athlete("Mike", "PG");
        m.weeklyPointSum(5,1);
        m.addToTotalPoints();
    }

    @Test
    void testGetName() {
        assertEquals(fd.getName(),"default");
        assertEquals(fc.getName(),"Warriors");
    }

    @Test
    void testDraftToTeam() {
        fc.draftToTeam(m);
        assertEquals(fc.getFantasyTeam().get(0).getAthleteName(),"Mike");
    }

    @Test
    void testWeeklyPointSum() {
        fc.draftToTeam(m);
        fc.weeklyPointSum();
        assertEquals(fc.getPoints(),5);
    }
}
