
public class RegisterDriver {
	
	public void doRegister(String username, String firstName, String lastName, String mobile, String password, String licence, String carNo) {
		Register_db db = new Register_db();
		db.use_keyspace();
		db.registerDriver(username, firstName, lastName, mobile, password, carNo, licence);
	}

}
