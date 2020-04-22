import java.io.File;

public class UprootModel {

	//This is the current line of text to display to the player
	private String displayValue;
	
	
	//This hashmap will store the rooms
	private Game game;

	
	
	//Below are the text files for the game 
	static File rooms = new File(System.getProperty("user.dir")+"/"+"Rooms.txt");
	static File navigation = new File(System.getProperty("user.dir") + "/" + "NavigationCommands.txt");
	static File items = new File(System.getProperty("user.dir") + "/" + "Items.txt");
	static File puzzles = new File(System.getProperty("user.dir") + "/" + "Puzzles.txt");
	static File helpCommands = new File(System.getProperty("user.dir") + "/" + "Help Commands.txt");
	static File monsters = new File(System.getProperty("user.dir") + "/" + "Monster.txt");
	static File description = new File(System.getProperty("user.dir") + "/" + "GameDescription.txt");
	
	
	//Constructor for a new model
	public UprootModel() {
		game = new Game(rooms,items,puzzles, monsters, helpCommands,description);
	}
	
	//Method to start new game 
	public void startNewGame() {
		displayValue = "Please enter a Player name: ";
	}
	
	//Method to display game description at the start of a new game
	public void gameDescription() {
		displayValue = game.displayDescription();
	}
	
	//Method to create new player
	public void createPlayer(String name) {
		displayValue = "Welcome " + name +"! Today, you will be known as Prince and the King's Eldest son.";
		game.setPlayer(new Player(1, name, 100, 1));
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
		displayValue = game.moveUp();
	}
	
	//Method for model to move down
	public void moveDown() {
		displayValue = game.moveDown();
	}
	
	//Method for model to move north
	public void moveNorth() {
		displayValue = game.moveNorth();
	}
	
	//Method for model to move east
	public void moveEast() {
		displayValue = game.moveEast();
	} 
	
	//Method for model to move south
	public void moveSouth() {
		displayValue = game.moveSouth();
	}
	
	//Method for model to move west
	public void moveWest() {
		displayValue = game.moveWest();
	}
	
	//Method for model to apply text command from player
	public void applyCommand(String text) {
		displayValue = game.applyCommand(text);
	}
	
}
