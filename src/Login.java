
public class Login {
	
	public boolean doLogin(String username, String password, String type) {
		System.out.println("connecting to database...");
		System.out.println("connected to database!");
		return AccessDB.login(username, password, type);	
	}

}