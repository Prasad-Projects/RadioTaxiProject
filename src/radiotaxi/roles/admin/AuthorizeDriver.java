package radiotaxi.roles.admin;

import java.util.List;

import com.datastax.driver.core.Row;

import radiotaxi.db.AccessDB;

public class AuthorizeDriver {

	public boolean authorise(String username) throws Exception {
		return AccessDB.authorizeDriver(username);		
	}

	public List<Row> getUnregisteredDrivers() throws Exception {
		List<Row> results = AccessDB.getUnregisteredDrivers();
		return results;
	}
}
