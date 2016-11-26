package in.ac.bits_pilani.radiotaxi.roles.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.User;

public class Admin extends User {

	public Admin(String username, String firstname, String lastname, 
	        String mobileNo, int balance) {
		super(username, firstname, lastname, mobileNo,balance);
	}

	public boolean approveDriver(String username) throws Exception {
		return AccessDB.approveDriver(username);		
	}

	public List<HashMap<String,String>> getUnregisteredDrivers() throws Exception {
		List<Row> results = AccessDB.getUnregisteredDrivers();
		 if(results == null)
             return null;
         List<HashMap<String, String>> history = new ArrayList<HashMap<String,String>>();
         for(Row r: results) {
             HashMap<String, String> row = new HashMap<String, String>();
             row.put("username", r.getString("username"));
             row.put("carNo", r.getString("car_no"));
             row.put("firstName", r.getString("first_name"));
             row.put("lastName", r.getString("last_name"));
             row.put("licenseNo", r.getString("license_no"));
             row.put("mobileNo", r.getString("mobile_no"));
             history.add(row);
         }
         return history;
	}

}
