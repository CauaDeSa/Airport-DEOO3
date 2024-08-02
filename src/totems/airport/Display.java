package totems.airport;

import data.airport.model.FlightData;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Display {

    private JFrame frame;
    private JPanel panel;
    private Map<Long, JLabel> displayItems;

    public Display(String totemName){
        frame = new JFrame("Flight Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);

        frame.add(panel);

        JLabel totem = new JLabel(totemName);
        totem.setBounds(10, 20, 80, 25);

        panel.add(totem);
        frame.setVisible(true);

        displayItems = new HashMap<Long, JLabel>();
    }

    public void insert(FlightData flight){
        JLabel flightLabel = new JLabel(flight.toString());

        panel.add(flightLabel);
        displayItems.put(flight.getFlightNumber(), flightLabel);

        updateDisplayContent();
    }

    public void remove(Long flightNumber){
        var flight = displayItems.remove(flightNumber);

        if (flight != null){
            panel.remove(flight);
            updateDisplayContent();
        }
    }

    private void updateDisplayContent(){
        panel.revalidate();
        panel.repaint();
    }
}