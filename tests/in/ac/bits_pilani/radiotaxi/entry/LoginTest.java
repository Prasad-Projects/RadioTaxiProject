package in.ac.bits_pilani.radiotaxi.entry;

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
		Login login = new Login();

		boolean expected1 = true;
		boolean expected2 = false;
		boolean expected3 = true;

		mockStatic(AccessDB.class);

		expect(login.login("rider", "rider", "rider")).andReturn(true);
		expect(login.login("driver", "driver", "rider")).andReturn(false);
		expect(AccessDB.login("admin", "admin", "admin")).andReturn(true);

		replay(AccessDB.class);

		boolean actual1 = login.login("rider", "rider", "rider");
		boolean actual2 = login.login("driver", "driver", "rider");
		boolean actual3 = login.login("admin", "admin", "admin");

		verify(AccessDB.class);

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
	}

}
