package in.ac.bits_pilani.radiotaxi.auth;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.User;

public class Login {
	
	public User login(String username, String password, String type) throws Exception {
		return AccessDB.login(username, password, type);	
	}

}
