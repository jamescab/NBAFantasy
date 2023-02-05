package placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Season;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class loadSaveTest {

    private Season seasonLoad;
    private Season seasonSave;

    @BeforeEach
    public void beforeEach() {
        seasonLoad = new Season();
        seasonSave = new Season();
    }

    @Test
    void testLoad() throws IOException {
        seasonLoad.load();
        String testName = seasonLoad.returnRoster().get(0).getAthleteName();
        assertEquals(testName, "Steven Adams");
    }

    @Test
    void testSave() throws IOException {
        seasonSave.load();
        seasonSave.save();
        seasonSave.load();
        int lastAthlete = seasonSave.returnRoster().size() - 1;
        String testName = seasonSave.returnRoster().get(lastAthlete).getAthleteName();
        assertEquals(testName, "Tohi Smith-Milner");
    }
}
