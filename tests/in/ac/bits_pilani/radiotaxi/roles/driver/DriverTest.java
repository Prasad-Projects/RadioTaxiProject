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

		mockStatic(AccessDB.class);

		/* expect */ driver.register("driver");
		expectLastCall();

		replay(AccessDB.class);

		driver.register("driver");

		verify(AccessDB.class);
	}

}
