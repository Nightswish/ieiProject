package ieiProject;

public class Login {
	private String ID;
	private String PA;
	
	public Login(String ID, String PA){
		this.ID = ID;
		this.PA = PA;
	}
	public Login(){}
	
	public String getID(){
		return ID;
	}
	public void setID(String ID){
		this.ID = ID;
	}
	public String getPA(){
		return PA;
	}
	public void setPA(String PA){
		this.PA = PA;
	}
}
