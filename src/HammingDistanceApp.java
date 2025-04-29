import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeSet;

public class HammingDistanceApp extends JFrame {

    private JComboBox<String> stationDropdown;
    private JComboBox<String> compareDropdown;
    private JSlider hammingSlider;
    private JTextField hammingField;
    private JButton showButton, calculateButton, addButton;
    private JTextField[] distanceFields;
    private JTextArea resultArea;
    private JTextField addStationField;
    private TreeSet<String> stationSet = new TreeSet<>();

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
        hammingSlider = new JSlider(1, 4, 1);
        hammingField.setText(String.valueOf(hammingSlider.getValue()));
        row1.add(hammingField);
        leftPanel.add(row1);
        
        //Row 12 : Slider on its own row
        JPanel row12 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        hammingSlider.setMajorTickSpacing(1);
        hammingSlider.setPaintTicks(true);
        hammingSlider.setPaintLabels(true);
        hammingSlider.addChangeListener((ChangeEvent e) ->
        hammingField.setText(String.valueOf(hammingSlider.getValue())));
        row12.add(hammingSlider);
        leftPanel.add(row12);
        
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
        
        // Here, we'll need to populate the dropdown menu. We can do this by 
        // calling our function to populate a TreeSet with all of the Radio Station
        // ID's
        loadStationsFromFile();
        for(String stid : stationSet) {
        	compareDropdown.addItem(stid);
        }
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
        row11.add(addButton);
        row11.add(addStationField);
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
    
    /**
     * This function is going to populate a TreeSet with the Radio station ID's that
     * that we can populate the drop down menu with
     */
    private void loadStationsFromFile() {
    	try(BufferedReader br = new BufferedReader(new FileReader("Mesonet.txt"))){
    		
    		// Skip the lines before the station ID's begin
			String sTemp; 
			String line;
			
			// Exhaust the first few lines that do not pertain to the station Id's
			while(	(line =  br.readLine()) != null) {
				
				// Capture the first word in the line			
				Scanner inSS = new Scanner(line);
				sTemp = inSS.next();
				
				if(sTemp.equals("STID")) {
					// if we're here, than we're at the starting point of where
					// we want to start checking
					break;
				}
				
			}
			
			// Now that we're here, we're at the start of the station ID's
			int arrIdx = 0;
			while( (line = br.readLine()) != null) {
				
				// Capture the first word in the line			
				Scanner inSS = new Scanner(line);
				sTemp = inSS.next();
				
				// Push the stID into the array holding stID names
				stationSet.add(sTemp);
			}	
    	}catch(Exception err) {
    		System.out.println("Error: " + err.getMessage());
    	}
    }
    
    private int getHammingDistance(String s1, String s2) {
    	int dist = 0;
    	for(int i = 0; i < s1.length(); i++) {
    		if(s1.charAt(i) != s2.charAt(i)) {
    			// If we're here, than the corresponding letters don't match
    			// so we can increase the hamming distance by 1
    			dist++;
    		}
    	} // end of for loop
    	// We can now safely return the hamming distance collected
    	return dist;
    }
    
    private TreeSet<String> getMatchingStations(String selectedStation, int hammingDist){
    	// Declare a new TreeSet to hold the values of all the matches
    	TreeSet<String> returnTree = new TreeSet<>();
    	
    	for(String otherStation : stationSet) {
    		// Make sure that we're not checking against the selected station
    		if( !(otherStation.equals(selectedStation))) {
    			// Call our function to calculate the hamming distance of the
    			// other station against the selected station. If this value
    			// matches the hammingDist, we can add it to the TreeSet that 
    			// we're going to return
    			if(getHammingDistance(otherStation, selectedStation) == hammingDist) {
    				returnTree.add(otherStation);
    			}
    		}
    	}
    	
    	return returnTree;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HammingDistanceApp::new);
    }
}
