package Models;

import static Enumeration.Enumeration.*;
import java.util.Arrays;

/**
 * Lasted Updated: 11/10/19
 * Class to split input string into individual pieces and create a user object.
 * @authors Steve Shay
 */
public class CreateUserFromInput {
    /**
     * Interprets a string array to create a user object.
     *
     * @param _input the users data as a string array.
     * @return The users as an object.
     */
    public static User createUser(String[] _input){

        String name = _input[DATA_ARRAY_NAME];
        int zip = Integer.parseInt(_input[DATA_ARRAY_ZIP]);
        String airport = _input[DATA_ARRAY_AIRPORT];
        String response = _input[DATA_ARRAY_RESPONSES];
        String[] stringResponses = response.split(RESPONSE_SPLIT);
        int[] intResponses = parseResponses(stringResponses, RESPONSE_ARRAY_SIZE);
        String categories = _input[DATA_ARRAY_CATAGORIES];
        String[] stringCategories = categories.split(RESPONSE_SPLIT);
        int[] intCategories = parseResponses(stringCategories, CATEGORY_ARRAY_SIZE);

        return new User(name, zip, airport, intCategories, intResponses);
    }

    /**
     * Turns a string array from read the data store into an integer array
     *
     * @param _responseInput The string array to be converted.
     * @param _size The size of the array.
     * @return The converted array.
     */
    private static int[] parseResponses(String[] _responseInput, int _size){
        int[] intResponses = new int[_size];

        for (int i = ARRAY_START; i < _size; i++){
            intResponses[i] = Integer.parseInt(_responseInput[i]);
        }
        return intResponses;
    }
}
