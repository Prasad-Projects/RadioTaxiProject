package radiotaxi.entry;

import radiotaxi.db.AccessDB;

public class Login {
	
	public boolean login(String username, String password, String type) throws Exception {
		return AccessDB.login(username, password, type);	
	}

}