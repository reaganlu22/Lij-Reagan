package Models;

import static Enumeration.Enumeration.*;
/**
 * Last Updated: 11/10/19
 * Class to split input string into individual pieces and create a Des object.
 * @author Steve
 */
public class CreateDestinationFromInput {
    /**
     * Interprets a string array to create a Destination object
     *
     * @param _input the Destinations data as a string array.
     * @return The destination as an object.
     */
    public static Destination createLocation(String[] _input){
        String name = _input[DATA_ARRAY_NAME];
        int zip = Integer.parseInt(_input[DATA_ARRAY_ZIP]);
        String airport = _input[DATA_ARRAY_AIRPORT];
        String cityCode = _input[DATA_ARRAY_CITY];
        String description = _input[DATA_ARRAY_DESCRIPTION];

        return new Destination(name, zip, airport, cityCode, description);
    }
}
