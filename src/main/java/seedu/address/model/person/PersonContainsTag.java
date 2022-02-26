package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsTag implements Predicate<Person> {
    private final List<String> tagNames;

    public PersonContainsTag(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(tag -> this.tagNames.contains(tag.tagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTag // instanceof handles nulls
                && tagNames.equals(((PersonContainsTag) other).tagNames)); // state check
    }

}
