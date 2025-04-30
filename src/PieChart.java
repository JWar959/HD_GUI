import javax.swing.*;
import java.awt.*;

/**
 * PieChart class that will construct a Pie Chart displaying the proportions of the different
 * Hamming Distances from the selected radio station Id.
 */
public class PieChart extends JPanel{
	
	private int[] hammingCounts = new int[5];

	private Color[] sliceColors = {
			Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN
	};
	
	public void setHammingCounts(int[] counts) {
		this.hammingCounts = counts;
		// ensure to repaint here to update the panel with the new data
		repaint();
	}
	
	/**
	 * Overridden constructor that will draw our pie chart displaying the proportions of the
	 * different Hamming Distances from the selected radio station Id.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		int total = 0;
		for(int count : hammingCounts) {
			total += count;
		}
		
		// Make sure we're not dividing by zero here
		if(total == 0) {
			return;
		}
		
		int diameter = Math.min(getWidth(), getHeight()) - 80;
		int x = (getWidth() - diameter) / 2;
		int y = 10;
		int startAngle = 0;
		
		// Loop through the different hamming distances, drawing the
		// slices of the pie as we go
		for(int i = 0; i < 5; i++) {
			int arcAngle = (int)Math.round(360.0 * hammingCounts[i] / total);
			g2.setColor(sliceColors[i]);
			g2.fillArc(x, y, diameter, diameter, startAngle, arcAngle);
			startAngle += arcAngle;
		}
		
		// Next, we'll have to make a legend for the pie chart
		int legendY = diameter + 30;
		int legendX = 10;
		// Add a spacing variable here to adjust the spacing between labels
		int spacing = 90;
		
		for(int i = 0; i < 5; i++) {
			g2.setColor(sliceColors[i]);
			g2.fillRect(legendX + i * spacing, legendY, 15, 15);
			g2.setColor(Color.BLACK);
			g2.drawString("Distance " + i + ": " + hammingCounts[i], legendX + i * spacing + 20, legendY + 12);
		}		
	}
}
