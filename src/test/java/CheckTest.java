import database.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class CheckTest {

    @Test
    public void isEmpty() {

        Check check = new Check();

        boolean isEmpty = check.checkIfEmpty("", "", null, null);

        assertTrue(isEmpty);
    }

    @Test
    public void isNotEmpty() {

        Check check = new Check();

        boolean isNotEmpty = check.checkIfEmpty("test", "test",LocalDate.parse("2021-11-27"), LocalDate.parse("2020-11-27"));

        assertFalse(isNotEmpty);
    }

    @Test
    public void dateIsOk() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2020-11-27"), LocalDate.parse("2021-11-27"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsWrong() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2021-11-27"), LocalDate.parse("2020-11-27"));

        assertTrue(isWrong);

    }

    @Test
    public void isBusy() {

        Check check = new Check();

        boolean isBusy = check.checkIfBusy("Tamas", LocalDate.parse("2021-05-17"), LocalDate.parse("2021-05-18"));

        assertTrue(isBusy);
    }

    @Test
    public void isNotBusy() {

        Check check = new Check();

        boolean isBusy = check.checkIfBusy("Tamas", LocalDate.parse("2020-11-27"), LocalDate.parse("2020-11-28"));

        assertFalse(isBusy);
    }
}