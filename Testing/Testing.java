package Testing;

import API.APITranslator;
import com.amadeus.exceptions.ResponseException;
import java.io.IOException;

/**
 * These are deprecated function tests stored for project history an in case they're needed again.
 * Further comments will not be provided unless they were copied with the original tests.
 * @author Steve Shay
 */
public class Testing {
    public static void main(String[] args) throws ResponseException, IOException{
        //api.apiPrototype.getExpectedFlightCost("GSO", "JFK", "2019-12-22");
        //apiPrototype.getExpectedHotelCost("NYC");
        //System.out.println(APITranslator.getExpectedHotelCost("HNL"));

        /*OLD TESTS
        System.out.println("");
        System.out.println(JamesTest.getName());
        System.out.println(JamesTest.getZipCode());
        System.out.println(JamesTest.getAirportCode());

        System.out.print(JamesTest.getSingleResponse(3));
        System.out.print(" ");
        System.out.print(JamesTest.getSingleResponse(20));
        System.out.print(" ");
        System.out.println(JamesTest.getSingleResponse(25));
        System.out.print(" ");
        //test integer attribute
        System.out.println(JamesTest.getSingleResponse(3)+JamesTest.getSingleResponse(20)+JamesTest.getSingleResponse(25));

        String date = "2019-12-22";
        User bob = new User("Bob", 27284, "GSO");
        VacationLocation NY = new VacationLocation("New York", 11430, "JFK", "NYC", "Test Description");

        //Test API calls for flights.
        try{
            APITranslator.getExpectedFlightCost(bob.getAirportCode(), NY.getAirportCode(), "2019-12-22");
        }
        catch(Exception e){
            System.out.println("error");
        }

        //Test loading user from file.
        String userFileData = DatabaseTranslator.getUserData("James Bond");
        String[] userData = userFileData.split(INPUT_SPLIT);

        String locationFileData = DatabaseTranslator.getLocationData("New York City");
        String[] locationData = locationFileData.split(INPUT_SPLIT);

        //Test User object data load and getters
        User JamesTest = CreateUserFromInput.createUser(userData);
        VacationLocation NYC = CreateVacationLocationFromInput.createLocation(locationData);

        try{
            double cost = APITranslator.getExpectedFlightCost(JamesTest.getAirportCode(), NYC.getAirportCode(), "2019-12-22");

            System.out.format("Flight Total: %.2f", cost);
            System.out.println("");
        }
        catch(Exception e){
            System.out.println("error");
        }

        System.out.println("");

        double hotelPrice = APITranslator.getExpectedHotelCost("NYC");
        System.out.format("Hotel (Cost per night): %.2f", hotelPrice);
        System.out.println("");

        double[] latAndLong = DatabaseTranslator.getLocationFromZip(JamesTest.getZipCode());
        System.out.println(latAndLong[0]);
        System.out.println(latAndLong[1]);

        String aircode = APITranslator.getAirportCode(latAndLong[0], latAndLong[1]);
        System.out.println("Walkertown: " + aircode);
        */
    }
}
