package radiotaxi.entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import radiotaxi.db.AccessDB;

/**
 * Application Lifecycle Listener implementation class Initialise
 *
 */
@WebListener
public class Initialise implements ServletContextListener {

    public Initialise() {

    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("DESTROY APP");
    }

    public void contextInitialized(ServletContextEvent arg0)  {
    	System.out.println("Initialising database...");
    	try {
    		AccessDB.initialise();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
