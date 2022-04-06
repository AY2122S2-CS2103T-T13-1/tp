package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_OWNERNAME = "S@rah";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "82345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_OWNERNAME = "Sarah";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PET, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PET, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseOwnerName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOwnerName((String) null));
    }

    @Test
    public void parseOwnerName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOwnerName(INVALID_OWNERNAME));
    }

    @Test
    public void parseOwnerName_validValueWithoutWhitespace_returnsOwnerName() throws Exception {
        OwnerName expectedOwnerName = new OwnerName(VALID_OWNERNAME);
        assertEquals(expectedOwnerName, ParserUtil.parseOwnerName(VALID_OWNERNAME));
    }

    @Test
    public void parseOwnerName_validValueWithWhitespace_returnsTrimmedOwnerName() throws Exception {
        String ownerNameWithWhitespace = WHITESPACE + VALID_OWNERNAME + WHITESPACE;
        OwnerName expectedOwnerName = new OwnerName(VALID_OWNERNAME);
        assertEquals(expectedOwnerName, ParserUtil.parseOwnerName(ownerNameWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseAttendanceDate_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("22-03-2022", formatter);
        assertEquals(ParserUtil.parseAttendanceDate("22-03-2022"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendanceDate("2022-03-22"));
    }

    @Test
    public void parseFilterDate_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("22-03-2022", formatter);
        assertEquals(ParserUtil.parseFilterDate("22-03-2022"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseFilterDate("2022-03-22"));
    }

    @Test
    public void parseFilterAppDate_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("22-03-2022", formatter);
        assertEquals(ParserUtil.parseFilterAppointmentDate("22-03-2022"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseFilterAppointmentDate("2022-03-22"));
    }

    @Test
    public void parseAppDateTime_returnsLocalDate() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse("23-03-2022", formatter);
        assertEquals(ParserUtil.parseAppointmentDate("23-03-2022 12:00"), expectedLocalDate);
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentDate("0-03-2022 12:00"));
        // missing date or time
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime("23-03-2022"));
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime("00:00"));
    }

    @Test
    public void parseAppDateTime_returnsLocalTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime expectedLocalTime = LocalTime.parse("12:00", formatter);
        assertEquals(ParserUtil.parseAppointmentTime("23-03-2022 12:00"), expectedLocalTime);
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime("23-03-2022 0:00"));
        // missing date or time
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime("23-03-2022"));
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentTime("00:00"));
    }

    @Test
    public void parsePickUpTime_returnsLocalTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime expectedLocalTime = LocalTime.parse("09:00", formatter);
        assertEquals(ParserUtil.parsePickUpTime("09:00"), expectedLocalTime);
        assertThrows(ParseException.class, () -> ParserUtil.parsePickUpTime("0800"));
    }

    @Test
    public void parseDropOffTime_returnsLocalTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime expectedLocalTime = LocalTime.parse("18:00", formatter);
        assertEquals(ParserUtil.parseDropOffTime("18:00"), expectedLocalTime);
        assertThrows(ParseException.class, () -> ParserUtil.parseDropOffTime("0800"));
    }
}
