package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Pet's owner name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOwnerName(String)}
 */
public class OwnerName {

    public static final String MESSAGE_CONSTRAINTS = "Owner names should only contain alphanumeric characters and "
            + "spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static final String SPECIAL_CHARACTERS = "+_.-";

    public final String value;


    /**
     * Constructs an {@code OwnerName}.
     *
     * @param ownerName A valid ownerName address.
     */
    public OwnerName(String ownerName) {
        requireNonNull(ownerName);
        checkArgument(isValidOwnerName(ownerName), MESSAGE_CONSTRAINTS);
        value = ownerName;
    }

    /**
     * Returns if a given string is a valid ownerName.
     */
    public static boolean isValidOwnerName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OwnerName // instanceof handles nulls
                && value.equals(((OwnerName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
