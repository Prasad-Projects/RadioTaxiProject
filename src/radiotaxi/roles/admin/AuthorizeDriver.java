package radiotaxi.roles.admin;

import radiotaxi.db.AccessDB;

public class AuthorizeDriver {
	
	public boolean authorise(String username) throws Exception {
		
		AccessDB db = new AccessDB();
		return db.authorizeDriver(username);
		
	}

}
