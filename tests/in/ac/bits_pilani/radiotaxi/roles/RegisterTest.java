package in.ac.bits_pilani.radiotaxi.roles;

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
import in.ac.bits_pilani.radiotaxi.roles.driver.Driver;
import in.ac.bits_pilani.radiotaxi.roles.rider.Rider;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class RegisterTest extends EasyMockSupport {

	@Test
	public void testRegisterRider() throws Exception {
		Register reg = new Register();
		Rider rider = new Rider("rider", "rider", "rider", "1234567890", 0);

		mockStatic(AccessDB.class);

		/* expect */ reg.registerRider(rider, "rider");
		expectLastCall();

		replay(AccessDB.class);

		reg.registerRider(rider, "rider");

		verify(AccessDB.class);
	}

	@Test
	public void testRegisterDriver() throws Exception {
		Register reg = new Register();
		Driver driver = new Driver("driver", "driver", "driver", "1234567890", "werwe34", "1234", 0);

		mockStatic(AccessDB.class);

		/* expect */ reg.registerDriver(driver, "driver");
		expectLastCall();

		replay(AccessDB.class);

		reg.registerDriver(driver, "driver");

		verify(AccessDB.class);
	}

}
