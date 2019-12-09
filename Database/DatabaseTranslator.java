package Database;

import static Enumeration.Enumeration.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Lasted Updated: 11/21/19
 * Connects standard methods to the Database solution currently in use.
 * @author Stephen Hampson
 */
public class DatabaseTranslator {
    //Supported Database types.
    private static final int TEXT = 1;

    //Set the database type for each usage type.
    private static final int CURRENT_USER_DATA = TEXT;
    private static final int CURRENT_LOCATION_DATA = TEXT;
    private static final int CURRENT_ZIP_DATA = TEXT;

    /**
     * Direct requests for primary user data (used to set User class variables) to the appropriate database class.
     *
     * @param _name The users name/filename.
     * @return The users stored data/variables as a string.
     * @throws IOException
     */
    public static String getUserData(String _name) throws IOException{
        switch(CURRENT_USER_DATA){
            case 1:
                return LoadData.loadData(USER_FILEPATH, convertName(_name));
            default:
                System.out.println("No database is selected.");
                throw new AssertionError();
        }
    }

    /**
     * Direct requests for a users location filename array to the appropriate database class.
     *
     * @param _name The users name/filename.
     * @return The users locations filename array as a string.
     * @throws IOException
     */
    public static String getUserLocations (String _name) throws IOException{
        switch(CURRENT_USER_DATA){
            case 1:
                return LoadData.loadUserLocations(convertName(_name));
            default:
                System.out.println("No database is selected.");
                throw new AssertionError();
        }
    }

    /**
     * Directs requests to map an index selection to a users specific location filename to the appropriate database class.
     *
     * @param _name The name/filename of the user.
     * @param _index The index of the location to be mapped.
     * @return the filename of the location selected.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String mapFilename(String _name, int _index) throws FileNotFoundException, IOException {
        switch (CURRENT_USER_DATA) {
            case 1:
                return LoadData.mapFilename(convertName(_name), _index);
            default:
                System.out.println("No database is selected.");
                throw new AssertionError();
        }
    }

    /**
     * Direct requests for a single locations data to the appropriate database class.
     *
     * @param _name The locations filename.
     * @return The locations stored data/variables as a string.
     * @throws IOException
     */
    public static String getLocationData(String _name) throws IOException{
        switch(CURRENT_LOCATION_DATA){
            case 1:
                return LoadData.loadData(LOCATION_FILEPATH, _name);
            default:
                System.out.println("No database is selected.");
                throw new AssertionError();
        }
    }

    /**
     * Direct requests for a zip codes latitude and longitude to the appropriate database class.
     *
     * @param _zip The zip code to be searched.
     * @return The matching latitude and longitude as a double array, latitude first, longitude second.
     * @throws IOException
     */
    public static double[] getLocationFromZip(int _zip) throws IOException{
        switch (CURRENT_ZIP_DATA) {
            case 1:
                return FindLocationFromZip.getLatandLong(_zip);
            default:
                System.out.println("No database is selected.");
                throw new AssertionError();
        }
    }

    /**
     * Direct requests to store a users data to the appropriate database class.
     *
     * @param _name The users name/filename.
     * @param _output The string to be written.
     * @throws IOException
     */
    public static void storeUserData(String _name, String _output) throws IOException{
        switch (CURRENT_USER_DATA) {
            case 1:
                StoreData.writeFile(convertName(_name), _output);
                break;
            default:
                System.out.println("No database is selected.");
                throw new AssertionError();
        }
    }

    /**
     * Check if a user has a record in the current database.
     *
     * @param _name The users name/filename.
     * @return A boolean value indicating whether or not the user already exists.
     */
    public static boolean checkUser(String _name){
        switch (CURRENT_USER_DATA) {
            case 1:
                return LoadData.checkUser(convertName(_name));
            default:
                System.out.println("No database selected.");
                throw new AssertionError();
        }
    }

    /**
     * Helper method to homogenize usernames by converting them to lowercase and removing spaces.
     *
     * @param _name The username to be homogenized.
     * @return The homogenized username.
     */
    private static String convertName(String _name){
        String lowercaseName = _name.toLowerCase();
        return lowercaseName.replaceAll("\\s","");
    }
}
