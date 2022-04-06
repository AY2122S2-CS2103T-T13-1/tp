package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ChargeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.charge.Charge;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String ownerName} into an {@code OwnerName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ownerName} is invalid.
     */
    public static OwnerName parseOwnerName(String ownerName) throws ParseException {
        requireNonNull(ownerName);
        String trimmedOwnerName = ownerName.trim();
        if (!OwnerName.isValidOwnerName(trimmedOwnerName)) {
            throw new ParseException(OwnerName.MESSAGE_CONSTRAINTS);
        }
        return new OwnerName(trimmedOwnerName);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String dateTime} into an {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param dateTime Date in String format of dd-MM-yyyy HH:mm.
     * @return Parsed LocalDate representation of input.
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseAppointmentDate(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        String trimmedDate;

        try {
            trimmedDate = trimmedDateTime.split(" ")[0];
        } catch (Exception e) {
            throw new ParseException("Missing date/time!");
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(trimmedDate, dateFormat);
        } catch (Exception e) {
            throw new ParseException("Appointment date should be entered in dd-MM-yyyy format!");
        }

    }

    /**
     * Parses a {@code String dateTime} into an {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param dateTime Date in String format of dd-MM-yyyy HH:mm.
     * @return Parsed LocalTime representation of input.
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseAppointmentTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        String trimmedTime;

        try {
            trimmedTime = trimmedDateTime.split(" ")[1];
        } catch (Exception e) {
            throw new ParseException("Missing date/time!");
        }


        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        try {
            return LocalTime.parse(trimmedTime, timeFormat);
        } catch (Exception e) {
            throw new ParseException("Appointment time should be entered in HH:mm format!");
        }

    }

    /**
     * Parses a {@code String attendanceDate} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Parsed local date.
     * @throws ParseException if the given attendance date is invalid.

     */
    public static LocalDate parseAttendanceDate(String attendanceDate) throws ParseException {
        requireNonNull(attendanceDate);
        String trimmedAttendanceDate = attendanceDate.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(trimmedAttendanceDate, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Attendance date should be in dd-MM-yyyy format!");
        }
    }

    /**
     * Checks if a keyword follows after the filter field. Returns keyword as String.
     * @param trimmedArgs Arguments to be parsed.
     * @return keyword as String.
     * @throws ParseException Throws exception if keyword is absent.
     */
    public static String parseFilterArgs(String trimmedArgs) throws ParseException {
        String[] argSplitter = trimmedArgs.split("/");
        if (argSplitter.length != 2) {
            throw new ParseException("You did not provide a keyword!");
        }
        return argSplitter[1];
    }

    /**
     * Parses a {@code String filterDate} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Parsed local date.
     * @throws ParseException if the given filter date is invalid.

     */
    public static LocalDate parseFilterDate(String filterDate) throws ParseException {
        requireNonNull(filterDate);
        String trimmedAttendanceDate = filterDate.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(trimmedAttendanceDate, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Filter date provided should be in dd-MM-yyyy format!");
        }
    }

    /**
     * Parses a {@code String date} into an {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param date Date in String format of dd-MM-yyyy HH:mm.
     * @return Parsed LocalDateTime representation of input.
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseFilterAppointmentDate(String date) throws ParseException {
        try {
            return parseFilterDate(date);
        } catch (Exception e) {
            throw new ParseException("Appointment date provided for filter should be entered in dd-MM-yyyy format!");
        }
    }

    /**
     * Parses a {@code String pickUpTime} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Parsed local time.
     * @throws ParseException if the given {@code Diet} is invalid.
     */
    public static LocalTime parsePickUpTime(String pickUpTime) throws ParseException {
        requireNonNull(pickUpTime);
        String trimmedPickUpTime = pickUpTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(trimmedPickUpTime, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Pick up time should be in HH:mm format!");
        }
    }

    /**
     * Parses a {@code String dropOffTime} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Parsed local time.
     * @throws ParseException if the given {@code Diet} is invalid.
     */
    public static LocalTime parseDropOffTime(String dropOffTime) throws ParseException {
        requireNonNull(dropOffTime);
        String trimmedDropOffTime = dropOffTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(trimmedDropOffTime, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException("Drop off time should be in HH:mm format!");
        }
    }

    /**
     * Parses a {@code String chargeDate} into an {@code ChargeDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Parsed charge date.
     * @throws ParseException if the given charge date is invalid.

     */
    public static YearMonth parseChargeDate(String chargeDate) throws ParseException {
        requireNonNull(chargeDate);
        String trimmedChargeDate = chargeDate.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        try {
            return YearMonth.parse(trimmedChargeDate, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(ChargeCommand.MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Parses a {@code String charge} into a {@code Charge}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Parsed charge.
     * @throws ParseException if the given charge is invalid.

     */
    public static Charge parseCharge(String charge) throws ParseException {
        requireNonNull(charge);

        String trimmedCharge = charge.trim();
        if (!Charge.isValidCharge(trimmedCharge)) {
            throw new ParseException(Charge.MESSAGE_INVALID_CHARGE_FORMAT);
        }
        return new Charge(trimmedCharge);
    }
}
