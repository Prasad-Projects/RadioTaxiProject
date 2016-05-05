package in.ac.bits_pilani.radiotaxi.payment;

import in.ac.bits_pilani.radiotaxi.CabType;
import junit.framework.TestCase;

public class FareCalculatorTest extends TestCase {
	public void testRideFare(){
		assertTrue(FareCalculator.rideFare("a", CabType.Regular, "4", "5")==33);
		assertTrue(FareCalculator.rideFare("a", CabType.Extended, "4", "5")==45);
		assertTrue(FareCalculator.rideFare("a", CabType.Double, "4", "5")==65);
	}
}
