import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UprootController implements ActionListener{
	
	//the model of uproot
	private UprootModel model;
	
	private UprootGUI view;
	
	
	public UprootController(UprootModel model, UprootGUI view){
		this.model = model;
		this.view = view;
		startGame();

		
	}
	
	public void startGame() {
		model.startNewGame();
		view.setDisplay(model.getValue());
		
		
	}
	public void gameDescription() {
		model.gameDescription();
		view.setDisplay(model.getValue());
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Exit")) {
			System.exit(0);
		} else if (command.contentEquals("Start New Game")) {
			model = new UprootModel();
		} else if (command.contentEquals("Save Game")) {
			model.saveGame();
		} else if (command.equals("Up")) {
			model.moveUp();
		} else if (command.equals("Down")) {
			model.moveDown();
		} else if (command.equals("North")) {
			model.moveNorth();
		} else if (command.equals("East")) {
			model.moveEast();
		} else if (command.equals("South")) {
			model.moveSouth();
		} else if (command.equals("West")) {
			model.moveWest();
		} else if (model.newGame == true){
			model.createPlayer(command);
			model.newGame = false;
		} else {
			model.applyCommand(command);
			view.updateInventory(model.getInventory());
		}
		view.setDisplay(model.getValue());
		view.updateImage(model.getRoomLevel());
		
	}
}
