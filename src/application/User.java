package application;

public class User {
	private String DisplayName, Password, Subdivision, Region;
	private int UniversityID, Score;
	
	
	public User (String DisplayName, String Password, int UniversityID, String Subdivision, String Region, int Score) {
		this.DisplayName = DisplayName;
		this.Password = Password;
		this.UniversityID = UniversityID;
		this.Subdivision = Subdivision;
		this.Region = Region;
		this.Score = Score;
	}
	
	public String getName() {
		return DisplayName;
	}

	public String getPassword() {
		return Password;
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
	
	public int getScore() {
		return Score;
	}
	
	public String toString() {
		String out = "User : " + DisplayName + "\nSubdivision : " + Subdivision + "\nRegion : " + Region;
		return out;
	}
}
