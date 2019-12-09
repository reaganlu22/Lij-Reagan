package Models;

/**
 * Lasted Updated: 11/21/19
 * Base data used by both users and locations.
 * @authors Steve Shay
 */

class BaseData {
    //The variables shared by objects extending BaseData.
    private String name;
    private String airportCode;
    private int zipCode;

    /**
     * Construct a base data object from a name and zip code.
     *
     * @param _name The objects name.
     * @param _zipCode the objects zip code.
     */
    BaseData(String _name, int _zipCode){
        this.name = _name;
        this.zipCode = _zipCode;
    }

    /**
     * Construct a base data object from a name, zip code, and airport code.
     *
     * @param _name The objects name.
     * @param _zipCode The objects zip code.
     * @param _airportCode The objects IATA airport code as a string.
     */
    BaseData(String _name, int _zipCode, String _airportCode){
        this.name = _name;
        this.zipCode = _zipCode;
        this.airportCode = _airportCode;
    }

    //======================== GETTERS ========================
    public String getName() {
        return this.name;
    }

    public String getLowercaseName() {
        String lowercaseName = getName().toLowerCase();
        return lowercaseName.replaceAll("\\s","");
    }

    public String getAirportCode() {
        return this.airportCode;
    }

    public int getZipCode() {
        return this.zipCode;
    }
    //======================== SETTERS ========================
    public void setName(String _name) {
        this.name = _name;
    }

    public void setAirportCode(String _airportCode) {
        this.airportCode = _airportCode;
    }

    public void setZipCode(int _zipCode) {
        this.zipCode = _zipCode;
    }
}
