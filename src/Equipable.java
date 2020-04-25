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
	

	//Method for player to equip items 
	void equip(Player p) {
		ArrayList<Equipable> eq = p.getEquipped();
		eq.add(this);
		p.setEquipped(eq);
	}
	//Method for player to unequip items
	void unequip(Player p) {
		ArrayList<Equipable> eq = p.getEquipped();
		eq.remove(this);
		p.setEquipped(eq);
	}
}
