import java.util.ArrayList;

public class Consumable extends Item{

	int healthIncrease;
	
	Consumable(int ID, String name, String desc, ArrayList<Integer> locations, int health){
		super(ID, name, desc,locations);
		healthIncrease =health;
	}
	
	
	
	public int getHealth() {
		return healthIncrease;
	}

	public void setHealth(int health) {
		this.healthIncrease = health;
	}
	

	public void consume(Player p){
		int currentHealth = p.getHP();
		int newHealth = currentHealth + healthIncrease;
		if(newHealth>100) {
			newHealth = 100;
		}
		p.setHP(newHealth);
	}
}
