package in.ac.bits_pilani.radiotaxi.auth;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class Login {
	
	public boolean login(String username, String password, String type) throws Exception {
		return AccessDB.login(username, password, type);	
	}

}
