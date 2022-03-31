package seedu.address.ui;

import static seedu.address.commons.util.AttendanceUtil.getPastWeekAttendance;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.AttendanceUtil;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Pet;

/**
 * An UI component that displays information of a {@code Pet}.
 */
public class PetCard extends UiPart<Region> {

    private static final String FXML = "PetListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Pet pet;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label ownerName;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label diet;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane attendanceTags;
    @FXML
    private FlowPane transportTags;
    @FXML
    private FlowPane dietLabel;
    @FXML
    private FlowPane appLabel;
    @FXML
    private FlowPane appointment;


    /**
     * Creates a {@code PetCode} with the given {@code Pet} and index to display.
     */
    public PetCard(Pet pet, int displayedIndex) {
        super(FXML);
        this.pet = pet;
        id.setText(displayedIndex + ". ");
        name.setText(pet.getName().fullName);
        ownerName.setText(pet.getOwnerName().value);
        phone.setText(pet.getPhone().value);
        address.setText(pet.getAddress().value);
        diet.setText(pet.getDiet().value);
        pet.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        AttendanceHashMap attendanceHashMap = pet.getAttendanceHashMap();
        getPastWeekAttendance(attendanceHashMap)
                .forEach(attendance -> {
                    attendanceTags
                            .getChildren()
                            .add(AttendanceTag.createAttendanceTag(attendance));
                });
        AttendanceUtil.getNextTwoDaysTransport(attendanceHashMap)
            .forEach(attendance -> {
                transportTags
                    .getChildren()
                    .add(TransportTag.createTransportTag(attendance));
            });
        dietLabel.getChildren().add(DietLabel.createDietLabel(pet.getDiet()));
        appLabel.getChildren().add(AppointmentLabel.createAppointmentLabel(pet.getAppointment()));
        appointment.getChildren().add(AppointmentInfoLabel.createAppointmentLabel(pet.getAppointment()));
    }

    public void setColour(String colour) {
        cardPane.setStyle("-fx-background-color: " + colour);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PetCard)) {
            return false;
        }

        // state check
        PetCard card = (PetCard) other;
        return id.getText().equals(card.id.getText())
                && pet.equals(card.pet);
    }
}
