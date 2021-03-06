package in.ac.bits_pilani.radiotaxi.roles.rider;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import in.ac.bits_pilani.radiotaxi.CabType;
import in.ac.bits_pilani.radiotaxi.payment.PaymentManager;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PaymentManager.class)
public class BookingTest extends EasyMockSupport {

	@Test
	public void testBooking() throws Exception {
		Booking booking = new Booking();

		mockStatic(PaymentManager.class);
		
		PaymentManager.moneyTransfer(eq("rider"),eq(CabType.Regular), eq("10 km"), eq("1 mins"),
		        eq("from"), eq("to"), anyString(), aryEq(new float[] {10.0f, 11.0f}),
		        aryEq(new float[] {10.0f, 11.0f}));
		expectLastCall();

		replay(PaymentManager.class);

		booking.bookTrip("rider", "from", "to", CabType.Regular, "10 km", "1 mins", 
		        new float[] {10.0f, 11.0f}, new float[] {10.0f, 11.0f});

		verify(PaymentManager.class);
	}

}
