import java.util.ArrayList;

public class Monster extends Entity{

	private int minAttack;
	private int maxAttack;
	private int originalHealth;
	private ArrayList<Integer> locations;
	private int locationPlaced = 0;
	
	Monster(int ID, String name, String description, int HP, int originalHealth, int minAttack, int maxAttack, ArrayList<Integer> locations)
	{
		super(ID, name, description, HP);
		this.originalHealth = originalHealth;
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

	public int getOriginalHealth() {
		return originalHealth;
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
	String monsterAttack(Player p) 
	{
		int monsterAttack = (int)(Math.random() * (this.maxAttack - this.minAttack)) + this.minAttack;
		int newHealth = p.getHP() - monsterAttack;
		if(newHealth < 0) {
			newHealth = 0; 
		}
		p.setHP(newHealth);
		return "\nThe monster attacks back decreases your health by "+ monsterAttack;
	}
	
	
}
