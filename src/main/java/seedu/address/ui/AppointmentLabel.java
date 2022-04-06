package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.Label;
import seedu.address.model.pet.Appointment;

public class AppointmentLabel {
    private static final String LABEL = "Appointment:";
    private static final String LABEL_COLOR = "-fx-background-color: #FFFF00";

    private AppointmentLabel() {}

    /**
     * Create an appointment label for GUI if there is any.
     * @param appointment of Pet
     * @return Appointment Label
     */
    public static Label createAppointmentLabel(Appointment appointment) {
        requireNonNull(appointment);
        Label appLabel = new Label();

        if (appointment.getDate() == null && appointment.getTime() == null) {
            return appLabel;
        }
        appLabel.setText(LABEL);
        appLabel.setStyle(LABEL_COLOR);
        return appLabel;
    }
}
