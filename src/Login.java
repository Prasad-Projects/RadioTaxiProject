
public class Login {
	
	public boolean login(String username, String password, String type) {
		AccessDB.initialise();
		return AccessDB.login(username, password, type);	
	}

}