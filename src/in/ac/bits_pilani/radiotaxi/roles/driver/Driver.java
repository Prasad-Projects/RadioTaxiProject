package in.ac.bits_pilani.radiotaxi.roles.driver;

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

	public List<Row> getRides() throws Exception {
		List<Row> results = AccessDB.getUnmatchedRides();
		return results;
	}
	
	public List<HashMap<String, String>> getTravelHistory() {
        return AccessDB.getDriverTravelHistory(this);
    }
}
