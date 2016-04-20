package in.ac.bits_pilani.radiotaxi.roles.driver;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class ConfirmMatch {
	
	public void confirmMatch(int bookingId, String driver) throws Exception {
		AccessDB.confirmMatch(bookingId, driver);
	}

}
