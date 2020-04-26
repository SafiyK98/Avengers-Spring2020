
public class Puzzle 
{
	public Puzzle() {
		super();
		// TODO Auto-generated constructor stub
	}


	private int ID;
	private String description;
	private String hint;
	private String solution;
	private int locationOpen;
	private int locationPlaced;
	private boolean solved;
	
	public Puzzle(int iD, String description, String hint, String solution, int locationOpen, int locationPlaced) {
		super();
		ID = iD;
		this.description = description;
		this.hint = hint;
		this.solution = solution;
		this.locationOpen = locationOpen;
		this.locationPlaced = locationPlaced;
		solved = false;
	}


	public int getLocationOpen() {
		return locationOpen;
	}


	public void setLocationOpen(int locationOpen) {
		this.locationOpen = locationOpen;
	}


	public int getLocationPlaced() {
		return locationPlaced;
	}


	public void setLocationPlaced(int locationPlaced) {
		this.locationPlaced = locationPlaced;
	}


	public void openRoom()
	{
		return;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getHint() {
		return hint;
	}


	public void setHint(String hint) {
		this.hint = hint;
	}


	public String getSolution() {
		return solution;
	}


	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Boolean getSolve() {
		return solved;
	}
	
	public void setSolved(Boolean b) {
		solved = b;
	}

	@Override
	public String toString() {
		return "Puzzle [ID=" + ID + ", description=" + description + ", hint=" + hint + ", solution=" + solution
				+ ", locationOpen=" + locationOpen + ", locationPlaced=" + locationPlaced + "]";
	}


	

	
	
	
}
