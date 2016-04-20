package in.ac.bits_pilani.radiotaxi.fare;

import in.ac.bits_pilani.radiotaxi.CabType;
import in.ac.bits_pilani.radiotaxi.db.AccessDB;


public class PaymentManager {

	private static int commision=0;
	private static int riderFares=0;
	
	public static void 	moneyTransfer(String rider, CabType cab, String distance, String duration, String origin, String dest,String time,float[] originCoord, float[] destCoord) throws Exception{
		
		int commisionForPlatform = FareCalculator.commisionForPlatform(rider,cab,distance,duration);
		int riderFare=FareCalculator.riderFare(rider,cab,distance,duration);
		int driverProfit=FareCalculator.driverProfit(rider,cab,distance,duration);
		commision+=commisionForPlatform;
		riderFares+=riderFare;
		AccessDB.bookARide(rider, time, origin, dest,driverProfit, originCoord, destCoord);
	}
	
	public boolean balanceCheck(){
	
		int bookedCabBal=AccessDB.bookedCabBalance();
		if( bookedCabBal+commision==riderFares)
			return true;
		return false;
	}
	
	public static int getCommision() {
		return commision;
	}

	public static int getRiderFares() {
		return riderFares;
	}
}