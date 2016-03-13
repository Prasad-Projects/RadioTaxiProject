import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AlreadyExistsException;

public class Register_db {

	Cluster cluster;
	Session session;

	public Register_db() {

		this.cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		this.session = cluster.connect();
	//	System.out.println("fff");

	}

	public void use_keyspace() {
		String query = "use radiotaxi";
		session.execute(query);
	}

	public void create_table() {
		String query = "create table driver_info( first_name text, last_name text, username text PRIMARY KEY, password text, mobile_no text, license_no text, car_no text);";
		try {
			session.execute(query);
		} catch (AlreadyExistsException e) {
			System.out.print(e);
		}
	}

	public void register_rider(String username, String first_name,
			String last_name, String mobile_no, String password) {
		String query = " insert into rider_info (username,first_name,last_name,mobile_no,password) values ('"
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

	public void register_driver(String username, String first_name,
			String last_name, String mobile_no, String password, String car_no,
			String license_no) {
		String query = " insert into driver_info (username,first_name,last_name,mobile_no,password,car_no,license_no) values ('"
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

	public boolean login(String username, String password, String type) {
		if (type.equals("rider")) {
			String query = "select * from rider_info where username='"
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
			System.out.println("yolo");
			return false;
		} else {
			String query = "select * from driver_info where username='"
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
			System.out.println("yolo");
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