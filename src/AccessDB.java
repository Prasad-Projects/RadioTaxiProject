import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

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
		useKeyspace();
	}

	public AccessDB() {
	}

	public static void useKeyspace() {
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
	
	public static String hashPassword(String password) {
		return DigestUtils.sha1Hex(password);
	}

	public static void registerRider(Rider rider, String password) {
		String query = " INSERT INTO rider_info (username,first_name,last_name,mobile_no,password) VALUES ('"
				+ rider.getUsername()
				+ "','"
				+ rider.getFirstname()
				+ "','"
				+ rider.getLastname()
				+ "','"
				+ rider.getMobile_no() + "','" + hashPassword(password) + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public List<Row> getUnregisteredDrivers() {
		String query = "SELECT * FROM unregistered_drivers;";
		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean authorizeDriver(String username) {

		String query = "SELECT * FROM unregistered_drivers WHERE username = '" + username + "';";

		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (results == null) {
			return false;
		}
		Row r = results.get(0);
		String firstName = r.getString("first_name");
		String lastName = r.getString("last_name");
		String carNo = r.getString("car_no");
		String licenseNo = r.getString("license_no");
		String mobileNo = r.getString("mobile_no");
		String password = r.getString("password");

		query = " INSERT INTO driver_info (username,first_name,last_name,mobile_no,password,car_no,license_no) VALUES ('"
				+ username + "','" + firstName + "','" + lastName + "','" + mobileNo + "','" + password + "','" + carNo
				+ "','" + licenseNo + "');";
				
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		query = "DELETE FROM unregistered_drivers WHERE username = '" + username + "';";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public static void registerDriver(Driver driver, String password) {
		String query = " INSERT INTO unregistered_drivers (username,first_name,last_name,mobile_no,car_no,license_no,password) VALUES ('"
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
				+ hashPassword(password) + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static void registerAdmin() {
		String query = " INSERT INTO admin_list (username,first_name,last_name,password) VALUES ('"
				+ "admin"
				+ "','"
				+ "Admin"
				+ "','"
				+ "Admin"
				+ "','"
				+ hashPassword("admin") + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	/*
	 * command to create bookings table - CREATE TABLE bookings (rider
	 * text,driver text,time timestamp,origin text,destination text,PRIMARY
	 * KEY(rider, time));
	 */
	public static String bookARide(String rider, String time, String origin, String destination) {
		// TODO: generate valid booking IDs
		int randBookingId = 0 + (int) (Math.random() * 100000);

		String query = "INSERT INTO unmatched_bookings (booking_id,rider,time,origin,destination) VALUES ("
				+ randBookingId + ",'" + rider + "','" + time + "','" + origin + "','" + destination + "');";
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
		String query = "SELECT * FROM unmatched_bookings WHERE booking_id = " + bookingId + ";";
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (results == null) {
			return false;
		}
		Row r = results.get(0);
		String rider = r.getString("rider");
		Date time = r.getTimestamp("time");
		LocalDateTime localTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.of("Asia/Kolkata"));
		String time_str = localTime.toString().replace('T', ' ').split("\\.")[0];

		String origin = r.getString("origin");
		String destination = r.getString("destination");

		query = "INSERT INTO matched_bookings (booking_id,rider,driver,time,origin,destination) VALUES (" + bookingId
				+ ",'" + rider + "','" + driver + "','" + time_str + "','" + origin + "','" + destination + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		query = "DELETE FROM unmatched_bookings WHERE booking_id = " + bookingId + ";";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public static boolean login(String username, String password, String type) {
		String table = null;
		switch (type) {
			case "rider":
				table = "rider_info";
				break;
			case "driver":
				table = "driver_info";
				break;
			case "admin":
				table = "admin_list";
		}
		String query = "SELECT * FROM " + table + " WHERE username = '" + username
				+ "' AND password = '" + hashPassword(password)	+ "' ALLOW FILTERING;";
		try {
			ResultSet rs = session.execute(query);
			return (!rs.all().isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
