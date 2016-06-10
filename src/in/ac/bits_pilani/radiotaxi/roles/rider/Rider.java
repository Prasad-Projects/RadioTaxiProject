package in.ac.bits_pilani.radiotaxi.roles.rider;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.User;

public class Rider extends User {

	public Rider(String username, String firstname, String lastname, String mobileNo, int balance) {
		super(username, firstname, lastname, mobileNo, balance);
	}

	public void register(String password) throws Exception {
		AccessDB.registerRider(this, password);
	}

}