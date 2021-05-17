import database.Check;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class CheckIfDatesAreCorrect {

    @Test
    public void dateIsOk(){

        Check check = new Check();

        boolean isCorrect = check.checkDates(LocalDate.parse("2020-11-27"), LocalDate.parse("2021-11-27"));

        assertFalse(isCorrect);

    }

    @Test
    public void dateIsWrong(){

        Check check = new Check();

        boolean isWrong = check.checkDates(LocalDate.parse("2021-11-27"), LocalDate.parse("2020-11-27"));

        assertTrue(isWrong);

    }

}
