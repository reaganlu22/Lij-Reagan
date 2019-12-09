package viewPage.Vacation.fxml;

import Database.DatabaseTranslator;
import static Enumeration.Enumeration.*;
import API.APITranslator;
import com.amadeus.exceptions.ResponseException;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import Models.CreateDestinationFromInput;
import Models.Destination;
import viewPage.Main;

/**
 * This is the Controller For the Vacation Transition view of the ATBL app.
 * This view can be loaded after logging in or registering and shows different text for ech situation so it must be initialized.
 *
 * Last Updated: 12/5/19
 * @author Reagan Berhe, Stephen Hampson
 */
public class VacationTransitionController {
    //The vairable/view object to be set
    @FXML private Label displayText;

    /**
     * Initialize the view.
     */
    @FXML private void initialize() {
        //If the user registered new data use this text.
        if (Main.registered) {
            displayText.setText("User Data Saved. \n \nDo you want to select a random destination?");
        }
        //Otherwise use this text.
        else {
            displayText.setText("User Data Loaded \n \nDo you want to select a random destination?");
        }
    }

    /**
     * Save the users data and exit.
     *
     * @throws IOException
     */
    @FXML void save() throws IOException {
        Main.save();
        Alert saved = new Alert(Alert.AlertType.CONFIRMATION, "All location preferences have been saved.", ButtonType.OK);
        saved.showAndWait();
        System.exit(0);
    }

    /**
     * Asks the User model to select a random destination based on the users stored responses.
     * If the user has visited all of their preferred locations allow them to register again or exit.
     * Otherwise load the selected location data and calculate the expected costs.
     *
     * @throws IOException
     * @throws ResponseException
     */
    @FXML void selectDestination() throws IOException, ResponseException {
        Main.selection = Main.currentUser.selectRandomDestination();

        //If the user has visited all of their locations.
        if (Main.selection == EMPTY){
            //Ask the user if the want to slect new locations (re-register).
            Alert complete = new Alert(Alert.AlertType.CONFIRMATION, "All of your prefered locations have been exhausted. Select new locations?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = complete.showAndWait();
            //If yes call the categories view.
            if (result.get() == ButtonType.YES) {
                Main.showCategories();
            }
            //If no exit.
            else {
                System.exit(0);
            }
        }
        //If the user hasn't visited all of their locations (selection isn't EMPTY)...
        else {
            //Show this alert so the user knows that the program is working. (API responses can take some time.)
            Alert wait = new Alert(Alert.AlertType.INFORMATION, "Please wait while we retrieve information on your selected destination.");
            wait.show();
            //Load the desitnation.
            Main.destination = loadLocation(DatabaseTranslator.mapFilename(Main.currentUser.getName(), Main.selection));
            //Get the expected costs set in Main's static variables.
            getFlightAndHotel();
            //Close the alert.
            wait.close();
            //Call the Vacation Final view.
            Main.showVacationFinal();
        }
    }

    /**
     * Simplifies the call for loading a selected location from file.
     *
     * @param _name The locations filename as a sting.
     * @return The location as a destination object.
     * @throws IOException
     */
    Destination loadLocation(String _name) throws IOException {
        //Get the data from the database text file.
        String locationFileData = DatabaseTranslator.getLocationData(_name);
        //Split the data into a string array.
        String[] locationData = locationFileData.split(INPUT_SPLIT);
        //Create the destination object.
        return CreateDestinationFromInput.createLocation(locationData);
    }

    /**
     * Simplifies the request for flight and hotel costs.
     *
     * @throws ResponseException
     * @throws IOException
     */
    void getFlightAndHotel() throws ResponseException, IOException{
        //Request the expected flight costs.
        Main.flight = APITranslator.getExpectedFlightCost(Main.currentUser.getAirportCode(), Main.destination.getAirportCode(), TEST_DATE);
        //Request the expected hotel costs.
        Main.hotel = APITranslator.getExpectedHotelCost(Main.destination.getCityCode());
    }
}
