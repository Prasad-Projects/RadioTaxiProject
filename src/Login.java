
public class Login {
	
	public boolean doLogin(String username, String password, String type) {
		
		System.out.println("connecting to database...");
		Register_db r = new Register_db();
		System.out.println("connected to database!");
		//r.use_keyspace();
		//System.out.println("database keyspace is use...");
		
		return r.login(username, password, type);
		
	}

}
