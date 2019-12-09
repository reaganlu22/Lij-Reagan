package Testing;

import java.util.ArrayList;
import java.util.Arrays;
import Models.User;
import API.DevAPI;
import java.io.IOException;
import static Enumeration.Enumeration.*;

/**
 * Lasted Updated: 11/21/19
 * Tests the selectRandomDestination function from the user class.
 * @authors Stephen Hampson
 */
public class BlackboxTestingClass {
    /**
     * Creates the Blackbox testing variables then executes the tests.
     * @param args
     */
    public static void main(String[] args) throws IOException{
        //Create five dummy users to execute the five tests.
        User test1 = new User("test1", 11111);
        User test2 = new User("test2", 11111);
        User test3 = new User("test3", 11111);
        User test4 = new User("test4", 11111);
        User test5 = new User("test5", 11111);

        //Set 1 (Edge case all locations are true)
        int[] test1Responses = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,25};
        ArrayList<Integer> correct1 = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24));
        test1.setUserResponses(test1Responses);

        //Set 2 (Edge/Error case all locations are false
        int[] test2Responses = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        ArrayList<Integer> correct2 = new ArrayList<Integer>(Arrays.asList(-1));
        test2.setUserResponses(test2Responses);

        //Set 3 (Normal/Edge case only one location is true)
        int[] test3Responses = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0};
        ArrayList<Integer> correct3 = new ArrayList<Integer>(Arrays.asList(24));
        test3.setUserResponses(test3Responses);

        //Set 4 (Normal case random true values)
        int[] test4Responses = {1,1,1,0,0,1,0,0,0,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,1,14};
        ArrayList<Integer> correct4 = new ArrayList<Integer>(Arrays.asList(0,1,2,5,9,10,12,14,15,18,19,21,23,24));
        test4.setUserResponses(test4Responses);

        //Set 5 (Normal case random true values)
        int[] test5Responses = {0,1,1,1,0,0,1,1,0,1,1,0,1,1,1,1,0,0,0,0,1,0,0,0,0,12};
        ArrayList<Integer> correct5 = new ArrayList<Integer>(Arrays.asList(1,2,3,6,7,9,10,12,13,14,15,20));
        test5.setUserResponses(test5Responses);

        //Run the tests.
        System.out.println("Test selection of random destination.");
        System.out.println("Test 1 Edge Case (All True) : " + blackboxTesting1(test1, correct1));
        System.out.println("Test 2 Error Case (No Trues) : " + blackboxTesting1(test2, correct2));
        System.out.println("Test 3 Edge Case (1 True)   : " + blackboxTesting1(test3, correct3));
        System.out.println("Test 4 Normal Case (14 True): " + blackboxTesting1(test4, correct4));
        System.out.println("Test 5 Normal Case (12 True): " + blackboxTesting1(test5, correct5));
        System.out.println("");
        System.out.println("Test Dev API for flight cost.");
        System.out.println("While the is an error case for no input there are no edge cases.");
        System.out.println("Test 6 Normal Case ('MCO'): " + DevAPI.getBlackboxResult(FLIGHT_TESTING, "MCO"));
        System.out.println("Test 7 Error Case ('')    : " + DevAPI.getBlackboxResult(FLIGHT_TESTING, ""));
        System.out.println("Test 8 Normal Case ('DCA'): " + DevAPI.getBlackboxResult(FLIGHT_TESTING, "DCA"));
        System.out.println("");
        System.out.println("Test Dev API for flight cost.");
        System.out.println("While the is an error case for no input there are no edge cases.");
        System.out.println("Test 9 Normal Case ('ORL') : " + DevAPI.getBlackboxResult(HOTEL_TESTING, "ORL"));
        System.out.println("Test 10 Error Case ('')    : " + DevAPI.getBlackboxResult(HOTEL_TESTING, ""));
        System.out.println("Test 11 Normal Case ('ATL'): " + DevAPI.getBlackboxResult(HOTEL_TESTING, "ATL"));
    }

    /**
     * Tests a given test case and returns a pass or fail value.
     *
     * @param _user The User object to be tested.
     * @param _correct The array list of acceptable outputs used to evaluate pass/fail.
     * @return If the selectRandomDestination() method passes more than 95% of tests return 'Pass', otherwise return 'Fail'
     */
    private static String blackboxTesting1 (User _user, ArrayList _correct){
        //Keeps track of times the test is run.
        int count = 0;
        //Keeps track of the times the method passes.
        int pass = 0;

        //Runs 1000 times.
        while (count < 1000){
            //Select a random destination.
            int selected = _user.selectRandomDestination();
            //If the selection is in the premade list of correct responses, the method passes
            if (_correct.contains(selected)){
                pass++;
            }
            count++;
        }
        //If the method passes more than 95% of trials it passes.
        if ((pass/1000) >= .950){
            return "Pass";
        }
        //Otherwise the method fails.
        else{
            return "Fail";
        }
    }
}
