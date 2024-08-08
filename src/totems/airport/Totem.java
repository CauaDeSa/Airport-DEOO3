package totems.airport;

import data.airport.listener.FlightDataObserver;
import data.airport.model.FlightData;
import totems.airport.Displays.IDisplay;

public class Totem implements FlightDataObserver {
    private final IDisplay display;

    public Totem(IDisplay display) {
        this.display = display;
    }

    @Override
    public void update(FlightData flight) {
        if (flight != null) {
            display.removeIfContains(flight);
            display.insert(flight);
        }
    }
}