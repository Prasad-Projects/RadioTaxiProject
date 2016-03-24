public class Register {

	public void doRegister(String username, String firstName, String lastName, String mobile, String password) {
		Register_db db = new Register_db();
		Register_db.use_keyspace();
		db.registerRider(username, firstName, lastName, mobile, password);
	}

	public void doRegister(String username, String firstName, String lastName, String mobile, String password, String licence, String carNo) {
		Register_db db = new Register_db();
		Register_db.use_keyspace();
		db.registerDriver(username, firstName, lastName, mobile, password, carNo, licence);
	}

	
}
