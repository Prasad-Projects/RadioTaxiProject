package radiotaxi.roles.rider;

import java.time.LocalDateTime;
import java.time.ZoneId;

import radiotaxi.db.AccessDB;

public class Booking {
	
	public String bookTrip(String rider, String origin, String dest) throws Exception {
		
		// get current time (format yyyy-mm-dd HH:mm:ss) 
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        String strTime = time.toString().replace('T', ' ').split("\\.")[0];

     	return AccessDB.bookARide(rider, strTime, origin, dest);
		
	}
}