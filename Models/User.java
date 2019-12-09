package Models;

/**
 * Lasted Updated: 12/5/19
 * User specific data which expands on the base data set.
 * @authors Steve Shay
 */

import static Enumeration.Enumeration.*;
import java.util.Arrays;
import java.util.Random;

public class User extends BaseData {
    //Static random declaration for two methods below.
    static Random r = new Random();

    //Create the two arrays unique to users.
    int[] userResponses = new int[RESPONSE_ARRAY_SIZE];
    int[] categoryResponses = new int[CATEGORY_ARRAY_SIZE];

    //All user constructors.
    /**
     * Construct a user from a name and zip code.
     *
     * @param _name The user's name.
     * @param _zipCode The user's zip code.
     */
    public User(String _name, int _zipCode){
        super(_name, _zipCode);
    }

    /**
     * Construct a user from a name, zip code, and airport code.
     *
     * @param _name The user's name.
     * @param _zipCode The user's zip code.
     * @param _airportCode The IATA airport code of the users nearest major airport.
     */
    public User (String _name, int _zipCode, String _airportCode){
        super(_name, _zipCode, _airportCode);
    }

    /**
     * Construct a user from a name, zip code, airport code, and array of location responses.
     *
     * @param _name The user's name.
     * @param _zipCode The user's zip code.
     * @param _airportCode The IATA airport code of the users nearest major airport.
     * @param _responses The array of responses that the user gave the 25 locations on the bucket list.
     */
    public User(String _name, int _zipCode, String _airportCode, int[] _responses){
        super(_name, _zipCode, _airportCode);
        this.userResponses = _responses;
    }

    /**
     * Construct a user from a name, zip code, airport code, array of location responses, and .
     *
     * @param _name The user's name.
     * @param _zipCode The user's zip code.
     * @param _airportCode The IATA airport code of the users nearest major airport.
     * @param _categories The array of category responses the user gave to the 5 location categories.
     * @param _responses The array of responses that the user gave the 25 locations on the bucket list.
     */
    public User(String _name, int _zipCode, String _airportCode, int[] _categories, int[] _responses){
        super(_name, _zipCode, _airportCode);
        this.userResponses = _responses;
        this.categoryResponses = _categories;
    }

    /**
     * Select a random destination based on the users responses about their interests.
     *
     * @return The index of the selected destination.
     */
    public int selectRandomDestination(){
        //Update the ammount of locations the user expressed interest in.
        countTrues();
        //Keeps track of how many true responses have been passed.
        int count = 0;
        //Keeps track of the current index for return.
        int returnIndex = 0;
        //Pull number of true responses from the final index in the array.
        int max = this.userResponses[25];
        int selection;
        if (max == 0){
            return EMPTY;
        }

        //Select a random integer from 1 to the number of true responses. (inclusive)
        selection = r.nextInt((max)) + 1;

        //Loop through all values in the array.
        for (int i : this.userResponses){
            //While the selected destination based on number of true responses hasn't been passed.
            if (count < selection){
                //If current index is true increment count.
                if (i == TRUE){
                    count++;
                }
                //Increment return index to current .
                returnIndex++;
            }
        }
        //End by returning the 'returnIndex' - 1 to adjust.
        return returnIndex - 1;
    }

    /**
     *
     * Check to make sure the user didn't answer false to all locations.
     * @return A boolean value indicating whether the response is valid.
     */
    public boolean checkResponseValid(){
        //Update the count true count of the userResponse array.
        countTrues();

        //Check if the user has answered false to everything.
        if (this.userResponses[25] == 0){
            return false;
        }
        return true;
    }

    /**
     * Check if the user expressed interest in any categories.
     *
     * @return Return true if any category is true, otherwise return false.
     */
    public boolean checkCategoriesVaild(){
        for (int i = 0; i < this.categoryResponses.length - 1; i++) {
            if (categoryResponses[i] == TRUE) {
                return true;
            }
        }
        return false;
    }

    /**
     * If the user answered false to all locations pick random locations to mark true until 5 locations have been selected.
     */
    public void responseOverride(){
        int selection;
        int count = 0;
        //Select five locations from the whole for them.
        while (count < 5){
            selection = r.nextInt((NUMBER_OF_LOCATIONS) + 1);
            if (this.userResponses[selection] == FALSE){
                    this.userResponses[selection] = TRUE;
                count++;
            }
        }
        countTrues();
    }

    @Override
    /**
     * Returns the users data as a string.
     */
    public String toString(){
        String catagories = Arrays.toString(getCategories());
        String responses = Arrays.toString(getUserResponses());
        String output = getName() + INPUT_SPLIT + getZipCode() + INPUT_SPLIT + getAirportCode() + INPUT_SPLIT + catagories.substring(1, catagories.length() - 1) + INPUT_SPLIT + responses.substring(1, responses.length() - 1) + "\n";
        return output;
    }

    /**
     * Loop through the entire array and update the final index with how many times they responded true.
     */
    private void countTrues(){
        int count = 0;
        for (int i = 0; i < this.userResponses.length - 1; i++){
            if (this.userResponses[i] == TRUE){
                count ++;
            }
        }
        this.userResponses[25] = count;
    }

    //======================== GETTERS ========================
    public int[] getUserResponses() {
        return this.userResponses;
    }

    public int getSingleResponse(int _index){
        return this.userResponses[_index];
    }

    public int[] getCategories(){
        return this.categoryResponses;
    }

    public int getSingleCategory(int _index){
        return this.categoryResponses[_index];
    }

    //======================== SETTERS ========================
    public void setUserResponses(int[] _userResponses) {
        this.userResponses = _userResponses;
    }

    public void setSingleResponse(int _index, int _value){
        this.userResponses[_index] = _value;
    }

    public void setCategories(int[] _categories) {
        this.categoryResponses = _categories;
    }

    public void setSingleCategory(int _index, int _value){
        this.categoryResponses[_index] = _value;
    }
}
