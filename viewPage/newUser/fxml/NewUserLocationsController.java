package viewPage.newUser.fxml;

/**
 * Lasted Updated: 12/5/19
 * This is the Locations Controller class, used for getting input from a user on whether or not they are interested in visiting a set of locations.
 * @authors Stephen Hampson, Reagan Berhe
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import viewPage.Main;

import static Enumeration.Enumeration.*;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NewUserLocationsController {

    //Variables/view objects set via initialization.
    @FXML private Label categoryName;
    @FXML private Label location0;
    @FXML private Label location1;
    @FXML private Label location2;
    @FXML private Label location3;
    @FXML private Label location4;

    //View objects which correspond with TRUE (1) results. Since arrays default to 0 we don't need to pull FALSE (0) results.
    @FXML private RadioButton location0Selection1;
    @FXML private RadioButton location0Selection3;
    @FXML private RadioButton location1Selection1;
    @FXML private RadioButton location1Selection3;
    @FXML private RadioButton location2Selection1;
    @FXML private RadioButton location2Selection3;
    @FXML private RadioButton location3Selection1;
    @FXML private RadioButton location3Selection3;
    @FXML private RadioButton location4Selection1;
    @FXML private RadioButton location4Selection3;

    /**
     * Initialize the view.
     */
    @FXML private void initialize() {
        //Pull the category name from the enumerated array at index count and use it to set the lable.
        categoryName.setText(CURRENT_CATEGORIES[Main.count]);
        //Since there are curtrently 5 locations per category we need to pull the location name at the startIndex as well as +1, +2, +3, and +4 and set the matching labels.
        location0.setText(CURRENT_LOCATION_NAMES[Main.startIndex]);
        location1.setText(CURRENT_LOCATION_NAMES[Main.startIndex + 1]);
        location2.setText(CURRENT_LOCATION_NAMES[Main.startIndex + 2]);
        location3.setText(CURRENT_LOCATION_NAMES[Main.startIndex + 3]);
        location4.setText(CURRENT_LOCATION_NAMES[Main.startIndex + 4]);
    }

    /**
     * Method called by the submit button. Process the users input form the radio buttons/toggle groups and uses the setter from the user model to save the results.
     * Main.startIndex is set in the loopCategories method in the main class and is equal to Main.count * LOCATIONS_PER_CATEGORY.
     *
     * @throws IOException
     */
    @FXML private void submitResponses() throws IOException {
        //If the users selected option 1 or 3 for a given location mark the matching index TRUE.
        if (location0Selection1.isSelected() || location0Selection3.isSelected()){
            Main.currentUser.setSingleResponse(Main.startIndex, TRUE);
        }
        if (location1Selection1.isSelected() || location1Selection3.isSelected()){
            Main.currentUser.setSingleResponse(Main.startIndex + 1, TRUE);
        }
        if (location2Selection1.isSelected() || location2Selection3.isSelected()){
            Main.currentUser.setSingleResponse(Main.startIndex + 2, TRUE);
        }
        if (location3Selection1.isSelected() || location3Selection3.isSelected()){
            Main.currentUser.setSingleResponse(Main.startIndex + 3, TRUE);
        }
        if (location4Selection1.isSelected() || location4Selection3.isSelected()){
            Main.currentUser.setSingleResponse(Main.startIndex + 4, TRUE);
        }

        //Save the current results to the users file.
        Main.save();
        //Increment the category count.
        Main.count++;

        //If this wasn't the last category return to the loopCategories method.
        if (Main.count < CATEGORY_ARRAY_SIZE) {
            Main.loopCategories();
        }
        //Otherwise check the users input.
        else {
            //If the user did not indicate interest in any locations (all indicies in userResponses are FALSE)
            if (!Main.currentUser.checkResponseValid()){
                //Alert the user that by default we are selecting 5 random locations.
                Alert invalidSelection = new Alert(Alert.AlertType.ERROR, "No locations have been selected. Selecting 5 random locations.", ButtonType.OK);
                invalidSelection.showAndWait();
                //Select the 5 random locations.
                Main.currentUser.responseOverride();
            }

            //Alert the user we have saved their data.
            Alert saved = new Alert(Alert.AlertType.CONFIRMATION, "All location preferences have been saved.", ButtonType.OK);
            saved.show();
            //Call the Vacation Transition view.
            Main.showVacationTransition();
        }
    }
}
