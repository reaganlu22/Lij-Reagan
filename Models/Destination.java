package Models;

/**
 * Lasted Updated: 9/30/19
 Destination specific data which expands on the base data set.
 * @authors Steve Shay
 */
public class Destination extends BaseData {
    //A destinations unique variables.
    String cityCode;
    String description;

    /**
     * Construct a destination object.
     *
     * @param _name The destination's name.
     * @param _zipCode The destination's zip code.
     * @param _airportCode The destinations IATA airport code as a string.
     * @param _cityCode The destination's IATA city code as a string.
     * @param _description A description of destination.
     */
    public Destination (String _name, int _zipCode, String _airportCode, String _cityCode, String _description){
        super(_name, _zipCode, _airportCode);
        this.cityCode = _cityCode;
        this.description = _description;
    }

    //======================== GETTERS ========================
    public String getCityCode() {
        return this.cityCode;
    }

    public String getDescription() {
        return this.description;
    }

    //======================== SETTERS ========================
    public void setCityCode(String _cityCode) {
        this.cityCode = _cityCode;
    }

    public void setDescription(String _description){
        this.description = _description;
    }
}
