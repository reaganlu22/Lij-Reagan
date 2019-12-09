package viewPage.Home.fxml;

/**
 * This is the Home view controller for home page. It tracks the home buttons and the action handler
 * methods for each other page controller
 * Last Updated: 11/04/2019
 * @author Reagan Berhe
 */

import java.io.IOException;
import javafx.fxml.FXML;
import viewPage.Main;

public class HomeViewController {

    /**
     * This handles the login button on the home page that directs the user to login page.
     * @throws IOException
     */
    @FXML private void goLoginPage() throws IOException {
        Main.showLogin();
    }

    /**
     * This handles the register button on the home page that directs the user to register page.
     * @throws IOException
     */
    @FXML private void goRegisterPage() throws IOException {
        Main.showRegister();
    }

    /**
     * This handles the About us button on the home page that directs the user to about us page.
     * @throws IOException
     */
    @FXML private void goAboutUs() throws IOException {
        Main.showAboutUs();
    }

    /**
     * This handles the contact us button on the home page that directs the user to contact us page.
     * @throws IOException
     */
    @FXML private void goContactUs() throws IOException {
        Main.showContactUs();
    }
}
