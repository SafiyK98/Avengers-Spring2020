
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
		return displayString;
	}
	
	//Method for model to save game
	public void saveGame() {
		
	}
	
	//Method for model to move up
	public void moveUp() {
		
	}
	
	//Method for model to move down
	public void moveDown() {
		
	}
	
	//Method for model to move north
	public void moveNorth() {
			
	}
	
	//Method for model to move east
	public void moveEast() {
			
	} 
	
	//Method for model to move south
	public void moveSouth() {
			
	}
	
	//Method for model to move west
	public void moveWest() {
			
	}
	
	//Method for model to apply text command from player
	public void applyCommand(String text) {
		
	}
	
}
