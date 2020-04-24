import java.io.File;
import java.util.ArrayList;

public class UprootModel {

	//This is the current line of text to display to the player
	private String displayValue;
	
	//This will be set to false if the player is continuing a saved game
	public boolean newGame = true;
	
	
	//This hashmap will store the rooms
	private Game game;
	
	//This will store the current level the player is on to change the map
	private int roomLevel = 1;

	
	
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
		game.setPlayer(new Player(1, name, 100, 1));
		displayValue = "Welcome " + name +"! Today, you will be known as Prince and the King's Eldest son.";
		displayValue = displayValue.substring(0, displayValue.length()) + "<BR><BR>" + game.displayDescription();
		displayValue = displayValue.substring(0, displayValue.length()) + "<BR><BR>" + game.startGame();
	}
	
	//Method to place player in their current location and begin game
	public void startGame() {
		displayValue = game.startGame();
	}
	
	
	//Method to display text to player
	public String getValue() {
		return displayValue;
	}
	
	//Method to get the room level
	public int getRoomLevel() {
		return roomLevel;
	}
	
	//Method for model to save game
	public void saveGame() {
		
	}
	
	//Method for model to move up
	public void moveUp() {
		displayValue = game.moveUp();
		roomLevel = game.getRoomLevel();
	}
	
	//Method for model to move down
	public void moveDown() {
		displayValue = game.moveDown();
		roomLevel = game.getRoomLevel();
	}
	
	//Method for model to move north
	public void moveNorth() {
		displayValue = game.moveNorth();
		roomLevel = game.getRoomLevel();
	}
	
	//Method for model to move east
	public void moveEast() {
		displayValue = game.moveEast();
		roomLevel = game.getRoomLevel();
	} 
	
	//Method for model to move south
	public void moveSouth() {
		displayValue = game.moveSouth();
		roomLevel = game.getRoomLevel();
	}
	
	//Method for model to move west
	public void moveWest() {
		displayValue = game.moveWest();
		roomLevel = game.getRoomLevel();
	}
	
	//Method for model to apply text command from player
	public void applyCommand(String command) {
		displayValue = game.applyCommand(command);
	}
	
	//Method to get the player inventory to update the view
	public ArrayList<Item> getInventory() {
		return game.getPlayer().getInventory();
	}
	
}
