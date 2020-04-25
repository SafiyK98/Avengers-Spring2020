import java.util.ArrayList;

public class Item 
{
	private int id;
	private String name;
	private String desc;
	private ArrayList<Integer> locations = new ArrayList<Integer>();
	


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

	//Add item to room inventory used at the start of the game
	void addItem(Room r) {
		ArrayList<Item> rm = r.getInventory();
		rm.add(this);
		r.setInventory(rm);
	}
	
	//Add to inventory for player and remove from room inventory
	void addItem(Player p, Room r) {
		ArrayList<Item> in = p.getInventory();
		ArrayList<Item> rm = r.getInventory();
		in.add(this);
		rm.remove(this);
		p.setInventory(in);
		r.setInventory(rm);
	}
	
	//Drop item from the player inventory and place into current room
	void dropItem(Player p,Room r) {
		ArrayList<Item> in = p.getInventory();
		ArrayList<Item> rm = r.getInventory();
		in.remove(this);
		rm.add(this);
		p.setInventory(in);
		r.setInventory(rm);
	} 
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
