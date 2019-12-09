package Models;

import Database.DatabaseTranslator;
import static Enumeration.Enumeration.*;
import API.APITranslator;
import com.amadeus.exceptions.ResponseException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Lasted Updated: 11/08/19
 * Takes data from a new user and creates a file for them.
 * @authors Steve Shay
 */
public class RegisterUser {
    /**
     * Register a new user.
     *
     * @param _name The users name.
     * @param _zipCode The users zip code.
     * @return A user object created from the users data.
     * @throws ResponseException
     * @throws IOException
     */
    public static User newUser (String _name, int _zipCode) throws ResponseException, IOException{

       //Get the users latitude and longitude from based on their zip code.
        double[] latAndLong = DatabaseTranslator.getLocationFromZip(_zipCode);
        //Get the users nearest major airport from the determined latitude and longitude.
        String aircode = APITranslator.getAirportCode(latAndLong[0], latAndLong[1]);

        //Use the users input and determined airporrt code to create and return a user object.
        return new User(_name, _zipCode, aircode);
    }

    /**
     * Store a new users data.
     *
     * @param _name The users name.
     * @param _zipCode The users zip code.
     * @param _airportCode The IATA code of the users nearest major airport as a string.
     * @param _catagories The users category response array.
     * @param _responses The users location response array.
     * @throws IOException
     */
    public static void storeUser (String _name, int _zipCode, String _airportCode, int[]_catagories, int[] _responses) throws IOException {
        //Create strings from the response arrays.
        String catagories = Arrays.toString(_catagories);
        String responses = Arrays.toString(_responses);
        //Create the output string.
        String output = _name + INPUT_SPLIT + _zipCode + INPUT_SPLIT + _airportCode + INPUT_SPLIT + catagories.substring(1, catagories.length() - 1) + INPUT_SPLIT + responses.substring(1, responses.length() - 1) + "\n";

        //Write out the current locations array for mapping user responses to the locations at their time of survey. Prevents errors in case of changes in location selection.
        for (int i = 0; i < CURRENT_LOCATION_FILENAMES.length;i++){
            output += (i + "\t" + CURRENT_LOCATION_FILENAMES[i] + "\n");
        }

        //Store the users data.
        DatabaseTranslator.storeUserData(_name, output);
    }
}
