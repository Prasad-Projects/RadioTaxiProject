import java.util.List;

import com.datastax.driver.core.Row;

public class DisplayQueuedRides {
	
	public DisplayQueuedRides() {
		
	}
	
	public List<Row> getRides() {
		
		Register_db db = new Register_db();
		List<Row> results = db.getRides();
		
		return results;
		
	}

}
