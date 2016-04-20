package in.ac.bits_pilani.radiotaxi.tests;
import in.ac.bits_pilani.radiotaxi.db.AccessDB;
import in.ac.bits_pilani.radiotaxi.entry.Login;
import junit.framework.TestCase;

public class LoginTest extends TestCase{

	 protected void setUp() throws Exception{
	     AccessDB.initialise();
	}
	 
	/**
	 * Using that the database has a driver with username "a" and password "a"
	 * @throws Exception
	 */
	public void testLogin() throws Exception
	{
		Login loginObject = new Login();
		assertTrue(loginObject.login("a","a","rider"));
		assertFalse(loginObject.login("ad","ad","rider"));
		assertFalse(loginObject.login("a","ad","rider"));
		assertTrue(loginObject.login("f","f","driver"));
		assertFalse(loginObject.login("dd","dd","driver"));
		assertFalse(loginObject.login("f","dd","driver"));
		assertTrue(loginObject.login("admin","admin","admin"));
		assertFalse(loginObject.login("adm","admi","admin"));
		assertFalse(loginObject.login("admin","admi","admin"));
	}
	
}
