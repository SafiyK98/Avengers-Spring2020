import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UprootController implements ActionListener{
	
	//the model of uproot
	private UprootModel model;
	
	private UprootGUI view;
	
	private boolean newGame;
	
	
	public UprootController(UprootModel model, UprootGUI view) {
		this.model = model;
		this.view = view;
		
		

		
		createPlayer();
		
	}
	
	public void gameDescription() {
		model.gameDescription();
		view.setDisplay(model.getValue());
	}
	
	public void createPlayer() {
		model.createPlayer();
		view.setDisplay(model.getValue());
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Exit")) {
			System.exit(0);
		} else if (command.contentEquals("Start New Game")) {
			model = new UprootModel();
			view.setDisplay(model.getValue());
		} else if (command.contentEquals("Save Game")) {
			model.saveGame();
		} else if (command.contentEquals("UP")) {
			model.moveUp();
			view.setDisplay(model.getValue());
		} else if (command.contentEquals("DOWN")) {
			model.moveDown();
			view.setDisplay(model.getValue());
		} else if (command.contentEquals("^")) {
			model.moveNorth();
			view.setDisplay(model.getValue());
		} else if (command.contentEquals(">")) {
			model.moveEast();
			view.setDisplay(model.getValue());
		} else if (command.contentEquals("v")) {
			model.moveSouth();
			view.setDisplay(model.getValue());
		} else if (command.contentEquals("<")) {
			model.moveWest();
			view.setDisplay(model.getValue());
		} else {
			model.applyCommand(command);
			view.setDisplay(model.getValue());
		}
	}

}
