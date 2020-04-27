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
	String playerAttack(Monster monster) {
		String response = "";
		ArrayList<Equipable> equippedItems = getEquipped();
		ArrayList<Item> inventoryItems = getInventory();
		boolean spellScroll = false;
		int j = 0;
		for(int i = 0; i<inventoryItems.size(); i++) {
			if(inventoryItems.get(i).getId() == 5) {
				spellScroll = true;
				j=i;
			}
		}
		
		int attackDamage = (int)(Math.random()*(50-1)) + 1;
		
		response = "Player HP: " + this.HP + "        Monster HP: " + monster.getHP();
		
		if(spellScroll == true) {
			if(monster.getID() == 4 || monster.getID() == 7) {
				response = response + "\nYou have the spell scroll in your inventory which automatically kills the zombie. The spell scroll have now been removed from your inventory.";
				inventoryItems.remove(j);
				setInventory(inventoryItems);
				monster.setHP(0);
			}
		}
		//Player has no items equipped
		else if (equippedItems.isEmpty()) {
			response = response + "\nYou attack the monster and deal damage of " +  attackDamage;
			int newHealth = monster.getHP()-attackDamage;
			if(newHealth < 0)
				newHealth = 0;
			monster.setHP(newHealth);
		}
		
		//Player has item(s) equipped
		else {
			int addedAttack = 0;
			for(int i = 0; i<equippedItems.size(); i++) {
				addedAttack = addedAttack + equippedItems.get(i).getAttack();
			}
			response = response + "\nYou attack the monster and deal damage of " +  (attackDamage + addedAttack);
			int newHealth = monster.getHP()-(attackDamage+addedAttack);
			if(newHealth < 0)
				newHealth = 0;
			monster.setHP(newHealth);
		}
		
		return response;
	}
	
}
