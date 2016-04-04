public class Register {

	public void registerRider(Rider rider, String password) {
		AccessDB.use_keyspace();
		AccessDB.registerRider(rider, password);
	}

	public void registerDriver(Driver driver, String password) {
		AccessDB.use_keyspace();
		AccessDB.registerDriver(driver ,password);
	}

}