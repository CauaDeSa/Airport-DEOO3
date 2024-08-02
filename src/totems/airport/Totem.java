package totems.airport;

import data.airport.listener.FlightDataObserver;
import data.airport.model.FlightData;
import data.airport.states.Arriving;
import data.airport.states.State;

public class Totem implements FlightDataObserver {
    private final State stateObserver;
    private final Display display;

    public Totem(State stateObserver, Display display) {
        this.stateObserver = stateObserver;
        this.display = display;
    }

    @Override
    public void update(FlightData flight) {
        if (flight != null) {
            if (flight.getState() == stateObserver)
                display.insert(flight);
            else
                display.remove(flight.getFlightNumber());
        }
    }
}