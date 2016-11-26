package in.ac.bits_pilani.radiotaxi.roles.rider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.User;

public class Rider extends User {

	public Rider(String username, String firstname, String lastname, String mobileNo, int balance) {
		super(username, firstname, lastname, mobileNo, balance);
	}

	public void register(String password) throws Exception {
		AccessDB.registerRider(this, password);
	}
	

    public List<HashMap<String, String>> getTravelHistory() throws Exception {
            List<Row> results =  AccessDB.getRiderTravelHistory(this);
            if(results == null)
                return null;
            List<HashMap<String, String>> history = new ArrayList<HashMap<String,String>>();
            for(Row r: results) {
                HashMap<String, String> row = new HashMap<String, String>();
                row.put("origin", r.getString("origin"));
                row.put("destination", r.getString("destination"));
                row.put("driver", r.getString("driver"));
                row.put("fare", new Integer(r.getInt("fare")).toString());
                row.put("bookingId", new Integer(r.getInt("booking_id")).toString());
                row.put("time", r.getTimestamp("time").toString());
                history.add(row);
            }
            return history;
    }

}