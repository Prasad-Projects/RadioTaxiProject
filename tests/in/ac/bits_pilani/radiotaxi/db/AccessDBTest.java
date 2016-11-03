package in.ac.bits_pilani.radiotaxi.db;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import in.ac.bits_pilani.radiotaxi.roles.driver.Driver;
import in.ac.bits_pilani.radiotaxi.roles.rider.Rider;

public class AccessDBTest extends EasyMockSupport {
    
    @Test
	public void testLogin() throws Exception {
	    Session session = mock(Session.class);
	    Row row = mock(Row.class);
	    ResultSet rs = mock(ResultSet.class);
	    List<Row> list = new ArrayList<Row>();
	    list.add(row);
	    String username = "rider";
	    String password = "rider";
	    String firstname = "ABC";
	    String lastname = "DEF";
	    String mobile_no = "7865432133";
	    int balance = 100;
	    
	    String query = "SELECT * FROM rider_info WHERE username = 'rider' "
                + "AND password = '" + AccessDB.hashPassword(password)
                + "' ALLOW FILTERING;";

	    
	    expect(session.execute(query)).andReturn(rs);
	    expect(rs.all()).andReturn(list);
	    expect(row.getString("first_name")).andReturn(firstname);
        expect(row.getString("last_name")).andReturn(lastname);
        expect(row.getString("mobile_no")).andReturn(mobile_no);
        expect(row.getInt("balance")).andReturn(balance);
	    
        
	    expectLastCall();
        replay(session);
	    replay(rs);
	    replay(row);
	    
	    
	    AccessDB.setSession(session);
	    AccessDB.login(username, password, "rider");
	    verify(session);
	    verify(rs);
	    verify(row);
	}

	@Test
	public void testRegisterRider() throws Exception {
		Session session = mock(Session.class);

		Rider rider = new Rider("rider", "rider", "rider", "1234567890", 0);
		String password = "rider";
		String query = "INSERT INTO rider_info (username,first_name,last_name,"
		        + "mobile_no,balance,password) VALUES ('"
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

		Driver driver = new Driver("driver", "driver", "driver", 
		        "2143658709", "asdf43", "123456", 0);
		String password = "driver";
		String query = " INSERT INTO unregistered_drivers (username,"
		        + "first_name,last_name,mobile_no,car_no,license_no,"
		        + "balance,password) VALUES ('"
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
				+ AccessDB.hashPassword(password) + "'"
				        + " WHERE username = '" + rider + "'";

		expect(session.execute(query)).andReturn(null);
		
		expectLastCall();

		replay(session);

		AccessDB.setSession(session);
		AccessDB.updateRiderDetails(rider, password);

		verify(session);
	}
	
	@Test
    public void testUpdateDriverDetails() throws Exception {
        Session session = mock(Session.class);

        String driver = "driver";
        String password = "dshafhj";

        String query = "UPDATE driver_info SET password = '" + 
                AccessDB.hashPassword(password) + "' WHERE username = '" + driver + "'"; 

        expect(session.execute(query)).andReturn(null);
        
        expectLastCall();

        replay(session);

        AccessDB.setSession(session);
        AccessDB.updateDriverDetails(driver, password);

        verify(session);
    }
	
	@Test
	public void testApproveDriver() throws Exception {

		Session session = mock(Session.class);
		ResultSet rs = mock(ResultSet.class);
		Row row = mock(Row.class);
        List<Row> list = new ArrayList<Row>();
        list.add(row);
        
		String username = "driver";
		String password = "password";
		Driver driver = new Driver("driver", "driver", "driver", 
		        "2143658709", "asdf43", "123456", 0);

		String query = "SELECT * FROM unregistered_drivers WHERE username = '"
                + username + "';";
		String query2 = "INSERT INTO driver_info (username,first_name,last_name,"
		        + "mobile_no,password,car_no,license_no,balance) VALUES ('"
				+ driver.getUsername() + "','" + driver.getFirstname() + "','"
				+ driver.getLastname() + "','" + driver.getMobileNo() + "','"
				+ password + "','" + driver.getCarNo() + "','"
				+ driver.getLicenseNo() + "',0);";
		String query3 = "DELETE FROM unregistered_drivers WHERE username = '"
                + username + "';";
		
		expect(session.execute(query)).andReturn(rs);
		expect(rs.all()).andReturn(list);
		expect(row.getString("first_name")).andReturn(driver.getFirstname());
        expect(row.getString("last_name")).andReturn(driver.getLastname());
        expect(row.getString("car_no")).andReturn(driver.getCarNo());
        expect(row.getString("license_no")).andReturn(driver.getLicenseNo());
        expect(row.getString("mobile_no")).andReturn(driver.getMobileNo());
        expect(row.getString("password")).andReturn(password);
		expect(session.execute(query2)).andReturn(null);
		expect(session.execute(query3)).andReturn(null);

		expectLastCall();

		replay(session);
		replay(rs);
		replay(row);

		AccessDB.setSession(session);
		AccessDB.approveDriver(username);

		verify(session);
		verify(rs);
		verify(row);
	}
	
	@Test
	public void testBookARide() throws Exception {

		Session session = mock(Session.class);

		String rider = "rider", time = "", origin = "from", destination = "to";
		int fare = 10; 
		int randBookingId = (((17*37 + rider.hashCode())* 37 + time.hashCode())
                * 37 + origin.hashCode())* 37 +
                destination.hashCode();
		float[] originCoord = new float[] {0f, 0f}, destCoord = new float[] {0f, 0f};

		String query = "INSERT INTO unmatched_bookings (booking_id,rider,time,"
		        + "origin,destination,fare,orig_coord,dest_coord) VALUES ("
				+ randBookingId	+ ",'" + rider + "','" + time + "','"
				+ origin + "','" + destination + "'," + fare + "," + "["
				+ originCoord[0] + "," + originCoord[1] + "]" + "," + "["
				+ destCoord[0] + "," + destCoord[1] + "]" + ");";
		
		expect(session.execute(query)).andReturn(null);

		expectLastCall();

		replay(session);

		AccessDB.setSession(session);
		AccessDB.bookARide(rider, time, origin, destination, fare, 
		        originCoord, destCoord);

		verify(session);
	}
	
	@Test
	public void testConfirmMatch() throws Exception {
	    Session session = mock(Session.class);
	    ResultSet rs = mock(ResultSet.class);
	    Row r = mock(Row.class);
	    List<Row> list = new ArrayList<Row>();
	    list.add(r);
	    
	    int bookingId = 983634;
	    String driver = "driver";
	    String rider = "rider";
	    String origin = "from";
	    String destination = "to";
	    int fare = 654;
	    int balance = 432;
	    int balance2 = 123;
	    int fr =  balance - fare;
	    int fd = fare + balance2;
	    Date time = new Date();
	    LocalDateTime localTime = LocalDateTime.ofInstant(time.toInstant(),
                ZoneId.of("Asia/Kolkata"));
        String time_str = localTime.toString().replace('T', ' ').split("\\.")[0];
	    
	    
	    String query = "SELECT * FROM unmatched_bookings WHERE booking_id = "
                + bookingId + ";";
	    String query2 ="select * from rider_info where username='"+rider+"';";
	    String query3 ="update rider_info set balance="+fr
	            +" where username='"+rider+"';";
	    String query4 ="select * from driver_info where username='"+driver+"';";
	    String query5 ="update driver_info set balance="+fd+" where username='"+driver+"';";
	    String query6 = "INSERT INTO matched_bookings (booking_id,rider,driver,"
	            + "time,origin,destination,fare) VALUES ("
                + bookingId
                + ",'"
                + rider
                + "','"
                + driver
                + "','"
                + time_str
                + "','" + origin + "','" + destination + "'," + fare + ");";
	    String query7 = "DELETE FROM unmatched_bookings WHERE booking_id = "
                + bookingId + ";";
	    
	    expect(session.execute(query)).andReturn(rs);
	    expect(rs.all()).andReturn(list);
	    expect(r.getString("rider")).andReturn(rider);
        expect(r.getTimestamp("time")).andReturn(time);
        expect(r.getString("origin")).andReturn(origin);
        expect(r.getString("destination")).andReturn(destination);
        expect(r.getInt("fare")).andReturn(fare);
	    expect(session.execute(query2)).andReturn(rs);
	    expect(rs.all()).andReturn(list);
	    expect(r.getInt("balance")).andReturn(balance);
	    expect(session.execute(query3)).andReturn(null);
	    expect(session.execute(query4)).andReturn(rs);
	    expect(rs.all()).andReturn(list);
	    expect(r.getInt("balance")).andReturn(balance2);
	    expect(session.execute(query5)).andReturn(null);
	    expect(session.execute(query6)).andReturn(null);
	    expect(session.execute(query7)).andReturn(null);
	    
	    expectLastCall();

        replay(session);
        replay(rs);
        replay(r);
        
        AccessDB.setSession(session);
        AccessDB.confirmMatch(bookingId, driver);

        verify(session);
	    
	}
	
	@Test
	public void testgetDriverTravelHistory() throws Exception {
	    Session session = mock(Session.class);
        ResultSet rs = mock(ResultSet.class);
        List<Row> list = new ArrayList<Row>();
	    Driver driver = new Driver("driver", "driver", "driver", 
                "2143658709", "asdf43", "123456", 0);
	    String query = "SELECT * FROM matched_bookings WHERE driver ='" 
                + driver.getUsername() + "' ALLOW FILTERING;";
	    expect(session.execute(query)).andReturn(rs);
	    expect(rs.all()).andReturn(list);
	    expectLastCall();
	    
	    replay(session);
	    replay(rs);
	    
	    AccessDB.setSession(session);
	    AccessDB.getDriverTravelHistory(driver);
	    verify(session);
	    verify(rs);
	    
	}
	
	@Test
    public void testgetRiderTravelHistory() throws Exception {
        Session session = mock(Session.class);
        ResultSet rs = mock(ResultSet.class);
        List<Row> list = new ArrayList<Row>();
        Rider rider = new Rider("rider", "rider", "rider", "1234567890", 0);
        String query = "SELECT * FROM matched_bookings WHERE rider ='" + 
        rider.getUsername() + "' ALLOW FILTERING;";
        expect(session.execute(query)).andReturn(rs);
        expect(rs.all()).andReturn(list);
        expectLastCall();
        
        replay(session);
        replay(rs);
        
        AccessDB.setSession(session);
        AccessDB.getRiderTravelHistory(rider);
        verify(session);
        verify(rs);
        
    }
}
