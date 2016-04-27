package in.ac.bits_pilani.radiotaxi.payment;

import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class PaymentManagerTest extends EasyMockSupport {
	
	@Ignore
	@Test
	public void testMoneyTransfer() {
		mockStatic(AccessDB.class);
		expect(AccessDB.bookedCabBalance()).andReturn(1);
		
		replay(AccessDB.class);
		
		
	}
	
	@Ignore
	@Test
	public void testBalanceCheck() {
		// TODO
	}

}
