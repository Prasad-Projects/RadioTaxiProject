package in.ac.bits_pilani.radiotaxi.roles.driver;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class ConfirmMatch {
	
	public boolean confirmMatch(int bookingId, String driver) throws Exception {
		return AccessDB.confirmMatch(bookingId, driver);
	}

}
