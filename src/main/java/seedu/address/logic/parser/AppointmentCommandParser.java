package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAR;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Appointment;


/**
 * Parses input arguments and creates a new AppointmentCommand object.
 */
public class AppointmentCommandParser implements Parser<AppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentCommand
     * and returns an AppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_APPOINTMENT_DATE_TIME, PREFIX_APPOINTMENT_LOCATION, PREFIX_CLEAR);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE), pe);
        }

        Boolean isClearPresent = argMultimap.getValue(PREFIX_CLEAR).isPresent();
        Boolean areDateTimeAndLocationPresent = arePrefixesPresent(argMultimap,
                PREFIX_APPOINTMENT_DATE_TIME, PREFIX_APPOINTMENT_LOCATION);
        if (isClearPresent && !areDateTimeAndLocationPresent) {
            Appointment clearAppointment = new Appointment();
            return new AppointmentCommand(index, clearAppointment);
        } else if (areDateTimeAndLocationPresent && !isClearPresent) {
            LocalDate date = ParserUtil.parseAppointmentDate(
                    argMultimap.getValue(PREFIX_APPOINTMENT_DATE_TIME).get());
            LocalTime time = ParserUtil.parseAppointmentTime(
                    argMultimap.getValue(PREFIX_APPOINTMENT_DATE_TIME).get());
            String location = argMultimap.getValue(PREFIX_APPOINTMENT_LOCATION).get();

            Appointment appointment = new Appointment(date, time, location);
            return new AppointmentCommand(index, appointment);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
