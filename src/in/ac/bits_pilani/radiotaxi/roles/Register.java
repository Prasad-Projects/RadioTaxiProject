package in.ac.bits_pilani.radiotaxi.roles;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.driver.Driver;
import in.ac.bits_pilani.radiotaxi.roles.rider.Rider;

/**
 * Backend class to send user registration queries to the database
 */

public class Register {

	public void registerRider(Rider rider, String password) throws Exception {
		AccessDB.registerRider(rider, password);
	}

	public void registerDriver(Driver driver, String password) throws Exception {
		AccessDB.registerDriver(driver ,password);
	}

}