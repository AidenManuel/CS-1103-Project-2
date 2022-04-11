package application;

public class User {
	private String DisplayName, Subdivision, Region;
	private int UniversityID, Rank;
	private double Score;
	
	
	public User ( int Rank, String DisplayName, double Score) {
		this.DisplayName = DisplayName;
		//this.Password = Password;
		//this.UniversityID = UniversityID;
		//this.Subdivision = Subdivision;
		//this.Region = Region;
		this.Score = Score;
		this.Rank = Rank;
	}
	
	public String getName() {
		return DisplayName;
	}

	public String getSubdivision() {
		return Subdivision;
	}
	
	public String getRegion() {
		return Region;
	}
	
	public int getUniveristy() {
		return UniversityID;
	}
	
	public double getScore() {
		return Score;
	}
	
	public int getRank() {
		return Rank;
	}
	
	public String toString() {
		String out = "User : " + DisplayName + "\nSubdivision : " + Subdivision + "\nRegion : " + Region;
		return out;
	}
}
