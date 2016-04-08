package radiotaxi.roles;

public abstract class User {
		
	private String username;
	private String firstname;
	private String lastname;
	private String mobile_no;
	
	public User(String username,String  firstname, String lastname, String mobile_no){
		this.username=username; 
		this.firstname=firstname;
		this.lastname=lastname;
		this.mobile_no=mobile_no;
	}
	
	public String getUsername(){
		return this.username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMobile_no() {
		return mobile_no;
	}
	
}
