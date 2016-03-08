import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AlreadyExistsException;

public class Register_db {

	static Cluster cluster;
	static Session session;
	
	static {
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect();
		//	System.out.println("connection successful");
		use_keyspace();
	}

	public Register_db() {

	}

	public static void use_keyspace() {
		String query = "use radiotaxi";
		session.execute(query);
	}

	public void createTable() {
		String query = "CREATE TABLE driver_info( first_name text, last_name text, username text PRIMARY KEY, password text, mobile_no text, license_no text, car_no text);";
		try {
			session.execute(query);
		} catch (AlreadyExistsException e) {
			System.out.print(e);
		}
	}

	public void registerRider(String username, String first_name,
			String last_name, String mobile_no, String password) {
		String query = " INSERT INTO rider_info (username,first_name,last_name,mobile_no,password) VALUES ('"
				+ username
				+ "','"
				+ first_name
				+ "','"
				+ last_name
				+ "','"
				+ mobile_no + "','" + password + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void registerDriver(String username, String first_name,
			String last_name, String mobile_no, String password, String car_no,
			String license_no) {
		String query = " INSERT INTO driver_info (username,first_name,last_name,mobile_no,password,car_no,license_no) VALUES ('"
				+ username
				+ "','"
				+ first_name
				+ "','"
				+ last_name
				+ "','"
				+ mobile_no
				+ "','"
				+ password
				+ "','"
				+ car_no
				+ "','"
				+ license_no + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	/*
	  command to create bookings table -
	    CREATE TABLE bookings (rider text,driver text,time timestamp,origin text,destination text,PRIMARY KEY(rider, time));
	 */
	public String registerBooking(String rider, String time, String origin, String destination) {
		
		// TODO: generate valid booking IDs
		int randBookingId = 0 + (int) (Math.random()*100000);
		
		String query = "INSERT INTO bookings (booking_id,rider,driver,time,origin,destination,matched) VALUES ("
				+ randBookingId
				+ ",'"
				+ rider
				+ "','"
				+ null // driver
				+ "','"
				+ time
				+ "','"
				+ origin
				+ "','"
				+ destination
				+ "',"
				+ false
				+ ");";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}
	
	public List<Row> getRides() {
		String query = "SELECT * FROM bookings WHERE matched = false ALLOW FILTERING;";
		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean login(String username, String password, String type) {
		if (type.equals("rider")) {
			String query = "SELECT * FROM rider_info WHERE username='"
					+ username + "' and password='" + password
					+ "' allow filtering;";
			try {
				ResultSet rs = session.execute(query);
				return (!rs.all().isEmpty());
				/*
				 * Iterator<Row> it= rs.iterator(); while(it.hasNext()) { Row r=
				 * it.next(); System.out.println(r); }
				 * System.out.println("done");
				 */
			} catch (Exception e) {
				System.out.print(e);
			}
			System.out.println("returning false...");
			return false;
		} else {
			String query = "SELECT * FROM driver_info WHERE username='"
					+ username + "' AND password='" + password
					+ "' ALLOW FILTERING;";
			try {
				ResultSet rs = session.execute(query);
				return (!rs.all().isEmpty());
				/*
				 * Iterator<Row> it= rs.iterator(); while(it.hasNext()) { Row r=
				 * it.next(); System.out.println(r); }
				 * System.out.println("done");
				 */
			} catch (Exception e) {
				System.out.print(e);
			}
			System.out.println("returning false...");
			return false;
		}
	}

/*	public static void main(String[] args) {
		Register_db r = new Register_db();
		r.use_keyspace();
		// r.create_table();
		System.out.println("f");
		r.register_rider("a","b", "c", "7", "d");
		// r.register_driver("a","b", "c", "7", "d","ca","li");
		// System.out.println("f");
		 System.out.print(r.login("a", "d", "rider"));
	
		 System.out.println("f");
	}*/
}