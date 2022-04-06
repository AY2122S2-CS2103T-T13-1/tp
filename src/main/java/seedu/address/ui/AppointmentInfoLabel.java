package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import javafx.scene.control.Label;
import seedu.address.model.pet.Appointment;

public class AppointmentInfoLabel {
    private static final String APPOINTMENT_AFTER_TODAY_STYLE = "-fx-background-color: #c4c4c4";
    private static final String APPOINTMENT_BEFORE_TODAY_STYLE = "-fx-background-color: #ff595e";
    private static final String APPOINTMENT_TODAY_STYLE = "-fx-background-color: #90be6d";
    private static final LocalDate NOW = LocalDate.now();

    private AppointmentInfoLabel() {}

    /**
     * Create an appointment info label containing information of appointment for GUI if there is any.
     * If appointment date is today label will be green,
     * else if appointment date is in the future it will be grey,
     * else red to signify appointment date is over.
     * @param appointment Pet appointment
     * @return Label with appropriate color
     */
    public static Label createAppointmentLabel(Appointment appointment) {
        requireNonNull(appointment);

        Label appLabel = new Label();

        if (appointment.getDate() == null) {
            return appLabel;
        } else {
            LocalDate appDate = appointment.getDate();
            appLabel.setText(appointment.toString());
            if (appDate.equals(NOW)) {
                appLabel.setStyle(APPOINTMENT_TODAY_STYLE);
            } else if (appDate.isBefore(NOW)) {
                appLabel.setStyle(APPOINTMENT_BEFORE_TODAY_STYLE);
            } else {
                appLabel.setStyle(APPOINTMENT_AFTER_TODAY_STYLE);
            }
        }
        return appLabel;
    }
}
