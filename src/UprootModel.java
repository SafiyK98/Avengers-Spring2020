public class UprootModel {

	//This is the current display to the player
	private String displayValue;
	
	//This will be the string corresponding to what the player is trying to do
	private String displayString;
	
	//This will store the last operation entered by the player
	private String operation;
	
	//This hashmap will store the rooms
	private Game game;
	
	//Constructor for a new model
	public UprootModel() {
		displayValue = "Welcome to UPROOT!";
		displayString = "" + displayValue;
		operation = "";
	}
	
	//Method to display text to player
	public String getValue() {
		return displayValue;
	}
	
	//Method for model to save game
	public void saveGame() {
		
	}
	
	//Method for model to move up
	public void moveUp() {
		displayString = game.moveUp();
		displayValue = displayValue + "\n" + displayString;
	}
	
	//Method for model to move down
	public void moveDown() {
		displayString = game.moveDown();
		displayValue = displayValue + "\n" + displayString;
	}
	
	//Method for model to move north
	public void moveNorth() {
		displayString = game.moveNorth();
		displayValue = displayValue + "\n" + displayString;	
	}
	
	//Method for model to move east
	public void moveEast() {
		displayString = game.moveEast();
		displayValue = displayValue + "\n" + displayString;	
	} 
	
	//Method for model to move south
	public void moveSouth() {
		displayString = game.moveSouth();
		displayValue = displayValue + "\n" + displayString;	
	}
	
	//Method for model to move west
	public void moveWest() {
		displayString = game.moveWest();
		displayValue = displayValue + "\n" + displayString;	
	}
	
	//Method for model to apply text command from player
	public void applyCommand(String text) {
		
	}
	
}
