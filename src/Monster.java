
public class Monster extends Entity{

	private int minAttack;
	private int maxAttack;
	private int location;
	
	Monster(int ID, String name, String description, int HP, int minAttack, int maxAttack, int location)
	{
		super(ID, name, description, HP);
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.location = location;
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
	int getLocation() {
		return location;
	}
	
	//Set location
	void setLocation(int location) {
		this.location = location;
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
		location = 0;
	}
	
	
}
