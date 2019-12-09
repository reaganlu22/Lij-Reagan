package ShittyGUI;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import static Enumeration.Enumeration.*;
import Database.DatabaseTranslator;
import java.io.IOException;
import Database.LoadData;
import Models.Destination;
import Models.User.*;
import Models.User;
import static Enumeration.Enumeration.*;
import API.APITranslator;
import com.amadeus.exceptions.ResponseException;
import java.util.ArrayList;
import java.util.Arrays;
import Models.CreateUserFromInput;
import Models.CreateDestinationFromInput;
import Models.RegisterUser;

/**
 * Lasted Updated: 11/10/19
 * Shitty text based GUI purely for testing functionality before final GUI is ready for integration.
 * @authors Steve Shay
 */
public class UserTesting {
    static Scanner scn = new Scanner(System.in);
    static ArrayList<String> agrees = new ArrayList<String>(Arrays.asList("Y", "y", "Yes", "yes", "YES"));
    //Test running through all project models
    public static void main(String[] args) throws ResponseException, IOException{
        User newUser;
        //Welcome User and prompt for name.
        System.out.println("Welcome to the American Travel Bucket List!");
        System.out.println("Here to help you pick a vacation when you can't decide.");
        System.out.println("");
        System.out.println("Please enter your name. New users will be routed to the interst Questionaire: ");

        String username = scn.nextLine();
        String filepath = USER_FILEPATH + username + TXT;
        File inputfile = new File(filepath);

        //If file for user doean't already exist run through registering a new user.
        if (!inputfile.exists()){
            //Get a zip code.
            System.out.println("Welcome " + username + " as a new user we need to ask you a few questions");
            System.out.println("so we can help you plan vacations you will enjoy.");
            System.out.println("");
            System.out.println("What is your local Zip Code (for finding flight costs): ");
            int zip = scn.nextInt();
            newUser = RegisterUser.newUser(username, zip);

            System.out.println("");
            System.out.println("Catagories: ");
            //Ask about interest in the 5 catagories.
            catagories(newUser);
            //If the user answered true during the catagorey questionaire, present that catagories locations.
            if (newUser.getSingleCategory(0) == TRUE){
                System.out.println("Outdoors Adventures: ");
                outdoorsLocations(newUser);
            }
            if (newUser.getSingleCategory(1) == TRUE){
                System.out.println("Entertainment Vacations");
                entertainmentLocations(newUser);
            }
            if (newUser.getSingleCategory(2) == TRUE){
                System.out.println("Educational Entertainment Vacations");
                educationalLocations(newUser);
            }
            if (newUser.getSingleCategory(3) == TRUE){
                System.out.println("Cultural Vacations");
                culturalLocations(newUser);
            }
            if (newUser.getSingleCategory(4) == TRUE){
                System.out.println("Historical Vacations");
                historicalLocations(newUser);
            }
            System.out.println("Thank you. Storing Reponses.");
            //Store the responses.
            RegisterUser.storeUser(newUser.getName(), newUser.getZipCode(), newUser.getAirportCode(), newUser.getCategories(), newUser.getUserResponses());
        }
        //If the user's file did exists load their data from it.
        else {
            String userFileData = DatabaseTranslator.getUserData(username);
            String[] userData = userFileData.split(INPUT_SPLIT);

            newUser = CreateUserFromInput.createUser(userData);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        //Ask user if they want the program to select their random vacation destination.
        System.out.println("Are you ready to select a random destination based on your questionairte responses?: (Y/N)");
        String response = scn.next();
        //If yes, do so.
        if (agrees.contains(response)){
            //Select a destination.
            int selection = newUser.selectRandomDestination();

            //Load destinations data from file.
            String locationFileData = DatabaseTranslator.getLocationData("Location" + selection);
            String[] locationData = locationFileData.split(INPUT_SPLIT);

            //Calculate costs.
            Destination destination = CreateDestinationFromInput.createLocation(locationData);
            double flightCost = APITranslator.getExpectedFlightCost(newUser.getAirportCode(), destination.getAirportCode(), "2019-12-22");
            String strFlightCost = String.format("%.2f", flightCost);
            double hotelCost = APITranslator.getExpectedHotelCost(destination.getCityCode());
            String strHotelCost = String.format("%.2f", hotelCost);

            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            //Present the information.
            System.out.println("We've selected " + destination.getName() + " from your list of interests.");
            System.out.println("Based on where you live you can expect to pay " + strFlightCost + " per person to fly to " + destination.getName() + ".");
            System.out.println("In " + destination.getName() +  " you can expect to pay " + strHotelCost + " per night for a room.");
            System.out.println(destination.getDescription());
        }
        else{
            System.out.println("Thank you for using the American Travel Buket List. Logging you out.");
            System.exit(0);
        }
    }

    //Loop through the catagories and get a user response for each.
    private static void catagories(User _user){
        String response;
        String[] catagories = {"1. Outdoor Adventure", "2. Entertainment", "3. Educational Entertainment", "4. Cultural", "5. Historical"};
        for (int i = 0; i < 5; i++){
            System.out.println("Are you interested in " + catagories[i] + " vacations? (Y/N)");
            response = scn.next();

            if (agrees.contains(response)){
                _user.setSingleCategory(i, TRUE);
            }
            else{
                _user.setSingleCategory(i, FALSE);
            }
        }
    }

    //Loop through the outdoor locations and get a user response for each.
    private static void outdoorsLocations(User _user){
        String response;
        String[] locations = {"Acadia National Park", "Anchorage Alaska","Boulder Colorado","Grand Canyon","Yellowstone"};
        for (int i = 0; i < 5; i++){
            System.out.println("Are you interested in going on a vacation to " + locations[i] + "? (Y/N)");
            response = scn.next();

            if (agrees.contains(response)){
                _user.setSingleResponse(i, TRUE);
            }
            else{
                _user.setSingleResponse(i, FALSE);
            }
        }
    }

    //Loop through the entertainment locations and get a user response for each.
    private static void entertainmentLocations(User _user){
        String response;
        String[] locations = {"Disney World", "Las Vegas","The Mall of America","Ocean City Beach","Six Flags Over Georgia"};
        for (int i = 0; i < 5; i++){
            System.out.println("Are you interested in going on a vacation to " + locations[i] + "? (Y/N)");
            response = scn.next();

            if (agrees.contains(response)){
                _user.setSingleResponse(5 + i, TRUE);
            }
            else{
                _user.setSingleResponse(5 + i, FALSE);
            }
        }
    }

    //Loop throught the catagories and get a user response for each.
    private static void educationalLocations(User _user){
        String response;
        String[] locations = {"The Childrens Museum of Indianapolis", "The Georgia Aquarium","The San Diego Zoo","Sea World Orlando","The Smithsonian Museum"};
        for (int i = 0; i < 5; i++){
            System.out.println("Are you interested in going on a vacation to " + locations[i] + "? (Y/N)");
            response = scn.next();

            if (agrees.contains(response)){
                _user.setSingleResponse(10 + i, TRUE);
            }
            else{
                _user.setSingleResponse(10 + i, FALSE);
            }
        }
    }

    //Loop through the cultural locations and get a user response for each.
    private static void culturalLocations(User _user){
        String response;
        String[] locations = {"Boston", "Honolulu","New Orleans","New York","San Francisco"};
        for (int i = 0; i < 5; i++){
            System.out.println("Are you interested in going on a vacation to " + locations[i] + "? (Y/N)");
            response = scn.next();

            if (agrees.contains(response)){
                _user.setSingleResponse(15 + 1, TRUE);
            }
            else{
                _user.setSingleResponse(15 + i, FALSE);
            }
        }
    }

    //Loop throught the historical locations and get a user response for each.
    private static void historicalLocations(User _user){
        String response;
        String[] locations = {"The Alamo", "Alcatraz","Gettysburg","The Kennedy Space Center","Washington DC"};
        for (int i = 0; i < 5; i++){
            System.out.println("Are you interested in going on a vacation to " + locations[i] + "? (Y/N)");
            response = scn.next();

            if (agrees.contains(response)){
                _user.setSingleResponse(20 + i, TRUE);
            }
            else{
                _user.setSingleResponse(20 + i, FALSE);
            }
        }
    }
}

