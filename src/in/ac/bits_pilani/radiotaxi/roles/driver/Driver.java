package in.ac.bits_pilani.radiotaxi.roles.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.roles.User;
import in.ac.bits_pilani.radiotaxi.db.AccessDB;

public class Driver extends User {

	private String licenseNo;
	private String carNo;
	
	public Driver(String username, String firstname, String lastname, String mobileNo,String licenseNo,String carNo, int balance) {
		super(username, firstname, lastname, mobileNo,balance);
		this.licenseNo=licenseNo;
		this.carNo=carNo;
	}

	public void register(String password) throws Exception {
		AccessDB.registerDriver(this, password);
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public String getCarNo() {
		return carNo;
	}

	public void confirmMatch(int bookingId) throws Exception {
		AccessDB.confirmMatch(bookingId, this.getUsername());
	}

	public List<HashMap<String, String>> getRides() throws Exception {
		List<Row> results = AccessDB.getUnmatchedRides();
		if(results ==  null)
		    return null;
		List<HashMap<String, String>> history = new ArrayList<HashMap<String,String>>();
		for(Row r: results) {
		            HashMap<String, String> row = new HashMap<String, String>();
		            row.put("origin", r.getString("origin"));
		            row.put("destination", r.getString("destination"));
		            row.put("rider", r.getString("rider"));
		            row.put("time", r.getTimestamp("time").toString());
		            row.put("bookingId", new Integer(r.getInt("booking_id")).toString());
		            history.add(row);
		        }
		return history;
	}
	
	public List<HashMap<String, String>> getTravelHistory() throws Exception {
	    List<Row> results = AccessDB.getDriverTravelHistory(this);
	    if (results== null)
	        return null;
	    List<HashMap<String, String>> history = new ArrayList<HashMap<String,String>>();
        for(Row r: results) {
            HashMap<String, String> row = new HashMap<String, String>();
            row.put("origin", r.getString("origin"));
            row.put("destination", r.getString("destination"));
            row.put("rider", r.getString("rider"));
            row.put("fare", r.getString("fare"));
            row.put("bookingId", new Integer(r.getInt("booking_id")).toString());
            history.add(row);
        }
        return history;
    }
}
