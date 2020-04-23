import java.util.ArrayList;

public class Player extends Entity{

	//private variables for player class
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<Equipable> equipped = new ArrayList<Equipable>();
	private int currentLocation;
	
	Player(int ID, String name, int HP, int location){
		super(ID, name, "You are the Kings eldest son, a Prince.", 100);
		this.currentLocation = location; 
	}
	
	
	//Helper Methods
	
	//Get Inventory
	ArrayList<Item> getInventory(){
		return inventory;
	}
	
	//Set inventory
	void setInventory(ArrayList<Item> in) {
		inventory = in;
	}
	
	//Get location
	int getLocation() {
		return currentLocation;
	}
	
	//Set location
	void setLocation(int location) {
		currentLocation = location;
	}
	
	//Get Equipped Items
	ArrayList<Equipable> getEquipped(){
		return equipped;
	}
	
	//Set equipped
	void setEquipped(ArrayList<Equipable> eq) {
		equipped = eq;
	}
	
	//Player attack monster
	void playerAttack(Monster monster) {
		
		ArrayList<Equipable> equippedItems = this.equipped;
		int attackDamage = (int)(Math.random()*(50-1)) + 1;
		
		System.out.println("Player HP: " + this.HP + "        Monster HP: " + monster.getHP());
		
		//Player has no items equipped
		if (equippedItems.isEmpty()) {
			System.out.println("You attack the monster and deal damage of " +  attackDamage);
			monster.setHP(monster.getHP()-attackDamage);
		}
		
		//Player has item(s) equipped
		else {
			int addedAttack = 0;
			for(int i = 0; i<equippedItems.size(); i++) {
				addedAttack = addedAttack + equippedItems.get(i).getAttack();
			}
			int addedDamage =  (int) (attackDamage*(addedAttack*0.01));
			System.out.println("You attack the monster and deal damage of " +  (attackDamage + addedAttack));
			monster.setHP(monster.getHP()-(attackDamage+addedAttack));
		}
		
		//Monster retaliation attack
		monster.monsterAttack(this);
		
	}
	
}
