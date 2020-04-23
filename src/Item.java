
public class Item 
{
	private int id;
	private String name;
	private String desc;
	private int health;
	private int attack;
	private int location;
	
	

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int id, String name, String desc, int health, int attack, int location) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.health = health;
		this.attack = attack;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public void pick()
	{
		
	}
	
	public void drop()
	{
		
	}
	
	public void equipItem()
	{
		
	}
	
	public void unequipItem()
	{
		
	}
	
	public void consume()
	{
		
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", desc=" + desc + ", health=" + health + ", attack=" + attack
				+ ", location=" + location + "]";
	}
	
	
}
