
public class AuthorizeDriver {
	
	public boolean authorise(String username) {
		
		AccessDB db = new AccessDB();
		return db.authorizeDriver(username);
		
	}

}
