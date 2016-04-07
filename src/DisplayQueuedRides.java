import java.util.List;

import com.datastax.driver.core.Row;

public class DisplayQueuedRides {
	
	public DisplayQueuedRides() {
		
	}
	
	public List<Row> getRides() {
		List<Row> results = AccessDB.getUnmatchedRides();
		return results;
	}
}
