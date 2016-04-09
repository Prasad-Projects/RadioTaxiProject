package radiotaxi.roles.admin;

import java.util.List;

import com.datastax.driver.core.Row;

import radiotaxi.db.AccessDB;

public class AdminPortal {
	
	public AdminPortal() {

	}
	
	public List<Row> getUnregisteredDrivers() throws Exception {
		AccessDB db = new AccessDB();
		List<Row> results = db.getUnregisteredDrivers();
		return results;
	}

}
