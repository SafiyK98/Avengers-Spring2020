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

	public void pick()
	{
		
	}
	
	//Add to inventory for player and remove from room inventory
	void addItem(Player p, Room r) {
		p.getInventory().add(this);
		r.getInventory().remove(this);
	}
		
	//Add items to rooms - used to place items upon starting the game
	void addItem(Room r) {
		r.getInventory().add(this);
	}
	//Drop item from the player inventory and place into current room
	void dropItem(Player p,Room r) {
		r.getInventory().add(this);
		p.getInventory().remove(this);
	} 
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
