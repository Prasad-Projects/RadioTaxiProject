package in.ac.bits_pilani.radiotaxi.payment;

import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.expectLastCall;

import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.expect;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import in.ac.bits_pilani.radiotaxi.CabType;
import in.ac.bits_pilani.radiotaxi.db.AccessDB;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class PaymentManagerTest extends EasyMockSupport {
	
	@Test
	public void testMoneyTransfer() throws Exception {

		CabType cab = CabType.Double;
		String rider = "rider", distance = "1 kms", duration = "10 mins",
				origin = "a", dest = "b";
		float[] originCoord = new float[] {10.0f, 11.0f},
				destCoord = new float[] {10.0f, 11.0f};
		// get current time (format yyyy-mm-dd HH:mm:ss) 
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        String timeStr = time.toString().replace('T', ' ').split("\\.")[0];		
		
		mockStatic(AccessDB.class);
		/*expect*/ AccessDB.bookARide(anyString(), anyString(), anyString(), anyString(), anyInt(), aryEq(originCoord), aryEq(destCoord));
		expectLastCall();
		
		replay(AccessDB.class);
		
		PaymentManager.moneyTransfer(rider, cab, distance, duration, origin, dest, timeStr, originCoord, destCoord);		
		
		verify(AccessDB.class);
	}
	
	@Test
	public void testBalanceCheck() {

		mockStatic(AccessDB.class);
		
		expect(AccessDB.bookedCabBalance()).andReturn(1);
		
		replay(AccessDB.class);
		
		PaymentManager pm = new PaymentManager();
		pm.balanceCheck();
		
		verify(AccessDB.class);
	}

}
