import java.util.ArrayList;

public class Equipable extends Item{

	int attackDamage;
	
	Equipable(int ID, String name, String desc,ArrayList<Integer> locations, int attack){
		super(ID, name, desc,locations);
		attackDamage =attack;
	}
	

	public int getAttack() {
		return attackDamage;
	}

	public void setAttack(int attack) {
		this.attackDamage = attack;
	}
	

	public void equipItem()
	{
		
	}
	
	public void unequipItem()
	{
		
	}
}
