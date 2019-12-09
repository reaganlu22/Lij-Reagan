package viewPage.Vacation.fxml;

import Database.DatabaseTranslator;
import static Enumeration.Enumeration.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import viewPage.Main;

/**
 * This is the Controller For the Vacation Final view of the ATBL app.
 * Since several variables necessary to create this view are set while the program is running this view must be initialized.
 * This View presents information to the user on their random;y selected destination and allows them to accept and exit the program or reject it by simply exiting the program.
 *
 * Last Updated: 12/5/19
 * @author Reagan Berhe, Stephen Hampson
 */
public class VacationFinalController {
    //Variables/View objects set during initilization.
    @FXML private Label locationName;
    @FXML private Label flightCost;
    @FXML private Label hotelPrice;
    @FXML private Label description;

    /**
     * Initialize the view.
     */
    @FXML private void initialize() {
        //Format the cost values to currency values as a string.
        String fCost = String.format("%.2f", Main.flight);
        String hCost = String.format("%.2f", Main.hotel);

        //Set the values in the view.
        locationName.setText(Main.destination.getName());
        flightCost.setText(fCost);
        hotelPrice.setText(hCost);
        description.setText(Main.destination.getDescription());
    }

    /**
     * Accepts the chosen destination by flipping the value of that destination in the user's response array to false, then saves and exits.
     *
     * @throws IOException
     */
    @FXML void acceptDestination() throws IOException {
        //Flip selection to FALSE.
        Main.currentUser.setSingleResponse(Main.selection, FALSE);
        //Save and exit.
        Main.save();
        Alert accepted = new Alert(Alert.AlertType.CONFIRMATION, "Destination accepted. Click 'OK' to exit.", ButtonType.OK);
        accepted.showAndWait();
        System.exit(0);
    }

    /**
     * User has not accepted the destination and wants to save and exit.
     *
     * @throws IOException
     */
    @FXML void save() throws IOException {
        Main.save();
        Alert saved = new Alert(Alert.AlertType.CONFIRMATION, "All location preferences have been saved. Click 'OK' to exit.", ButtonType.OK);
        saved.showAndWait();
        System.exit(0);
    }
}
