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
	

	public void consume()
	{
		
	}
}
