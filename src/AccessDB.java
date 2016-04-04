import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AlreadyExistsException;

public class AccessDB {

	static Cluster cluster;
	static Session session;
	
	public static void initialise(){
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect();
		use_keyspace();
		
	}

	public AccessDB() {
	}

	public static void use_keyspace() {
		String query = "use radiotaxi";
		session.execute(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE driver_info( first_name text, last_name text, username text PRIMARY KEY, password text, mobile_no text, license_no text, car_no text);";
		try {
			session.execute(query);
		} catch (AlreadyExistsException e) {
			System.out.print(e);
		}
	}

	public static void registerRider(Rider rider, String password) {
		String query = " INSERT INTO rider_info (username,first_name,last_name,mobile_no,password) VALUES ('"
				+ rider.getUsername()
				+ "','"
				+ rider.getFirstname()
				+ "','"
				+ rider.getLastname()
				+ "','"
				+ rider.getMobile_no() + "','" + password + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static void registerDriver(Driver driver, String password) {
		String query = " INSERT INTO driver_info (username,first_name,last_name,mobile_no,car_no,license_no,password) VALUES ('"
				+ driver.getUsername()
				+ "','"
				+ driver.getFirstname()
				+ "','"
				+ driver.getLastname()
				+ "','"
				+ driver.getMobile_no()
				+ "','"
				+ driver.getLicense_no() 
				+ "','"
				+ driver.getCar_no()
				+ "','"
				+ password+ "');";
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
	public static String bookARide(String rider, String time, String origin, String destination) {
		
		// TODO: generate valid booking IDs
		int randBookingId = 0 + (int) (Math.random()*100000);
		
		String query = "INSERT INTO unmatched_bookings (booking_id,rider,time,origin,destination) VALUES ("
				+ randBookingId
				+ ",'"
				+ rider
				+ "','"
				+ time
				+ "','"
				+ origin
				+ "','"
				+ destination
				+ "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}
	
	public static List<Row> getUnmatchedRides() {
		String query = "SELECT * FROM unmatched_bookings;";
		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public static boolean confirmMatch(int bookingId, String driver) {
		List<Row> results = null;
		String query = "SELECT * FROM unmatched_bookings WHERE booking_id = "
				+bookingId
				+";";
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(results == null) {
			return false;
		}
		Row r = results.get(0);
		String rider = r.getString("rider");
		Date time = r.getTimestamp("time");
		LocalDateTime localTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.of("Asia/Kolkata"));
		String time_str = localTime.toString().replace('T', ' ').split("\\.")[0];
		
		String origin = r.getString("origin");
		String destination = r.getString("destination");
		
		query = "DELETE FROM unmatched_bookings WHERE booking_id = "
				+bookingId
				+";";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO matched_bookings (booking_id,rider,driver,time,origin,destination) VALUES ("
				+ bookingId
				+ ",'"
				+ rider
				+ "','"
				+ driver
				+ "','"
				+ time_str
				+ "','"
				+ origin
				+ "','"
				+ destination
				+ "');";
		
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public static boolean login(String username, String password, String type) {
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
		AccessDB r = new AccessDB();
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