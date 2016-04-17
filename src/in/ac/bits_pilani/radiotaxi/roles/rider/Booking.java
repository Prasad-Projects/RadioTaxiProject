package in.ac.bits_pilani.radiotaxi.roles.rider;

import java.time.LocalDateTime;
import java.time.ZoneId;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class Booking {

	public String bookTrip(String rider, String origin, String dest, int fare) throws Exception {
		
		// get current time (format yyyy-mm-dd HH:mm:ss) 
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        String timeStr = time.toString().replace('T', ' ').split("\\.")[0];

     	return AccessDB.bookARide(rider, timeStr, origin, dest,fare);
			
	}
}