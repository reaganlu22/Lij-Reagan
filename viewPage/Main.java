package viewPage;

/**
 * This is the main controller for the American Travel Bucket List app.
 * This class contains the primary loader method, the extensions of this loader method for each view,
 * the static variables which hold the data used by multiple views during operation,
 * and a small amount of logic for saving data and picking the correct view values.
 *
 * Last Updated: 12/5/19
 * @author Reagan Berhe, Stephen Hampson
 */

import Database.DatabaseTranslator;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Models.User;
import static Enumeration.Enumeration.*;
import Models.Destination;

public class Main extends Application {
<<<<<<< HEAD
    //Static variables used by multiple views. Declared here so they can be imported then acceessed globally.
    public static User currentUser;
    public static Destination destination;
    public static int count = 0;
    public static int startIndex = 0;
    public static boolean registered = false;
    public static int selection = 0;
    public static double flight = 0.0;
    public static double hotel = 0.0;

    //Enumeration of the values and filepaths used to build the views used by the American Travel Bucket List.
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private static final String MAIN_VIEW_PATH = "Home/fxml/HomePageView.fxml";
    private static final String LOGINPAGE_PATH = "Login/fxml/LoginPageView.fxml";
    private static final String REGISTERPAGE_PATH = "Register/fxml/RegistrationPageView.fxml";
    private static final String ABOUTUSPAGE_PATH = "Home/fxml/AboutUsView.fxml";
    private static final String COTNTACT_US_PATH = "Home/fxml/ContactUsView.fxml";
    private static final String CATEGORY_PATH = "NewUser/fxml/NewUserCategories.fxml";
    private static final String LOCATIONS_PATH = "NewUser/fxml/NewUserLocations.fxml";
    private static final String TRANSITON_PATH = "Vacation/fxml/VacationTransition.fxml";
    private static final String FINAL_PATH = "Vacation/fxml/VacationFinal.fxml";

    /**
     * Called during the launch command inherent to Applications, this override allows us to define the primary stage and set the first view as the MainView.
     *
     * @param primaryStage The primary stage used by the views in the program.
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("American Travel Bucketlist");

        //Call the extension of the FXMLLoader for the MainView.
        showMainView();
    }

    /**
     * Generalized FXMLLoader accepts the relative filepath of the view to be loaded and executes the commands necessary to do so.
     *
     * @param _fxmlFilePath The relative filepath of the view fxml file to be loaded as a string.
     * @throws IOException
     */
    public static void FxmlLoader(String _fxmlFilePath) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Construct a FXML loader for the home page.
        loader.setLocation(Main.class.getResource(_fxmlFilePath));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        //Construct a main layout for the Main home page
        primaryStage.setScene(scene);
    }

    /**
     * An extension of the FXMLLoader for the Main view file.
     *
     * @throws IOException
     */
    public static void showMainView() throws IOException {
        //Load the MainView.
        Main.FxmlLoader(MAIN_VIEW_PATH);
        //Display the view to the user.
        primaryStage.show();
    }

    /**
     * An extension of the FXMLLoader for the Login view file.
     *
     * @throws IOException
     */
    public static void showLogin() throws IOException {
        Main.FxmlLoader(LOGINPAGE_PATH);
    }

    /**
     * An extension of the FXMLLoader for the Registration view file.
     *
     * @throws IOException
     */
    public static void showRegister() throws IOException {
        Main.FxmlLoader(REGISTERPAGE_PATH);
    }

    /**
     * An extension of the FXMLLoader for the About Us view file.
     *
     * @throws IOException
     */
    public static void showAboutUs() throws IOException {
        Main.FxmlLoader(ABOUTUSPAGE_PATH);
    }

    /**
     * An extension of the FXMLLoader for the Contact Us view file.
     *
     * @throws IOException
     */
    public static void showContactUs() throws IOException {
        Main.FxmlLoader(COTNTACT_US_PATH);
    }

    /**
     * An extension of the FXMLLoader for the Categories view file.
     *
     * @throws IOException
     */
    public static void showCategories() throws IOException {
        Main.FxmlLoader(CATEGORY_PATH);
    }

    /**
     * An extension of the FXMLLoader for the Locations view file.
     * This method is called multiple times and check if the current categories index (signified by the static count variable) is less than the number of categories defined in the enumeration package.
     * If the user responded as interested in the category at index count adjust the startIndex value to the Categories index times the number of locations per category.
     * This allows the Locations view to be used regardless of what category if being viewed. Alterations to the number of locations per category will require view file and controller alterations.
     *
     * @throws IOException
     */
    public static void loopCategories() throws IOException {
        //If all of the categories haven't been checked yet check the response at the current index (count).
        if (count < CATEGORY_ARRAY_SIZE) {
            //If that response if equal to TRUE (1).
            if (currentUser.getSingleCategory(count) == TRUE) {
                //Adjust the start index.
                startIndex = count * LOCATIONS_PER_CATEGORY;
                //Call the locaitons view.
                Main.FxmlLoader(LOCATIONS_PATH);
            }
            //Otherwise increment count and check the next index recursively.
            else {
                count++;
                loopCategories();
            }
        }
    }

    /**
     * An extension of the FXMLLoader for the Vacation Transition view file.
     *
     * @throws IOException
     */
    public static void showVacationTransition() throws IOException {
        Main.FxmlLoader(TRANSITON_PATH);
    }

    /**
     * An extension of the FXMLLoader for the Vacation Final view file.
     *
     * @throws IOException
     */
    public static void showVacationFinal() throws IOException {
        Main.FxmlLoader(FINAL_PATH);
    }

    /**
     * A save method used multiple times to overwrite user data with new data.
     *
     * @throws IOException
     */
    public static void save() throws IOException {
        String output = Main.currentUser.toString();
        output += DatabaseTranslator.getUserLocations(currentUser.getName());
        DatabaseTranslator.storeUserData(currentUser.getName(), output);
    }

=======
	 private static Stage primaryStage;
	 private static BorderPane mainLayout;
	
	 /**
	  * create the main stage for the entire application
	  * 
	  */
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("American Traveller Bucketlist");
		
		showMainView();			
	}
	
	/**
	 * This method display using fxml loader and load the home page.
	 */
	public static void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		//Construct a FXML loader for the home page.
		loader.setLocation(Main.class.getResource("home/HomePageView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		//Construct a main layout for the Main home page
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	/**
	 * This method display using fxml loader and load login page. 
	 */
	public static void showLogin() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("login/LoginPageView.fxml"));
		BorderPane loginPage = loader.load();
		mainLayout.setCenter(loginPage);
	}
	
	/**
	 * This method display using fxml loader and load register page.
	 */
	public static void showRegister() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("register/RegistrationPageView.fxml"));
		BorderPane registerPage = loader.load();
		mainLayout.setCenter(registerPage);
		
	}
	
	/**
	 * This method display using fxml loader and load about us page. 
	 */
	public static void showAboutUs() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("aboutUs/AboutUsView.fxml"));
		BorderPane aboutUs = loader.load();
		Stage showAboutUsDialogStage = new Stage();
		showAboutUsDialogStage.setTitle("Register Here! ");
		showAboutUsDialogStage.initModality(Modality.WINDOW_MODAL);
		showAboutUsDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(aboutUs);
		showAboutUsDialogStage.setScene(scene);
		showAboutUsDialogStage.showAndWait();
	}
	
	/**
	 * This method display using fxml loader and load contact us page.
	 */
	public static void showContactUs() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("contactUs/ContactUsView.fxml"));
		BorderPane contactUs = loader.load();
		mainLayout.setCenter(contactUs);
	}
	
>>>>>>> 7f895515775ff7cc523e9bdc2f4e6fbae8656a03
    /**
     * Used by Applications to launch the program when the file is run.
     *
     * @param args The launch arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
