package in.ac.bits_pilani.radiotaxi.roles.driver;

import java.util.List;

import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class DisplayQueuedRides {
	
	public DisplayQueuedRides() {
		
	}
	
	public List<Row> getRides() throws Exception {
		List<Row> results = AccessDB.getUnmatchedRides();
		return results;
	}
}
