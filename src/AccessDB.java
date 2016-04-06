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

<<<<<<< HEAD:src/Register_db.java
	public void registerRider(String username, String firstName, String lastName, String mobileNo, String password) {
		String query = " INSERT INTO rider_info (username,first_name,last_name,mobile_no,password) VALUES ('" + username
				+ "','" + firstName + "','" + lastName + "','" + mobileNo + "','" + password + "');";
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void registerDriver(String username, String firstName, String lastName, String mobileNo, String password,
			String carNo, String licenseNo) {
		String query = " INSERT INTO unregistered_drivers (username,first_name,last_name,mobile_no,password,car_no,license_no) VALUES ('"
				+ username + "','" + firstName + "','" + lastName + "','" + mobileNo + "','" + password + "','" + carNo
				+ "','" + licenseNo + "');";
=======
	public static void registerRider(Rider rider, String password) {
		String query = " INSERT INTO rider_info (username,first_name,last_name,mobile_no,password) VALUES ('"
				+ rider.getUsername()
				+ "','"
				+ rider.getFirstname()
				+ "','"
				+ rider.getLastname()
				+ "','"
				+ rider.getMobile_no() + "','" + password + "');";
>>>>>>> Release-1.1:src/AccessDB.java
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

<<<<<<< HEAD:src/Register_db.java
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
=======
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
>>>>>>> Release-1.1:src/AccessDB.java
		try {
			session.execute(query);
		} catch (Exception e) {
			System.out.print(e);
		}

		query = "DELETE FROM unregistered_drivers WHERE username = '" + username + "';";
		try {
			session.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	/*
	 * command to create bookings table - CREATE TABLE bookings (rider
	 * text,driver text,time timestamp,origin text,destination text,PRIMARY
	 * KEY(rider, time));
	 */
<<<<<<< HEAD:src/Register_db.java
	public String registerBooking(String rider, String time, String origin, String destination) {

=======
	public static String bookARide(String rider, String time, String origin, String destination) {
		
>>>>>>> Release-1.1:src/AccessDB.java
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
<<<<<<< HEAD:src/Register_db.java

	public List<Row> getRides() {
=======
	
	public static List<Row> getUnmatchedRides() {
>>>>>>> Release-1.1:src/AccessDB.java
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
<<<<<<< HEAD:src/Register_db.java

	public boolean confirmMatch(int bookingId, String driver) {
=======
	
	public static boolean confirmMatch(int bookingId, String driver) {
>>>>>>> Release-1.1:src/AccessDB.java
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

<<<<<<< HEAD:src/Register_db.java
	public boolean login(String username, String password, String type) {
		String table = null;
		switch (type) {
		case "rider":
			table = "rider_info";
		case "driver":
			table = "driver_info";
		case "admin":
			table = "admin_list";
=======
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
>>>>>>> Release-1.1:src/AccessDB.java
		}
		String query = "SELECT * FROM " + table + " WHERE username = '" + username + "' AND password = '" + password
				+ "' ALLOW FILTERING;";
		try {
			ResultSet rs = session.execute(query);
			return (!rs.all().isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("returning false...");
		return false;
	}

<<<<<<< HEAD:src/Register_db.java
	/*
	 * public static void main(String[] args) { Register_db r = new
	 * Register_db(); r.use_keyspace(); // r.create_table();
	 * System.out.println("f"); r.register_rider("a","b", "c", "7", "d"); //
	 * r.register_driver("a","b", "c", "7", "d","ca","li"); //
	 * System.out.println("f"); System.out.print(r.login("a", "d", "rider"));
	 * 
	 * System.out.println("f"); }
	 */
=======
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
>>>>>>> Release-1.1:src/AccessDB.java
}
