package in.ac.bits_pilani.radiotaxi.roles.admin;

import java.util.List;

import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.User;

public class Admin extends User {

	public Admin(String username, String firstname, String lastname, String mobileNo, int balance) {
		super(username, firstname, lastname, mobileNo,balance);
	}

	public boolean approveDriver(String username) throws Exception {
		return AccessDB.approveDriver(username);		
	}

	public List<Row> getUnregisteredDrivers() throws Exception {
		List<Row> results = AccessDB.getUnregisteredDrivers();
		return results;
	}

}
