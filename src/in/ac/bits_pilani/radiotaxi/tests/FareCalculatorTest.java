package in.ac.bits_pilani.radiotaxi.tests;
import in.ac.bits_pilani.radiotaxi.CabType;
import in.ac.bits_pilani.radiotaxi.fare.FareCalculator;
import junit.framework.*;

public class FareCalculatorTest extends TestCase {
	public void testRideFare(){
		assertTrue(FareCalculator.rideFare("a", CabType.Regular, "4", "5")==33);
		assertTrue(FareCalculator.rideFare("a", CabType.Extended, "4", "5")==45);
		assertTrue(FareCalculator.rideFare("a", CabType.Double, "4", "5")==65);
	}
}
