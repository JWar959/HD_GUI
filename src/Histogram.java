import javax.swing.*;
import java.awt.*;

/**
 * Histogram Class constructs a histogram to display the corresponding number of radio
 * station Id matches for each Hamming Distance of 0 through 4. 
 */
public class Histogram extends JPanel {
	// Declare an array to hold the hamming distances of 0 - 4
	private int[] hammingCounts = new int[5];
	
	private Color[] sliceColors = {
			Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN
	};
	
	/**
	 * Function that sets the number of Hamming Distance matches
	 * @param counts An int array that sets the number of matching radio station Id's from
	 * Hamming Distances of 0 through 4.
	 */
	public void setHammingCounts(int[] counts) {
		this.hammingCounts = counts;
		// We'll also need to re-draw the panel at this part since the data 
		// changes here
		repaint();
	}
	
	/**
	 * Overriden paintComponent constructor that will draw our histogram. 
	 * @param Graphic object g that will be cast as a Graphics2D object to help draw our
	 * histogram
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Cast to Graphics2D here for better control
		Graphics2D g2 = (Graphics2D) g;
		
		int barHeight = 40;
		int startY = 20;
		int spacing = 10;
		// Going to subtract 100 from this value to keep space for the titles
		int maxBarWidth = getWidth() - 100;
		
		// Next, we'll have to deduce what the max value is so we can
		// scale the histogram correctly
		int maxCount = 0;
		for(int count : hammingCounts) {
			if(count > maxCount) {
				maxCount = count;
			}
		}
		
		for(int i = 0; i < 5; i++) {
			int barWidth = maxCount == 0 ? 0 : (hammingCounts[i] * maxBarWidth/maxCount);
			
			// Here we can set the bar's color and shape
			g2.setColor(sliceColors[i]);
			g2.fillRect(95, startY + i * (barHeight + spacing), barWidth, barHeight);
			
			// Next we can set the label
			g2.setColor(Color.BLACK);
			g2.drawString("Distance " + i + ": " + hammingCounts[i], 10, startY + i * (barHeight + spacing) + 25);

			
		}
	} // end of paintComponent	
} // end of Histogram
