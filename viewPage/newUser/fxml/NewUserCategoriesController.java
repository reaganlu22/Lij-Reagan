package viewPage.newUser.fxml;

/**
 * Lasted Updated: 12/5/19
 * This is the Categories Controller class, used for getting input from a user on whether or not they are interested in given categories of vacation.
 * @authors Reagan Berhe, Stephen Hampson
 */

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import static Enumeration.Enumeration.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import viewPage.Main;

public class NewUserCategoriesController {

    //View objects which correspond with TRUE (1) results. Since arrays default to 0 we don't need to pull FALSE (0) results.
    @FXML private RadioButton OutdoorInterested;
    @FXML private RadioButton EntertainmentInterested;
    @FXML private RadioButton EducationalInterested;
    @FXML private RadioButton HistoricalInterested;
    @FXML private RadioButton CulturalIntereested;


    @FXML private void SubmitResponses() throws IOException {
        //Create array to store parsed values.
        int[] categories = new int[CATEGORY_ARRAY_SIZE];

        //Pull user responses from toggle groups. If the interested toggle is selected mark index TRUE.
        if (OutdoorInterested.isSelected()) {
            categories[OUTDOOR_ADVENTURES] = TRUE;
        }
        if (EntertainmentInterested.isSelected()) {
            categories[ENTERTAINMENT] = TRUE;
        }
        if (EducationalInterested.isSelected()) {
            categories[EDUCATIONAL_ENTERTAINMENT] = TRUE;
        }
        if (CulturalIntereested.isSelected()) {
            categories[CULTURAL] = TRUE;
        }
        if (HistoricalInterested.isSelected()) {
            categories[HISTORICAL] = TRUE;
        }

        //Replace users default categories array with user input.
        Main.currentUser.setCategories(categories);

        //Checck if the user wan't interested in any category. If the were, save results and proceed to loopCategories.
        if (Main.currentUser.checkCategoriesVaild()) {
            Main.save();
            Main.loopCategories();
        }
        //Otherwise alert the user that we will select 5 random locations for them in case they keep using the program despite not being interested.
        else {
            //Create and show the alert.
            Alert invalidSelection = new Alert(Alert.AlertType.ERROR, "No categories have been selected. Selecting 5 random locations.", ButtonType.OK);
            invalidSelection.showAndWait();
            //Select the 5 random locations.
            Main.currentUser.responseOverride();
            //Save and proceed to the Vacation Transition view.
            Main.save();
            Main.showVacationTransition();
        }


    }
}
