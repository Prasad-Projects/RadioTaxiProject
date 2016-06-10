package in.ac.bits_pilani.radiotaxi.roles.admin;

import static org.easymock.EasyMock.expect;
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
public class AdminTest extends EasyMockSupport {
	
	@Test
	public void testApproveDriver() throws Exception {
		Admin admin = new Admin("admin", "admin", "admin", "1111111111", 0);

		mockStatic(AccessDB.class);

		expect(admin.approveDriver("driver")).andReturn(true);
		expectLastCall();

		replay(AccessDB.class);

		admin.approveDriver("driver");

		verify(AccessDB.class);
	}

}
