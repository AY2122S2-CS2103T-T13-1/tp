package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonContainsTag;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags are as specified. "
            + "The specified tag names are case-insensitive and will be displayed as a list with index numbers.\n"
            + "Parameters: TAGNAME [MORE_TAGNAME]...\n"
            + "Example: " + COMMAND_WORD + " friend";

    private final PersonContainsTag tagName;

    public TagCommand(PersonContainsTag tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(tagName);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && tagName.equals(((TagCommand) other).tagName)); // state check
    }
}
