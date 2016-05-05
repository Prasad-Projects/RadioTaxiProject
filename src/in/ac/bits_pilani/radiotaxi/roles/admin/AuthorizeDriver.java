package in.ac.bits_pilani.radiotaxi.roles.admin;

import java.util.List;

import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class AuthorizeDriver {

	public void authorise(String username) throws Exception {
		AccessDB.authorizeDriver(username);		
	}

	public List<Row> getUnregisteredDrivers() throws Exception {
		List<Row> results = AccessDB.getUnregisteredDrivers();
		return results;
	}
}
