package in.ac.bits_pilani.radiotaxi.roles;

/**
 * Generic user class. 'Admin', 'Rider' and 'Driver' derive from this class
 */

public abstract class User {
	
	private String username;
	private String firstname;
	private String lastname;
	private String mobileNo;
	private int balance;
	
	public User(String username,String  firstname, String lastname, String mobileNo, int balance){
		this.username=username; 
		this.firstname=firstname;
		this.lastname=lastname;
		this.mobileNo=mobileNo;
		this.balance=balance;
	}
	
	public int getBalance() {
		return balance;
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

	public String getMobileNo() {
		return mobileNo;
	}
}