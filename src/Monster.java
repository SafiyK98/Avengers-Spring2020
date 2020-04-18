
public class Monster extends Entity{

	private int location;
	
	Monster(int ID, String name, String description, int HP, int location){
		super(ID, name, description, HP);
		this.location = location; 
	}
	
	
	//Helper Methods
	
	//Get location
	int getLocation() {
		return location;
	}
	
	//Set location
	void setLocation(int location) {
		this.location = location;
	}
	
	//Monster attack player
	void monsterAttack(Player p) {
		
	}
	
	//Kill monster
	void killMonster() {
		HP = 0;
		location = 0;
	}
	
	
}
