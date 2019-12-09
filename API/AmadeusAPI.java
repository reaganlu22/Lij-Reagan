package API;

import static Enumeration.Enumeration.*;
import com.amadeus.Amadeus;
import com.amadeus.Params;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOffer;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.Location;
import java.io.IOException;

/**
 * Lasted Updated: 11/13/19
 * API translation prototypes which will eventually be transfered to the final API translators.
 * @authors Stephen Hampson
 */
public class AmadeusAPI {
    //Our projects Amadeus API key and secret key, built staticly to reduce code duplication
    private static final String AMADEUS_ID = "L32XhR5nGjRdV3KiGCT58c90NoZ7YAk5";
    private static final String AMADEUS_SECRET = "W1AWTYJzWte788dg";
    private static final Amadeus amadeus = Amadeus.builder(AMADEUS_ID, AMADEUS_SECRET).build();

    static double getExpectedFlightCost (String _originAirport, String _destinationAirport, String _departureDate) throws ResponseException, IOException {
        double total = 0;
        int count = 1;
        FlightOffer[] flightOffers = null;

        //Get an API response for flight offers from the origin city to the destination city.
        //amadeus.shopping.flightOffers.get(Params
        try {
            flightOffers = amadeus.shopping.flightOffers.get(Params
                .with(ORIGIN, _originAirport)
                .and(DESTINATION, _destinationAirport)
                .and(DEPARTURE_DATE, _departureDate));
        }
        //If the response fails call the fallover option.
        catch(Exception e){
            return APITranslator.falloverFlightAPI(_originAirport, _destinationAirport, _departureDate);
        }
        //Try to add the first value to the total.
        try {
            total += flightOffers[0].getOfferItems()[0].getPrice().getTotal();
        }
        //If the addition fails fallover to the dev estimates.
        catch(Exception e){
            return APITranslator.falloverFlightAPI(_originAirport, _destinationAirport, _departureDate);
        }

        //Try to add more offers from the API results to the total.
        try{
            for (int i = 1; i < 5; i++) {
                total += flightOffers[i].getOfferItems()[0].getPrice().getTotal();
                count++;
            }
        }
        //If there aren't 5 results log in the console output how many were used in the average.
        catch(Exception e){
            System.out.println("Only " + count + " hotel offer(s).");
        }

        //Return the average price by dividing by the count of offers added.
        return total/count;
    }

    static double getExpectedHotelCost(String _citycode) throws ResponseException, IOException{
        double total = 0;
        int count = 1;
        HotelOffer[] offers;

        //Try for API response of hotel offers in destination city.
        try{
            offers = amadeus.shopping.hotelOffers.get(Params.with("cityCode", _citycode));
        }
        //If there is no response fallover to Dev estimates.
        catch(Exception e){
            return APITranslator.falloverHotelAPI(_citycode);
        }

        //If the first value can't be added to the total fallover to the Dev estimates.
        try{
            total += Double.parseDouble(offers[0].getOffers()[0].getPrice().getTotal());
        }
        catch(Exception e){
            return APITranslator.falloverHotelAPI(_citycode);
        }

        //Try to add more offers from the API results to the total.
        try{
            for (int i = 1; i < 3; i++) {
                total += Double.parseDouble(offers[i].getOffers()[0].getPrice().getTotal());
                count++;
            }
        }
        //If there aren't 5 results log in output how many were used in the average.
        catch(Exception e){
            System.out.println("Only " + count + " hotel offer(s).");
        }

        //Return the averace price by dividing total by the count of prices added.
        return total/count;
    }

    static String getAirportCode(double _latitude, double _longitude) throws ResponseException{

        //Request the nearest major airport to the given latitude and longitude from the Amadeus API.
        Location[] locations = amadeus.referenceData.locations.airports.get(Params
            .with("latitude", _latitude)
            .and("longitude", _longitude));
        //Return the airport code returned by the Amadeus API.
        return locations[0].getIataCode();
    }

    //Depricated code from before JSON parsing.
    /*public static double findFlightAverage (String _apiResponse, String _splitType){
        String[] values = _apiResponse.split(_splitType);
        double totalPricing = 0;

        for (int i = STRING_ARRAY_START; i < AVERAGE_SAMPLE_SIZE + 1; i++){
            String iFlight = values[i].toString();
            String[] prices = iFlight.split(PRICE_SPLIT);
            String price = prices[STRING_ARRAY_START].toString();
            price = price.substring(SUB_LOWER_BOUND,price.length() - 3);
            System.out.println(price);
            String[] taxes = iFlight.split(TAX_SPLIT);
            String tax = taxes[STRING_ARRAY_START].toString();
            tax = tax.substring(SUB_LOWER_BOUND, TAX_UPPER_BOUND);
            System.out.println(tax);
            double subTotal = Double.parseDouble(price) + Double.parseDouble(tax);
            totalPricing += subTotal;
        }
        double average = totalPricing / AVERAGE_SAMPLE_SIZE;
        System.out.println(totalPricing);
        System.out.println(average);

        return average;
    }*/

}
