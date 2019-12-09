package viewPage.Home.fxml;
/**
 * This class manages the interaction between contact us and home page.
 * Reagan Berhe
 * 11/04/2019
 */

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewPage.Main;

public class contactUsController {
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailAdressField;
    @FXML
    private TextField companyField;
    @FXML
    private TextField messageField;

    /**
     * .... THOSE METHODS ARE UNDER CONSTRUCTION/ NOT DECIDE BY GROUP YET!
     *
     * This method store the users full name for their comments on site
     */
    @FXML
    private void showFullName() {
    }

    /**
     * This method stores the users email for their comments on site.
     */
    @FXML
    private void showEmailField() {
    }

    /**
     * This method stores the users company name for their comment on site.
     */
    @FXML
    private void showNameCompanyField() {
    }

    /**
     * This method stores the users message/comment on the site.
     */
    @FXML
    private void showMessageField() {
    }

    /**
     * This method submits their message and returns to home page.
     */
    @FXML
    private void goHome() throws IOException {
        Main.showMainView();
    }

}
