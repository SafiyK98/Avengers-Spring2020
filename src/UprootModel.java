import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UprootModel {

	//This is the current line of text to display to the player
	private String displayValue;
	
	//This will be set to false if the player is continuing a saved game
	public boolean newGame = true;
	
	//This will hold if the player is tryig to load a saved game
	public boolean loadPlayer = false;
	
	//This will hold if the player needs to start a new game
	public boolean createPlayer = false;
	
	//This hashmap will store the rooms
	private Game game;
	
	//This will store the current level the player is on to change the map
	private int roomLevel = 1;

	//This will store all of the players and their info
	private HashMap<String, String> playersInfo;
	
	//This will be used to read the saved player file
	Scanner sc;
	
	//Below are the text files for the game 
	static File rooms = new File(System.getProperty("user.dir")+"/"+"Rooms.txt");
	static File items = new File(System.getProperty("user.dir") + "/" + "Items.txt");
	static File puzzles = new File(System.getProperty("user.dir") + "/" + "Puzzles.txt");
	static File helpCommands = new File(System.getProperty("user.dir") + "/" + "HelpCommands.txt");
	static File monsters = new File(System.getProperty("user.dir") + "/" + "Monsters.txt");
	static File description = new File(System.getProperty("user.dir") + "/" + "GameDescription.txt");
	static File savedPlayers = new File(System.getProperty("user.dir") + "/" + "SavedPlayers.txt");
	
	
	//Constructor for a new model
	public UprootModel() {
		readPlayers();
		game = new Game(rooms,items,puzzles, monsters, helpCommands,description);
	}
	
	//Method to read all of the saved players into a Hashmap 
	public void readPlayers() {
		try {
			sc = new Scanner(savedPlayers);	
		}catch (FileNotFoundException e) {
			System.out.print("Player file does not exist.");
		}
		playersInfo = new HashMap<String,String>();
		while(sc.hasNextLine()) {
			String[] strParts = sc.nextLine().split(":");
			playersInfo.put(strParts[0], strParts[1]);
		}
		
	}
	
	//Method to initiate game
	public void initiateGame() {
		displayValue = "Would you like to: \nStart New Game \nLoad Saved Game \nExit";
	}
	
	//Method to start new game or load saved game
	public void startOrLoad(String command) {
		if(command.equalsIgnoreCase("Start New Game"))
			startNewGame();
		else if (command.equalsIgnoreCase("Load Saved Game"))
			loadSavedGame();
		else
			displayValue = "Sorry but that is not a valid command right now.";
	}
	
	//Method to start new game 
	public void startNewGame() {
		displayValue = "Please enter player name: ";
		newGame = false;
		createPlayer = true;
	}
	
	//Method to load a saved game
	public void loadSavedGame() {
		displayValue = "Please enter player name: ";
		newGame = false;
		loadPlayer = true;
	}
	
	//Method to display game description at the start of a new game
	public void gameDescription() {
		displayValue = game.displayDescription();
	}
	
	//Method to create new player
	public void createPlayer(String name) {
		if(playersInfo.containsKey(name)) {
			displayValue = "Sorry, that name has already been taken. Please enter a different player name.";
		}
		else {
			game.setPlayer(new Player(1, name, 100, 1));
			displayValue = "Welcome " + name +"! Today, you will be known as Prince and the King's Eldest son.";
			displayValue = displayValue + "\n \n" + game.startGame();
			createPlayer = false;
		}
	}
	
	//Method to load player
	public void loadPlayerInfo(String name) {
		if(!(playersInfo.containsKey(name))) {
			displayValue = "Sorry, that player name is not in our records. Please enter a valid player name.";
		}
		else {
			game = new Game(rooms,items,puzzles, monsters, helpCommands,description,name, playersInfo.get(name));
			displayValue = "Welcome back " + name + "! Start the game where you left off.";
			displayValue = displayValue + "\n \n" + game.startGame();
			loadPlayer = false;
		}
	}
	
	//Method to save the game
	public void saveGame() {
		String info = game.saveGame();
		 try {
	         if (!savedPlayers.exists()) {
	            savedPlayers.createNewFile();
	         } 
	         FileWriter fw = new FileWriter(savedPlayers.getAbsoluteFile());
	         BufferedWriter bw = new BufferedWriter(fw);
	         bw.write(info);
	         bw.close();
	         displayValue = "Game saved successfully";
	      } catch (IOException e) {
	         e.printStackTrace();
	      } 
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
	
	//Method to get the player equipped items to update the view
	public ArrayList<Equipable> getEquipped(){
		return game.getPlayer().getEquipped();
	}
	
}
