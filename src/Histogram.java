import javax.swing.*;
import java.awt.*;

public class Histogram extends JPanel {
	// Declare an array to hold the hamming distances of 0 - 4
	private int[] hammingCounts = new int[5];
	
	private Color[] sliceColors = {
			Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN
	};
	
	public void setHammingCounts(int[] counts) {
		this.hammingCounts = counts;
		// We'll also need to re-draw the panel at this part since the data 
		// changes here
		repaint();
	}
	
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
