package totems.airport.Displays;

import data.airport.model.FlightData;

public interface IDisplay {
    void insert(FlightData flight);
    void removeIfContains(FlightData flight);
}