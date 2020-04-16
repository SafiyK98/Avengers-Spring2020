import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UprootController implements ActionListener{
	
	//the model of uproot
	private UprootModel model;
	
	private UprootGUI view;
	
	
	public UprootController(UprootModel model, UprootGUI view) {
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Exit")) {
			System.exit(0);
		} else if (command.contentEquals("Start New Game")) {
			model = new UprootModel();
			view.updateText(model.getValue());
		} else if (command.contentEquals("Save Game")) {
			model.saveGame();
		} else if (command.contentEquals("UP")) {
			model.moveUp();
			view.updateText(model.getValue());
		} else if (command.contentEquals("DOWN")) {
			model.moveDown();
			view.updateText(model.getValue());
		} else if (command.contentEquals("^")) {
			model.moveNorth();
			view.updateText(model.getValue());
		} else if (command.contentEquals(">")) {
			model.moveEast();
			view.updateText(model.getValue());
		} else if (command.contentEquals("v")) {
			model.moveSouth();
			view.updateText(model.getValue());
		} else if (command.contentEquals("<")) {
			model.moveWest();
			view.updateText(model.getValue());
		} else {
			model.applyCommand(command);
			view.updateText(model.getValue());
		}
	}

}
