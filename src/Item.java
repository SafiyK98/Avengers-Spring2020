import java.util.ArrayList;

public class Item 
{
	private int id;
	private String name;
	private String desc;
	private ArrayList<Integer> locations = new ArrayList<Integer>();
	private int locationPlaced;
	


	public Item(int id, String name, String desc, ArrayList<Integer> location) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.locations = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ArrayList<Integer> getLocation() {
		return locations;
	}

	public void setLocation(ArrayList<Integer> location) {
		this.locations = location;
	}
	
	int getLocationPlaced() {
		return locationPlaced;
	}
	
	void setLocationPlaced(int i) {
		locationPlaced = i;
	}

	//Add item to room inventory used at the start of the game
	void addItem(Room r) {
		ArrayList<Item> rm = r.getInventory();
		rm.add(this);
		r.setInventory(rm);
		setLocationPlaced(r.getID());
	}
	
	//Add to inventory for player and remove from room inventory
	void addItem(Player p, Room r) {
		ArrayList<Item> in = p.getInventory();
		ArrayList<Item> rm = r.getInventory();
		in.add(this);
		rm.remove(this);
		p.setInventory(in);
		r.setInventory(rm);
		setLocationPlaced(-1);
	}
	
	//Drop item from the player inventory and place into current room
	void dropItem(Player p) {
		ArrayList<Item> in = p.getInventory();
		in.remove(this);
		p.setInventory(in);
		setLocationPlaced(0);
	} 
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
