package radiotaxi.db;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AlreadyExistsException;

import radiotaxi.roles.driver.Driver;
import radiotaxi.roles.rider.Rider;

public class AccessDB {

	static Cluster cluster;
	static Session session;
	
	private static Logger logger = Logger.getLogger("AccessDB.class");
	
	public static void initialise() throws Exception {
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		try {
			session = cluster.connect();
			useKeyspace();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error initialising db", e);
			throw e;
		}
	}

	public AccessDB() {
	}

	public static void useKeyspace() throws Exception {
		String query = "use radiotaxi";
		try {
			session.execute(query);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "error using keyspace", e);
			throw e;
		}
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

	public static void registerRider(Rider rider, String password) throws Exception {
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
			logger.log(Level.SEVERE, "error registerRider", e);
			throw e;
		}
	}

	public List<Row> getUnregisteredDrivers() throws Exception {
		String query = "SELECT * FROM unregistered_drivers;";
		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error getUnregisteredDrivers", e);
			throw e;
		}
		return results;
	}

	public boolean authorizeDriver(String username) throws Exception {

		String query = "SELECT * FROM unregistered_drivers WHERE username = '" + username + "';";

		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error authorizeDriver", e);
			throw e;
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
			logger.log(Level.SEVERE, "error authorizeDriver", e);
			throw e;
		}

		query = "DELETE FROM unregistered_drivers WHERE username = '" + username + "';";
		try {
			session.execute(query);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error authorizeDriver", e);
			throw e;
		}

		return true;
	}

	public static void registerDriver(Driver driver, String password) throws Exception {
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
			logger.log(Level.SEVERE, "error registerDriver", e);
			throw e;
		}
	}

	public static void registerAdmin() throws Exception {
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
			logger.log(Level.SEVERE, "error registerAdmin", e);
			throw e;
		}
	}

	/*
	 * command to create bookings table - CREATE TABLE bookings (rider
	 * text,driver text,time timestamp,origin text,destination text,PRIMARY
	 * KEY(rider, time));
	 */
	public static String bookARide(String rider, String time, String origin, String destination) throws Exception {
		// TODO: generate valid booking IDs
		int randBookingId = 0 + (int) (Math.random() * 100000);

		String query = "INSERT INTO unmatched_bookings (booking_id,rider,time,origin,destination) VALUES ("
				+ randBookingId + ",'" + rider + "','" + time + "','" + origin + "','" + destination + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error bookARide", e);
			throw e;
		}
		return query;
	}
	public static List<Row> getUnmatchedRides() throws Exception {
		String query = "SELECT * FROM unmatched_bookings;";
		List<Row> results = null;
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error getUnmatchedRides", e);
			throw e;
		}
		return results;
	}
	
	public static boolean confirmMatch(int bookingId, String driver) throws Exception {
		List<Row> results = null;
		String query = "SELECT * FROM unmatched_bookings WHERE booking_id = " + bookingId + ";";
		try {
			ResultSet rs = session.execute(query);
			results = rs.all();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error confirmMatch", e);
			throw e;
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
			logger.log(Level.SEVERE, "error confirmMatch", e);
			throw e;
		}

		query = "DELETE FROM unmatched_bookings WHERE booking_id = " + bookingId + ";";
		try {
			session.execute(query);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error confirmMatch", e);
			throw e;
		}

		return true;
	}

	public static boolean login(String username, String password, String type) throws Exception {
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
			logger.log(Level.SEVERE, "error login", e);
			throw e;
		}
	}
}
