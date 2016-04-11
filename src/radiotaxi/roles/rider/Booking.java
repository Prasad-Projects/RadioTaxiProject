package radiotaxi.roles.rider;

import java.time.LocalDateTime;
import java.time.ZoneId;

import radiotaxi.db.AccessDB;

public class Booking {

	public String bookTrip(String rider, String origin, String dest, int fare) throws Exception {
		
		// get current time (format yyyy-mm-dd HH:mm:ss) 
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        String time_str = time.toString().replace('T', ' ').split("\\.")[0];

     	return AccessDB.bookARide(rider, time_str, origin, dest,fare);
			
	}
}