import database.Check;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class CheckIsEmptyTest {

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
}