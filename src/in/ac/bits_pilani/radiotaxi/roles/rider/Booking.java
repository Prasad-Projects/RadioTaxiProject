package in.ac.bits_pilani.radiotaxi.roles.rider;

import java.time.LocalDateTime;
import java.time.ZoneId;

import in.ac.bits_pilani.radiotaxi.CabType;
import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.payment.PaymentManager;

public class Booking {

	public void bookTrip(String rider, String origin, String dest, CabType cab,String  distance,String  duration, float[] originCoord, float[] destCoord) throws Exception {
		
		// get current time (format yyyy-mm-dd HH:mm:ss) 
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        String timeStr = time.toString().replace('T', ' ').split("\\.")[0];
        PaymentManager.moneyTransfer(rider, cab, distance, duration,origin,dest,timeStr, originCoord, destCoord);
    }
}