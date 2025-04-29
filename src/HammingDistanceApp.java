import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HammingDistanceApp extends JFrame{
	
	private JComboBox<String> stationDropdown = new JComboBox<>();
	// Hamming Distances will range from 0 - 4
	private JSlider hammingSlider; 
	private JButton showButton;
	private JTextArea resultArea;
	
	public HammingDistanceApp() {
		super("CMPS 367 - Hamming Distance App");
		
		stationDropdown = new JComboBox<>();
		// Hamming Distances will range from 0 - 4
		hammingSlider = new JSlider(0, 4, 0); 
		showButton = new JButton("Show Station");
		resultArea = new JTextArea(10, 30);
		
		// Create a panel for the dropdown and slider
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Select Station:"));
		topPanel.add(stationDropdown);
		topPanel.add(new JLabel("Hamming Distance:"));
		topPanel.add(hammingSlider);
		topPanel.add(showButton);
		
		// Now, we'll need to add this to the main layout
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(new JScrollPane(resultArea), BorderLayout.CENTER);
		
		// set the window settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
