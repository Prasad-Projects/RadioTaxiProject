package ac.in.bits_pilani.radiotaxi.roles.driver;

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
import in.ac.bits_pilani.radiotaxi.roles.driver.ConfirmMatch;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class ConfirmMatchTest extends EasyMockSupport {
	
	@Test
	public void testConfirmMatch() throws Exception {
		ConfirmMatch confirm = new ConfirmMatch();
		
		mockStatic(AccessDB.class);
		
		/* expect */ confirm.confirmMatch(123456, "driver");
		expectLastCall();
		
		replay(AccessDB.class);
		
		confirm.confirmMatch(123456, "driver");
		
		verify(AccessDB.class);
	}

}
