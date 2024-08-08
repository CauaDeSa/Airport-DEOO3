package totems.airport.Displays;

import data.airport.model.FlightData;
import data.airport.states.State;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoStateDisplay implements IDisplay{
    private JFrame frame;
    private Map<State, JPanel> displayPanels;
    private Map<Long, JLabel> displayLabels;


    public TwoStateDisplay(String firstTitle, String secondTitle, State firstState, State secondState){
        displayPanels = new HashMap<>();
        displayLabels = new HashMap<>();

        frame = new JFrame(firstTitle + " & " + secondTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setAlwaysOnTop(true);

        var mainDiv = new JPanel();
        mainDiv.setLayout(new GridLayout(1, 2));

        frame.add(mainDiv);

        JLabel title1 = new JLabel(firstTitle);
        title1.setFont(new Font("Arial", Font.BOLD, 16));

        var firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
        firstPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        firstPanel.add(title1);

        JLabel title2 = new JLabel(secondTitle);
        title2.setFont(new Font("Arial", Font.BOLD, 16));

        var secondPanel = new JPanel();
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
        secondPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        secondPanel.add(title2);

        mainDiv.add(firstPanel);
        mainDiv.add(secondPanel);

        displayPanels.put(firstState, firstPanel);
        displayPanels.put(secondState, secondPanel);

        frame.setVisible(true);
    }

    @Override
    public void insert(FlightData flight) {
        if (flight == null || !displayPanels.containsKey(flight.getState()))
            return;

        JLabel flightLabel = new JLabel(flight.toString());
        var panel = displayPanels.get(flight.getState());

        if (Arrays.asList(panel.getComponents()).contains(flightLabel))
            return;

        panel.add(flightLabel);
        displayLabels.put(flight.getFlightNumber(), flightLabel);

        updateDisplayContent();
    }

    @Override
    public void removeIfContains(FlightData flight) {
        var label = displayLabels.get(flight.getFlightNumber());

        if (label != null){
            for (var panel : displayPanels.values()){
                panel.remove(label);
            }

            displayLabels.remove(flight.getFlightNumber());
            updateDisplayContent();
        }
    }

    private void updateDisplayContent(){
        frame.revalidate();
        frame.repaint();
    }
}
