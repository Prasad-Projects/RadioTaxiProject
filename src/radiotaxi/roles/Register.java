package radiotaxi.roles;

import radiotaxi.db.AccessDB;
import radiotaxi.roles.driver.Driver;
import radiotaxi.roles.rider.Rider;

public class Register {

	public void registerRider(Rider rider, String password) {
		AccessDB.initialise();
		AccessDB.useKeyspace();
		AccessDB.registerRider(rider, password);
	}

	public void registerDriver(Driver driver, String password) {
		AccessDB.initialise();
		AccessDB.useKeyspace();
		AccessDB.registerDriver(driver ,password);
	}

}