
public class RegisterRider {
	
	public void doRegister(String username, String firstName, String lastName, String mobile, String password) {
		Register_db db = new Register_db();
		db.useKeyspace();
		db.registerRider(username, firstName, lastName, mobile, password);
	}

}
