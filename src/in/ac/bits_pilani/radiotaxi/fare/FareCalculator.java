package in.ac.bits_pilani.radiotaxi.fare;

import in.ac.bits_pilani.radiotaxi.CabType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FareCalculator {

	public static int rideFare(String rider, CabType cab, String distance, String duration)
	{
		int d=0,t=0;
		
		String s = duration;
	    Pattern p = Pattern.compile("([0-9]+)");
	    Matcher m = p.matcher(s);
	    if (m.find()) {
	         t=Integer.parseInt(m.group());
	    }
		
	    s = distance;
	    p = Pattern.compile("([0-9]+)");
	    m = p.matcher(s);
	    if (m.find()) {
	    	d=Integer.parseInt(m.group());
	    }
	    
	    int fare=0;
	    
	    if (cab==CabType.Regular)
	    	fare=d*7+t;
	    else if(cab==CabType.Double)
	    	fare=d*10+t;
	    else
	    	fare=d*15+t;
	    
	    return fare;
	}
	
	public static int riderFare(String rider, CabType cab, String distance, String duration)
	{
		return FareCalculator.rideFare(rider, cab, distance, duration);
	}

	public static int driverProfit(String rider, CabType cab, String distance, String duration)
	{
		return (int)(FareCalculator.rideFare(rider, cab, distance, duration)*0.9);
	}
	
	public static int commisionForPlatform(String rider, CabType cab, String distance, String duration)
	{
		return (int)(FareCalculator.rideFare(rider, cab, distance, duration)*0.1);
	}
	
}
