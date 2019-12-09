package viewPage.Register.fxml;

import Database.DatabaseTranslator;
import com.amadeus.exceptions.ResponseException;
import java.io.File;
import java.io.FileWriter;

/**
 * This is the register page controller for the American Traveller Bucket list webapp.
 *  It switch the new user page and go back home page in the page.
 * Last Updated: 12/5/2019
 * @author Reagan Berhe
 */

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import Models.RegisterUser;
import viewPage.Main;

public class RegisterController {
    //View elements that we need user input from.
    @FXML private TextField fullNameRegField, zipCodeRegField;

    /**
     * Go back to the Main view.
     *
     * @throws IOException
     */
    @FXML private void goHome() throws IOException {
            Main.showMainView();
    }

    /**
     * Pulls and interprets the users input for name and zip code.
     *
     * @throws IOException
     * @throws ResponseException
     */
    @FXML private void registerNewUser() throws IOException, ResponseException {
        //Pull user input into variables
        String name = fullNameRegField.getText();
        String strZip = zipCodeRegField.getText();
        //Initializing the zip code to an invalid value.
        int intZip = 0;

        //Try to convert the string input for zip code into an integer.
        try{
            intZip = Integer.parseInt(strZip);
        }
        //Long in output that the input wasn't an integer.
        catch (Exception e){
            System.out.println("Zip input is not an Integer");
        }

        //Since the return array for getLocationFromZip defaults to the initialization value of 0, 0 if the first value is anything else the zip code entered is valid.
        if(DatabaseTranslator.getLocationFromZip(intZip)[0] != 0.0) {
            //Mark that the user is newly registered.
            Main.registered = true;
            //Create the user.
            Main.currentUser = RegisterUser.newUser(name, intZip);
            //Store the users initial data.
            RegisterUser.storeUser(Main.currentUser.getName(), Main.currentUser.getZipCode(), Main.currentUser.getAirportCode(), Main.currentUser.getCategories(), Main.currentUser.getUserResponses());
            //Go to the Gategoroes view.
            Main.showCategories();
        }
        //Else (If the first result was 0) prevent registration till the user enters a valid zip code.
        else {
            //Create and show an alert about the invalid zip code.
            Alert zipError = new Alert(AlertType.ERROR, "Invalid Zip Code", ButtonType.OK);
            zipError.show();
        }
    }
}
