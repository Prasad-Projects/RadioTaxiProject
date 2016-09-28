package in.ac.bits_pilani.radiotaxi.auth;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.User;
import in.ac.bits_pilani.radiotaxi.roles.admin.Admin;
import in.ac.bits_pilani.radiotaxi.roles.rider.Rider;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class LoginTest extends EasyMockSupport {

	/**
	 * Using that the database has a driver with username "a" and password "a"
	 * @throws Exception
	 */
	@Test
	public void testLogin() throws Exception
	{
		//Arrange
	    Login login = new Login();
		Rider rider = mock(Rider.class);
		Admin admin = mock(Admin.class);
		
		mockStatic(AccessDB.class);
		
		expect(AccessDB.login("rider", "rider", "rider")).andReturn(rider);
		expect(AccessDB.login("driver", "driver", "rider")).andReturn(null);
		expect(AccessDB.login("admin", "admin", "admin")).andReturn(admin);
		
		replay(AccessDB.class);
		
		//Act
		User return1 = login.login("rider", "rider", "rider");
		User return2 = login.login("driver", "driver", "rider");
		User return3 = login.login("admin", "admin", "admin");
		
		//Assert
		assertEquals(return1, rider);
		//verify(AccessDB.class);
		assertEquals(return2, null); 
		//verify(AccessDB.class);
		assertEquals(return3,admin);
		verify(AccessDB.class);
	}

}
