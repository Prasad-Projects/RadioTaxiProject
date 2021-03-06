package in.ac.bits_pilani.radiotaxi.roles.admin;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.datastax.driver.core.Row;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccessDB.class)
public class AdminTest extends EasyMockSupport {
	
	@Test
	public void testApproveDriver() throws Exception {
		Admin admin = new Admin("admin", "admin", "admin", "1111111111", 0);

		mockStatic(AccessDB.class);

		expect(AccessDB.approveDriver("driver1")).andReturn(true);
		expect(AccessDB.approveDriver("driver2")).andReturn(false);
		expectLastCall();

		replay(AccessDB.class);

		assertTrue(admin.approveDriver("driver1"));
		assertFalse(admin.approveDriver("driver2"));

		verify(AccessDB.class);
	}
	
	@Test
	public void testGetUnregisteredDrivers() throws Exception {
	    Admin admin = new Admin("admin", "admin", "admin", "1111111111", 0);

        mockStatic(AccessDB.class);
	    /*Row r = mock(Row.class);
        List<Row> list = new ArrayList<Row>();
        list.add(r);*/
        
        expect(AccessDB.getUnregisteredDrivers()).andReturn(new ArrayList<Row>());
       /* expect(r.getString("username")).andReturn("user");
        expect(r.getString("car_no")).andReturn("car");
        expect(r.getString("first_name")).andReturn("first");
        expect(r.getString("last_name")).andReturn("last");
        expect(r.getString("license_no")).andReturn("license");
        expect(r.getString("mobile_no")).andReturn("mobile");
        expectLastCall();*/
        
        replay(AccessDB.class);
/*        replay(Row.class);*/
        
        admin.getUnregisteredDrivers();
        
        verify(AccessDB.class);
        /*verify(Row.class);*/
	}

}
