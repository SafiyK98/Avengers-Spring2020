
public class Entity {
	
	int ID;
	String description;
	String name;
	int HP;
	
	Entity(int ID, String name, String description, int HP){
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.HP = HP;
	}
	
	//Helper methods
	
	//Get ID
	int getID() {
		return ID;
	}
	
	//Get name
	String getName() {
		return name;
	}
	
	//Get description
	String getDescription() {
		return description;
	}
	
	//Get HP
	int getHP() {
		return HP;
	}
	
	//Set HP
	void setHP(int i) {
		HP = i;
	}
	
	//To String
	@Override
	public String toString() {
		return name + " : " + description + " Health = " + HP;
	}

}
