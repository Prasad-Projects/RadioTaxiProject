import java.util.List;

import com.datastax.driver.core.Row;

public class AdminPortal {
	
	public AdminPortal() {
		
	}
	
	public List<Row> getUnregisteredDrivers() {
		AccessDB db = new AccessDB();
		List<Row> results = db.getUnregisteredDrivers();
		return results;
	}

}
