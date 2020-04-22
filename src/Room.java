import java.util.ArrayList;

public class Room {
	
	//variables for the room class
	private int ID;
	private String name;
	private String description;
	
	private int North;
	private int East;
	private int South;
	private int West;
	private int Up;
	private int Down;
	

	private boolean prevVisited;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private Puzzle puzzle = null;
	private Monster monster = null;

	Room(int ID, String name, String description, int North, int East, int South, int West, int Up, int Down){
		this.ID = ID; this.name = name; this.description = description;
		this.North = North; this.East = East; this.South = South; this.West = West; this.Up = Up; this.Down = Down;
		prevVisited = false;
	}
	
	//Get room ID
	int getID() {
		return ID;
	}
	
	//Get room name
	String getName() {
		return name;
	}
	
	//Get room description
	String getDescription() {
		return description;
	}
	
	//Get room North of current room
	int getNorth() {
		return North;
	}
	
	//Get room East of current room
	int getEast() {
		return East;
	}
	
	//Get room South of current room
	int getSouth() {
		return South;
	}
	
	//Get room West of current room
	int getWest() {
		return West;
	}
	
	//Get room North of current room
	int getUp() {
		return Up;
	}
	
	//Get room Down of current room
	int getDown() {
		return Down;
	}
	
	//Get Inventory
	ArrayList<Item> getInventory(){
		return inventory;
	}
		
	//Set inventory
	void setInventory(ArrayList<Item> in) {
		inventory = in;
	}
	
	//Get puzzle within current room
	Puzzle getPuzzle() {
		return puzzle;
	}
	
	//Set puzzle within current room
	void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	//Get monster within current room
	Monster getMonster() {
		return monster;
	}
	
	//Set monster within current room
	void setMonster(Monster monster) {
		this.monster = monster;
	}
	
	//See if the room has been visited already
	boolean getPrevVisited() {
		return prevVisited;
	}
		
	//Set previously visited true
	void setVisited(boolean b) {
		prevVisited = b;
	}
}
