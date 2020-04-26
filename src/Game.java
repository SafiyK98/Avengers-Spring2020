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
	ArrayList<String> helpCommands;
	
	//Stores the current Player
	Player player;
	
	//Scanner for the game class
	Scanner sc;
	
	//Stores the game description
	String description;
	
	
	//Create a new game from the files
	Game(File rooms, File items, File puzzles, File monsters, File helpCommands, File description)
	{
		//Read all of the files
		readRooms(rooms);
		readItems(items);
		readPuzzles(puzzles);
		readMonsters(monsters);
		readHelpCommands(helpCommands);
		readDescription(description);
		
		//Set the location for all objects
		setStartingIDItems();
		setPuzzles();
		setStartingLocMonsters();		
	}
	
	//Create a saved game from Strings
	Game(File rooms, File items, File puzzles, File monsters, File helpCommands, File description, String name, String info){
		//Read all of the files
		readRooms(rooms);
		readItems(items);
		readPuzzles(puzzles);
		readMonsters(monsters);
		readHelpCommands(helpCommands);
		readDescription(description);
		
		readInfo(name, info);
		
		//Set the location for all objects after adjusting their locations
		setStartingIDItems();
		setPuzzles();
		setStartingLocMonsters();
	}
	
	
	//Method to read the rooms text file
	void readRooms(File rooms) {
		try {
			sc = new Scanner(rooms);
		} catch (FileNotFoundException e) {
			System.out.print("Rooms file does not exist.");
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
			System.out.print("Items file does not exist.");
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
		try {
			sc = new Scanner(puzzles);
		} catch(FileNotFoundException e) {
			System.out.print("Puzzle file does not exist.");
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
		try {
			sc = new Scanner(monster);
		}catch(FileNotFoundException e) {
			System.out.print("Monster file does not exist.");
		}
		hmMonsters = new HashMap<Integer, Monster>();
		while(sc.hasNext()) {
			
			String[] strParts = sc.nextLine().split(",");
			//read from array and create Puzzle object and add to Hashmap
			int id = Integer.parseInt(strParts[0]);
			String name = strParts[1];
			String desc = strParts[2];
			int HP = Integer.parseInt(strParts[3]);
			int min = Integer.parseInt(strParts[4]);
			int max = Integer.parseInt(strParts[5]);
			
			String locations = strParts[6];
			String[] loc = locations.split(":");
			ArrayList<Integer> location = new ArrayList<Integer>();
			for(int i = 0; i<loc.length; i++) {
				location.add(Integer.parseInt(loc[i]));
			}
			
			Monster mon = new Monster(id, name, desc, HP,HP, min, max, location);
			hmMonsters.put(mon.getID(),mon);
			}
		}
	
	//Method to read the helpCommands file
	void readHelpCommands(File helpCom) {
		try {
			sc = new Scanner(helpCom);
		} catch (FileNotFoundException e) {
			System.out.print("helpcommands file does not exist.");
		}
		helpCommands = new ArrayList<String>();
		while(sc.hasNext()) {
			helpCommands.add(sc.nextLine());
		}
	}
	
	//Method to read the game description
	void readDescription(File description) {
		try {
			sc = new Scanner(description);
		} catch(FileNotFoundException e) {
			System.out.print("description file does not exist.");
		}
		while(sc.hasNextLine()) {
			this.description = sc.nextLine();
		}
	}
	
	//Method to read info for saved game
	void readInfo(String name, String info) {
		String[] strParts = info.split("/");
		//Read all relevant info to change for saved game
		readPuzzleInfo(strParts[1]);
		readMonsterInfo(strParts[2]);
		readItemInfo(strParts[3]);
		
		player = new Player(1, name, 100, 1);
		String[] playerParts = strParts[0].split(";");
		int HP = Integer.parseInt(playerParts[0]);
		player.setHP(HP);
		int location = Integer.parseInt(playerParts[1]);
		player.setLocation(location);
		ArrayList<Equipable> eq = new ArrayList<Equipable>();
		String[] equippedItems = playerParts[2].split("&");
		for(int i = 0; i<equippedItems.length; i++) {
			int itemId = Integer.parseInt(equippedItems[i]);
			eq.add((Equipable) hmItems.get(itemId));
		}
		player.setEquipped(eq);
	}
	
	//Method to read puzzle info for saved game
	void readPuzzleInfo(String info) {
		String[] puzzles = info.split(";");
		for(int i = 0; i<puzzles.length; i++) {
			String[] puzzleParts = puzzles[i].split("&");
			int ID = Integer.parseInt(puzzleParts[0]);
			Puzzle puzzle = hmPuzzles.get(ID);
			String s = puzzleParts[1];
			
			puzzle.setSolved(s);
			int location = Integer.parseInt(puzzleParts[2]);
			puzzle.setLocationPlaced(location);
		}
	}
	
	//Method to read monster info for saved game
	void readMonsterInfo(String info) {
		String[] monsters = info.split(";");
		for(int i = 0; i<monsters.length; i++) {
			String[] monsterParts = monsters[i].split("&");
			int ID = Integer.parseInt(monsterParts[0]);
			Monster monster = hmMonsters.get(ID);
			int HP = Integer.parseInt(monsterParts[1]);
			monster.setHP(HP);
			ArrayList<Integer> locations = new ArrayList<Integer>();
			int location = Integer.parseInt(monsterParts[2]);
			locations.add(location);
			monster.setLocations(locations);
		}
	}
	
	//Method to read items info for saved game
	void readItemInfo(String info) {
		String[] items = info.split(";");
		for(int i = 0; i<items.length; i++) {
			String[] itemParts = items[i].split("&");
			int ID = Integer.parseInt(itemParts[0]);
			Item item = hmItems.get(ID);
			ArrayList<Integer> locations = new ArrayList<Integer>();
			int location = Integer.parseInt(itemParts[1]);
			locations.add(location);
			item.setLocation(locations);
		}
	}
	
	
	
	//Method to set the starting ID for the items
	void setStartingIDItems() {	
		for(int i =1; i<=hmItems.size(); i++) {
			ArrayList<Integer> locations = hmItems.get(i).getLocation();
			if(locations.size() == 1) {
				if(locations.get(0)==100) {
					int rand = (int)(Math.random()*(31-1))+1;
					hmItems.get(i).addItem(hmRooms.get(rand));
				}
				else if(locations.get(0)==0) {
					continue;
				}
				else if(locations.get(0) == -1) {
					ArrayList<Item> pInventory = player.getInventory();
					pInventory.add(hmItems.get(i));
					player.setInventory(pInventory);
				}
				else {
					hmItems.get(i).addItem(hmRooms.get(locations.get(0)));
				}
			}
			else {
				int rand = (int)(Math.random()*(locations.size()-1)+1);
				hmItems.get(i).addItem(hmRooms.get(locations.get(rand)));
			}	
		}
	}
	
	//Method to set the puzzles in their respective locations
	void setPuzzles() {
		for(int i =1; i<=hmPuzzles.size(); i++) {
			int location = hmPuzzles.get(i).getLocationPlaced();
			if(location == 0)
				continue;
			else
				hmRooms.get(location).setPuzzle(hmPuzzles.get(i));
		}
	}
	
	
	//Method to set the starting ID for the monsters
	void setStartingLocMonsters() {	
		for(int i =1; i<=hmMonsters.size(); i++) {
			ArrayList<Integer> locations = hmMonsters.get(i).getLocation();
			if(locations.size() == 1) {
				if(locations.get(0)==0)
					continue;
				else {
					hmRooms.get(locations.get(0)).setMonster(hmMonsters.get(i));
					hmMonsters.get(i).setLocationPlaced(locations.get(0));
				}
			}
			else {
				int rand = (int)(Math.random()*(locations.size()-1) +1);
				hmRooms.get(rand).setMonster(hmMonsters.get(i));
				hmMonsters.get(i).setLocationPlaced(locations.get(rand));
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
		if (newRoomIndex == -1) {
			return "Sorry, that door is lock. You can not move that direction right now.";
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
		if (newRoomIndex == -1) {
			return "Sorry, that door is lock. You can not move that direction right now.";
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
		if (newRoomIndex == -1) {
			return "Sorry, that door is lock. You can not move that direction right now.";
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
		if (newRoomIndex == -1) {
			return "Sorry, that door is lock. You can not move that direction right now.";
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
		if (newRoomIndex == -1) {
			return "Sorry, that door is lock. You can not move that direction right now.";
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
		if (newRoomIndex == -1) {
			return "Sorry, that door is lock. You can not move that direction right now.";
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
		String response = "Sorry, that is not a valid command. Please try again.";
		if(text.equalsIgnoreCase("Help")) {
			response = getHelp();
		}
		
		else if(text.equalsIgnoreCase("Explore")) {
			response = searchRoom(player.getLocation());
		}
		else if(text.equalsIgnoreCase("Room Description")) {
			response = hmRooms.get(player.getLocation()).getDescription();
		}
		else if(text.equalsIgnoreCase("Status")) {
			response = "Your current HP is: " + player.getHP();
		}
		else if(text.contains("Pick up") || text.contains("pick up")) {
			response = pickUpItem(text);
		}
		else if(text.contains("Drop") || text.contains("drop")) {
			response = dropItem(text);
		}
		else if(text.contains("Examine") || text.contains("examine")) {
			response = examineItem(text);
		}
		else if (text.contains("Consume") || text.contains("consume")) {
			response = consumeItem(text);
		}
		else if(text.contains("Unequip") || text.contains("unequip")) {
			response = unequipItem(text);
		}
		else if(text.contains("Equip") || text.contains("equip")) {
			response = equipItem(text);
		}
	
		return response;
	}
	
		//Method to get help commands
		String getHelp(){
			String output = "";
			for(int i = 0; i<helpCommands.size(); i++) {
				output = output + "\n"+helpCommands.get(i);
			}
			return output;
		}
		
		//Method to get the monster in the current room of the player
		Monster getMonster() {
			return hmRooms.get(player.getLocation()).getMonster();
		}
		
		//Method to get the puzzle in the current room of the player
		Puzzle getPuzzle() {
			return hmRooms.get(player.getLocation()).getPuzzle();
		}
	
		//Method to search room 
		String searchRoom(int roomIndex){
			Room room = hmRooms.get(roomIndex);
			String ret = "";
			if(!room.getInventory().isEmpty()) {
				ret = ", " + room.getInventory();
			}
			if(room.getMonster()!= null) {
				ret = ret + ", " + room.getMonster().getName();
			}
			if(room.getPuzzle()!= null) {
				ret = ret + ", puzzle";
			}
			if(ret.equals(""))
				return "\nThe " + room.getName() + " has no items in it.";
			else
				return "\nIn the " + room.getName() + " you find the following" + ret;
		}	
		
		//Method to examine item
		String examineItem(String text) {
			int roomIndex = player.getLocation();
			Room room = hmRooms.get(roomIndex);
			ArrayList<Item> items = room.getInventory();
			
			
			int j = 0;
			boolean yes = false;
			for(int k = 0; k<items.size(); k++) {
				if(text.toLowerCase().contains(items.get(k).getName().toLowerCase())) {
					yes = true;
					j = k;
				}
			}
			if(yes == true) {
				return items.get(j).getDesc();
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
				if(text.toLowerCase().contains(items.get(i).getName().toLowerCase())) {
					yes = true;
					j = i;
				}
			}
			if(yes == true) {
				Item item = items.get(j);
				if(item.getId()==9) {
					return "Congratulations, you have won the game!";
				}
				item.addItem(player, room);
				item.setLocationPlaced(-1);
				return item.getName() + " has been picked up from the room and successfully added to the player inventory.";
			}
			return "Sorry we did not find that item in the " + room.getName() +". Make sure to check your spelling.";
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
				if(text.toLowerCase().contains(items.get(i).getName().toLowerCase())) {
					yes = true;
					j = i;
				}
			}
			if(yes == true) {
				Item item = items.get(j);
				item.dropItem(player);
				return item.getName() + " has been dropped successfully from the player inventory and placed in the " + room.getName();
				
			}
			else {
				return "Sorry we did not find that item in the player inventory. Make sure to check your spelling.";
			}
		}
		
		//Method to equip item
		String equipItem(String text) {
			ArrayList<Item> items = player.getInventory();
			if(items.isEmpty()) {
				return "Sorry, the player inventory is empty. You have no items to equip.";
			}
			boolean yes = false;
			int j =0;
			for(int i = 0; i<items.size(); i++) {
				if(text.toLowerCase().contains(items.get(i).getName().toLowerCase())) {
					yes = true;
					j = i;
				}
			}
			
			if(!(items.get(j) instanceof Equipable)) {
				return "Sorry but that item is not equippable. Maybe try to consume it.";
			}
			else if(yes == true) {
				Equipable item = (Equipable)items.get(j);
				item.equip(player);
				return item.getName() + " has been successfully equipped";
			}
			return "Sorry but that item could not be successfully equipped";
		}
		
		//Method to unequip item
		String unequipItem(String text) {
			ArrayList<Equipable> items= player.getEquipped();
			if(items.isEmpty()) {
				return "Sorry,you currently have no items equipped.";
			}
			int j =0;
			boolean yes = false;
			for(int i = 0; i<items.size(); i++) {
				if(text.toLowerCase().contains(items.get(i).getName().toLowerCase())) {
					yes = true;
					j = i;
				}
			}
			if(yes == true) {
				Equipable item = items.get(j);
				item.unequip(player);
				return item.getName() + " has been successfully unequipped";
			}
			return "Sorry, that item could not be unequipped successfully.";
		}
		
		//Method to examine item
		String consumeItem(String text) {
			int roomIndex = player.getLocation();
			Room room = hmRooms.get(roomIndex);
			ArrayList<Item> items = player.getInventory();
			int j = 0;
			boolean yes = false;
			for(int k = 0; k<items.size(); k++) {
				Item item = items.get(k);
				if(text.substring(8).equalsIgnoreCase(item.getName())) {
					yes = true;
					j = k;
				}
				if(room.getInventory().contains(item)) {
					yes = true;
				}
			}
			if(yes == true) {
				Consumable item = (Consumable) items.get(j);
				item.consume(player);
				return item.getName() + " has been successfully consumed and your health has been rejuvinated. Type \"Status\" to check your HP.";
			}
			else {
				return "Sorry we did not find that item in the " + room.getName() +". Make sure to check your spelling.";
				}
			}
		
		//Method to handle when in combat with a monster
		String attack(String command) {
			String response = "Sorry, that is not a valid command. Please try again.";
			if(hmRooms.get(player.getLocation()).getMonster() == null) {
				response = "Sorry but there is no monster in this room.";
			}
			else if(command.equalsIgnoreCase("Attack")) {
				response = player.playerAttack(hmRooms.get(player.getLocation()).getMonster());
				if(hmRooms.get(player.getLocation()).getMonster().getHP() == 0) {
					response = response + "\nYou killed the monster! Congratulations.";
					hmRooms.get(player.getLocation()).setMonster(null);
				}
				//Monster retaliation attack
				response = response + hmRooms.get(player.getLocation()).getMonster().monsterAttack(player);
				
				if(player.getHP() == 0)
					response = response + "\nYour health is at 0. You have been defeated by the monster.";
				
			}
			else if(command.equalsIgnoreCase("Stats")) {
				response = hmRooms.get(player.getLocation()).getMonster().getName() + " : " + hmRooms.get(player.getLocation()).getMonster().getDescription() + " Health = " + hmRooms.get(player.getLocation()).getMonster().getHP();
			}
			else if(command.equalsIgnoreCase("Status")) {
				response = "Your current HP is: " + player.getHP();
			}
			else if (command.contains("Consume") || command.contains("consume")) {
				response = consumeItem(command);
			}
			else if(command.contains("Equip") || command.contains("equip")) {
				response = equipItem(command);
			}
			else if(command.contains("Unequip") || command.contains("unequip")) {
				response = equipItem(command);
			}
			else if(command.equalsIgnoreCase("Abandon Fight")) {
				double rand = Math.random();
				if(rand>0.5) {
					int health = hmRooms.get(player.getLocation()).getMonster().getOriginalHealth();
					hmRooms.get(player.getLocation()).getMonster().setHP(health);
					response = "You have abandoned the fight";
				}
				else {
					response = "You couldn't get away from the monster.";
					response = response + hmRooms.get(player.getLocation()).getMonster().monsterAttack(player);
				}
			}
			
			return response;
		}
		
		
		//Method to handle when you encounter a puzzle
		String playPuzzle(String command) {
			String response = "Sorry, that is not a valid command. Please try again."; 
			if(command.equalsIgnoreCase("Explore Puzzle"))
				response = getPuzzle().getDescription();
			else if (command.equalsIgnoreCase("Hint"))
				response = getPuzzle().getHint();
			else
				response = answerPuzzle(command);
				
			return response;
		}
		
		//Method to check the answer of a puzzle
		String answerPuzzle(String answer) {
			String response = "Sorry, that answer is incorrect.";
			if(getPuzzle().getID() ==1) {
				if(answer.toLowerCase().contains(getPuzzle().getSolution().toLowerCase())) {
					response = "Congratulations, You solved the puzzle.";
					unlockRooms();
				}
				
			}
			if(getPuzzle().getSolution().equalsIgnoreCase(answer)) {
				response = "Congratulations, You solved the puzzle.";
				unlockRooms();
			}
			return response;
		}
		
		//Method to unlock locked rooms once a puzzle is solved
		void unlockRooms() {
			Puzzle puzzle = getPuzzle();
			hmRooms.get(player.getLocation()).setPuzzle(null);
			Room room = hmRooms.get(puzzle.getLocationPlaced());
			if(room.getNorth() == -1)
				room.setNorth(puzzle.getLocationOpen());
			if(room.getEast() == -1)
				room.setEast(puzzle.getLocationOpen());
			if(room.getSouth() == -1)
				room.setSouth(puzzle.getLocationOpen());
			if(room.getWest() == -1)
				room.setWest(puzzle.getLocationOpen());
			if(room.getUp() == -1)
				room.setUp(puzzle.getLocationOpen());
			if(room.getDown() == -1)
				room.setDown(puzzle.getLocationOpen());
		}
		
		
		//Method to save the game
		String saveGame() {
			String info = "";
			
			//Add player info
			info = info + player.getName() +":" + player.getHP() + ";" + player.getLocation() + ";";
			ArrayList<Equipable> eq = player.getEquipped();
			if(!eq.isEmpty()) {
				for(int i = 1; i<eq.size(); i++) {
					info = info + eq.get(i).getId() + "&";
				}
				info = info + eq.get(0).getId() + "/";
			}
			
			//Add puzzle info
			for(int i = 1; i<hmPuzzles.size(); i++) {
				info = info + hmPuzzles.get(i).getID() + "&" + hmPuzzles.get(i).getSolve() + "&" + hmPuzzles.get(i).getLocationPlaced() + ";";
			}
			info = info + hmPuzzles.get(hmPuzzles.size()).getID() + "&" + hmPuzzles.get(hmPuzzles.size()).getSolve() + "&" + hmPuzzles.get(hmPuzzles.size()).getLocationPlaced();
			info = info+ "/";
			
			//Add monster info
			for(int i = 1; i<hmMonsters.size(); i++) {
				info = info + hmMonsters.get(i).getID() + "&" + hmMonsters.get(i).getHP() + "&" + hmMonsters.get(i).getLocationPlaced() + ";";
			}
			info = info + hmMonsters.get(hmMonsters.size()).getID() + "&" + hmMonsters.get(hmMonsters.size()).getHP() + "&" + hmMonsters.get(hmMonsters.size()).getLocationPlaced();
			info = info+ "/";
			
			//Add item info
			for(int i = 1; i<hmItems.size(); i++) {
				int location = hmItems.get(i).getLocationPlaced();
				info = info + hmItems.get(i).getId() + "&" + location + ";";
			}
			info = info + hmItems.get(hmItems.size()).getId() + "&" + hmItems.get(hmItems.size()).getLocationPlaced();
			
			return info;
		}

}
