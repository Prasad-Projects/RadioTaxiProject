
public class ConfirmMatch {
	
	public boolean confirmMatch(int bookingId, String driver) {
		Register_db db = new Register_db();
		return db.confirmMatch(bookingId, driver);
	}

}
