package radiotaxi.roles;

import radiotaxi.db.AccessDB;
import radiotaxi.roles.driver.Driver;
import radiotaxi.roles.rider.Rider;

public class Register {

	public void registerRider(Rider rider, String password) throws Exception {
		AccessDB.registerRider(rider, password);
	}

	public void registerDriver(Driver driver, String password) throws Exception {
		AccessDB.registerDriver(driver ,password);
	}

}