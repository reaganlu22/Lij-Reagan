package viewPage.Login.fxml;

/**
 * This controller manages actions taken in the login view.
 *
 * Reagan Berhe, Stephen Hampson
 * 12/5/2019
 */

import Database.DatabaseTranslator;
import java.io.IOException;
import static Enumeration.Enumeration.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import Models.CreateUserFromInput;
import viewPage.Main;

public class LoginController {
    //This text field allows user to enter their name to log in.
    @FXML private TextField fullNameLoginField;

    /**
     * This method is used by the cancel button to return the user to the Main view.
     * @throws IOException
     */
    @FXML private void loginPageCancel() throws IOException {
        Main.showMainView();
    }

    /**
     * This method is used by the login button to see if a user of the name entered in the text field has registered with the ATBL.
     *
     * @throws IOException
     */
    @FXML private void loginLoginBtn() throws IOException {
        //Get the users input.
        String name = fullNameLoginField.getText();
        //Ask the Database translator if the program has data from a user of the same name. (names are converted to lowercase with no spaces. If yes....
        if(DatabaseTranslator.checkUser(name)){
            //Load the users data and create a user opject assigned to the static main variable 'currentUser'
            String userFileData = DatabaseTranslator.getUserData(name);
            String[] userData = userFileData.split(INPUT_SPLIT);
            Main.currentUser = CreateUserFromInput.createUser(userData);
            //Continue to the Vacation Transition view.
            Main.showVacationTransition();
        }
        //If no data is found go to the Registration view. Users can still navigate back to this page if they made an error.
        else{
            Main.showRegister();
        }
    }
}
