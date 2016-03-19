import java.time.LocalDateTime;
import java.time.ZoneId;

public class Booking {
	
	public String bookTrip(String rider, String origin, String dest) {
		
		// get current time (format yyyy-mm-dd HH:mm:ss) 
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        String time_str = time.toString().replace('T', ' ').split("\\.")[0];

		Register_db db = new Register_db();
		return db.registerBooking(rider, time_str, origin, dest);
		
	}
}