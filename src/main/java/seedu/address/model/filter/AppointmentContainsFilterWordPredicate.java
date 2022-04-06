package seedu.address.model.filter;

import java.time.LocalDate;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.Pet;

public class AppointmentContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate {
    private final LocalDate appointmentDate;

    /**
     * Constructor class. Checks if filter word is 'today'. If so, sets {@code appointmentDate} to a
     * {@code LocalDate} of today's date. Else, parse filter word to be a {@code LocalDate}.
     * @param filterWord String keyword provided after filter field.
     * @throws ParseException Throws exception if keyword (date) is not in the correct format.
     */
    public AppointmentContainsFilterWordPredicate(String filterWord) throws ParseException {
        super(filterWord);
        if (filterWord.equals("today")) {
            this.appointmentDate = LocalDate.now();
        } else {
            this.appointmentDate = ParserUtil.parseFilterAppointmentDate(filterWord);
        }
    }

    @Override
    public boolean test(Pet pet) {
        Appointment petAppointment = pet.getAppointment();
        LocalDate petAppointmentDate = petAppointment.getDate();
        if (petAppointmentDate != null) {
            return petAppointmentDate.equals(this.appointmentDate);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        return (other instanceof AppointmentContainsFilterWordPredicate// instanceof handles nulls
                && appointmentDate.equals(((AppointmentContainsFilterWordPredicate) other)
                .appointmentDate)); // state check
    }
}
