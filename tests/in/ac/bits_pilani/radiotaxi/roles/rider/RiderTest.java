package in.ac.bits_pilani.radiotaxi.roles.rider;

import static org.easymock.EasyMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class RiderTest {
	
	@Test
	public void testRegisterRider() throws Exception {
		Rider rider = new Rider("rider", "rider", "rider", "1234567890", 0);
		String password = "abdyfjfhb";
		mockStatic(AccessDB.class);
		
		AccessDB.registerRider(rider, password);
		expectLastCall();

		replay(AccessDB.class);

		rider.register(password);

		verify(AccessDB.class);
	}

}
