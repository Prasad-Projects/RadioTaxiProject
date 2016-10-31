package in.ac.bits_pilani.radiotaxi.roles.driver;

import static org.easymock.EasyMock.expectLastCall;
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
public class DriverTest extends EasyMockSupport {

	@Test
	public void testRegister() throws Exception {
		Driver driver = new Driver("driver", "driver", "driver", "1234567890", "werwe34", "1234", 0);
		String password = "dshjdvsaf";
		mockStatic(AccessDB.class);

		AccessDB.registerDriver(driver, password);
		expectLastCall();

		replay(AccessDB.class);

		driver.register(password);

		verify(AccessDB.class);
	}

	@Test
	public void testConfirmMatch() throws Exception {
		Driver driver = new Driver("driver1", "driver", "driver", "1111111111", "12345", "12345", 0);

		mockStatic(AccessDB.class);

		AccessDB.confirmMatch(123456,driver.getUsername());
		expectLastCall();

		replay(AccessDB.class);

		driver.confirmMatch(123456);

		verify(AccessDB.class);
	}

}
