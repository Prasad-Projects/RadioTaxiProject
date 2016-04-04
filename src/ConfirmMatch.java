
public class ConfirmMatch {
	
	public boolean confirmMatch(int bookingId, String driver) {
		return AccessDB.confirmMatch(bookingId, driver);
	}

}
