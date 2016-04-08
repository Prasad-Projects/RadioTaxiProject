package radiotaxi.roles.driver;

import java.util.List;

import com.datastax.driver.core.Row;

import radiotaxi.db.AccessDB;

public class DisplayQueuedRides {
	
	public DisplayQueuedRides() {
		
	}
	
	public List<Row> getRides() {
		List<Row> results = AccessDB.getUnmatchedRides();
		return results;
	}
}
