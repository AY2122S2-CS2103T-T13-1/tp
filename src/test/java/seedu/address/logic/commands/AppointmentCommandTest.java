package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;



class AppointmentCommandTest {

    private static final String APPOINTMENT_DATE_STUB = "02-04-2022";
    private static final String APPOINTMENT_TIME_STUB = "09:30";
    private static final String APPOINTMENT_LOCATION_STUB = "NUS Vet Clinic";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addAppointmentUnfilteredList_success() {
        Pet firstPerson = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(firstPerson)
                .withAppointment(APPOINTMENT_DATE_STUB,
                        APPOINTMENT_TIME_STUB,
                        APPOINTMENT_LOCATION_STUB).build();

        AppointmentCommand appointmentCommand =
                new AppointmentCommand(INDEX_FIRST_PET,
                        new Appointment(editedPerson.getAppointment().getDate(),
                                editedPerson.getAppointment().getTime(),
                                editedPerson.getAppointment().getLocation()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearAppointmentUnfilteredList_success() {
        Pet firstPerson = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(firstPerson).withAppointment().build();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PET,
                editedPerson.getAppointment());

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet firstPerson = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased()))
                .withAppointment(APPOINTMENT_DATE_STUB, APPOINTMENT_TIME_STUB, APPOINTMENT_LOCATION_STUB).build();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PET,
                new Appointment(editedPerson.getAppointment().getDate(),
                        editedPerson.getAppointment().getTime(),
                        editedPerson.getAppointment().getLocation()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(firstPerson, editedPerson);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        AppointmentCommand appointmentCommand = new AppointmentCommand(outOfBoundIndex,
                new Appointment());

        assertCommandFailure(appointmentCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**s
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPetList().size());

        AppointmentCommand appointmentCommand = new AppointmentCommand(outOfBoundIndex,
                new Appointment());

        assertCommandFailure(appointmentCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AppointmentCommand standardCommand = new AppointmentCommand(INDEX_FIRST_PET,
                new Appointment());
        // same values -> returns true
        AppointmentCommand commandWithSameValues = new AppointmentCommand(INDEX_FIRST_PET,
                new Appointment());
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_SECOND_PET,
                new Appointment())));
    }

}
