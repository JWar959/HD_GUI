import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class HammingDistanceApp extends JFrame {

    private JComboBox<String> stationDropdown;
    private JComboBox<String> compareDropdown;
    private JSlider hammingSlider;
    private JTextField hammingField;
    private JButton showButton, calculateButton, addButton;
    private JTextField[] distanceFields;
    private JTextArea resultArea;
    private JTextField addStationField;

    public HammingDistanceApp() {
        super("CMPS 367: Hamming Distance App");

        // === Main Panel: Two columns (col-6, col-6) ===
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // === Left Panel: Contains all required UI elements ===
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Row 1: Slider label + slider + hammingField
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Enter Hamming Dist:"));
        hammingField = new JTextField(2);
        hammingField.setEditable(false);
        hammingSlider = new JSlider(0, 4, 0);
        hammingSlider.setMajorTickSpacing(1);
        hammingSlider.setPaintTicks(true);
        hammingSlider.setPaintLabels(true);
        hammingField.setText(String.valueOf(hammingSlider.getValue()));
        hammingSlider.addChangeListener((ChangeEvent e) ->
                hammingField.setText(String.valueOf(hammingSlider.getValue()))
        );
        row1.add(hammingField);
        row1.add(hammingSlider);
        leftPanel.add(row1);

        // Row 2: "Show Station" button
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        showButton = new JButton("Show Station");
        row2.add(showButton);
        leftPanel.add(row2);

        // Row 3: Text Area to show station results
        JPanel row3 = new JPanel(new BorderLayout());
        resultArea = new JTextArea(10, 25);
        resultArea.setEditable(false);
        row3.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        leftPanel.add(row3);

        // Row 4: Compare With dropdown
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(new JLabel("Compare with:"));
        compareDropdown = new JComboBox<>();
        row4.add(compareDropdown);
        leftPanel.add(row4);

        // Row 5: "Calculate HD" button
        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        calculateButton = new JButton("Calculate HD");
        row5.add(calculateButton);
        leftPanel.add(row5);

        // Rows 6–10: Distance 0–4 output fields
        distanceFields = new JTextField[5];
        for (int i = 0; i <= 4; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel("Distance " + i + ":"));
            distanceFields[i] = new JTextField(5);
            distanceFields[i].setEditable(false);
            row.add(distanceFields[i]);
            leftPanel.add(row);
        }

        // Row 11: Add Station field + button
        JPanel row11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addStationField = new JTextField(5);
        addButton = new JButton("Add Station");
        row11.add(addStationField);
        row11.add(addButton);
        leftPanel.add(row11);

        // === Right Panel (col-6): Free space ===
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JLabel freeZoneLabel = new JLabel("FREE ZONE: You are free to fill this area with a creative idea");
        freeZoneLabel.setHorizontalAlignment(JLabel.CENTER);
        rightPanel.add(freeZoneLabel, BorderLayout.NORTH);

        // === Add both panels to main container ===
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

        // Window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HammingDistanceApp::new);
    }
}
