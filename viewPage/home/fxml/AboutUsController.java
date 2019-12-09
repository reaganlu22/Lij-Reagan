package viewPage.Home.fxml;
/**
 * This class manages the interaction between About us and home page.
 * Reagan Berhe
 * 11/18/2019
 */

import java.io.IOException;
import javafx.fxml.FXML;
import viewPage.Main;

public class AboutUsController {
    @FXML
    private void aboutUsCancel() throws IOException {
        Main.showMainView();
    }
}
