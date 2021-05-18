import database.Check;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckIfBusy {

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
