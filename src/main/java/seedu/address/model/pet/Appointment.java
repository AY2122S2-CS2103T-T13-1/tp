package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a Pet's upcoming appointment in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment implements Comparable<Appointment> {

    /** Regex to match value attribute. eg: "Mar-04-2022 09:30 AM at NUS VET".*/
    public static final String VALIDATION_REGEX =
            "\\w{3}-\\d{2}-\\d{4} \\d{2}:\\d{2} \\w{2} \\w+(\\s\\w+){1,}";

    /** Date and time of appointment in "dd-MM-yyyy HH:mm" format.*/
    public final LocalDate date;
    public final LocalTime time;
    public final LocalDateTime dateTime;

    /** Location of appointment.*/
    public final String location;

    /**
     * Appointment details comprising information from dateTime and location.
     * Format of value: dateTime + " at " + location. To be reflected in GUI.
     */
    public final String value;

    /**
     * Constructs an {@code Appointment} from the user input details.
     * @param date in LocalDate format.
     * @param time in LocalTime format.
     * @param location of appointment.
     */
    public Appointment(LocalDate date, LocalTime time, String location) {
        requireAllNonNull(date, time, location);
        this.date = date;
        this.time = time;
        this.dateTime = date.atTime(time);
        this.location = location;
        this.value = formatDateTime(date, time) + " at " + location;
    }

    /**
     * Constructs an {@code Appointment} from the retrieved stored details.
     * @param value String representation of appointment details retrieved from storage.
     */
    public Appointment(String value) {
        requireNonNull(value);
        if (value.equals("")) {
            this.value = value;
            this.location = null;
            this.date = null;
            this.time = null;
            this.dateTime = null;
        } else {
            String[] appointmentDetails = value.split(" at ");
            String retrievedDateTime = appointmentDetails[0];
            String retrievedLocation = appointmentDetails[1];
            String retrievedDate = retrievedDateTime.split(" ", 2)[0].trim();
            String retrievedTime = retrievedDateTime.split(" ", 2)[1].trim();

            DateTimeFormatter formatOfRetrievedDate = DateTimeFormatter.ofPattern("MMM-dd-yyyy")
                .withLocale(Locale.ENGLISH);
            DateTimeFormatter formatOfRetrievedTime = DateTimeFormatter.ofPattern("h:mm a")
                .withLocale(Locale.ENGLISH);
            this.value = value;
            this.location = retrievedLocation;
            this.date = LocalDate.parse(retrievedDate, formatOfRetrievedDate);
            this.time = LocalTime.parse(retrievedTime, formatOfRetrievedTime);
            this.dateTime = date.atTime(time);
        }
    }

    /**
     * Constructs an {@code Appointment} for empty appointment.
     * To be used when appointment field is empty or when appointment is cleared.
     */
    public Appointment() {
        this.value = "";
        this.date = null;
        this.time = null;
        this.location = null;
        this.dateTime = null;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getLocation() {
        return this.location;
    }

    /**
     * Formats the LocalDateTime to String representation in format of "MMM-dd-yyyy h:mm a"
     * to be reflected in GUI and for storage.
     * @param date in LocalDate.
     * @param time in LocalTime.
     * @return String representation of dateTime in "MMM-dd-yyyy h:mm a".
     */
    private String formatDateTime(LocalDate date, LocalTime time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy")
            .withLocale(Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
            .withLocale(Locale.ENGLISH);
        String formattedDate = date.format(dateFormatter);
        String formattedTime = time.format(timeFormatter);
        return formattedDate + " " + formattedTime;
    }

    /**
     * Returns true if a given string is a valid appointment.
     * @param test The input string.
     * @return True if input matches VALIDATION_REGEX, False otherwise.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares appointment objects based on their date and time attributes.
     * @param other appointment to be compared with.
     * @return Value signifying in the difference between the comparing attribute.
     */
    @Override
    public int compareTo(Appointment other) {
        if (this.dateTime == null && other.getDateTime() == null) {
            return 0;
        } else if (this.dateTime != null && other.getDateTime() != null) {
            return this.dateTime.compareTo(other.getDateTime());
        } else if ((this.dateTime == null && other.getDateTime() != null)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Appointment
                && value.equals(((Appointment) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
