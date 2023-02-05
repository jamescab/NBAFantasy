package ExceptionTests;

import exceptions.InvalidPointsError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.fail;

public class exceptionTests {

    @Test
    void testInvalidInputException() {
        String test = "a";
        int testInt = 0;
        try {
            testInt++;
            if (!test.equals("a")) {
                throw new InvalidInputException();
            } else {
            }
        } catch (InvalidInputException e) {
            fail();
        }
        assertTrue(testInt == 1);

        test = "b";
        try {
            testInt++;
            if (!test.equals("a")) {
                throw new InvalidInputException();
            } else {
                fail();
            }
        } catch (InvalidInputException e) {
        }
        assertTrue(testInt == 2);

    }

    @Test
    void testInvalidPointsError() {
        int test = 0;
        int testInt = 0;
        try {
            testInt++;
            if (test != 0) {
                throw new InvalidPointsError();
            }
        } catch (InvalidPointsError e) {
            fail();
        }
        assertTrue(testInt == 1);

        test = 1;
        try {
            testInt++;
            if (test != 0) {
                throw new InvalidPointsError();
            } else {
                fail();
            }
        } catch (InvalidPointsError e) {
        }
        assertTrue(testInt == 2);
    }
}
