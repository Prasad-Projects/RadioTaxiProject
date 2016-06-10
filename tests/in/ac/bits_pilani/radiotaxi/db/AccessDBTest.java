package in.ac.bits_pilani.radiotaxi.db;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.find;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.Test;

import com.datastax.driver.core.Session;

import in.ac.bits_pilani.radiotaxi.roles.driver.Driver;
import in.ac.bits_pilani.radiotaxi.roles.rider.Rider;

public class AccessDBTest extends EasyMockSupport {

	@Ignore
	@Test
	public void testLogin() throws Exception {

		// TODO

	}

	@Test
	public void testRegisterRider() throws Exception {
		Session session = mock(Session.class);

		Rider rider = new Rider("rider", "rider", "rider", "1234567890", 0);
		String password = "rider";
		String query = "INSERT INTO rider_info (username,first_name,last_name,mobile_no,balance,password) VALUES ('"
				+ rider.getUsername() + "','" + rider.getFirstname() + "','"
				+ rider.getLastname() + "','" + rider.getMobileNo() + "',0,'"
				+ AccessDB.hashPassword(password) + "');";

		expect(session.execute(query)).andReturn(null);

		expectLastCall();

		replay(session);

		AccessDB.setSession(session);
		AccessDB.registerRider(rider, password);

		verify(session);
	}

	@Test
	public void testRegisterDriver() throws Exception {
		Session session = mock(Session.class);

		Driver driver = new Driver("driver", "driver", "driver", "2143658709", "asdf43", "123456", 0);
		String password = "driver";
		String query = " INSERT INTO unregistered_drivers (username,first_name,last_name,mobile_no,car_no,license_no,balance,password) VALUES ('"
				+ driver.getUsername() + "','" + driver.getFirstname() + "','"
				+ driver.getLastname() + "','" + driver.getMobileNo() + "','"
				+ driver.getCarNo() + "','" + driver.getLicenseNo()	+ "',0,'"
				+ AccessDB.hashPassword(password) + "');";

		expect(session.execute(query)).andReturn(null);

		expectLastCall();

		replay(session);

		AccessDB.setSession(session);
		AccessDB.registerDriver(driver, password);

		verify(session);
	}

	@Test
	public void testUpdateRiderDetails() throws Exception {
		Session session = mock(Session.class);

		String rider = "rider";
		String password = "driver";

		String query = "UPDATE rider_info SET password = '"
				+ AccessDB.hashPassword(password) + "' WHERE username = '" + rider + "'";

		expect(session.execute(query)).andReturn(null);

		expectLastCall();

		replay(session);

		AccessDB.setSession(session);
		AccessDB.updateRiderDetails(rider, password);

		verify(session);
	}

	@Ignore
	@Test
	public void testApproveDriver() throws Exception {

		// TODO: first query returns a List<Row>
		Session session = mock(Session.class);

		String username = "driver";
		String password = "password";
		Driver driver = new Driver("driver", "driver", "driver", "2143658709", "asdf43", "123456", 0);

		String query = "SELECT * FROM unregistered_drivers WHERE username = '"
				+ username + "';";
		String query2 = "INSERT INTO driver_info (username,first_name,last_name,mobile_no,password,car_no,license_no,balance) VALUES ('"
				+ driver.getUsername() + "','" + driver.getFirstname() + "','"
				+ driver.getLastname() + "','" + driver.getMobileNo() + "','"
				+ password + "','" + driver.getCarNo() + "','"
				+ driver.getLicenseNo() + "',0);";

		//expect(session.execute(query)).andReturn(null);
		expect(session.execute(query2)).andReturn(null);

		expectLastCall();

		replay(session);

		AccessDB.setSession(session);
		AccessDB.approveDriver(username);

		verify(session);
	}

	@Ignore
	@Test
	public void testBookARide() throws Exception {

		//TODO: currently working on this
		Session session = mock(Session.class);

		String rider = "rider", time = "", origin = "from", destination = "to",
				regex = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
		int fare = 10, randBookingId = 0 + (int) (Math.random() * 100000);
		float[] originCoord = new float[] {0f, 0f}, destCoord = new float[] {0f, 0f};

		String query = "INSERT INTO unmatched_bookings (booking_id,rider,time,origin,destination,fare,orig_coord,dest_coord) VALUES ("
				+ randBookingId	+ ",'" + rider + "','" + find(regex) + "','"
				+ origin + "','" + destination + "'," + fare + "," + "["
				+ originCoord[0] + "," + originCoord[1] + "]" + "," + "["
				+ destCoord[0] + "," + destCoord[1] + "]" + ");";

		expect(session.execute(query)).andReturn(null);

		expectLastCall();

		replay(session);

		AccessDB.bookARide(rider, time, origin, destination, fare, originCoord, destCoord);

		verify(session);
	}

}
