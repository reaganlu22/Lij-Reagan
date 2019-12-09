package API;

import com.amadeus.exceptions.ResponseException;
import java.io.IOException;


/**
 * Lasted Updated: 11/13/19
 * Connects standard methods to the API currently in use.
 * @author Stephen Hampson
 */
public class APITranslator {
    //Supported API types.
    private static final int AMADEUS = 1;
    private static final int DEV = 2;

    //Set the API type for each usage type.
    private static final int CURRENT_FLIGHT_API = AMADEUS;
    private static final int CURRENT_HOTEL_API = AMADEUS;
    private static final int CURRENT_AIRPORT_API = AMADEUS;

    /**
     * Direst requests for expected flight costs to the appropriate API class.
     *
     * @param _originAirport The origin airports IATA airport code as a string.
     * @param _destinationAirport The destination airports IATA airport code as a string.
     * @param _departureDate The departure date as a string.
     * @return The average cost of a flight as a double.
     * @throws ResponseException
     * @throws IOException
     */
    public static double getExpectedFlightCost (String _originAirport, String _destinationAirport, String _departureDate) throws ResponseException, IOException{
        switch(CURRENT_FLIGHT_API){
            case 1:
                return AmadeusAPI.getExpectedFlightCost(_originAirport, _destinationAirport, _departureDate);
            case 2:
                return DevAPI.getExpectedFlightCost(_originAirport, _destinationAirport, _departureDate);
            default:
                System.out.println("No Flight API is selected");
                throw new AssertionError();
        }
    }

    /**
     * Directs request for flight costs to the DevAPI in the event of API failure.
     *
     * @param _originAirport The origin airports IATA airport code as a string.
     * @param _destinationAirport The destination airports IATA airport code as a string.
     * @param _departureDate The departure date as a string.
     * @return The average cost of a flight as a double.
     * @throws IOException
     */
    public static double falloverFlightAPI (String _originAirport, String _destinationAirport, String _departureDate) throws IOException{
        System.out.println("No Flights Found. Using Dev estimates.");
        return DevAPI.getExpectedFlightCost(_originAirport, _destinationAirport, _departureDate);
    }

    /**
     * Directs request for hotel costs in a given city to the appropriate API class.
     *
     * @param _cityCode The IATA city code of the destination city as a string.
     * @return The average cost of a hotel per night as a string.
     * @throws ResponseException
     * @throws IOException
     */
    public static double getExpectedHotelCost (String _cityCode) throws ResponseException, IOException{
        switch (CURRENT_HOTEL_API){
            case 1:
                return AmadeusAPI.getExpectedHotelCost(_cityCode);
            case 2:
                return DevAPI.getExpectedHotelCost(_cityCode);
            default:
                throw new AssertionError();
        }
    }

    /**
     * Directs request for hotel costs to the DevAPI in the event of API failure.
     *
     * @param _cityCode The IATA city code of the destination city as a string.
     * @return The average cost of a hotel per night as a string.
     * @throws IOException
     */
    public static double falloverHotelAPI (String _cityCode) throws IOException{
        System.out.println("No Hotels Found.");
        return DevAPI.getExpectedHotelCost(_cityCode);
    }

    /**
     * Directs requests for the nearest major airport to a given latitude and longitude to the appropriate API class.
     *
     * @param _latitude The latitude of a location as a double.
     * @param _longitude The longitude of a location as a double.
     * @return The IATA airport code of the nearest major airport as a string.
     * @throws ResponseException
     */
    public static String getAirportCode (double _latitude, double _longitude) throws ResponseException{
        switch (CURRENT_AIRPORT_API){
            case 1:
                return AmadeusAPI.getAirportCode(_latitude, _longitude);
            case 2:
                return DevAPI.getAirportCode(_latitude, _longitude);
            default:
                System.out.println("No Airport API selected");
                throw new AssertionError();
        }
    }
}
