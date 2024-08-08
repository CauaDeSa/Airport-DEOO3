package totems.airport.Displays;

import data.airport.model.FlightData;
import data.airport.states.State;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OneStateDisplay implements IDisplay {
    private JFrame frame;
    private JPanel panel;
    private State state;
    private Map<Long, JLabel> displayItems;

    public OneStateDisplay(String totemName, State state){
        frame = new JFrame(totemName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setAlwaysOnTop(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);

        frame.add(panel);
        frame.setVisible(true);

        displayItems = new HashMap<Long, JLabel>();
        this.state = state;
    }

    @Override
    public void insert(FlightData flight){
        if (flight == null || !flight.getState().equals(state))
            return;

        JLabel flightLabel = new JLabel(flight.toString());

        if (displayItems.put(flight.getFlightNumber(), flightLabel) == null)
        {
            panel.add(flightLabel);
            updateDisplayContent();
        }
    }

    @Override
    public void removeIfContains(FlightData flight){
        var flightLabel = displayItems.remove(flight.getFlightNumber());

        if (flightLabel != null){
            panel.remove(flightLabel);
            updateDisplayContent();
        }
    }

    private void updateDisplayContent(){
        panel.revalidate();
        panel.repaint();
    }
}