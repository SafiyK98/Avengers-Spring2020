import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		UprootModel model = new UprootModel();
		UprootGUI view = new UprootGUI();
		
		UprootController controller = new UprootController(model, view);
		
		view.registerListener(controller);
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(1500,1200);
		view.setLocation(100, 0);
		view.setVisible(true);
		
	}
	
}
