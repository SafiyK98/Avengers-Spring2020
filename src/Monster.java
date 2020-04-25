import java.util.ArrayList;

public class Monster extends Entity{

	private int minAttack;
	private int maxAttack;
	private ArrayList<Integer> locations;
	private int locationPlaced = 0;
	
	Monster(int ID, String name, String description, int HP, int minAttack, int maxAttack, ArrayList<Integer> locations)
	{
		super(ID, name, description, HP);
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.locations = locations;
	}
	
	
	//Helper Methods
	
	public int getMinAttack() {
		return minAttack;
	}


	public void setMinAttack(int minAttack) {
		this.minAttack = minAttack;
	}


	public int getMaxAttack() {
		return maxAttack;
	}


	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}


	//Get location
	ArrayList<Integer> getLocation() {
		return locations;
	}
	
	public void setLocations(ArrayList<Integer> loc) {
		locations = loc;
	}
	
	int getLocationPlaced() {
		return locationPlaced;
	}
	
	void setLocationPlaced(int i) {
		locationPlaced = i;
	}
	
	//Monster attack player
	void monsterAttack(Player p) 
	{
		int monsterAttack = (int)(Math.random() * (this.maxAttack - this.minAttack)) + this.minAttack;
		p.setHP(p.getHP() - monsterAttack);
	}
	
	//Kill monster
	void killMonster() {
		HP = 0;
	}
	
	
}
