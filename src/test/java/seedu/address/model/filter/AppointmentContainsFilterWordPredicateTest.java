package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PetBuilder;

public class AppointmentContainsFilterWordPredicateTest {

    private static final String PARSE_EX_THROWN_MESSAGE = "Should not have thrown parse exception.";
    private static final String DATE_STUB = "22-03-2022";
    private static final String TIME_STUB = "09:00";
    private static final String TODAY_LOCAL_DATE_STUB = "today";
    private static final String LOCATION_STUB = "NUS VET";

    @Test
    public void test_equals() {
        try {
            AppointmentContainsFilterWordPredicate firstPredicate =
                    new AppointmentContainsFilterWordPredicate(TODAY_LOCAL_DATE_STUB);
            AppointmentContainsFilterWordPredicate secondPredicate =
                    new AppointmentContainsFilterWordPredicate(DATE_STUB);

            // same object -> returns true
            assertTrue(firstPredicate.equals(firstPredicate));

            // same values -> returns true
            AppointmentContainsFilterWordPredicate firstPredicateCopy =
                    new AppointmentContainsFilterWordPredicate(TODAY_LOCAL_DATE_STUB);
            assertTrue(firstPredicate.equals(firstPredicateCopy));

            // different types -> returns false
            assertFalse(firstPredicate.equals(1));

            // null -> returns false
            assertFalse(firstPredicate.equals(null));

            // different pet -> returns false
            assertFalse(firstPredicate.equals(secondPredicate));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void test_appContainsDate_returnsTrue() {
        try {
            AppointmentContainsFilterWordPredicate predicate =
                    new AppointmentContainsFilterWordPredicate(DATE_STUB);
            assertTrue(predicate.test(new PetBuilder().withAppointment(DATE_STUB, TIME_STUB, LOCATION_STUB).build()));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void test_appDoesNotContainsDate_returnsFalse() {
        try {
            // With appointment
            AppointmentContainsFilterWordPredicate predicate =
                    new AppointmentContainsFilterWordPredicate(TODAY_LOCAL_DATE_STUB);
            assertFalse(predicate.test(new PetBuilder().withAppointment(DATE_STUB, TIME_STUB, LOCATION_STUB).build()));

            // Empty appointment
            assertFalse((predicate.test(new PetBuilder().build())));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }
}
