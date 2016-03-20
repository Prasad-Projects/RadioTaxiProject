import java.util.List;

import com.datastax.driver.core.Row;

public class AdminPortal {
	
	public AdminPortal() {
		
	}
	
	public List<Row> getUnregisteredDrivers() {
		Register_db db = new Register_db();
		List<Row> results = db.getUnregisteredDrivers();
		return results;
	}

}
