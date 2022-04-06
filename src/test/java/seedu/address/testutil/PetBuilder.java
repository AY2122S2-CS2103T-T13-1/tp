package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attendance.AbsentAttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Diet;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Pet objects.
 */
public class PetBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_OWNER_NAME = "Emma Lee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DIET = "";

    private Name name;
    private OwnerName ownerName;
    private Phone phone;
    private Address address;
    private Set<Tag> tags;
    private Diet diet;
    private Appointment appointment;
    private final AttendanceHashMap attendanceHashMap;

    /**
     * Creates a {@code PetBuilder} with the default details.
     */
    public PetBuilder() {
        name = new Name(DEFAULT_NAME);
        ownerName = new OwnerName(DEFAULT_OWNER_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        diet = new Diet(DEFAULT_DIET);
        appointment = new Appointment();
        attendanceHashMap = new AttendanceHashMap();
    }

    /**
     * Initializes the PetBuilder with the data of {@code petToCopy}.
     */
    public PetBuilder(Pet petToCopy) {
        name = petToCopy.getName();
        ownerName = petToCopy.getOwnerName();
        phone = petToCopy.getPhone();
        address = petToCopy.getAddress();
        diet = petToCopy.getDiet();
        appointment = petToCopy.getAppointment();
        tags = new HashSet<>(petToCopy.getTags());
        attendanceHashMap = petToCopy.getAttendanceHashMap();
    }

    /**
     * Sets the {@code Name} of the {@code Pet} that we are building.
     */
    public PetBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Pet} that we are building.
     */
    public PetBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Pet} that we are building.
     */
    public PetBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Pet} that we are building.
     */
    public PetBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code OwnerName} of the {@code Pet} that we are building.
     */
    public PetBuilder withOwnerName(String ownerName) {
        this.ownerName = new OwnerName(ownerName);
        return this;
    }

    /**
     * Sets the {@code Diet} of the {@code Pet} that we are building.
     */
    public PetBuilder withDiet(String diet) {
        this.diet = new Diet(diet);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Pet} that we are building to be empty.
     */
    public PetBuilder withAppointment() {
        this.appointment = new Appointment();
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Pet} that we are building with the input.
     */
    public PetBuilder withAppointment(String date, String time, String location) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate formattedDate = LocalDate.parse(date, dateFormatter);
        LocalTime formattedTime = LocalTime.parse(time, timeFormatter);
        this.appointment = new Appointment(formattedDate, formattedTime, location);
        return this;
    }

    /**
     * Sets the {@code PresentAttendanceEntry} of the {@code Pet} that we are building,
     * including transport arrangements.
     */
    public PetBuilder withPresentAttendanceEntry(String attendanceDate, String pickUpTime, String dropOffTime) {
        attendanceHashMap.addAttendance(
            new PresentAttendanceEntry(
                LocalDate.parse(attendanceDate),
                LocalTime.parse(pickUpTime),
                LocalTime.parse(dropOffTime)));
        return this;
    }

    /**
     * Sets the {@code PresentAttendanceEntry} of the {@code Pet} that we are building,
     * excluding transport arrangements.
     */
    public PetBuilder withPresentAttendanceEntry(String attendanceDate) {
        attendanceHashMap.addAttendance(
            new PresentAttendanceEntry(
                LocalDate.parse(attendanceDate),
                null,
                null
            )
        );
        return this;
    }

    /**
     * Sets the {@code AbsentAttendanceEntry} of the {@code Pet} that we are building.
     */
    public PetBuilder withAbsentAttendanceEntry(String attendanceDate) {
        attendanceHashMap.addAttendance(
            new AbsentAttendanceEntry(
                LocalDate.parse(attendanceDate)
            )
        );
        return this;
    }

    /**
     * Builds the {@code Pet} object with the data in the builder.
     *
     * @return a Pet object.
     */
    public Pet build() {
        return new Pet(name, ownerName, phone, address, tags, diet, appointment, attendanceHashMap);
    }

}
