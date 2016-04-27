package in.ac.bits_pilani.radiotaxi.auth;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

/**
 * Calls database initialisation routines on application startup
 */
@WebListener
public class InitialiseApp implements ServletContextListener {

    public InitialiseApp() {

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
