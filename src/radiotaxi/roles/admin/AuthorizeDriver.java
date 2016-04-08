package radiotaxi.roles.admin;

import radiotaxi.db.AccessDB;

public class AuthorizeDriver {
	
	public boolean authorise(String username) {
		
		AccessDB db = new AccessDB();
		return db.authorizeDriver(username);
		
	}

}
