
public class AuthorizeDriver {
	
	public boolean doAuthorize(String username) {
		
		Register_db db = new Register_db();
		return db.authorizeDriver(username);
		
	}

}
