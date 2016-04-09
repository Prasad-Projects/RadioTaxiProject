package radiotaxi.roles.driver;

import radiotaxi.db.AccessDB;

public class ConfirmMatch {
	
	public boolean confirmMatch(int bookingId, String driver) throws Exception {
		return AccessDB.confirmMatch(bookingId, driver);
	}

}
