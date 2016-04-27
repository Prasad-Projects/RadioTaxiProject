package ac.in.bits_pilani.radiotaxi.roles.admin;

import static org.easymock.EasyMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.roles.admin.AuthorizeDriver;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class AuthorizeDriverTest extends EasyMockSupport {
	
	@Test
	public void testAuthorise() throws Exception {
		AuthorizeDriver auth = new AuthorizeDriver();
		
		mockStatic(AccessDB.class);
		
		/* expect */ auth.authorise("driver");
		expectLastCall();
		
		replay(AccessDB.class);
		
		auth.authorise("driver");
		
		verify(AccessDB.class);
	}
	
	@Ignore
	@Test
	public void testGetUnregisteredDrivers() throws Exception {
		AuthorizeDriver auth = new AuthorizeDriver();
		
		mockStatic(AccessDB.class);
		
		// expect(auth.getUnregisteredDrivers()).andReturn(new List<int>());

	}

}
