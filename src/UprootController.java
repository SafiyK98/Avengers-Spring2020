import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UprootController implements ActionListener{
	
	//the model of uproot
	private UprootModel model;
	
	private UprootGUI view;
	
	
	public UprootController(UprootModel model, UprootGUI view){
		this.model = model;
		this.view = view;
		initiateGame();

		
	}
	
	public void initiateGame() {
		gameDescription();
		model.initiateGame();
		view.setDisplay(model.getValue());
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
		} else if (model.newGame == true){
		 	model.startOrLoad(command);
		} else if (model.createPlayer == true) {
			model.createPlayer(command);
		} else if(model.loadPlayer==true) {
			model.loadPlayerInfo(command);
			if(model.loadedPlayer == true)
				view.updateInventory(model.getInventory(), model.getEquipped());
		} else if(model.attack == true) {
			model.attackMode(command);
			if(model.getValue().contains("defeated"))
				System.exit(0);
			view.updateInventory(model.getInventory(), model.getEquipped());
		} else if (model.solve == true) {
			model.solvePuzzle(command);
		}else if (model.drop == true) {
			model.dropItem(command);
			view.updateInventory(model.getInventory(), model.getEquipped());
		} else if (command.equalsIgnoreCase("Start New Game")) {
			model.startNewGame();
			view.startNewGame();
		} else if (command.equalsIgnoreCase("Save Game")) {
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
		} else if(command.equalsIgnoreCase("Attack")) {
			model.attackMode(command);
		} else if(command.equalsIgnoreCase("Explore Puzzle")) {
			model.solvePuzzle(command);
		} else{
			model.applyCommand(command);
			view.updateInventory(model.getInventory(), model.getEquipped());
		}
		view.setDisplay(model.getValue());
		view.updateImage(model.getRoomLevel());	
	}
}
