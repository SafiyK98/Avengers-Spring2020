import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//This class will store all of the methods for the game to run
public class Game {
	
	//Stores all of the rooms in the game
	HashMap<Integer, Room> hmRooms;
	
	//Stores all the items in the game
	HashMap<Integer, Item> hmItems;
	
	//Stores all the puzzles in the game
	HashMap<Integer, Puzzle> hmPuzzles;
	
	//Stores all the monsters in the game
	HashMap<Integer, Monster> hmMonsters;
	
	//Stores all the help commands in the game
	HashMap<String, String> hmHelpCommands;
	
	//Stores the current Player
	Player player;
	
	//Scanner for the game class
	Scanner sc;
	
	//Stores the game description
	String description;
	
	
	
	Game(File rooms, File items, File puzzles, File monsters, File helpCommands, File description)
	{
		//Read all of the files
		readRooms(rooms);
		readItems(items);
		readPuzzles(puzzles);
//		readMonsters(monsters);
//		readHelpCommands(helpCommands);
		readDescription(description);
		
		//Set the location for the items
		setStartingIDItems();
		
	}
	
	//Method to read the rooms text file
	void readRooms(File rooms) {
		try {
			sc = new Scanner(rooms);
		} catch (FileNotFoundException e) {
			System.out.print("No such file exists");
		}
		hmRooms = new HashMap<Integer, Room>();
		
		//split file into lines
		while (sc.hasNextLine()) {
			
			//split each line on the comma and add to array
			String[] strParts = sc.nextLine().split( "," ); 
			
			//read from array and create Room object and add to ArrayList
			int id = Integer.parseInt(strParts[0]);
			String name = strParts[1];
			String desc = strParts[2];
			int north = Integer.parseInt(strParts[3]);
			int east = Integer.parseInt(strParts[4]);
			int south = Integer.parseInt(strParts[5]);
			int west = Integer.parseInt(strParts[6]);
			int up = Integer.parseInt(strParts[7]);
			int down = Integer.parseInt(strParts[8]);
			int level = Integer.parseInt(strParts[9]);
					
			Room room = new Room(id, name, desc, north, east, south, west, up, down, level);
				
			hmRooms.put(room.getID(), room);
			
		  }
	}
	
	//Method to read the items file
	void readItems(File items) {
		try {
			sc = new Scanner(items);
		} catch (FileNotFoundException e) {
			System.out.print("No such file exists ");
		}
		hmItems = new HashMap<Integer, Item>();
		
		//split file into lines
		while (sc.hasNextLine()) 
		{
			//split each line on the comma and add to array
			String[] strParts = sc.nextLine().split( "," ); 
			
			Item item = null;
			int id = Integer.parseInt(strParts[0]);
			String name = strParts[1];
			String desc = strParts[2];
			int effect = Integer.parseInt(strParts[3]);
			int type = Integer.parseInt(strParts[4]);
			
			String locations = strParts[5];
			String[] loc = locations.split(":");
			ArrayList<Integer> location = new ArrayList<Integer>();
			for(int i = 0; i<loc.length; i++) {
				location.add(Integer.parseInt(loc[i]));
			}
			
			//read from array and create Item object and add to ArrayList
			if(type == 0) {
				item = new Equipable(id,name,desc,location,effect);
			}else {
				item = new Consumable(id,name,desc,location,effect);
			}
			
			hmItems.put(item.getId(), item);
		}
	}
	
	//Method to read the puzzle file
	void readPuzzles(File puzzles) {
			try 
			{
				sc = new Scanner(puzzles);
			} 
			catch(FileNotFoundException e) 
			{
				System.out.print("No such file exists");
			}
			hmPuzzles = new HashMap<Integer, Puzzle>();
			while(sc.hasNextLine()) 
			{
				String[] strParts = sc.nextLine().split(",");
				
					//read from array and create Puzzle object and add to Hashmap
					int id = Integer.parseInt(strParts[0]);
					String desc = strParts[1];
					String hint = strParts[2];
					String solution = strParts[3];
					int locationOpen = Integer.parseInt(strParts[4]);
					int locationPlaced = Integer.parseInt(strParts[5]);
						
					Puzzle puzzle = new Puzzle(id, desc, hint, solution, locationOpen, locationPlaced);
						
					hmPuzzles.put(puzzle.getID(), puzzle);
			
			}
	}
	
	//Method to read the monster file
	void readMonsters(File monster) {
		
	}
	
	//Method to read the helpCommands file
	void readHelpCommands(File helpCommands) {
		
	}
	
	//Method to read the game description
	void readDescription(File description) {
		try {
			sc = new Scanner(description);
		} catch(FileNotFoundException e) {
			System.out.print("No such file exists");
		}
		while(sc.hasNextLine()) {
			this.description = sc.nextLine();
		}
	}
	
	//Method to set the starting ID for the 3 items
	void setStartingIDItems() {	
		for(int i =1; i<=hmItems.size(); i++) {
			ArrayList<Integer> locations = hmItems.get(i).getLocation();
			for(int j = 0; j<locations.size(); j++) {
				int loc = locations.get(j);
				if(loc == 0) {
					loc = (int)(Math.random()*(31-1)) +1;
				}
				else {
					loc = locations.get(j);
				}
			hmItems.get(i).addItem(hmRooms.get(loc));
			}
		}
	}
	
	
	
	
	
	//Method to display the game description
	String displayDescription() {
		return description;
	}
	
	//Method to set the player
	void setPlayer(Player p) {
		this.player = p;
	}
	
	//Method to get the player
	Player getPlayer() {
		return player;
	}
	
	//Method to initate game in the players current room
	String startGame() {
		int startingID = player.getLocation();
		Room room = hmRooms.get(startingID);
		String print = "Your starting location is in room " + startingID + " which is the " + room.getName();
		print = print + ". Explore the casle and see what you can find. To move use the buttons on the right and to perform an action please type in the box below. For more information on commands type \"Help\".";
		return print;
	}
	
	//Method to get the current room level of the player
	int getRoomLevel() {
		return hmRooms.get(player.getLocation()).getLevel();
	}
	
	
	//Method to move North
	String moveNorth(){
		Room currentRoom = hmRooms.get(player.getLocation());
		int newRoomIndex = currentRoom.getNorth();
		
		if (newRoomIndex==0) {
			return "Sorry, you can not move that direction.";
		}
		
		else {
			currentRoom.setVisited(true);
			player.setLocation(newRoomIndex);
			Room newRoom = hmRooms.get(newRoomIndex);
			return "You are now in the " + newRoom.getName() + ".";
		}
		
	}
	
	//Method to move East
	String moveEast(){
		Room currentRoom = hmRooms.get(player.getLocation());
		int newRoomIndex = currentRoom.getEast();
		
		if (newRoomIndex==0) {
			return "Sorry, you can not move that direction.";
		}
		
		else {
			currentRoom.setVisited(true);
			player.setLocation(newRoomIndex);
			Room newRoom = hmRooms.get(newRoomIndex);
			return "You are now in the " + newRoom.getName() + ".";
		}
		
	}
	
	//Method to move South
	String moveSouth(){
		Room currentRoom = hmRooms.get(player.getLocation());
		int newRoomIndex = currentRoom.getSouth();
		
		if (newRoomIndex==0) {
			return "Sorry, you can not move that direction.";
		}
		
		else {
			currentRoom.setVisited(true);
			player.setLocation(newRoomIndex);
			Room newRoom = hmRooms.get(newRoomIndex);
			return "You are now in the " + newRoom.getName() + ".";
		}
		
	}
	
	//Method to move West
	String moveWest(){
		Room currentRoom = hmRooms.get(player.getLocation());
		int newRoomIndex = currentRoom.getWest();
		
		if (newRoomIndex==0) {
			return "Sorry, you can not move that direction.";
		}
		
		else {
			currentRoom.setVisited(true);
			player.setLocation(newRoomIndex);
			Room newRoom = hmRooms.get(newRoomIndex);
			return "You are now in the " + newRoom.getName() + ".";
		}
		
	}
	
	//Method to move Up
	String moveUp(){
		Room currentRoom = hmRooms.get(player.getLocation());
		int newRoomIndex = currentRoom.getUp();
		
		if (newRoomIndex==0) {
			return "Sorry, you can not move that direction.";
		}
		
		else {
			currentRoom.setVisited(true);
			player.setLocation(newRoomIndex);
			Room newRoom = hmRooms.get(newRoomIndex);
			return "You are now in the " + newRoom.getName() + ".";
		}
		
	}
	
	//Method to move Down
	String moveDown(){
		Room currentRoom = hmRooms.get(player.getLocation());
		int newRoomIndex = currentRoom.getDown();
		
		if (newRoomIndex==0) {
			return "Sorry, you can not move that direction.";
		}
		
		else {
			currentRoom.setVisited(true);
			player.setLocation(newRoomIndex);
			Room newRoom = hmRooms.get(newRoomIndex);
			return "You are now in the " + newRoom.getName() + ".";
		}
		
	}
	
	//Method to apply other commands 
	String applyCommand(String text) {
		String response = "";
		if(text.equalsIgnoreCase("Help")) {
//			getHelp();
		}
		
		else if(text.equalsIgnoreCase("Explore")) {
			response = searchRoom(player.getLocation());
		}
		
		else if(text.contains("Drop") || text.contains("drop")) {
//			dropItem(text);
		}
		
		else if(text.contains("Equip") || text.contains("equip")) {
//			equipItem(text);
		}
		
		else if(text.contains("Examine") || text.contains("examine")) {
			examineItem(text);
		}
		
		else if(text.contains("Pick up") || text.contains("pick up")) {
			response = pickUpItem(text);
		}
		
			
		else if (text.equalsIgnoreCase("Heal")) {
//			text.useConsumable();
		}
		return response;
	}
	
		//Method to search room 
		String searchRoom(int roomIndex){
			Room room = hmRooms.get(roomIndex);
			if(!room.getInventory().isEmpty()) {
				return "\nIn " + room.getName() + " you find the following things " + room.getInventory();
			}
			else {
				return "\nThe " + room.getName() + " has no items in it.";
			}
		}	
		
		//Method to examine item
		String examineItem(String text) {
			int roomIndex = player.getLocation();
			Room room = hmRooms.get(roomIndex);
			ArrayList<Item> items = room.getInventory();
			
			
			int j = 0;
			boolean yes = false;
			for(int k = 0; k<items.size(); k++) {
				if(text.substring(8).equalsIgnoreCase(items.get(k).getName())) {
					yes = true;
					j = k;
				}
			}
			if(yes == true) {
				return "\n"+items.get(j).getDesc();
			}
			else {
				return "Sorry we did not find that item in the " + room.getName() +". Make sure to check your spelling.";
				}
			}
			

		//Method to pick up items
		String pickUpItem(String text) {
			int roomIndex = player.getLocation();
			Room room = hmRooms.get(roomIndex);
			ArrayList<Item> items = room.getInventory();
			if(items.isEmpty()) {
				return "Sorry, the " + room.getName() + " is empty.";
			}
			int j = 0;
			boolean yes = false;
			for(int i = 0; i<items.size(); i++) {
				if(text.substring(8).equalsIgnoreCase(items.get(i).getName())) {
					yes = true;
					j = i;
				}
			}
			if(yes == true) {
				Item item = items.get(j);
				item.addItem(player, room);
				return item.getName() + " has been picked up from the room and successfully added to the player inventory.";
				
			}
			else {
				return "Sorry we did not find that item in the " + room.getName() +". Make sure to check your spelling.";
			}
		}
		
		//Method to drop items
		String dropItem(String text) {
			ArrayList<Item> items = player.getInventory();
			Room room = hmRooms.get(player.getLocation());
			if(items.isEmpty()) {
				return "Sorry, the player inventory is empty.";
			}
			int j =0;
			boolean yes = false;
			for(int i = 0; i<items.size(); i++) {
				if(text.substring(5).equalsIgnoreCase(items.get(i).getName())) {
					yes = true;
					j = i;
				}
			}
			if(yes == true) {
				Item item = items.get(j);
				item.dropItem(player, room);
				return items.get(j).getName() + " has been dropped successfully from the player inventory and placed in the " + room.getName();
				
			}
			else {
				return "Sorry we did not find that item in the player inventory. Make sure to check your spelling.";
			}
		}

}
