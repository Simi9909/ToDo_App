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
    public void isNotEmpty2() {

        Check check = new Check();

        boolean isNotEmpty = check.checkIfEmpty("test2", "test2",LocalDate.parse("2021-11-28"), LocalDate.parse("2020-11-28"));

        assertFalse(isNotEmpty);
    }

    @Test
    public void isNotEmpty3() {

        Check check = new Check();

        boolean isNotEmpty = check.checkIfEmpty("test3", "test3",LocalDate.parse("2021-10-10"), LocalDate.parse("2020-11-11"));

        assertFalse(isNotEmpty);
    }

    @Test
    public void dateIsOk() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2020-11-26"), LocalDate.parse("2021-11-27"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsOk2() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2020-12-27"), LocalDate.parse("2021-12-28"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsOk3() {

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2020-11-27"), LocalDate.parse("2022-12-20"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsWrong() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2021-11-27"), LocalDate.parse("2020-11-27"));

        assertTrue(isWrong);

    }

    @Test
    public void dateIsWrong2() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2021-11-28"), LocalDate.parse("2020-11-27"));

        assertTrue(isWrong);

    }

    @Test
    public void dateIsWrong3() {

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2022-11-28"), LocalDate.parse("2020-10-02"));

        assertTrue(isWrong);

    }

    @Test
    public void isBusy() {

        Check check = new Check();

        boolean isBusy = check.checkIfBusy("Tamas", LocalDate.parse("2021-05-17"), LocalDate.parse("2021-05-18"));

        assertTrue(isBusy);
    }

    @Test
    public void isBusy2() {

        Check check = new Check();

        boolean isBusy = check.checkIfBusy("Tamas", LocalDate.parse("2021-05-18"), LocalDate.parse("2021-05-18"));

        assertTrue(isBusy);
    }

    @Test
    public void isNotBusy() {

        Check check = new Check();

        boolean isBusy = check.checkIfBusy("Tamas", LocalDate.parse("2020-11-27"), LocalDate.parse("2020-11-28"));

        assertFalse(isBusy);
    }

    @Test
    public void isNotBusy2() {

        Check check = new Check();

        boolean isBusy = check.checkIfBusy("Tamas", LocalDate.parse("2020-12-27"), LocalDate.parse("2020-12-28"));

        assertFalse(isBusy);
    }
}