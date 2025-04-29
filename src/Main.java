import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		System.out.println("Test123");
		SwingUtilities.invokeLater(HammingDistanceApp::new);
	}

}
